package ers.dao;

import com.mongodb.client.FindIterable;
import ers.model.ReimbursementRequest;

import java.util.List;

public interface ReimbursementRequestDao {
    public List getAllReimbursementRequests();
    public FindIterable<ReimbursementRequest> getRequestsByUsername(String username);
    public void updateReimbursementRequest(String requestID, ReimbursementRequest r_request);
    public ReimbursementRequest getReimbursementRequest (String requestID);
    public void addReimbursementRequest(ReimbursementRequest r_request);
    public void deleteReimbursementRequest(String requestID);
    public String getMaxRequestID();
    public List<ReimbursementRequest> getAllResolvedRequests();
    public List<ReimbursementRequest> getAllPendingRequests();
    public List<ReimbursementRequest> getAllRequestsByEmployee(String username);
    public ReimbursementRequest getReimbursementRequestById(String approvedID);
    public void updateReimbursementRequestByID(String requestID, ReimbursementRequest r);
}
