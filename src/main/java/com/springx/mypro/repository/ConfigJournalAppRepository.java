package com.springx.mypro.repository;

import com.springx.mypro.entity.ConfigJournalAppEntity;
import com.springx.mypro.entity.User;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface ConfigJournalAppRepository extends MongoRepository<ConfigJournalAppEntity, ObjectId> {

}