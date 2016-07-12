package springHibernate.domain;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "HOBBY")
public class Hobby {

    private String hobbyId;
    private Set<Contact> contacts;


    @Id
    @Column(name = "HOBBY_ID")
    public String getHobbyId() {
        return hobbyId;
    }

    public void setHobbyId(String hobbyId) {
        this.hobbyId = hobbyId;
    }

    @ManyToMany
    @JoinTable(name = "CONTACT_HOBBY_DETAIL",
    joinColumns = @JoinColumn(name = "HOBBY_ID"),
    inverseJoinColumns = @JoinColumn(name = "CONTACT_ID"))
    public Set<Contact> getContacts() {
        return contacts;
    }

    public void setContacts(Set<Contact> contacts) {
        this.contacts = contacts;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Hobby hobby = (Hobby) o;

        return hobbyId != null ? hobbyId.equals(hobby.hobbyId) : hobby.hobbyId == null;

    }

    @Override
    public int hashCode() {
        return hobbyId != null ? hobbyId.hashCode() : 0;
    }

    @Override
    public String toString() {
        return "Hobby{" +
                "hobbyId='" + hobbyId + '\'' +
                ", contacts=" + contacts +
                '}';
    }
}
