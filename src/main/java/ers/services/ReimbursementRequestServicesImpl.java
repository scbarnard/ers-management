package ers.services;

import ers.dao.ReimbursementRequestDaoImpl;
import ers.model.ReimbursementRequest;
import io.javalin.Javalin;

public class ReimbursementRequestServicesImpl implements ReimbursementRequestServices {

    ReimbursementRequestDaoImpl dao;

    public ReimbursementRequestServicesImpl(ReimbursementRequestDaoImpl dao){
        this.dao = dao;
    }

    /**
     * @param request
     */
    @Override
    public void createReq(ReimbursementRequest request) {
        dao.addReimbursementRequest(request);
    }

    /**
     * @param requestID
     * @param request
     */
    @Override
    public void modifyReqById(String requestID, ReimbursementRequest request) {
        dao.updateReimbursementRequestByID(requestID, request);
    }

    /**
     * @param username
     * @param request
     */
    @Override
    public void modifyReqByUsername(String username, ReimbursementRequest request){
        dao.updateReimbursementRequest(username, request);
    }

    public ReimbursementRequestDaoImpl getDao(){
        return this.dao;
    }
}
