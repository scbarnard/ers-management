package ers.services;

import ers.controller.ManagerController;
import ers.dao.ManagerDao;
import ers.dao.ManagerDaoImpl;

public class ManagerServicesImpl implements ManagerServices{
    ManagerDaoImpl dao;

    public ManagerServicesImpl(ManagerDaoImpl dao){
        this.dao = dao;
    }

    public ManagerDao getDao() { return this.dao; }
}
