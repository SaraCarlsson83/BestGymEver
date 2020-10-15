
/**
 * Created by Sara Carlsson
 * Date: 08/10/2020
 * Time:16:59
 * Project: Inlämning 2
 * Copywright: MIT
 */
public class Customer {
    private String name;
    private String socialSecurityNumber;
    private String gymCardDate;

    public Customer(String name, String socialSecurityNumber, String gymCardDate) {
        this.name = name;
        this.socialSecurityNumber = socialSecurityNumber;
        this.gymCardDate = gymCardDate;
    }

    public String getName() {
        return name;
    }

    public String getSocialSecurityNumber() {
        return socialSecurityNumber;
    }

    public String getGymCardDate() {
        return gymCardDate;
    }
}
