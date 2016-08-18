package springJpa2.domain;

import java.io.Serializable;

public class ContactSummary implements Serializable {
    String firstName;
    String lastName;
    String telNumber;

    public ContactSummary(String firstName, String lastName, String telNumber) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.telNumber = telNumber;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getTelNumber() {
        return telNumber;
    }

    @Override
    public String toString() {
        return "ContactSummary{" +
                "firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", telNumber='" + telNumber + '\'' +
                '}';
    }
}
