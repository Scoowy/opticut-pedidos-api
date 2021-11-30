package com.opticortes.data;

import com.mongodb.MongoClientSettings;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoDatabase;
import org.bson.codecs.configuration.CodecProvider;
import org.bson.codecs.configuration.CodecRegistries;
import org.bson.codecs.configuration.CodecRegistry;
import org.bson.codecs.pojo.PojoCodecProvider;
import com.opticortes.utils.DbConfig;

public class ConnectionMongoDB {
    private static MongoClient mongoClient;
    private static MongoDatabase db;

    public static MongoClient getMongoClient() {
        final String URL_CONN = String.format(DbConfig.DB_URL, DbConfig.DB_USER, DbConfig.DB_PASSWORD);
        if (mongoClient == null) {
            mongoClient = MongoClients.create("mongodb+srv://admin:admin1234@cluster0.ysgkt.mongodb.net/opticut-orders-api?retryWrites=true&w=majority");
        }

        return mongoClient;
    }

    public static MongoDatabase getDb() {
        db = getMongoClient().getDatabase("opticut-orders-api");
        return db;
    }

    public static MongoDatabase getDbPojo() {
        // POJO's usage
        CodecProvider pojoCodecProvider = PojoCodecProvider.builder().automatic(true).build();
        CodecRegistry pojoCodecRegistry = CodecRegistries.fromRegistries(MongoClientSettings.getDefaultCodecRegistry(), CodecRegistries.fromProviders(pojoCodecProvider));
        // End POJO's usage

        db = getMongoClient().getDatabase("opticut-orders-api").withCodecRegistry(pojoCodecRegistry);
        return db;
    }
}