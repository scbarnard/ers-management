package ers.model;

public class ReimbursementRequest {
    private String requestID;
    private String purpose;
    private String expense;
    private String status;
    private String resolvingManager;
    private String user;

    public ReimbursementRequest() {}

    public ReimbursementRequest(String requestID, String purpose, String expense, String status, String resolvingManager, String user){
        this.requestID = requestID;
        this.purpose = purpose;
        this.expense = expense;
        this.status = status;
        this.resolvingManager = resolvingManager;
        this.user = user;
    }

    public String getPurpose() {
        return purpose;
    }

    public void setPurpose(String purpose) {
        this.purpose = purpose;
    }

    public String getExpense() {
        return expense;
    }

    public void setExpense(String expense) {
        this.expense = expense;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getResolvingManager() {
        return resolvingManager;
    }

    public void setResolvingManager(String resolvingManager) {
        this.resolvingManager = resolvingManager;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public String getRequestID() { return requestID; }

    public void setRequestID(String requestID) { this.requestID = requestID; }

    @Override
    public String toString() {
        return "ReimbursementRequest{" +
                "requestID='" + requestID + '\'' +
                ", purpose='" + purpose + '\'' +
                ", expense='" + expense + '\'' +
                ", status='" + status + '\'' +
                ", resolvingManager='" + resolvingManager + '\'' +
                ", user='" + user + '\'' +
                '}';
    }
}
