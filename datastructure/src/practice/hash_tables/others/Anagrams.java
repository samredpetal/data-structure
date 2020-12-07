package practice.hash_tables.others;

import java.io.File;
import java.util.*;

public class Anagrams {
    public static void main(String[] args) {
//        String anagrams[] = new Anagrams().getAnagrams("abd");
        String anagrams[] = new Anagrams().getAnagramsFromFile("words.txt", "abd");
        for (String anagram : anagrams) {
            System.out.println(anagram);
        }

    }

    public String[] getAnagrams(String string) {
        char letters[] = string.toCharArray();
        String anagrams[] = new String[string.length() * (string.length() - 1)];
        int c = 0;
        for(int i = 0; i < letters.length; i++) {
            for(int j = 1; j < letters.length; j++){
                anagrams[c] = String.copyValueOf(letters);
                char temp = letters[j];
                letters[j] = letters[j - 1];
                letters[j - 1] = temp;
                c++;
            }
        }
        return anagrams;
    }

    public String[] getAnagramsFromFile(String fileName, String string) {
        String result[];
        HashMap<String, List<String>> anagrams = new HashMap<>();
        string = sortLetters(string);
        fileName = "src/practice.hash_tables/others/" + fileName;
        try {
            Scanner scanner = new Scanner(new File(fileName));
            while (scanner.hasNext()) {
                String word = scanner.next().toLowerCase();
                String key = sortLetters(word);
                if (!anagrams.containsKey(key)) {
                    anagrams.put(key, new LinkedList<>());
                }
                if (string.equals(sortLetters(word))) {
                    anagrams.get(key).add(word);
                }
            }
        } catch (Exception e) {
            System.out.println(fileName + " не найден в этой папке");
            System.exit(1);
        }
        result = new String[anagrams.get(string).size()];
        int i = 0;
        for (String anagram : anagrams.get(string)) {
            result[i++] = anagram;
        }
        return result;
    }

    public static String sortLetters(String string) {
        char[] array = string.toCharArray();
        Arrays.sort(array);
        return String.valueOf(array);
    }


}
