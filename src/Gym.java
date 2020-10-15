
import javax.swing.JOptionPane;
import java.io.*;
import java.nio.file.*;
import java.time.*;
import java.util.*;

/**
 * Created by Sara Carlsson
 * Date: 08/10/2020
 * Time:17:07
 * Project: Inlämning 2
 * Copywright: MIT
 */
public class Gym {
    public boolean test = false;

    public List <Customer> addCustomerFromFile(Path fileName){
        List<Customer> customerList = new ArrayList<>();
        try (Scanner scan = new Scanner(fileName);){
            scan.useDelimiter("(,|\\s)+");
            while(scan.hasNext()) {
                String socialSecurityNumber = scan.findInLine("[0-9]{10}");
                String firstName = scan.next();
                String lastName = scan.next();
                scan.nextLine();
                String gymCardDate = scan.findInLine("[0-9]{4}-[0-9]{2}-[0-9]{2}");
                if (scan.hasNextLine())
                    scan.nextLine();

                String name = firstName + " " + lastName;

                customerList.add(new Customer(name, socialSecurityNumber, gymCardDate));
            }
        }
        catch(IOException e){
            System.out.println("Något gick fel vid inläsningen från filen.");
            e.printStackTrace();
            System.exit(0);
        }
        catch (Exception e){
            System.out.println("Något gick fel, programmet är avslutat." );
            e.printStackTrace();
            System.exit(0);
        }
        return customerList;
    }


    public String inputFromUser(String optionalTestParameter){
        String input = "";
        if(test){
            input = optionalTestParameter;
        }
        else {
            input = JOptionPane.showInputDialog
                    ("Skriv in namn eller personnummer (10 siffror)\npå den personen du söker");
            }
        if (input == null) {
            JOptionPane.showMessageDialog(null, "Du stängde ner programmet!");
            System.exit(0);
        }
        return input;
    }

    public String findCustomerInList(List<Customer> searchForCustomerList, String input, Path fileOut) {
            String text = "Den personen har inget medlemsskap i det här gymmet!";
            for (Customer c : searchForCustomerList) {
                if (c.getName().equalsIgnoreCase(input.trim()) ||
                        c.getSocialSecurityNumber().equals(input.trim())) {
                    text = isGymCardActive(c,fileOut);
                    break;
                }
            }
            return text;
    }

    public LocalDate parseGymCardDate(Customer c){
        LocalDate dateGymCard = LocalDate.parse(c.getGymCardDate());
        return dateGymCard;
    }

    public String isGymCardActive (Customer c, Path fileOut){
        LocalDate dateGymCard = parseGymCardDate(c);
        LocalDate dateToday = LocalDate.now();

        Period p = Period.between(dateGymCard, dateToday);

        if(p.getYears()==0 || (p.getYears()==1 && p.getMonths()==0 && p.getDays()==0)){
            addActiveCustomerInFile(c,fileOut);
            return "Gymkortet är aktivt och " + c.getName() +
                    "s \nträningstillfälle är tillagt i filen \"" + fileOut + "\"";
        }
        else
            return c.getName() + "s gymkort har tyvärr gått ut!";
    }

    public void addActiveCustomerInFile(Customer c, Path fileOut){
        LocalDate dateToday = LocalDate.now();

        try (BufferedWriter writer = new BufferedWriter(new FileWriter(String.valueOf(fileOut), true))){
            if (!Files.exists(fileOut)) {
                Files.createFile(fileOut);
            }
            writer.write("Datum: " + dateToday +
                    "\nNamn: " + c.getName()+
                    "\nPersonnummer: " + c.getSocialSecurityNumber() + "\n");
            writer.newLine();
        }
        catch(IOException e){
            System.out.println("Fel vid läsning till fil!");
            e.printStackTrace();
            System.exit(0);
        }
        catch (Exception e){
            System.out.println("Något gick fel, programmet är avslutat." );
            e.printStackTrace();
            System.exit(0);
        }
    }

    public void printCustomer(String text){
        JOptionPane.showMessageDialog(null, text);
    }
}
