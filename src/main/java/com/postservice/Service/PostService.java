package com.postservice.Service;


import com.postservice.Model.PostModel;
import com.postservice.Repository.PostRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PostService {

    @Autowired
    private PostRepo postRepo;

    public PostModel findById(String postId){
        return this.postRepo.findById(postId).get();
    }
}
