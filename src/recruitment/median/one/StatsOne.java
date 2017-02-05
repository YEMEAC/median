package recruitment.median.one;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 *
 * @author Yeison Melo <melo.yeison@gmail.com>
 */
public class StatsOne {

    //Number of elements in memmory
    private static final int CHUNK_SIZE = 50;

    public static Integer main(String[] args) throws FileNotFoundException, IOException {

        String txt = args[0];
        int chunkId = 0;
        FileReader file = new FileReader(txt);
        Scanner src = new Scanner(file).useDelimiter(", ");
        List<Integer> chunk = new ArrayList();

        while (src.hasNext()) {
            if (src.hasNextInt()) {
                chunk.add(src.nextInt());
                if (chunk.size() == CHUNK_SIZE || !src.hasNext()) {
                    QuickSort.quickSortRecursive(chunk, 0, chunk.size() - 1);
                    StatsOneUtils.writeChunkFile(chunk, chunkId);
                    chunkId++;
                    chunk.clear();
                }
            } else {
                src.next();
            }
        }
        file.close();
        return StatsOneUtils.mergeChunks(chunkId);
    }
}
