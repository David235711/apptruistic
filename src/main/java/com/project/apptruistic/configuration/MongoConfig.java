package com.project.apptruistic.configuration;

import com.mongodb.MongoClient;
import com.mongodb.MongoClientURI;
import com.project.apptruistic.persistence.cascade.CascadeSaveMongoEventListener;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.config.AbstractMongoConfiguration;

@Configuration
public class MongoConfig extends AbstractMongoConfiguration{
    private final String connectionString = "mongodb://apptruistic:apptruist1c@ds247479.mlab.com:47479/apptruistic?retryWrites=false";

    @Override
    public MongoClient mongoClient() {
        MongoClientURI mongoClientURI = new MongoClientURI(connectionString);
        return new MongoClient(mongoClientURI);
    }

    @Override
    protected String getDatabaseName() {
        return "apptruistic";
    }

    @Bean
    public CascadeSaveMongoEventListener cascadingMongoEventListener() {
        return new CascadeSaveMongoEventListener();
    }
}
