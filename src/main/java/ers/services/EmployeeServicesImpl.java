package ers.services;

import ers.dao.EmployeeDao;
import ers.dao.EmployeeDaoImpl;
import ers.model.Employee;

public class EmployeeServicesImpl implements EmployeeServices{

    private EmployeeDao dao;

    public EmployeeServicesImpl(EmployeeDao dao){
        this.dao = dao;
    }

    public EmployeeDao getDao(){
        return this.dao;
    }
}
