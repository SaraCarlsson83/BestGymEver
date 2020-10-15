
import java.nio.file.*;
import java.util.*;
import java.lang.*;

/**
 * Created by Sara Carlsson
 * Date: 10/10/2020
 * Time:20:42
 * Project: Inlämning 2
 * Copywright: MIT
 */
public class Demo {
    public Demo() {
        Gym bestGymEver = new Gym();
        List<Customer> customerList;
        Path fileIn = Paths.get("src/customers.txt");
        Path fileOut = Paths.get("src/gymActivities.txt");

        customerList = bestGymEver.addCustomerFromFile(fileIn);

        while (true) {

            String input = bestGymEver.inputFromUser("");

            String isCustomerInList = bestGymEver.findCustomerInList(customerList, input, fileOut);

            bestGymEver.printCustomer(isCustomerInList);
        }
    }

    public static void main(String[] args) {
        Demo demo = new Demo();
    }
}
