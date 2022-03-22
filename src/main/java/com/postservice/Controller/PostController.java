package com.postservice.Controller;


import com.postservice.Model.PostModel;
import com.postservice.Service.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
public class PostController {

    @Autowired
    private PostService postService;


    @GetMapping("/posts/{postId}")
    public ResponseEntity<PostModel> findById(@PathVariable("postId") String postId){
        return new ResponseEntity<>(postService.findById(postId), HttpStatus.ACCEPTED);
    }


    @PostMapping("/posts")
    public ResponseEntity<PostModel> savePost(@RequestBody @Valid PostModel postModel){
        return  new ResponseEntity<>(postService.savePost(postModel), HttpStatus.ACCEPTED);
    }






}
