package propertyEditors;

public class UkrainianName {
    final private String familyName;
    final private String firstName;
    final private String fathersName;

    public UkrainianName(String familyName, String firstName, String fathersName) {
        this.familyName = familyName;
        this.firstName = firstName;
        this.fathersName = fathersName;
    }

    public String getFamilyName() {
        return familyName;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getFathersName() {
        return fathersName;
    }

    @Override
    public String toString() {
        return "Prizvysche: " + familyName +
                " Imya: " + firstName +
                " Pobatkovi: " + fathersName ;
    }
}
