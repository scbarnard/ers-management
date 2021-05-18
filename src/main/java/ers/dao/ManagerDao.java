package ers.dao;

import com.mongodb.client.MongoCollection;
import ers.model.Manager;

public interface ManagerDao {

    public MongoCollection<Manager> getAllManagers();
//    public Manager getManager(String _id);
    public Manager getManager(String username, String password);
    public Manager getManager(String username);
}
