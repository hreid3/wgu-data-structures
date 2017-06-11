package edu.wgu.hreid6.datastructures.performance;

import static java.lang.System.*;
import static edu.wgu.hreid6.datastructures.performance.HelperFunctions.*;

import edu.wgu.hreid6.datastructures.performance.hashtable.Hashtable;
import edu.wgu.hreid6.datastructures.performance.tree.BinaryTree;

/**
 * HR
 * Main class to test the custom data structures specified in performance assessment.
 *
 * Created by hreid6 on 1/29/17.
 */
public class Main {

    public static void main(String... args) throws Exception {
        runTests(new Hashtable<>(), "Hash Table");
        runTests(new BinaryTree<>(), "Tree");
        out.println("");
    }

    /**
     * HR
     * Run tests based on rubric
     *
     * @param contacts
     * @param msg
     */
    static void runTests(StorableContacts<String, Contact> contacts, String msg) {
        out.println("");
        out.println("=============== Starting " + msg + " tests =============== ");

        insertContact(contacts, "Bob", "Smith", "bsmith@somewhere.com", "555-235-1111");
        insertContact(contacts, "Jane", "Williams", "jw@something.com", "555-235-1112");
        insertContact(contacts, "Mohammed", "al-Salam", "mas@someplace.com", "555-235-1113");
        insertContact(contacts, "Pat", "Jones", "pjones@homesweethome.com", "555-235-1114");
        insertContact(contacts, "Billy", "Kidd", "billy_the_kid@nowhere.com", "555-235-1115");
        insertContact(contacts, "H.", "Houdini", "houdini@noplace.com", "555-235-1116");
        insertContact(contacts, "Jack", "Jones", "jjones@hill.com", "555-235-1117");
        insertContact(contacts, "Jill", "Jones", "jillj@hill.com", "555-235-1118");
        insertContact(contacts, "John", "Doe", "jdoe@somedomain.com", "555-235-1119");
        insertContact(contacts, "Jane", "Doe", "jdoe@somedomain.com", "555-235-1120");

        findContact(contacts, "Pat", "Jones");
        findContact(contacts, "Billy", "Kidd");
        findContact(contacts, "John", "Doe");

        deleteContact(contacts, "John", "Doe");
        try {
            insertContact(contacts, null, null, "Test_Case@testcase.com", "555-235-1121");
        } catch (IllegalArgumentException ex) {
            out.println("No first or last name");
        }
        insertContact(contacts, "Nadezhda", "Kanachekhovskaya", "dr.nadezhda.kanacheckovskaya@somehospital.moscow.ci.ru", "555-235-1122");
        insertContact(contacts, "Jo", "Wu", "wu@h.com", "555-235-1123");
        insertContact(contacts, "Millard", "Fillmore", "millard@theactualwhitehouse.us", "555-235-1124");
        insertContact(contacts, "Bob", "vanDyke", "vandyke@nodomain.com", "555-235-1125");
        insertContact(contacts, "Upside", "Down", "upsidedown@rightsideup.com", "555-235-1126");

        findContact(contacts, "Jack", "Jones");
        findContact(contacts, "Nadezhda", "Kanachekhovskaya");

        deleteContact(contacts, "Jill", "Jones");
        deleteContact(contacts, "John", "Doe");

        findContact(contacts, "Jill", "Jones");
        findContact(contacts, "John", "Doe");

        out.print("Size=" + contacts.size());
        out.println(" - DONE: " + msg);
    }

    static void insertContact(StorableContacts<String, Contact> storableContacts, String firstName, String lastName, String email, String phoneNumber) {
        Contact contact = new Contact(firstName, lastName, email, phoneNumber);
        if (storableContacts.insert(getRubicKey(firstName, lastName), contact)) {
            out.println("Added contact " + contact);
        } else {
            out.println("Not sure why we could not add contact... " + contact);
        }
    }

    static Contact findContact(StorableContacts<String, Contact> storableContacts, String firstName, String lastName) {
        Contact aContact = storableContacts.find(getRubicKey(firstName, lastName));
        if (aContact != null) {
            out.println("Found contact " + aContact);
        } else {
            out.println("Could not find contact " + firstName + " " + lastName);
        }
        return aContact;
    }

    static Contact deleteContact(StorableContacts<String, Contact> contacts, String firstName, String lastName) {
        Contact aContact = contacts.delete(getRubicKey(firstName, lastName));
        if (aContact != null) {
            out.println("Deleted contact " + aContact);
        } else {
            out.println("Could not delete contact " + firstName + " " + lastName);
        }
        return aContact;
    }
}
