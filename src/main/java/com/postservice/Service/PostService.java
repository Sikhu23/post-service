package com.postservice.Service;


import com.postservice.Model.PostModel;
import com.postservice.Repository.PostRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import java.time.LocalDateTime;


@Service
public class PostService {

    @Autowired
    private PostRepo postRepo;

    public PostModel savePost(PostModel postModel){
        postModel.setCreatedAt(LocalDateTime.now());
        postModel.setUpdatedAt(null);
        return postRepo.save(postModel);
    }
}
