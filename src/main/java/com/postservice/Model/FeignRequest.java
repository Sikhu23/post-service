package com.postservice.Model;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString

public class FeignRequest {

    private PostModel postModel;
    private int commentCounts;
    private int likeCounts;
}
