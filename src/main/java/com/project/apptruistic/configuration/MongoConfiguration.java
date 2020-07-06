package com.project.apptruistic.configuration;

import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.project.apptruistic.logic.time.MongoLocalTimeToStringConverter;
import com.project.apptruistic.logic.time.StringToMongoLocalTimeConverter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.convert.converter.Converter;
import org.springframework.data.mongodb.config.AbstractMongoClientConfiguration;
import org.springframework.data.mongodb.core.convert.MongoCustomConversions;

import java.util.ArrayList;
import java.util.List;

@Configuration
public class MongoConfiguration extends AbstractMongoClientConfiguration {
    @Value("${spring.data.mongodb.database}")
    String database;

    @Value("${spring.data.mongodb.uri}")
    String host;

    @Override
    protected String getDatabaseName() {
        return database;
    }

    @Bean
    @Override
    public MongoCustomConversions customConversions() {
        List<Converter<?, ?>> converterList = new ArrayList<>();
        converterList.add(new MongoLocalTimeToStringConverter());
        converterList.add(new StringToMongoLocalTimeConverter());
        return new MongoCustomConversions(converterList);
    }

    @Override
    public MongoClient mongoClient() {
        return MongoClients.create(host);
    }
}
