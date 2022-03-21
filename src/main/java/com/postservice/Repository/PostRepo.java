package com.postservice.Repository;

import com.postservice.Model.PostModel;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PostRepo extends MongoRepository<PostModel,String> {
}
