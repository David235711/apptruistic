package com.project.apptruistic.persistence.query;

import com.project.apptruistic.persistence.domain.Opportunity;
import com.project.apptruistic.persistence.repository.OpportunityRepository;

import org.junit.jupiter.api.Test;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Import;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@EnableMongoRepositories()
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@ContextConfiguration(classes = {
        OpportunityRepository.class
})
class OpportunityRepositoryTest {

    @Autowired
    OpportunityRepository opportunityRepository;

    @Test
    public void query_combinedQuery_shouldReturnList() {
        // Given
        // DB with default books
        final String creatorName = "individual2";
        final String startTime = "morning";
        final int zipCode = 1120;
        final DynamicQuery dynamicQuery = new DynamicQuery();
        dynamicQuery.setZipCode(zipCode);
        dynamicQuery.setCreatorName(creatorName);
        dynamicQuery.setStartTime(startTime);

        // When
        final List<Opportunity> books = opportunityRepository.query(dynamicQuery);

        // Then
        final int expectedCount = 1;
        assertEquals(expectedCount, books.size());
    }

}