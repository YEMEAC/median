/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package recruitment.median;

import java.io.File;
import java.io.FileFilter;
import java.io.FilenameFilter;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Yeison Melo <melo.yeison@gmail.com>
 */
public class StatsTest {

    private final static String TEST1 = "99.txt";
    private final static String TEST2 = "999.txt";
    private final static String TEST3 = "20000.txt";
    private final static Integer RESULT_TEST1 = 58;
    private final static Integer RESULT_TEST2 = 507;
     private final static Integer RESULT_TEST3 = 507;

    public StatsTest() {
    }

    @After
    /**
     * After execute a test the files generated have to be deleted
     * otherwise this could provoke a fail on the next test, since
     * the final merged file is created or appened to the existing one
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
     * @throws java.lang.Exception
     */
    @Test

    public void testSmall() throws Exception {
        System.out.println("testSmall");
        String[] args = {TEST1};

        Integer result = Stats.main(args);
        assertEquals(result, RESULT_TEST1);
    }

    /**
     * Test of main method, of class Stats.
     * @throws java.lang.Exception
     */
    @Test
    
    public void testBigger() throws Exception {
        System.out.println("testBigger");
        String[] args = {TEST2};

        Integer result = Stats.main(args);
        assertEquals(result, RESULT_TEST2);
    }
    
     /**
     * Test of main method, of class Stats.
     * @throws java.lang.Exception
     */
    @Test
    
    public void testLarge() throws Exception {
        System.out.println("testLarge");
        String[] args = {TEST3};

        Integer result = Stats.main(args);
        assertEquals(result, RESULT_TEST3);
    }

}
