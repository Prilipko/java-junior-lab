package contactService.domain;

import javax.annotation.Generated;
import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * @author worker
 * @created 11.09.16.
 */
@Entity
public class Contact implements Serializable {
    private Long id;
    private Long version;
    private String name;

    private LocalDateTime birthDate;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Version
    public Long getVersion() {
        return version;
    }

    public void setVersion(Long version) {
        this.version = version;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public LocalDateTime getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(LocalDateTime birthDate) {
        this.birthDate = birthDate;
    }

    @Override
    public String toString() {
        return "Contact{" +
                "id=" + id +
                ", version=" + version +
                ", name='" + name + '\'' +
                ", birthDate=" + birthDate +
                '}';
    }
}
