import org.junit.Test;
import java.nio.file.*;
import java.util.*;
import static org.junit.Assert.*;

/**
 * Created by Sara Carlsson
 * Date: 08/10/2020
 * Time:17:07
 * Project: Inlämning 2
 * Copywright: MIT
 */
public class GymTest {
    Gym testGym = new Gym();
    Path fileIn = Paths.get("Test/test.txt");
    Path fileOut = Paths.get("Test/testOut.txt");

    List<Customer> list = testGym.addCustomerFromFile(fileIn);

    @Test
    public final void addCustomerFromFileTest(){
        String [] name = {"Alhambra Aromes","Bear Belle","Chamade Coriola"};
        String [] socialSN= {"7603021234","8104020123","8512022345"};
        String[] gymCardDate = {"2019-12-01","2018-12-02", "2017-03-12"};
        int counter = 0;

        for (Customer c : list) {
            assertTrue(c.getName().equals(name[counter]));
            assertTrue(c.getSocialSecurityNumber().equals(socialSN[counter]));
            assertTrue(c.getGymCardDate().equals(gymCardDate[counter]));
            assertFalse(c.getGymCardDate().equals("ndljfndkjn"));
            counter++;
        }
    }

    @Test
    public void inputFromUserTest(){
        testGym.test = true;

        String outputCorrect = "Chamade Coriola";
        String outputWrong = "Elmer Ekorrsson";

        assertTrue(testGym.inputFromUser("Chamade Coriola").equalsIgnoreCase(outputCorrect));
        assertFalse(testGym.inputFromUser("Chamade Coriola").equalsIgnoreCase(outputWrong));
    }

    @Test
    public void findCustomerInListTest(){

        String inputCorrect = "Bear Belle";
        String outputCorrect = "gymkort har tyvärr gått ut!";
        String inputCorrect2 = "7603021234";
        String outputCorrect2 = "Gymkortet är aktivt";
        String inputWrong = "Sara Carlsson";
        String outputWrong = "Den personen finns inte i listan!";

        assertTrue(testGym.findCustomerInList(list, inputCorrect, fileOut).endsWith(outputCorrect));
        assertTrue(testGym.findCustomerInList(list, inputCorrect2, fileOut).startsWith(outputCorrect2));
        assertTrue(testGym.findCustomerInList(list, inputWrong, fileOut).equals(outputWrong));
        assertFalse(testGym.findCustomerInList(list, inputCorrect, fileOut).equals(outputWrong));
    }

    @Test
    public void parseGymCardDateTest(){
        Customer c1 = list.get(1);
        String correctDate = "2018-12-02";
        String wrongDate = "2018-12-20";

        assertTrue(testGym.parseGymCardDate(c1).toString().equals(correctDate));
        assertFalse(testGym.parseGymCardDate(c1).toString().equals(wrongDate));
    }

    @Test
    public void isGymCardActiveTest() {

        Customer c1 = list.get(1);//inaktivt kort
        Customer c2 = list.get(0);//aktivt kort
        String oldGymCard = c1.getName() + "s gymkort har tyvärr gått ut!";
        String activeGymCard = "Gymkortet är aktivt och " + c2.getName() + "s träningstillfälle " +
                "är tillagt i filen \"" + fileOut + "\"";

        assertTrue(testGym.isGymCardActive(c1, fileOut).equals(oldGymCard));
        assertTrue(testGym.isGymCardActive(c2, fileOut).equals(activeGymCard));
        assertFalse(testGym.isGymCardActive(c2, fileOut).equals(oldGymCard));
    }
}
