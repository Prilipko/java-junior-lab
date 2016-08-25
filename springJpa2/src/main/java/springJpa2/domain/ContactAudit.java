package springJpa2.domain;

import org.hibernate.annotations.Type;
import org.hibernate.envers.Audited;
import org.joda.time.DateTime;
import org.springframework.data.domain.Auditable;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Entity
@Audited
@Table(name = "CONTACT_AUDIT")
public class ContactAudit implements Serializable, Auditable<String, Long> {
    private Long id;
    private String firstName;
    private String lastName;
    private Date birthDate;
    private Long version;
    private String createdBy;
    private DateTime createdDate;
    private String lastModifiedBy;
    private DateTime lastModifiedDate;

//    private Set<ContactTelDetail> contactTelDetails;
//    private Set<Hobby> hobbies;

//    public ContactAudit() {
//        this.contactTelDetails = new HashSet<>();
//    }

    @Id
    @Column(name = "ID")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Long getId() {
        return id;
    }

    @Override
    @Transient
    public boolean isNew() {
        return id == null;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Column(name = "FIRST_NAME")
    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    @Column(name = "LAST_NAME")
    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    @Temporal(TemporalType.DATE)
    @Column(name = "BIRTH_DATE")
    public Date getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(Date birthDate) {
        this.birthDate = birthDate;
    }

    @Version
    @Column(name = "VERSION")
    public Long getVersion() {
        return version;
    }

    public void setVersion(Long version) {
        this.version = version;
    }

//    @OneToMany(mappedBy = "contact",
//            cascade = CascadeType.ALL,
//            orphanRemoval = true)
//    public Set<ContactTelDetail> getContactTelDetails() {
//        return contactTelDetails;
//    }
//
//    public void setContactTelDetails(Set<ContactTelDetail> contactTelDetails) {
//        this.contactTelDetails = contactTelDetails;
//    }
//
//    public void addContactTelDetail(ContactTelDetail contactTelDetail) {
//        contactTelDetail.setContact(this);
//        this.contactTelDetails.add(contactTelDetail);
//    }
//
//    public void removeContactTelDetails(ContactTelDetail contactTelDetails) {
//        this.contactTelDetails.remove(contactTelDetails);
//    }

//    @ManyToMany
//    @JoinTable(name = "CONTACT_HOBBY_DETAIL",
//            joinColumns = @JoinColumn(name = "CONTACT_ID"),
//            inverseJoinColumns = @JoinColumn(name = "HOBBY_ID")
//    )
//    public Set<Hobby> getHobbies() {
//        return hobbies;
//    }
//
//    public void setHobbies(Set<Hobby> hobbies) {
//        this.hobbies = hobbies;
//    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        ContactAudit contact = (ContactAudit) o;

        return !(id != null ? !id.equals(contact.id) : contact.id != null);

    }

    @Override
    public int hashCode() {
        return id != null ? id.hashCode() : 0;
    }


    @Override
    @Column(name = "CREATED_BY")
    public String getCreatedBy() {
        return this.createdBy;
    }

    @Override
    public void setCreatedBy(String createdBy) {
        this.createdBy = createdBy;
    }

    @Override
    @Column(name = "CREATED_DATE")
    @Type(type = "org.jadira.usertype.dateandtime.joda.PersistentDateTime")
    public DateTime getCreatedDate() {
        return this.createdDate;
    }

    @Override
    public void setCreatedDate(DateTime createdDate) {
        this.createdDate = createdDate;
    }

    @Override
    @Column(name = "LAST_MODIFIED_BY")
    public String getLastModifiedBy() {
        return lastModifiedBy;
    }

    @Override
    public void setLastModifiedBy(String lastModifiedBy) {
        this.lastModifiedBy = lastModifiedBy;
    }

    @Override
    @Column(name = "LAST_MODIFIED_DATE")
    @Type(type = "org.jadira.usertype.dateandtime.joda.PersistentDateTime")
    public DateTime getLastModifiedDate() {
        return lastModifiedDate;
    }

    @Override
    public void setLastModifiedDate(DateTime lastModifiedDate) {
        this.lastModifiedDate = lastModifiedDate;
    }

    @Override
    public String toString() {
        return "ContactAudit{" +
                "id=" + id +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", birthDate=" + birthDate +
                ", version=" + version +
                ", createdBy='" + createdBy + '\'' +
                ", createdDate=" + createdDate +
                ", lastModifiedBy='" + lastModifiedBy + '\'' +
                ", lastModifiedDate=" + lastModifiedDate +
                '}';
    }
}
