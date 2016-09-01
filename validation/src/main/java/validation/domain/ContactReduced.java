package validation.domain;

import java.io.Serializable;

public class ContactReduced implements Serializable {
    private String fullName;

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        ContactReduced that = (ContactReduced) o;

        return fullName != null ? fullName.equals(that.fullName) : that.fullName == null;

    }

    @Override
    public int hashCode() {
        return fullName != null ? fullName.hashCode() : 0;
    }

    @Override
    public String toString() {
        return "ContactReduced{" +
                "fullName='" + fullName + '\'' +
                '}';
    }
}
