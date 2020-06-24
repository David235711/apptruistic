package com.project.apptruistic.persistence.query;

import com.project.apptruistic.persistence.domain.Opportunity;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.MongoRegexCreator;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.repository.query.parser.Part;

import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
//import java.util.Objects;

public class OpportunityRepositoryImpl implements OpportunityRepositoryCustom{

    private final MongoTemplate mongoTemplate;

    public OpportunityRepositoryImpl(MongoTemplate mongoTemplate) {
        this.mongoTemplate = mongoTemplate;
    }

    @Override
    public List<Opportunity> query(DynamicQuery dynamicQuery) {
        final Query query = new Query();
        final List<Criteria> criteria = new ArrayList<>();
        if(dynamicQuery.getCreatorName() != null) {
            criteria.add(Criteria.where("creatorName").is(dynamicQuery.getCreatorName()));
        }
        if(dynamicQuery.getZipCode() != (Integer) null) {
            criteria.add(Criteria.where("zipCode").is(dynamicQuery.getZipCode()));
        }

        if(dynamicQuery.getStartTime().equals("morning")) {
            criteria.add(Criteria.where("startTime").gte(LocalTime.of(6, 0))
                    .andOperator(Criteria.where("startTime").lt(LocalTime.of(12,00))));
        }

        if(!criteria.isEmpty()) {
            query.addCriteria(new Criteria().andOperator(criteria.toArray(new Criteria[criteria.size()])));
        }
        return mongoTemplate.find(query, Opportunity.class);
    }



}


