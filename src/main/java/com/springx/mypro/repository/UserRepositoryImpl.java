package com.springx.mypro.repository;

import com.springx.mypro.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.schema.JsonSchemaObject;

import java.util.List;

public class UserRepositoryImpl {

    @Autowired
    private MongoTemplate mongoTemplate;

    public List<User> getUserForSA() {
        Query query = new Query();
//        Criteria criteria = new Criteria();
//        query.addCriteria(criteria.andOperator(
//                Criteria.where("email").regex("^[A-Za-z0-9._%+-]+@[A-Za-z0-9.-]+\\.[A-Z|a-z]{2,6}$"),
//                Criteria.where("sentimentAnalysis").is(true),
//                Criteria.where("roles").is("ADMIN")
//        ));
//        query.addCriteria(Criteria.where("userName").in("RamJi1"));
//        query.addCriteria(Criteria.where("sentimentAnalysis").type(JsonSchemaObject.Type.BOOLEAN));
//        query.addCriteria(Criteria.where("userName").nin("RamJi1"));

        query.addCriteria(Criteria.where("email").regex("^[A-Za-z0-9._%+-]+@[A-Za-z0-9.-]+\\.[A-Z|a-z]{2,6}$"));
        query.addCriteria(Criteria.where("sentimentAnalysis").is(true));
        List<User> users = mongoTemplate.find(query, User.class);
        return users;

//        for Example
//        query.addCriteria(Criteria.where("userName").is("RamJi"));
//        query.addCriteria(Criteria.where("<fieldName>").ne("<value>"));
//        query.fields().include("<userName>");
//        query.addCriteria( Criteria.where("<roles>").is("ADMIN"));
//        query.addCriteria( Criteria.where("<roles>").is("USER"));
//        for Example
    }
}
