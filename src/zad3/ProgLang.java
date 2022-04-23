package zad3;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public class ProgLang {

    private final List<String> fromFile;

    public ProgLang (String path) throws IOException {
        this.fromFile =  Files.readAllLines(Paths.get(path));
    }

    public Map<String, Set<String>> getLangsMap() {
        Map<String, Set<String>> result = new LinkedHashMap<>();
        for(String s : fromFile){
            String[] entry = s.trim().split("\\t");
            String entryKey = entry[0];
            Set<String> values = Arrays.stream(entry)
                    .skip(1).collect(Collectors.toCollection(LinkedHashSet::new));
            result.put(entryKey, values);
        }
        return result;
    }

    public Map<String, Set<String>> getProgsMap(){
        Map<String, Set<String>> result = new LinkedHashMap<>();
        Map<String, Set<String>> mapToMapFrom = getLangsMap();

        List<String> keys = mapToMapFrom.values().stream().flatMap(Collection::stream).distinct().toList();
        for (String s : keys) result.put(s, new LinkedHashSet<>());

        for(String s : result.keySet()){
            for(Map.Entry <String,Set<String>> entry : mapToMapFrom.entrySet()){
                if(entry.getValue().contains(s)) result.get(s).add(entry.getKey());
            }
        }
        return result;
    }

    public Map<String, Set<String>> getLangsMapSortedByNumOfProgs() {
        return this.sorted(getLangsMap(), (e1, e2) -> {
            int diff = e2.getValue().size() - e1.getValue().size();
            if(diff==0) return e1.getKey().compareTo(e2.getKey());
            return diff;
        });}

    public Map<String, Set<String>> getProgsMapSortedByNumOfLangs() {
        return this.sorted(getProgsMap(), (e1, e2) -> {
            int diff = e2.getValue().size() - e1.getValue().size();

            if (diff == 0) return e1.getKey().compareTo(e2.getKey());
            return diff;
        });}

    public Map<String, Set<String>> getProgsMapForNumOfLangsGreaterThan(int i) {
        return this.filtered(getProgsMap(),
                (value) -> value.getValue().size() > i
        );
    }

    private <T,U> Map <T,U> sorted (Map <T,U> argument, Comparator <Map.Entry<T,U>> entryComparator){
        return argument.entrySet().stream()
                .sorted(entryComparator)
                .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue, (oldValue, newValue)-> oldValue, LinkedHashMap::new));
    }

    private <T,U> Map <T,U> filtered (Map <T,U> argument, Predicate <Map.Entry<T,U>> predicate){
        return argument.entrySet()
                .stream()
                .filter(predicate)
                .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue, (oldValue, newValue) -> oldValue, LinkedHashMap::new));
    }
}
