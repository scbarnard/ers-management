package test;

import ers.constants.Constants;
import ers.dao.ManagerDaoImpl;
import ers.model.Manager;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Assertions;


public class ManagerDaoTest {
    ManagerDaoImpl dao = new ManagerDaoImpl(Constants.TEST_DB);

    @Test
    public void shouldFindManager(){
        Manager man = dao.getManager("m_test", "m_test");
        Assertions.assertEquals(Manager.class, man.getClass());
    }

    @Test
    public void shouldFindManagerByUsernameOnly(){
        Manager man = dao.getManager("m_test");
        Assertions.assertEquals(Manager.class, man.getClass());
    }
}
