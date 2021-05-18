package ers.dao;

import com.mongodb.BasicDBObject;
import com.mongodb.ConnectionString;
import com.mongodb.MongoClientSettings;
import com.mongodb.client.*;
import com.mongodb.client.model.Filters;
import ers.constants.Constants;
import ers.model.Employee;
import ers.model.ReimbursementRequest;
import org.bson.Document;
import org.bson.codecs.configuration.CodecRegistry;
import org.bson.codecs.pojo.PojoCodecProvider;

import java.util.List;
import java.util.logging.Logger;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

import static com.mongodb.client.model.Aggregates.limit;
import static com.mongodb.client.model.Filters.*;
import static org.bson.codecs.configuration.CodecRegistries.fromProviders;
import static org.bson.codecs.configuration.CodecRegistries.fromRegistries;

public class ReimbursementRequestDaoImpl implements ReimbursementRequestDao{

    MongoCollection<ReimbursementRequest> reimbursementRequests;

    public ReimbursementRequestDaoImpl(String database){
        ConnectionString connectionString = new ConnectionString("mongodb://localhost:27017/"+ database);
        CodecRegistry pojoCodecRegistry = fromProviders(PojoCodecProvider.builder().automatic(true).build());
        CodecRegistry codecRegistry = fromRegistries(MongoClientSettings.getDefaultCodecRegistry(), pojoCodecRegistry);
        MongoClientSettings clientSettings = MongoClientSettings.builder()
                .applyConnectionString(connectionString)
                .codecRegistry(codecRegistry)
                .build();
        MongoClient client = MongoClients.create(clientSettings);
        MongoDatabase db = client.getDatabase(database);
        this.reimbursementRequests = db.getCollection("reimbursementRequests", ReimbursementRequest.class);
    }

    @Override
    public List getAllReimbursementRequests() { return StreamSupport.stream(this.reimbursementRequests.find().spliterator(), false).collect(Collectors.toList()); }

    @Override
    public FindIterable<ReimbursementRequest> getRequestsByUsername(String username) { return this.reimbursementRequests.find(eq("user", username)); }

    @Override
    public void updateReimbursementRequest(String username, ReimbursementRequest r_request) { reimbursementRequests.findOneAndReplace(eq("user", username), r_request); }

    @Override
    public ReimbursementRequest getReimbursementRequest(String username) { return reimbursementRequests.find(eq("user", username)).first(); }

    @Override
    public void addReimbursementRequest(ReimbursementRequest r_request) {
        reimbursementRequests.insertOne(r_request);
    }

    @Override
    public void deleteReimbursementRequest(String requestID) { reimbursementRequests.deleteOne(eq("requestID", requestID)); }

    @Override
    public String getMaxRequestID(){
        Iterable<ReimbursementRequest> allAccounts = reimbursementRequests.find();
        MongoCursor<ReimbursementRequest> iterator = (MongoCursor<ReimbursementRequest>) allAccounts.iterator();
        String max = "0";
        while(iterator.hasNext()) {
            ReimbursementRequest req = iterator.next();
            String accountNumber = req.getRequestID();
              if(Integer.parseInt(accountNumber) > Integer.parseInt(max)) {
                max = accountNumber;
            }
        }
        return max;
    }

    /**
     * @return
     */
    @Override
    public List<ReimbursementRequest> getAllResolvedRequests() {
        return StreamSupport.stream(this.reimbursementRequests.find(or(eq("status", "DENIED"), eq("status", "APPROVED"))).spliterator(), false).collect(Collectors.toList());
    }

    /**
     * @return
     */
    @Override
    public List<ReimbursementRequest> getAllPendingRequests(){
        return StreamSupport.stream(this.reimbursementRequests.find(eq("status", "PENDING")).spliterator(),false).collect(Collectors.toList());
    }

    /**
     * @param username
     * @return
     */
    @Override
    public List<ReimbursementRequest> getAllRequestsByEmployee(String username){
        return StreamSupport.stream(this.reimbursementRequests.find(eq("user", username)).spliterator(),false).collect(Collectors.toList());
    }

    /**
     * @param approvedID
     * @return
     */
    @Override
    public ReimbursementRequest getReimbursementRequestById(String approvedID) {
        return this.reimbursementRequests.find(eq("requestID", approvedID)).first();
    }

    /**
     * @param requestID
     * @param request
     */
    @Override
    public void updateReimbursementRequestByID(String requestID, ReimbursementRequest request) { reimbursementRequests.findOneAndReplace(eq("requestID", requestID), request); }
}
