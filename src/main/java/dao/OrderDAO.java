package dao;

import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.model.Filters;
import com.mongodb.client.model.UpdateOptions;
import com.mongodb.client.model.Updates;
import com.mongodb.client.result.DeleteResult;
import com.mongodb.client.result.InsertOneResult;
import com.mongodb.client.result.UpdateResult;
import data.ConnectionMongoDB;
import entities.Order;
import org.bson.conversions.Bson;

import java.util.ArrayList;
import java.util.List;

public class OrderDAO implements ICRUD<Order> {
    private final MongoDatabase db;

    public OrderDAO() {
        this.db = ConnectionMongoDB.getDbPojo();
    }

    public OrderDAO(MongoDatabase db) {
        this.db = db;
    }

    private Bson filterId(Object id) {
        return Filters.eq("_id", id);
    }

    @Override
    public List<Order> selectAll() {
        MongoCollection<Order> collection = db.getCollection("orders", Order.class);
        List<Order> orders = new ArrayList<>();

        FindIterable<Order> docs = collection.find();
        docs.forEach(orders::add);

        return orders;
    }

    @Override
    public int deleteAll() {
        MongoCollection<Order> collection = db.getCollection("orders", Order.class);
        Bson filter = Filters.empty();
        DeleteResult docs = collection.deleteMany(filter);
        return (int) docs.getDeletedCount();
    }

    @Override
    public Order select(Order entity) {
        MongoCollection<Order> collection = db.getCollection("orders", Order.class);

        Bson filter = filterId(entity.getId());

        FindIterable<Order> docs = collection.find(filter);

        Order order = docs.first();

        return order;
    }

    @Override
    public int insert(Order entity) {
        MongoCollection<Order> collection = db.getCollection("orders", Order.class);

        //        Document doc = new Document();
        //        doc.append("client", entity.getClient());
        //        doc.append("plank", entity.getPlank());
        //        doc.append("pieces", entity.getPieces());
        //        doc.append("status", entity.getStatus());

        InsertOneResult docs = collection.insertOne(entity);

        return docs.getInsertedId() != null ? 1 : 0;
    }

    @Override
    public int update(Order entity) {
        MongoCollection<Order> collection = db.getCollection("orders", Order.class);

        Bson filter = filterId(entity.getId());
        Bson update = Updates.combine(
                Updates.set("material", entity.getPlank()),
                Updates.set("pieces", entity.getPieces()),
                Updates.set("status", entity.getStatus())
        );
        UpdateOptions options = new UpdateOptions();

        UpdateResult doc = collection.updateOne(filter, update, options);

        return (int) doc.getModifiedCount();
    }

    @Override
    public int delete(Order entity) {
        MongoCollection<Order> collection = db.getCollection("orders", Order.class);

        Bson filter = filterId(entity.getId());
        DeleteResult doc = collection.deleteOne(filter);

        return (int) doc.getDeletedCount();
    }

    public int update(Order entity, String key, Object value) {
        MongoCollection<Order> collection = db.getCollection("orders", Order.class);

        Bson filter = filterId(entity.getId());
        Bson update = Updates.set(key, value);
        UpdateOptions options = new UpdateOptions();

        UpdateResult docs = collection.updateOne(filter, update, options);

        return (int) docs.getModifiedCount();
    }
}