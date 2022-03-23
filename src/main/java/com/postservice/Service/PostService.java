package com.postservice.Service;


import com.postservice.Exception.PostNotFoundException;
import com.postservice.Feign.FeignComment;
import com.postservice.Feign.FeignLike;
import com.postservice.Feign.FeignUser;
import com.postservice.Model.FeignRequest;
import com.postservice.Model.PostDTO;
import com.postservice.Model.PostModel;
import com.postservice.Repository.PostRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;


import java.util.ArrayList;
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
        if(this.postRepo.findById(postId).isPresent()){

            this.postRepo.deleteById(postId);
            return "Post id " + postId + " Deleted Successfully";
        }
        else{
            throw new PostNotFoundException("Post ID Doesnot Exists");
        }
    }


    public PostDTO updatePost(PostModel postModel, String postId) {
        postModel.setPostID(postId);
        postModel.setCreatedAt(postRepo.findById(postId).get().getCreatedAt());
        postModel.setUpdatedAt(LocalDateTime.now());
        this.postRepo.save(postModel);

        PostDTO postDTO= new PostDTO(postModel.getPostID(),postModel.getPost(),
                feignUser.findByID(postModel.getPostedBy())
                ,postModel.getCreatedAt(),postModel.getUpdatedAt(),feignLike.likeCount(postModel.getPostID()),
                feignComment.commentCount(postModel.getPostID()));

        return postDTO;
    }


    public PostDTO findById(String postId){

        try{

            PostModel postModel=postRepo.findById(postId).get();

            PostDTO postDTO= new PostDTO(postModel.getPostID(),postModel.getPost(),
                    feignUser.findByID(postModel.getPostedBy())
                    ,postModel.getCreatedAt(),postModel.getUpdatedAt(),feignLike.likeCount(postModel.getPostID()),
                    feignComment.commentCount(postModel.getPostID()));

            return postDTO;
        }
        catch (Exception e){
                    throw new PostNotFoundException("Post ID Doesnot Exists");
        }


    }

    public PostDTO savePost(PostModel postModel){

        postModel.setCreatedAt(LocalDateTime.now());
        postModel.setUpdatedAt(postModel.getCreatedAt());
        postRepo.save(postModel);

        PostDTO postDTO= new PostDTO(postModel.getPostID(),postModel.getPost(),
                feignUser.findByID(postModel.getPostedBy())
                ,postModel.getCreatedAt(),postModel.getUpdatedAt(),0,0);

        return postDTO;



    }

    public List<PostDTO> showAll(Integer page,Integer pageSize ){
        if(page==null){
            page=1;
        }
        if(pageSize==null){
            pageSize=10;
        }
        Pageable firstPage = PageRequest.of(page-1, pageSize);
        List<PostModel> postModels= postRepo.findAll(firstPage).toList();
        if(postModels.isEmpty()){
            throw new PostNotFoundException("Post Doesnot Exist");
        }
        List<PostDTO> postDTOS=new ArrayList<>();
        for(PostModel postModel:postModels){
            PostDTO postDTO = new PostDTO(postModel.getPostID(),postModel.getPost(),
                    feignUser.findByID(postModel.getPostedBy()),postModel.getCreatedAt(),
                    postModel.getUpdatedAt(),feignLike.likeCount(postModel.getPostID()),
                    feignComment.commentCount(postModel.getPostID()));
            postDTOS.add(postDTO);
        }
        return  postDTOS;


                }





}
