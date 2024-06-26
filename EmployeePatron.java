package librarymanagement;


public class EmployeePatron extends Patron {
    private static final long serialVersionUID = 1L;
    private String employeeID;
    private String department;

    public EmployeePatron(String name, String address, String phoneNumber, String employeeID, String department) {
        super(name, address, phoneNumber);
        setEmployeeID(employeeID);
        setDepartment(department);
    }

    public String getEmployeeID() {
        return employeeID;
    }

    public void setEmployeeID(String employeeID) {
        if (employeeID == null || employeeID.trim().isEmpty()) {
            throw new IllegalArgumentException("Employee ID cannot be null or empty.");
        }
        this.employeeID = employeeID;
    }

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        if (department == null || department.trim().isEmpty()) {
            throw new IllegalArgumentException("Department cannot be null or empty.");
        }
        this.department = department;
    }

    @Override
    public String toString() {
        return "EmployeePatron{" +
                "patronID=" + getPatronID() +
                ", name='" + getName() + '\'' +
                ", address='" + getAddress() + '\'' +
                ", phoneNumber='" + getPhoneNumber() + '\'' +
                ", employeeID='" + employeeID + '\'' +
                ", department='" + department + '\'' +
                '}';
    }
}

