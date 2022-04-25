/**
 *
 *  @author Śnieżko Eugeniusz S23951
 *
 */

package zad1;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;
import java.util.stream.Collectors;

public class Anagrams {

    private final List<String> rawDataFromFile;

    public Anagrams(String path) throws IOException{
        List<String> input = Files.readAllLines(Paths.get(path));
            rawDataFromFile = input.stream()
                    .map(n-> n.split(" "))
                    .flatMap(Arrays::stream)
                    .collect(Collectors.toCollection(ArrayList::new));
    }

    public List<List<String>> getSortedByAnQty() {
        Set<Set<String>> result = new LinkedHashSet<>();
        for (String s : rawDataFromFile) {
            String temp = Anagrams.convertToSortedParameter(s);
            result.add(rawDataFromFile.stream().filter(n -> Anagrams.convertToSortedParameter(n).equals(temp)).collect(Collectors.toCollection(LinkedHashSet::new)));
        }
        List<List<String>> finalresult = new ArrayList<>();
        for (Set<String> s : result) {
            finalresult.add(new ArrayList<>(s)); }
        return finalresult.stream().sorted(Comparator.comparingInt(List::size)).collect(Collectors.collectingAndThen(Collectors.toCollection(ArrayList::new), list -> {
            Collections.reverse(list);
            return list; }));
    }

    private static String convertToSortedParameter(String input){
        String [] entry = input.split("");
        return Arrays.stream(entry).sorted(String::compareTo).collect(Collectors.joining());
    }

    String getAnagramsFor(String word){
        return word + ": " + rawDataFromFile.stream()
                .filter(n -> n.length() == word.length())
                .filter(n-> !n.equals(word))
                .filter(n -> Anagrams.convertToSortedParameter(word).equals(Anagrams.convertToSortedParameter(n)))
                .collect(Collectors.toList());
    }
}
