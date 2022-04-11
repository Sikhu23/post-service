package com.postservice.Feign;


import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;



@FeignClient(name="COMMENT-SERVICE")
public interface FeignComment {

    @GetMapping("/posts/{postId}/comments/count")
    public int commentCount(@PathVariable("postId") String postId);





}