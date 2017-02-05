package recruitment.median.two;

import java.util.Map;

/**
 *
 * @author Yeison Melo <melo.yeison@gmail.com>
 * 0. Pre: appearances is a ordered map by key
 * 1. numElemns = number elemens in the original file. Sum(appearances.values)=numElemens
 * 2. Since the map is ordere by key, is like having the list of inputs already ordered but
 * whithout repeated numbers. So we just need to iterate over the map moving as many positions
 * as repetitions of a numbers we found (entry.value)
 */
public class StatsTwoUtils {

    public static Integer getMedianOfTwo(int numElems, Map<Integer, Integer> appearances) {
        int sum = 0;
        for (Map.Entry<Integer, Integer> entry : appearances.entrySet()) {
            sum += entry.getKey();
        }
        return sum / 2;
    }

    public static Integer getMedianEven(int numElems, Map<Integer, Integer> appearances) {
        Integer medianPosion = (numElems / 2) + 1;
        int tempPrevius = 0;
        for (Map.Entry<Integer, Integer> entry : appearances.entrySet()) {
            int numRepetitionKey = entry.getValue();
            for (int i = 0; i < numRepetitionKey; ++i) {
                --medianPosion;
                if (medianPosion == 0) {
                    return (tempPrevius + entry.getKey()) / 2;
                }
                tempPrevius = entry.getKey();
            }
        }
        return Integer.MIN_VALUE;
    }

    public static Integer getMedianOdd(int numElems, Map<Integer, Integer> appearances) {
        Integer medianPosion = (numElems / 2) + 1;

        for (Map.Entry<Integer, Integer> entry : appearances.entrySet()) {
            medianPosion -= entry.getValue();
            if (medianPosion <= 0) {
                return entry.getKey();
            }
        }
        return Integer.MIN_VALUE;
    }
}
