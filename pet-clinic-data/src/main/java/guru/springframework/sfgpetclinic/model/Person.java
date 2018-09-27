package guru.springframework.sfgpetclinic.model;

public class Person extends BaseEntity {
    private String firstName;
    private String lasString;

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLasString() {
        return lasString;
    }

    public void setLasString(String lasString) {
        this.lasString = lasString;
    }
}
