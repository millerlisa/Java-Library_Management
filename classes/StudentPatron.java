package librarymanagement;

public class StudentPatron extends Patron {
    private static final long serialVersionUID = 1L;
    private String studentID;
    private String major;

    public StudentPatron(String name, String address, String phoneNumber, String studentID, String major) {
        super(name, address, phoneNumber);
        setStudentID(studentID);
        setMajor(major);
    }

    public String getStudentID() {
        return studentID;
    }

    public void setStudentID(String studentID) {
        if (studentID == null || studentID.trim().isEmpty()) {
            throw new IllegalArgumentException("Student ID cannot be null or empty.");
        }
        this.studentID = studentID;
    }

    public String getMajor() {
        return major;
    }

    public void setMajor(String major) {
        if (major == null || major.trim().isEmpty()) {
            throw new IllegalArgumentException("Major cannot be null or empty.");
        }
        this.major = major;
    }

    @Override
    public String toString() {
        return "StudentPatron{" +
                "patronID=" + getPatronID() +
                ", name='" + getName() + '\'' +
                ", address='" + getAddress() + '\'' +
                ", phoneNumber='" + getPhoneNumber() + '\'' +
                ", studentID='" + studentID + '\'' +
                ", major='" + major + '\'' +
                '}';
    }
}

