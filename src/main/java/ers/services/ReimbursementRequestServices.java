package ers.services;

import ers.model.ReimbursementRequest;

public interface ReimbursementRequestServices {
    public void createReq(ReimbursementRequest r);
    public void modifyReqByUsername(String requestID, ReimbursementRequest r);
    public void modifyReqById(String requestID, ReimbursementRequest r);
}
