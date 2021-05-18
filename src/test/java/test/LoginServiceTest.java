package test;

import ers.constants.Constants;
import ers.dao.EmployeeDaoImpl;
import ers.dao.ManagerDaoImpl;
import ers.services.LoginServiceImpl;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Assertions;

public class LoginServiceTest {
    EmployeeDaoImpl employees = new EmployeeDaoImpl(Constants.TEST_DB);
    ManagerDaoImpl managers = new ManagerDaoImpl(Constants.TEST_DB);
    LoginServiceImpl service = new LoginServiceImpl(employees, managers);

    @Test
    public void shouldReturnZeroForManager(){
        //Manager credentials
        int check = service.userLoginCheck("m_test", "m_test");
        Assertions.assertEquals(0, check);
    }

    @Test
    public void shouldReturnOneForEmployee(){
        //Manager credentials
        int check = service.userLoginCheck("e_test", "e_test");
        Assertions.assertEquals(1, check);
    }

    @Test void shouldReturnNegativeOneForUserNotFound(){
        int check = service.userLoginCheck("mickey", "mouse");
        Assertions.assertEquals(-1, check);
    }
}
