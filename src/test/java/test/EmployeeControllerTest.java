package test;

import ers.constants.Constants;
import ers.controller.EmployeeController;
import ers.dao.EmployeeDaoImpl;
import ers.services.EmployeeServicesImpl;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Assertions;

public class EmployeeControllerTest {
    EmployeeDaoImpl dao = new EmployeeDaoImpl(Constants.TEST_DB);
    EmployeeServicesImpl services = new EmployeeServicesImpl(dao);
    EmployeeController controller = new EmployeeController(services);

    @Test
    public void shouldReturnTrueBecauseEmployeeIsTheCurrentUser(){
        controller.setCurrentUser("test");
        Assertions.assertEquals(true, (controller.isEmployee()));
    }

}
