package ers.dao;

import com.mongodb.client.MongoCollection;
import ers.model.Employee;

import java.util.List;

public interface EmployeeDao {

    public MongoCollection<Employee> getAllEmployeesCollection();
    public List<Employee> getAllEmployeesList();
    public void updateEmployee(String _id, Employee emp);
    public Employee getEmployee(String username, String password);
    public Employee getEmployee(String username);
    public void addEmployee(Employee emp);
    public void deleteEmployee(String _id);

}
