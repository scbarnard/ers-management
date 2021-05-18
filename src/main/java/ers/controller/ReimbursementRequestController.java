package ers.controller;

import com.mongodb.client.FindIterable;
import ers.model.ReimbursementRequest;
import ers.services.ReimbursementRequestServicesImpl;
import io.javalin.http.Context;
import org.json.JSONArray;

import java.util.List;
import ers.util.Logger;

public class ReimbursementRequestController{

    private EmployeeController employeeController;
    private ReimbursementRequestServicesImpl reimbursementRequestServicesImpl;

    public ReimbursementRequestController(ReimbursementRequestServicesImpl service, EmployeeController controller){
        this.employeeController = controller;
        this.reimbursementRequestServicesImpl = service;
    }

    /**
     * Handles the creation of a new reimbursement request.
     * @param ctx
     */
    public void createReimbursementRequest(Context ctx)
    {
        String username = ctx.formParam("username");
        System.out.println();
        String expense = String.valueOf(ctx.formParam("expense"));
        String purpose = ctx.formParam("purpose");
        String requestID = reimbursementRequestServicesImpl.getDao().getMaxRequestID();
        int new_req_id = Integer.parseInt(requestID) + 1;
        requestID = String.valueOf(new_req_id);
        ReimbursementRequest req = new ReimbursementRequest(requestID, purpose, expense, "PENDING", "N/A", username);
        reimbursementRequestServicesImpl.createReq(req);
        Logger.logger.info("New request inserted with ID#"+requestID);
        ctx.redirect("/newRequest.html");
    }

    /**
     * Returns all reimbursement requests in JSON object format
     * @param ctx
     */
    public void getReimbursementRequest(Context ctx){

        String username = employeeController.getCurrentUser();
        FindIterable<ReimbursementRequest> requests = reimbursementRequestServicesImpl.getDao().getRequestsByUsername(username);
        JSONArray response = new JSONArray();
        for (ReimbursementRequest request : requests){
            response.put(request);
        }
        ctx.json(response);
    }

    /**
     * Returns all reimbursement requests that have been resolved (Denied or Approved) in JSON format
     * @param ctx
     */
    public void getAllResolvedRequests(Context ctx){
        List<ReimbursementRequest> reqs = reimbursementRequestServicesImpl.getDao().getAllResolvedRequests();
        JSONArray response = new JSONArray();
        for (ReimbursementRequest req : reqs){
            response.put(req);
        }
        ctx.json(response);
    }

    /**
     * Returns all reimbursement requests that are unresolved (Pending) in JSON format
     * @param ctx
     */
    public void getAllPendingRequests(Context ctx){
        List<ReimbursementRequest> reqs = reimbursementRequestServicesImpl.getDao().getAllPendingRequests();
        JSONArray response = new JSONArray();
        for(ReimbursementRequest req : reqs){
            response.put(req);
        }
        ctx.json(response);
    }

    /**
     * Returns a custom built page that has all requests for a particular employee (from the search requests by employee page)
     * @param ctx
     */
    public void getRequestsByEmployee(Context ctx) {
        String username = ctx.formParam("username");
        List<ReimbursementRequest> reqs = reimbursementRequestServicesImpl.getDao().getAllRequestsByEmployee(username);
        String page = "<!DOCTYPE html>" +
                "<html lang=\"en\">" +
                "<head>" +
                "  <meta charset=\"UTF-8\">" +
                "  <link rel=\"stylesheet\" href=\"https://fonts.googleapis.com/css?family=Source+Sans+Pro:200,300\"/>" +
                "  <link rel=\"stylesheet\" href=\"css/styles.css\" />" +
                "    <title>Requests by Employee</title>" +
                "</head>" +
                "<body>";
        if (!(reqs.isEmpty())) {
            page +=
                    "<h3> Requests for " + username + "</h3>" +
                            "<table><tr><th>Request ID</th>" +
                            "<th>Expense</th>" +
                            "<th>Purpose</th>" +
                            "<th>Resolving Manager</th>" +
                            "<th>Status</th></tr>";

            JSONArray response = new JSONArray();
            for (ReimbursementRequest req : reqs) {
                page += "<tr><td>" + req.getRequestID() + "</td>";
                page += "<td>" + req.getExpense() + "</td>";
                page += "<td>" + req.getPurpose() + "</td>";
                page += "<td>" + req.getResolvingManager() + "</td>";
                page += "<td>" + req.getStatus() + "</td></tr>";
                response.put(req);
            }
            page += "</table>";
        } else {
            page += "<h3>No requests to display for " + username + "</h3>";
        }
        page += "<br><a href=\"/managerHome.html\">Return to Home</a><a href=\"requestsByEmployee.html\">Search Again</a>";
        page += "</body>";
        page += "</html>";
        ctx.html(page);
    }

    /**
     * Updates a reimbursement request.
     * @param ctx
     */
    public void updateReimbursementRequest(Context ctx) {
        String approvedID = ctx.formParam("approvedReq");
        String deniedID = ctx.formParam("deniedReq");
        String manager = ctx.formParam("username");

        if(!(approvedID.isEmpty())) {
            ReimbursementRequest approved_req = reimbursementRequestServicesImpl.getDao().getReimbursementRequestById(approvedID);
            approved_req.setStatus("APPROVED");
            approved_req.setResolvingManager(manager);
            reimbursementRequestServicesImpl.modifyReqById(approvedID, approved_req);
            Logger.logger.info("Updated request with ID#" + approvedID);
        }
        if(!(deniedID.isEmpty())) {
            ReimbursementRequest denied_req = reimbursementRequestServicesImpl.getDao().getReimbursementRequestById(deniedID);
            denied_req.setStatus("DENIED");
            denied_req.setResolvingManager(manager);
            reimbursementRequestServicesImpl.modifyReqById(deniedID, denied_req);
            Logger.logger.info("Updated request with ID#" + deniedID);
        }

        ctx.redirect("/allPending.html");
    }
}
