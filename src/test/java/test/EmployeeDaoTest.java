package test;

import com.mongodb.client.MongoCollection;
import ers.constants.Constants;
import ers.dao.EmployeeDaoImpl;
import ers.model.Employee;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Assertions;

public class EmployeeDaoTest {
    EmployeeDaoImpl dao = new EmployeeDaoImpl(Constants.TEST_DB);

    @Test
    public void shouldFindEmployee(){
        Employee emp = dao.getEmployee("e_test","e_test");
        Assertions.assertEquals(emp.getUsername(), "e_test");
    }

    @Test
    public void shouldReturnAnEmployee(){
        Assertions.assertEquals(dao.getEmployee("e_test", "e_test").getEmpID(), "1");
    }

    @Test
    public void shouldReturnEmployeeByUsername(){
        Assertions.assertEquals(dao.getEmployee("e_test").getClass(), Employee.class);
    }

    @Test
    public void shouldAddEmployeeToDB(){
        Employee emp = new Employee();
        emp.setUsername("test1");
        emp.setPassword("test1");
        if(dao.getEmployee("test1") == null){
            dao.addEmployee(emp);
            Assertions.assertEquals(emp.getUsername(), dao.getEmployee("test1").getUsername());
        }
        dao.deleteEmployee("test1");
    }

    @Test
    public void shouldDeleteEmployeeFromMongoDB(){
        if(dao.getEmployee("test1") != null){
            dao.deleteEmployee("test1");
        }
        Employee emp = new Employee();
        emp.setUsername("test1");
        emp.setPassword("test1");
        if(dao.getEmployee("test1") == null) {
            dao.addEmployee(emp);
        }
        if(dao.getEmployee("test1") != null){
            dao.deleteEmployee("test1");
            Assertions.assertEquals(null, dao.getEmployee("test1"));
        }
    }

    @Test
    public void shouldReturnEmployeeCollection(){
        MongoCollection<Employee> emps = dao.getAllEmployeesCollection();
        Assertions.assertNotNull(emps);
    }

    @Test
    public void shouldUpdateEmployee(){
        Employee emp = dao.getEmployee("e_test");
        String old_pw = emp.getPassword();
        String new_pw = "excited";
        emp.setPassword(new_pw);
        String empID = emp.getEmpID();
        dao.updateEmployee(empID, emp);
        Employee new_emp= dao.getEmployee("e_test");
        String new_emp_pw = new_emp.getPassword();
        new_emp.setPassword("e_test");
        dao.updateEmployee(empID, new_emp);
        Assertions.assertEquals(new_pw, new_emp_pw);

    }

}

