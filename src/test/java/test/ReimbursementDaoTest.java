package test;
import com.mongodb.client.FindIterable;
import ers.constants.Constants;
import ers.dao.ReimbursementRequestDaoImpl;
import ers.model.ReimbursementRequest;
import ers.services.ReimbursementRequestServicesImpl;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Assertions;

import java.util.List;

public class ReimbursementDaoTest {
    ReimbursementRequestDaoImpl dao = new ReimbursementRequestDaoImpl("Project1_Test");
    ReimbursementRequestServicesImpl service = new ReimbursementRequestServicesImpl(dao);
    @Test
    public void shouldFindRequest(){
        Assertions.assertEquals(dao.getReimbursementRequest("e_test").getClass(), ReimbursementRequest.class);
    }

    @Test
    public void shouldAddRequest(){
        if(dao.getReimbursementRequest("test1") == null) {
            ReimbursementRequest req = new ReimbursementRequest("2", "test1", "test1", "test1", "test1", "test1");
            dao.addReimbursementRequest(req);
        }
        Assertions.assertEquals(dao.getReimbursementRequest("test1").getRequestID(), "2");
    }

    @Test
    public void shouldReturnListOfRequests(){
        List<ReimbursementRequest> reqs = dao.getAllReimbursementRequests();
        Assertions.assertNotEquals(null, reqs);
    }

    @Test
    public void shouldReturnFindIterableOfRequests(){
        FindIterable<ReimbursementRequest> reqs = dao.getRequestsByUsername("StevieB");
        Assertions.assertNotEquals(null, reqs);
    }

    @Test
    public void shouldReturnMaxRequestID(){
        Assertions.assertEquals("2", dao.getMaxRequestID());
    }
}
