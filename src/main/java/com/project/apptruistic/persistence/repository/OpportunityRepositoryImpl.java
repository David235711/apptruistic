package com.project.apptruistic.persistence.repository;

import com.project.apptruistic.persistence.domain.Opportunity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;

import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

public class OpportunityRepositoryImpl implements OpportunityRepositoryCustom {

    private final MongoTemplate mongoTemplate;

    @Autowired
    public OpportunityRepositoryImpl(MongoTemplate mongoTemplate) {
        this.mongoTemplate = mongoTemplate;
    }

    @Override
    public List<Opportunity> query(DynamicQuery dynamicQuery) {
        Query query = new Query();
        List<Criteria> criteria = new ArrayList<>();
        if (dynamicQuery.getCreatorName() != null) {
            criteria.add(Criteria.where("creatorName").is(dynamicQuery.getCreatorName()));
        }

        if (dynamicQuery.getZipCode() > 0 ) {
            criteria.add(Criteria.where("zipCode").is(dynamicQuery.getZipCode()));
        }

        if (dynamicQuery.getCategory() != null) {
            criteria.add(Criteria.where("category").is(dynamicQuery.getCategory()));
        }

        int participants = dynamicQuery.getNumberOfParticipants();
        if (participants > 0) {
            if (participants == 1) {
                System.out.println("receiving: " + participants);
                criteria.add(Criteria.where("numberOfParticipants").is(participants));
            } else {
                System.out.println("receiving: " + participants);
                criteria.add(Criteria.where("numberOfParticipants").gte(participants));
            }
        }

        if (dynamicQuery.getCreatorType() != null) {
            criteria.add(Criteria.where("creatorType").is(dynamicQuery.getCreatorType()));
        }

        //ToDo: doesn't work
        if (dynamicQuery.getStartTime() != null && dynamicQuery.getStartTime().equals("morning")) {
            criteria.add(Criteria.where("startTime").gt(LocalTime.of(0, 0))
                    /*.lt(LocalTime.of(12, 0))*/);
        }

        /*if (dynamicQuery.getOccurDate() != null) {
            criteria.add(Criteria.where("occurDate").is(dynamicQuery.getOccurDate()));
        }*/


        if (!criteria.isEmpty()) {
            query.addCriteria(new Criteria().andOperator(criteria.toArray(new Criteria[criteria.size()])));
        }
        return mongoTemplate.find(query, Opportunity.class);
    }
}


