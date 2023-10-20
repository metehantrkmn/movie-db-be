package com.metehan.movie;

import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;

public interface UserDocumentRepository extends MongoRepository<UserDocument, String > {

    Optional<UserDocument> findUserDocumentByEmail(String email);

}
