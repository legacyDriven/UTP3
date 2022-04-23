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

    public Anagrams(String path) {
        List<String> input = null;
        try {
            input = Files.readAllLines(Paths.get(path));
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        rawDataFromFile = input.stream()
                .map(n-> n.split(" "))
                .flatMap(Arrays::stream)
                .collect(Collectors.toCollection(ArrayList::new));
    }

    public List<List<String>> getSortedByAnQty() {
        Set<Set<String>> result = new LinkedHashSet<>();
        for (String s : rawDataFromFile){
            final String temp = Anagrams.convertToSortedParameter(s); //making compiler happy (:
            result.add(rawDataFromFile.stream().filter(n-> Anagrams.convertToSortedParameter(n).equals(temp)).collect(Collectors.toCollection(LinkedHashSet::new)));
        }
        List<List<String>> finalresult = new ArrayList<>();
        for(Set<String> s : result){
            finalresult.add(new ArrayList<>(s));
        }
        finalresult.sort(Comparator.comparingInt(List::size));
        Collections.reverse(finalresult);
        return finalresult; }

    private static String convertToSortedParameter(String input){
        String [] entry = input.split("");
        return Arrays.stream(entry).sorted(String::compareTo).collect(Collectors.joining());

    }

    String getAnagramsFor(String word){
        List <String> toProcess = rawDataFromFile.stream().filter(n -> n.length() == word.length()).toList();
        return word + ": " + toProcess.stream()
                .filter(n-> !n.equals(word))
                .filter(n -> Anagrams.convertToSortedParameter(word).equals(Anagrams.convertToSortedParameter(n)))
                .toList();
    }
}
