/**
 *
 *  @author Śnieżko Eugeniusz S23951
 *
 */

package zad2;


import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

class Purchase{

    final String customerId;

    final String customerSurname;

    final String customerName;

    final String item;

    final BigDecimal itemPrice;

    final BigDecimal amount;

    final BigDecimal totalAmount;

    public Purchase(String input) {
        List<String> toParse = Purchase.parsePurchase(input);
        this.customerId = toParse.get(0);
        this.customerSurname = toParse.get(1).substring(0,toParse.get(1).lastIndexOf(" "));
        this.customerName = toParse.get(1).substring(toParse.get(1).lastIndexOf(" "));
        this.item = toParse.get(2);
        this.itemPrice = new BigDecimal(toParse.get(3));
        this.amount = new BigDecimal(toParse.get(4));
        this.totalAmount = itemPrice.multiply(amount).setScale(1, RoundingMode.HALF_UP);
    }

    private static ArrayList<String> parsePurchase(String input){
        return  Arrays.stream(input.split(";")).collect(Collectors.toCollection(ArrayList::new));
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(customerId)
                .append(";")
                .append(customerSurname)
                .append(" ")
                .append(customerName)
                .append(";")
                .append(item)
                .append(";")
                .append(itemPrice)
                .append(";")
                .append(amount);
        return sb.toString();
    }

    public BigDecimal getTotalAmount() {
        return totalAmount;
    }

    public String getCustomerSurname() {
        return customerSurname;
    }

    public String getCustomerId() {
        return customerId;
    }
}
