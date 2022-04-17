/**
 *
 *  @author Śnieżko Eugeniusz S23951
 *
 */

package zad1;


import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class Anagrams {

    private List<String> data;

    public Anagrams(String path) {
        List<String> input = null;
        try {
            input = Files.readAllLines(Paths.get(path));
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        this.data = new ArrayList<>(input);
    }

    public List<String> getSortedByAnQty() {
        return new ArrayList<>();
    }

    String getAnagramsFor(String word){
        return "";
    }

    @Override
    public String toString() {
        return "Anagrams{" +
                "data=" + data +
                '}';
    }
}
