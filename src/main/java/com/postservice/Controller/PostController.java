package com.postservice.Controller;



import com.postservice.Model.PostDTO;
import com.postservice.Model.PostModel;
import com.postservice.Service.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import org.springframework.web.bind.annotation.*;

import java.util.List;


import org.springframework.web.bind.annotation.PathVariable;


import org.springframework.web.bind.annotation.GetMapping;


import org.springframework.web.bind.annotation.RequestBody;



import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import javax.ws.rs.QueryParam;

@CrossOrigin(value="*")
@RestController
@RequestMapping("/posts")
public class PostController {

    @Autowired
    private PostService postService;


   

    @GetMapping()
    public ResponseEntity<List<PostDTO>> showAll(@QueryParam("page") Integer page, @QueryParam("pageSize") Integer pageSize){
        return new ResponseEntity<>(postService.showAll(page,pageSize), HttpStatus.ACCEPTED);
    }


    @DeleteMapping("/{postId}")
    public ResponseEntity<String> deleteById(@PathVariable("postId") String postId){
        return  new ResponseEntity<>(postService.deleteById(postId), HttpStatus.ACCEPTED);
    }



    @PutMapping("/{postId}")
    public ResponseEntity<PostDTO> updatePost(@RequestBody @Valid PostModel postModel, @PathVariable("postId") String postId){
        return  new ResponseEntity<>(postService.updatePost(postModel,postId), HttpStatus.ACCEPTED);
    }

    @GetMapping("/{postId}")
    public ResponseEntity<PostDTO> findById(@PathVariable("postId") String postId){
        return new ResponseEntity<>(postService.findById(postId), HttpStatus.ACCEPTED);
    }


    @PostMapping()
    public ResponseEntity<PostDTO> savePost(@RequestBody @Valid PostModel postModel){
        return  new ResponseEntity<>(postService.savePost(postModel), HttpStatus.ACCEPTED);
    }









}
