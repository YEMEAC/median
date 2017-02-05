package recruitment.median.two;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Scanner;
import java.util.TreeMap;

/**
 *
 * @author Yeison Melo <melo.yeison@gmail.com>
 0. Pre: file with elements between 0..N
 1. Map of appearances ordered by key V[x]=Y means that number X appeared Y times
 */
public class StatsTwo {

    ///RANGE on input elements [0..2000]
    private static final int RANGE_MAX = 2001;

    public static Integer main(String[] args) throws FileNotFoundException, IOException {

        String txt = args[0];
        FileReader file = new FileReader(txt);
        Scanner src = new Scanner(file).useDelimiter(", ");

        //treeMap keep order by key
        Map<Integer, Integer> appearances = new TreeMap();
        int numElems = 0;

        while (src.hasNext()) {
            if (src.hasNextInt()) {
                int key = src.nextInt();
                int newValue = appearances.get(key) != null ? appearances.get(key) + 1 : 1;
                appearances.put(key, newValue);
                ++numElems;
            } else {
                src.next();
            }
        }

        if (numElems == 2) {
            return StatsTwoUtils.getMedianOfTwo(numElems, appearances);
        }
        if (numElems % 2 == 0) {
            return StatsTwoUtils.getMedianEven(numElems, appearances);
        } else {
            return StatsTwoUtils.getMedianOdd(numElems, appearances);
        }
    }
}
