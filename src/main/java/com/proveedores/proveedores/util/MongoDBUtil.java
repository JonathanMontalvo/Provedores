package com.proveedores.proveedores.util;

import com.mongodb.ConnectionString;
import com.mongodb.MongoClientSettings;
import com.mongodb.MongoCompressor;
import com.mongodb.ServerApi;
import com.mongodb.ServerApiVersion;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoDatabase;

public class MongoDBUtil {
    private static final String CONNECTION_STRING = "mongodb+srv://proveedores:0KfhA5BB6l0xstku@cluster.yix4x.mongodb.net/test?retryWrites=true&w=majority&appName=Cluster";
    private static MongoDBUtil instance;
    private static MongoClient mongoClient;
    private static MongoDatabase database;

    private MongoDBUtil() {
        ServerApi serverApi = ServerApi.builder()
                .version(ServerApiVersion.V1)
                .build();

        MongoClientSettings settings = MongoClientSettings.builder()
                .applyConnectionString(new ConnectionString(CONNECTION_STRING))
                .applyToSslSettings(builder -> builder.enabled(true)) // Se activa ssl explicitó
                .serverApi(serverApi)
                .build();

        mongoClient = MongoClients.create(settings);
        database = mongoClient.getDatabase("test");
    }

    public static synchronized MongoDBUtil getInstance() {
        if (instance == null) {
            instance = new MongoDBUtil();
        }
        return instance;
    }

    public MongoDatabase getDatabase() {
        return database;
    }

    public static void close() {
        if (mongoClient != null) {
            mongoClient.close();
        }
    }
}
