package ers.dao;

import com.mongodb.ConnectionString;
import com.mongodb.MongoClientSettings;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import ers.constants.Constants;
import ers.model.Employee;
import org.bson.codecs.configuration.CodecRegistry;
import org.bson.codecs.pojo.PojoCodecProvider;

import java.util.List;
import java.util.logging.Logger;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

import static com.mongodb.client.model.Filters.and;
import static com.mongodb.client.model.Filters.eq;
import static org.bson.codecs.configuration.CodecRegistries.fromProviders;
import static org.bson.codecs.configuration.CodecRegistries.fromRegistries;

public class EmployeeDaoImpl implements EmployeeDao{

    private MongoCollection<Employee> employees;

    /**
     * @param database
     */
    public EmployeeDaoImpl(String database){
        ConnectionString connectionString = new ConnectionString("mongodb://localhost:27017/"+ database);
        CodecRegistry pojoCodecRegistry = fromProviders(PojoCodecProvider.builder().automatic(true).build());
        CodecRegistry codecRegistry = fromRegistries(MongoClientSettings.getDefaultCodecRegistry(), pojoCodecRegistry);
        MongoClientSettings clientSettings = MongoClientSettings.builder()
                .applyConnectionString(connectionString)
                .codecRegistry(codecRegistry)
                .build();
        MongoClient client = MongoClients.create(clientSettings);
        MongoDatabase db = client.getDatabase(database);
        this.employees = db.getCollection("employees", Employee.class);
    }


    /**`
     * @return
     */
    @Override
    public MongoCollection<Employee> getAllEmployeesCollection(){
        return this.employees;
    }

    /**
     * @return List
     */
    @Override
    public List<Employee> getAllEmployeesList() {  return StreamSupport.stream(this.employees.find().spliterator(), false).collect(Collectors.toList()); }

    /**
     * @param empID
     * @param emp
     */
    @Override
    public void updateEmployee(String empID, Employee emp) {
        employees.findOneAndReplace(eq("empID", empID), emp);
    }

    /**
     * @param username
     * @param password
     * @return
     */
    @Override
    public Employee getEmployee(String username, String password) { return employees.find(and(eq("username", username), eq("password", password))).first(); }

    /**
     * @param username
     * @return
     */
    @Override
    public Employee getEmployee(String username){
        return employees.find(eq("username", username)).first();
    }

    /**
     * @param emp
     */
    @Override
    public void addEmployee(Employee emp) { employees.insertOne(emp); }

    /**
     * @param username
     */
    @Override
    public void deleteEmployee(String username) { employees.deleteOne(eq("username", username)); }
}
