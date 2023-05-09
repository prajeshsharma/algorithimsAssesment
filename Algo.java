import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Algo {
    String[][] getSearchResults(String[] words, String[] queries) {
        String[][] anagramArrays = new String[queries.length][];
        for (int i = 0; i < queries.length; i++) {
            String query = queries[i];
            HashMap<String, List<String>> anagramMap = new HashMap<>();

            for (String word : words) {
                if (word.length() != query.length()) continue;
                char[] wordChars = word.toCharArray();
                Arrays.sort(wordChars);
                String sortedWord = new String(wordChars);

                if (anagramMap.containsKey(sortedWord))
                    anagramMap.get(sortedWord).add(word);
                else {
                    ArrayList<String> newList = new ArrayList<>();
                    newList.add(word);
                    anagramMap.put(sortedWord, newList);
                }
            }

            List<String> anagramsList = anagramMap.getOrDefault(sortString(query), new ArrayList<>());
            anagramArrays[i] = anagramsList.toArray(new String[0]);
        }

        return anagramArrays;
    }

    String sortString(String s) {
        char[] sChars = s.toCharArray();
        Arrays.sort(sChars);
        return new String(sChars);
    }

    int pollution(int[] A) {
        double sum = 0;
        Double[] newArray = new Double[A.length];
        for (int i = 0; i < A.length; i++) newArray[i] = (double) A[i];
        for (Double d : newArray) sum += d;
        int filters = 0;
        double pollution = sum;
        if (newArray.length == 1) {
            while (pollution > sum / 2) {
                newArray[0] /= 2;
                pollution -= newArray[0];
                filters++;
            }
        } else {
            Arrays.sort(newArray, (o1, o2) -> Double.compare(o2, o1));
            while (pollution > sum / 2) {
                newArray[0] /= 2;
                pollution -= newArray[0];
                filters++;
                if (newArray[0] < A[1]) Arrays.sort(newArray, (o1, o2) -> Double.compare(o2, o1));
            }
        }
        return filters;
    }

    void maxRowMinCol(int[][] M) {
        for (int i = 0; i < M.length; i++)
            for (int j = 0; j < M[0].length; j++) {
                int maxRow = M[i][j];
                for (int k = 0; k < M[0].length; k++) {
                    if (M[i][k] > maxRow) break;
                    for (int[] ints : M)
                        if (ints[i] < maxRow) {
                            System.out.println(maxRow);
                            return;
                        }
                }
            }
        System.out.println(-1);
    }

    int minStart(int[] arr) {
        int firstPositiveIndex = -1;
        for (int i = 0; i < arr.length; i++)
            if (arr[i] < 0) {
                firstPositiveIndex = i;
                break;
            }
        if (firstPositiveIndex == -1) return 1;
        if (firstPositiveIndex == 0) return 1 - arr[firstPositiveIndex];
        return 1 - arr[firstPositiveIndex] - arr[firstPositiveIndex - 1];
    }

    int substrings(String s) {
        Map<Character, Integer> lastSeenIndex = new HashMap<>();
        int left = 0, count = 0;
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            if (lastSeenIndex.containsKey(c) && lastSeenIndex.get(c) >= left) {
                count++;
                left = i;
            }
            lastSeenIndex.put(c, i);
        }
        return count + 1;
    }


}
