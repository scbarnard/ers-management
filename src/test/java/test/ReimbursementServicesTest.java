package test;

import ers.dao.ReimbursementRequestDaoImpl;
import ers.model.ReimbursementRequest;
import ers.services.ReimbursementRequestServicesImpl;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class ReimbursementServicesTest {
    ReimbursementRequestDaoImpl dao = new ReimbursementRequestDaoImpl("Project1_Test");
    ReimbursementRequestServicesImpl service = new ReimbursementRequestServicesImpl(dao);

    @Test
    public void shouldAddARequest(){
        ReimbursementRequest req = new ReimbursementRequest();
        req.setRequestID("3");
        req.setResolvingManager("N/A");
        req.setUser("e_test");
        service.createReq(req);
        Assertions.assertEquals(dao.getReimbursementRequestById("3").getRequestID(), "3");
    }

    @AfterEach
    public void resetDB(){
        dao.deleteReimbursementRequest("3");
    }
}
