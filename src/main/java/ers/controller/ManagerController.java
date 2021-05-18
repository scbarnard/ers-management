package ers.controller;

import ers.model.Manager;
import ers.services.ManagerServicesImpl;
import io.javalin.http.Context;
import ers.util.Logger;

public class ManagerController {
    public String username = "";
    ManagerServicesImpl service;

    public ManagerController(ManagerServicesImpl service){
        this.service = service;
    }

    /**
     * Returns a JSON object of the managers info to the view - used in XMLHttp requests
     * @param ctx
     */
    public void jsonifyManagerInfo(Context ctx) {
        Manager manager = service.getDao().getManager(username);
        ctx.json(manager);
    }

    public String getManager() {return this.username;}


    public void setManager(String username) {
        this.username = username;
        Logger.logger.info("Manager: " + username + " has logged in.");
    }

    /**
     * Verifies if a manager is logged in or not.
     * @return
     */
    public boolean isManager(){
        if(this.username == null || this.username.isEmpty()){
            return false;
        }
        return true;
    }

}
