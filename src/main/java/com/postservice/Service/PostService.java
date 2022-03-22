package com.postservice.Service;


import com.postservice.Feign.FeignComment;
import com.postservice.Feign.FeignLike;
import com.postservice.Feign.FeignUser;
import com.postservice.Model.FeignRequest;
import com.postservice.Model.PostModel;
import com.postservice.Repository.PostRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;


import java.util.List;

import java.time.LocalDateTime;


@Service
public class PostService {

    @Autowired
    private PostRepo postRepo;

    @Autowired
    private FeignComment feignComment;
    @Autowired
    private FeignLike feignLike;
    @Autowired
    private FeignUser feignUser;



    public String deleteById(String postId) {
        this.postRepo.deleteById(postId);
        return "Post id " + postId + " Deleted Successfully";
    }


    public PostModel updatePost(PostModel postModel, String postId) {

        postModel.setCreatedAt(postRepo.findById(postId).get().getCreatedAt());
        postModel.setUpdatedAt(LocalDateTime.now());
        return this.postRepo.save(postModel);
    }


    public FeignRequest findById(String postId){

        FeignRequest feignRequest= new FeignRequest();
        feignRequest.setCommentCounts(feignComment.commentCount(postId));
        feignRequest.setLikeCounts(feignLike.likeCount(postId));
        feignRequest.setUser(feignUser.findByID(postRepo.findById(postId).get().getPostedBy()));


             feignRequest.setPostModel(this.postRepo.findById(postId).get());
             return  feignRequest;
        }

    public PostModel savePost(PostModel postModel){
        postModel.setCreatedAt(LocalDateTime.now());
        postModel.setUpdatedAt(postModel.getCreatedAt());
        return postRepo.save(postModel);


    }

    public List<PostModel> showAll(int page,int pageSize ){
        Pageable firstPage = PageRequest.of(page, pageSize);
                    return postRepo.findAll(firstPage).toList();
                }





}
