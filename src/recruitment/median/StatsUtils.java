package recruitment.median;

import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Reader;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Yeison Melo <melo.yeison@gmail.com>
 */
public class StatsUtils {

    private static final String LINE_SEPARATOR = "line.separator";
    public static final String TXT = ".txt";
    public static final String CHUNK = "chunk";
    public static final String FINAL_CHUNK = "finalChunk";
    private static final int BUFFER_SIZE = 4; //elems [0-1000] 4 bytes max

    /**
     *
     * @param chunks
     * @return true if any of the files still has line to write
     * @throws IOException
     */
    private static boolean chunksWithContent(List<LineNumberReaderWrapper> chunks) throws IOException {
        for (int i = 0; i < chunks.size(); ++i) {
            if (chunks.get(i).readLine() != null) {
                return true;
            }
        }
        return false;
    }

    /**
     * Writes a txt file with the content of the chunk
     *
     * @param chunk list of integers already sorted
     * @param chunkId chunk's id with id chunkId
     * @throws java.io.IOException
     */
    public static void writeChunkFile(List<Integer> chunk, int chunkId) throws IOException {
        FileWriter fw = new FileWriter(CHUNK + chunkId + TXT);
        BufferedWriter bw = new BufferedWriter(fw);
        bw.write(chunk.toString().replace("[", "").replace("]", "").replace(", ", System.getProperty(LINE_SEPARATOR)) + "");
        bw.close();
    }

    /**
     * Writes the elem in the final sorted file
     *
     * @param elem
     * @throws IOException
     */
    public static void writeElementFinalFile(Integer elem) throws IOException {
        FileWriter fw = new FileWriter(FINAL_CHUNK + TXT, true);
        BufferedWriter bw = new BufferedWriter(fw);
        bw.write(elem.toString() + System.getProperty(LINE_SEPARATOR));
        bw.close();
    }

    /**
     *
     * @param numElements number of elements in the final sorted file
     * @return median of the final sorted file
     * @throws FileNotFoundException
     * @throws IOException
     */
    private static int getMedianOdd(int numElements) throws FileNotFoundException, IOException {
        int medianPosition = numElements / 2;
        Reader reader = new FileReader(FINAL_CHUNK + TXT);

        LineNumberReaderWrapper file = new LineNumberReaderWrapper(reader, BUFFER_SIZE);
        while (file.getLineNumber() < medianPosition) {
            file.moveLine();
        }

        Integer result = Integer.parseInt(file.readLine());
        reader.close();
        return result;
    }

    /**
     *
     * @param numElements number of elements in the final sorted file
     * @return median of the final sorted file
     * @throws FileNotFoundException
     * @throws IOException
     */
    private static int getMedianEven(int numElements) throws FileNotFoundException, IOException {

        int medianPosition = numElements / 2;

        Integer result;
        Reader reader = new FileReader(FINAL_CHUNK + TXT);
        LineNumberReaderWrapper file = new LineNumberReaderWrapper(reader, BUFFER_SIZE);
        while (file.getLineNumber() < (medianPosition - 1)) {
            file.moveLine();
        }

        result = Integer.parseInt(file.readLine());
        result += Integer.parseInt(file.moveLine());

        reader.close();
        return result/2;
    }

    /**
     * Merge the diferent sorted chunks in one sorted file
     *
     * @param numberOfChunks
     * @return median of the final sorted file
     * @throws FileNotFoundException
     * @throws IOException
     */
    public static Integer mergeChunks(int numberOfChunks) throws FileNotFoundException, IOException {
        List<LineNumberReaderWrapper> chunks = new ArrayList();
        List<Reader> reader = new ArrayList();
        for (int i = 0; i < numberOfChunks; ++i) {
            reader.add(new FileReader(CHUNK + i + TXT));
            chunks.add(new LineNumberReaderWrapper(reader.get(i), BUFFER_SIZE));  //buffer size 4 bytes
        }

        int numElems = 0;
        while (chunksWithContent(chunks)) {
            int idChunkChoosen = Integer.MIN_VALUE;
            int minimum = Integer.MAX_VALUE;
            int temp;
            String str;
            for (int i = 0; i < chunks.size(); ++i) {
                if ((str = chunks.get(i).readLine()) != null) {
                    temp = Integer.parseInt(str);
                    chunks.get(i).getLineNumber();
                    if (minimum > temp) {
                        minimum = temp;
                        idChunkChoosen = i;
                    }
                }
            }
            ++numElems;
            writeElementFinalFile(minimum);
            chunks.get(idChunkChoosen).moveLine();
        }

        for (int i = 0; i < numberOfChunks; ++i) {
            reader.get(i).close();
        }

        if (numElems % 2 == 0) {
            return getMedianEven(numElems);
        } else {
            return getMedianOdd(numElems);
        }
    }
}
