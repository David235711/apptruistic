package com.project.apptruistic.persistence.repository;

//import com.project.apptruistic.logic.CreatorType;
//import com.project.apptruistic.logic.OpportunityCategory;
//import com.project.apptruistic.persistence.domain.Opportunity;
//import org.junit.jupiter.api.Test;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.autoconfigure.data.mongo.AutoConfigureDataMongo;
//import org.springframework.boot.test.context.SpringBootTest;
//
//import java.time.LocalDate;
//import java.time.LocalDateTime;
//import java.util.List;
//
//import static org.junit.jupiter.api.Assertions.assertEquals;
//
//@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
//@AutoConfigureDataMongo
//class OpportunityRepositoryTest {
//
//    @Autowired
//    OpportunityRepository opportunityRepository;
//
//    @Test
//    public void queryCreatorNameAndZipCode() {
//        String creatorName = "individual2";
//        String startTime = "morning";
//        int zipCode = 1120;
//        DynamicQuery dynamicQuery = new DynamicQuery();
//        dynamicQuery.setZipCode(zipCode);
//        dynamicQuery.setCreatorName(creatorName);
////ToDo: can't match in         dynamicQuery.setStartTime(startTime);
//
//        List<Opportunity> opportunities = opportunityRepository.query(dynamicQuery);
//        int expectedCount = 2;
//        System.out.println(opportunities.toString());
//        assertEquals(expectedCount, opportunities.size());
//    }
//
//    @Test
//    public void queryCategory() {
//        OpportunityCategory category = OpportunityCategory.ITSUPPORT;
//        DynamicQuery dynamicQuery = new DynamicQuery();
//        dynamicQuery.setCategory(category);
//
//        List<Opportunity> opportunities = opportunityRepository.query(dynamicQuery);
//        int expectedCount = 1;
//        System.out.println(opportunities.toString());
//        assertEquals(expectedCount, opportunities.size());
//    }
//
//    @Test
//    public void queryNumberOfParticipants() {
//        int numberOfParticipants = 100;
//        DynamicQuery dynamicQuery = new DynamicQuery();
//        dynamicQuery.setNumberOfParticipants(numberOfParticipants);
//
//        List<Opportunity> opportunities = opportunityRepository.query(dynamicQuery);
//        int expectedCount = 1;
//        System.out.println(opportunities.toString());
//        assertEquals(expectedCount, opportunities.size());
//    }
//
//    @Test
//    public void queryCreatorType() {
//        CreatorType type = CreatorType.INDIVIDUAL;
//        DynamicQuery dynamicQuery = new DynamicQuery();
//        dynamicQuery.setCreatorType(type);
//
//        List<Opportunity> opportunities = opportunityRepository.query(dynamicQuery);
//        int expectedCount = 8;
//        System.out.println(opportunities.toString());
//        assertEquals(expectedCount, opportunities.size());
//    }
//
//    @Test
//    public void queryOccurDate() {
//        LocalDate localDate = LocalDate.of(2020, 7, 1);
//        DynamicQuery dynamicQuery = new DynamicQuery();
//        dynamicQuery.setOccurDate(localDate);
//
//        List<Opportunity> opportunities = opportunityRepository.query(dynamicQuery);
//        int expectedCount = 3;
//        System.out.println(opportunities.toString());
//        assertEquals(expectedCount, opportunities.size());
//    }
//}