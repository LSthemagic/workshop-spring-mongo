package com.railansantana.workshop.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.railansantana.workshop.domain.Post;

@Repository
public interface PostRepository extends MongoRepository<Post, String> {

}
