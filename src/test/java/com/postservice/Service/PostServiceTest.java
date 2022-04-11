package com.postservice.Service;

import com.postservice.Exception.PostNotFoundException;
import com.postservice.Feign.FeignComment;
import com.postservice.Feign.FeignLike;
import com.postservice.Feign.FeignUser;
import com.postservice.Model.PostDTO;
import com.postservice.Model.PostModel;
import com.postservice.Model.User;
import com.postservice.Repository.PostRepo;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.PageImpl;
import org.springframework.test.context.junit.jupiter.SpringExtension;


import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.assertj.core.api.AssertionsForClassTypes.*;
import static org.mockito.Mockito.*;

@ExtendWith(SpringExtension.class)
@SpringBootTest
class PostServiceTest {

    @InjectMocks
    PostService service;

    @Mock
    PostRepo postRepo;
    @Mock
    FeignUser userFeign;
    @Mock
    FeignComment commentFeign;
    @Mock
    FeignLike likeFeign;

    private PostModel createPost(){
        return new PostModel("1","Nice POst","12",null,null);
    }

    private User createUser() throws ParseException {
        User user = new User();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Date c= sdf.parse("2015-05-26");
        user.setUserID("1");
        user.setFirstName("firstTest");
        user.setMiddleName("J");
        user.setLastName("S");
        user.setPhoneNumber("9090909090");
        user.setEmail("natsu@mail.com");
        user.setDateOfBirth(c);
        user.setEmployeeNumber("12345");
        user.setBloodGroup("O+");
        user.setGender("MALE");
        return user;

    }

    private PostDTO createPostModel() throws ParseException {
        return new PostDTO("1","Nice POst",createUser(),null,null,0,0);
    }

    @Test
    void deleteById() {

        postRepo.deleteById("1");
        verify(postRepo, times(1)).deleteById("1");


    }

    @Test
    void updatePost() throws ParseException {
        PostModel postModel = createPost();
        PostDTO postDTO =createPostModel();

        when(this.postRepo.save(postModel)).thenReturn(postModel);
        when(this.postRepo.findById("1")).thenReturn(Optional.of(postModel));
        assertThat(this.service.updatePost(postModel,"1").getPost()).isEqualTo(postDTO.getPost());


    }

    @Test
    void findById() throws ParseException {
        PostModel post = new PostModel();
        post.setPostID("1");
        post.setPost("Nice POst");
        post.setPostedBy("12");

        PostDTO postDTO = createPostModel();

        when(postRepo.findById("1")).thenReturn(Optional.of(post));
//


        assertThat(service.findById("1").getPost()).isEqualTo(postDTO.getPost());
        assertThrows(PostNotFoundException.class, () -> service.findById(null));

    }

    @Test
    void savePost() throws ParseException {

        PostModel postModel = createPost();


        when(this.postRepo.save(any(PostModel.class))).thenReturn(postModel);
        PostModel postSaved = postRepo.save(postModel);

        assertThat(postSaved.getPost()).isNotNull();
        assertThat(postSaved.getPost()).isEqualTo("Nice POst");

    }

    @Test
    void showAll() {
        List<PostModel> postModelList =new ArrayList<>();
        PostModel postModel = createPost();
        postModelList.add(postModel);
        PageImpl<PostModel> pageImpl = new PageImpl<>(postModelList);

        when(this.postRepo.findAll((org.springframework.data.domain.Pageable) any())).thenReturn(pageImpl);
        assertEquals(1, this.service.showAll(1, 3).size());

    }
}