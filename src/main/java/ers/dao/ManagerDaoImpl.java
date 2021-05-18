package ers.dao;

import com.mongodb.ConnectionString;
import com.mongodb.MongoClientSettings;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.model.Filters;
import ers.constants.Constants;
import ers.model.Manager;
import org.bson.codecs.configuration.CodecRegistry;
import org.bson.codecs.pojo.PojoCodecProvider;

import java.util.logging.Logger;

import static com.mongodb.client.model.Filters.and;
import static com.mongodb.client.model.Filters.eq;
import static org.bson.codecs.configuration.CodecRegistries.fromProviders;
import static org.bson.codecs.configuration.CodecRegistries.fromRegistries;

public class ManagerDaoImpl implements ManagerDao {

    private MongoCollection<Manager> managers;
    Logger mongoLogger = Logger.getLogger("org.mongodb.driver");

    public ManagerDaoImpl(String database){
        ConnectionString connectionString = new ConnectionString("mongodb://localhost:27017/"+ database);
        CodecRegistry pojoCodecRegistry = fromProviders(PojoCodecProvider.builder().automatic(true).build());
        CodecRegistry codecRegistry = fromRegistries(MongoClientSettings.getDefaultCodecRegistry(), pojoCodecRegistry);
        MongoClientSettings clientSettings = MongoClientSettings.builder()
                .applyConnectionString(connectionString)
                .codecRegistry(codecRegistry)
                .build();
        MongoClient client = MongoClients.create(clientSettings);
        MongoDatabase db = client.getDatabase(database);
        this.managers = db.getCollection("managers", Manager.class);
    }

    /**
     * @return
     */
    @Override
    public MongoCollection<Manager> getAllManagers() { return this.managers; }


    /**
     * @param username
     * @param password
     * @return
     */
    @Override
    public Manager getManager(String username, String password){
        return managers.find(and(eq("username", username), eq("password", password))).first();
    }

    /**
     * @param username
     * @return
     */
    @Override
    public Manager getManager(String username){
        return managers.find(eq("username", username)).first();
    }
}
