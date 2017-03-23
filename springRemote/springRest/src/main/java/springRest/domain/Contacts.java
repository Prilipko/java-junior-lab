package springRest.domain;

import contactService.domain.Contact;

import java.util.List;

/**
 * Created by Alexander Prilipko on 23.03.2017.
 */
public class Contacts {
    List<Contact> contacts;

    public Contacts(List<Contact> contacts) {
        this.contacts = contacts;
    }

    public Contacts() {
    }

    public List<Contact> getContacts() {
        return contacts;
    }

    public void setContacts(List<Contact> contacts) {
        this.contacts = contacts;
    }
}
