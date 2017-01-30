package recruitment.median;

import java.io.File;
import java.io.FileFilter;
import org.junit.After;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Yeison Melo <melo.yeison@gmail.com>
 */
public class StatsTest {

    private final static String TEST0 = "7.txt";
    private final static String TEST1 = "100.txt";
    private final static String TEST2 = "1000.txt";
    private final static String TEST3 = "20000.txt";
    private final static Integer RESULT_TEST0 = 2;
    private final static Integer RESULT_TEST1 = 58;
    private final static Integer RESULT_TEST2 = 507;
    private final static Integer RESULT_TEST3 = 507;

    public StatsTest() {
    }

    @After
    /**
     * After execute a test the files generated have to be deleted otherwise
     * this could provoke a fail on the next test, since the final merged file
     * is created or appened to the existing one
     */
    public void tearDown() {

        //delete chunk files
        final File folder = new File(".");
        File[] files = folder.listFiles(new FileFilter() {
            public boolean accept(File pathname) {
                return (pathname.getName().startsWith(StatsUtils.CHUNK) && pathname.getName().endsWith(StatsUtils.TXT));
            }
        });

        for (File file : files) {
            if (!file.delete()) {
                System.err.println("Can't remove " + file.getAbsolutePath());
            }
        }

        //delete final file merged
        File f = new File(StatsUtils.FINAL_CHUNK + StatsUtils.TXT);
        f.delete();
    }

    /**
     * Test of main method, of class Stats.
     *
     * @throws java.lang.Exception
     */
    @Test

    public void testZero() throws Exception {
        System.out.println("test0");
        String[] args = {TEST0};

        Integer result = Stats.main(args);
        assertEquals(RESULT_TEST0, result);
    }

    /**
     * Test of main method, of class Stats.
     *
     * @throws java.lang.Exception
     */
    @Test

    public void testSmall() throws Exception {
        System.out.println("testSmall");
        String[] args = {TEST1};

        Integer result = Stats.main(args);
        assertEquals(RESULT_TEST1, result);
    }

    /**
     * Test of main method, of class Stats.
     *
     * @throws java.lang.Exception
     */
    @Test

    public void testBigger() throws Exception {
        System.out.println("testBigger");
        String[] args = {TEST2};

        Integer result = Stats.main(args);
        assertEquals(RESULT_TEST2, result);
    }

    /**
     * Test of main method, of class Stats.
     *
     * @throws java.lang.Exception
     */
    @Test

    public void testLarge() throws Exception {
        /*System.out.println("testBigger");
        String[] args = {TEST3};

        Integer result = Stats.main(args);
        assertEquals(RESULT_TEST3, result);*/
    }

}
