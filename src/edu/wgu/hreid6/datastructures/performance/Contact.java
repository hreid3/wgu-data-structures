package edu.wgu.hreid6.datastructures.performance;

/**
 * HR
 *
 * Represents a Contact based on the requirements set by Performance Assessment instructions.
 *
 * Created by hreid6 on 1/29/17.
 */
public class Contact {

    private String firstName;
    private String lastName;
    private String email;
    private String phoneNumber;

    public Contact(String firstName, String lastName, String email, String phoneNumber) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.phoneNumber = phoneNumber;
    }

    public Contact(String firstName, String lastName) {
        this(firstName, lastName, null, null);
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    @Override
    public String toString() {
        return new StringBuilder()
                .append(this.getFirstName()).append(" ")
                .append(this.getLastName()).append(" : ")
                .append(this.getPhoneNumber()).append(" : ")
                .append(this.getEmail()).append("")
                .toString()
                ;
    }
}
