package ers.model;

import java.util.List;

public class Employee extends User{

    private String empID;

    public Employee() {}

    public String getEmpID() {
        return empID;
    }

    public void setEmpID(String empID) {
        this.empID = empID;
    }

    @Override
    public String toString() {
        return "Employee{" +
                "empID='" + empID + '\'' +
                '}';
    }
}
