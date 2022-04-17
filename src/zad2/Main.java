/**
 *
 *  @author Śnieżko Eugeniusz S23951
 *
 */

package zad2;


import java.io.IOException;

public class Main {

  public static void main(String[] args) throws IOException {
    CustomersPurchaseSortFind cpsf = new CustomersPurchaseSortFind();
    String fname = System.getProperty("user.home") + "/customers.txt";
    cpsf.readFile(fname);
    cpsf.showSortedBy("Nazwiska");
    cpsf.showSortedBy("Koszty");

    String[] custSearch = { "c00001", "c00002" };

    for (String id : custSearch) {
      cpsf.showPurchaseFor(id);
    }
  }

}
