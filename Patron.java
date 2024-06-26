package librarymanagement;

import java.io.Serializable;

public class Patron implements Serializable {
    private static final long serialVersionUID = 1L;
    private int patronID;
    private String name;
    private String address;
    private String phoneNumber;
    private static int nextID = 1;

    public Patron(String name, String address, String phoneNumber) {
        this.patronID = nextID++;
        this.name = name;
        this.address = address;
        this.phoneNumber = phoneNumber;
    }

    public int getPatronID() {
        return patronID;
    }

    public String getName() {
        return name;
    }

    public String getAddress() {
        return address;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    @Override
    public String toString() {
        return "Patron{" +
                "patronID=" + patronID +
                ", name='" + name + '\'' +
                ", address='" + address + '\'' +
                ", phoneNumber='" + phoneNumber + '\'' +
                '}';
    }
}

