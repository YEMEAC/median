package recruitment.median;

import java.io.File;
import java.io.FileFilter;
import org.junit.After;
import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.Ignore;

/**
 *
 * @author Yeison Melo <melo.yeison@gmail.com>
 */
public class StatsTest {

    private final static String TEST1 = "test1.txt";
    private final static String TEST2 = "test2.txt";
    private final static String TEST3 = "test3.txt";
    private final static String TEST4 = "test4.txt";
    private final static String TEST5 = "test5.txt";
    private final static String TEST6 = "test6.txt";
    private final static String TEST7 = "test7.txt";
    private final static String TEST8 = "test8.txt";
    private final static Integer RESULT_TEST1 = 6;
    private final static Integer RESULT_TEST2 = 2;
    private final static Integer RESULT_TEST3 = 1000;
    private final static Integer RESULT_TEST4 = 3;
    private final static Integer RESULT_TEST5 = 2000;
    private final static Integer RESULT_TEST6 = 58;
    private final static Integer RESULT_TEST7 = 507;
    private final static Integer RESULT_TEST8 = 507;

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

    public void test1() throws Exception {
        System.out.println("test1");
        String[] args = {TEST1};

        Integer result = Stats.main(args);
        assertEquals(RESULT_TEST1, result);
    }

    @Test
    public void test2() throws Exception {
        System.out.println("test2");
        String[] args = {TEST2};

        Integer result = Stats.main(args);
        assertEquals(RESULT_TEST2, result);
    }

    @Test
    public void test3() throws Exception {
        System.out.println("test3");
        String[] args = {TEST3};

        Integer result = Stats.main(args);
        assertEquals(RESULT_TEST3, result);
    }

    @Test
    public void test4() throws Exception {
        System.out.println("test4");
        String[] args = {TEST4};

        Integer result = Stats.main(args);
        assertEquals(RESULT_TEST4, result);
    }

    @Test
    public void test5() throws Exception {
        System.out.println("test5");
        String[] args = {TEST5};

        Integer result = Stats.main(args);
        assertEquals(RESULT_TEST5, result);
    }

    @Test
    public void test6() throws Exception {
        System.out.println("test6");
        String[] args = {TEST6};

        Integer result = Stats.main(args);
        assertEquals(RESULT_TEST6, result);
    }

    @Test
    public void test7() throws Exception {
        System.out.println("test7");
        String[] args = {TEST7};

        Integer result = Stats.main(args);
        assertEquals(RESULT_TEST7, result);
    }
    
    @Test
    @Ignore
    public void test8() throws Exception {
        System.out.println("test8");
        String[] args = {TEST8};

        Integer result = Stats.main(args);
        assertEquals(RESULT_TEST8, result);
    }
}
