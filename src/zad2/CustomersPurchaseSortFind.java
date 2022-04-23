/**
 *
 *  @author Śnieżko Eugeniusz S23951
 *
 */

package zad2;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

class CustomersPurchaseSortFind {

    private final List<Purchase> data;

    public void readFile(String path) throws IOException {
        List<String> fromFile = Files.readAllLines(Paths.get(path));
        for(String purchase : fromFile){
            data.add(new Purchase(purchase));
        }
    }

    public CustomersPurchaseSortFind() {
        this.data = new ArrayList<>();
    }

    public void showSortedBy(String param) {
        if(param.equals("Nazwiska")) printSortedBySurname();
        else if (param.equals("Koszty")) printSortedByTotalAmount();
        else printDefaultResponse();
    }

    private void printSortedBySurname(){
        System.out.println("Nazwiska");

        data.stream()
                .sorted(Comparator.comparing(Purchase::getCustomerSurname).thenComparing(Purchase::getCustomerId))
                .forEach(System.out::println);

        System.out.println();
    }

    private void printSortedByTotalAmount(){
        System.out.println("Koszty");

        data.stream()
                .sorted(Comparator.comparing(Purchase::getTotalAmount).reversed())
                .map(n -> n + " (Koszt: " + n.totalAmount + ")")
                .forEach(System.out::println);

        System.out.println();
    }

    private void printDefaultResponse(){
        System.out.println("Wrong parameter");
    }

    public void showPurchaseFor(String customerId) {

        if (checkIfCustomerIdExists(customerId)) {
            System.out.println("Klient " + customerId);

            data.stream()
                    .filter(n -> n.customerId.equals(customerId))
                    .forEach(System.out::println);
        }
        System.out.println();
    }

    private boolean checkIfCustomerIdExists(String customerId){
        return data.stream().anyMatch(n -> n.customerId.equals(customerId));
    }
}
