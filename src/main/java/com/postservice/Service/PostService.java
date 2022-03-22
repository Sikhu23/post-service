package com.postservice.Service;


import com.postservice.Model.PostModel;
import com.postservice.Repository.PostRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;



import java.time.LocalDateTime;



@Service
public class PostService {

    @Autowired
    private PostRepo postRepo;


    public PostModel updatePost(PostModel postModel, String postId){

        postModel.setCreatedAt(postRepo.findById(postId).get().getCreatedAt());
        postModel.setUpdatedAt(LocalDateTime.now());
        return this.postRepo.save(postModel);


    public PostModel findById(String postId){
        return this.postRepo.findById(postId).get();

    public PostModel savePost(PostModel postModel){
        postModel.setCreatedAt(LocalDateTime.now());
        postModel.setUpdatedAt(null);
        return postRepo.save(postModel);


    }
}
