package recruitment.median;

import java.io.IOException;
import java.io.LineNumberReader;
import java.io.Reader;

/**
 *
 * @author Yeison Melo <melo.yeison@gmail.com>
 */
class LineNumberReaderWrapper extends  LineNumberReader{
    
    private static final int READ_AHEAD_LIMIT = 6;
    
    public LineNumberReaderWrapper(Reader in, int bufferSize) {
        super(in, bufferSize);
    }

 
    /** 
     * Avoid advance to the next line after read
     */
    @Override
    public String readLine() throws IOException{
        this.mark(READ_AHEAD_LIMIT);
        String result = super.readLine();
        this.reset();
        return result;
    }

    void moveLine() throws IOException {
       super.readLine();
    }
}
