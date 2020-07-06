package com.project.apptruistic.persistence.repository;

import com.project.apptruistic.logic.time.MongoLocalTime;
import com.project.apptruistic.persistence.domain.Opportunity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;

import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class OpportunityRepositoryImpl implements OpportunityRepositoryCustom {

    private final MongoTemplate mongoTemplate;

    @Autowired
    public OpportunityRepositoryImpl(MongoTemplate mongoTemplate) {
        this.mongoTemplate = mongoTemplate;
    }

    @Override
    public List<Opportunity> query(DynamicQuery dynamicQuery) {
        System.out.println(dynamicQuery);
        Query query = new Query();
        List<Criteria> criteria = new ArrayList<>();
        if (dynamicQuery.getCreatorName().isBlank() == false) {
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

        criteria.add(Criteria.where("done").is(dynamicQuery.isDone()));

        if (dynamicQuery.getStartTime() != null && dynamicQuery.getStartTime().equals("morning")) {
            criteria.add(Criteria.where("startTime").gte(MongoLocalTime.of(5, 0)).lt(MongoLocalTime.of(12, 0)));
        }

        if (dynamicQuery.getStartTime() != null && dynamicQuery.getStartTime().equals("afternoon")) {
            criteria.add(Criteria.where("startTime").gte(MongoLocalTime.of(12, 0)).lt(MongoLocalTime.of(18, 0)));
        }

        if (dynamicQuery.getStartTime() != null && dynamicQuery.getStartTime().equals("night")) {
            criteria.add(
                    new Criteria().orOperator(
                            Criteria.where("startTime").gte(MongoLocalTime.of(18, 0)).lt(MongoLocalTime.of(23, 59)),
                            Criteria.where("startTime").gte(MongoLocalTime.of(0, 0)).lt(MongoLocalTime.of(5, 0))
                    )
            );
        }

        if (dynamicQuery.getOccurDate() != null) {
            System.out.println("checking occurDate search");
            criteria.add(Criteria.where("occurDate").gte(dynamicQuery.getOccurDate()).lt(dynamicQuery.getOccurDate().plusDays(1L)));
        }


        if (!criteria.isEmpty()) {
            query.addCriteria(new Criteria().andOperator(criteria.toArray(new Criteria[criteria.size()])));
        }
        return mongoTemplate.find(query, Opportunity.class);
    }
}


