package controller;

import domain.Post;
import dto.ResponseDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import service.PostService;

@RestController
public class PostController {
    @Autowired
    private PostService postService;

    @PostMapping("/create/post")
    public ResponseDto<Integer> create(@RequestBody Post post){
        postService.createPost(post);
        return new ResponseDto<>(HttpStatus.OK,1);
    }
    @DeleteMapping("/delete/post")
    public  ResponseDto<Integer> deleteByPostId(@PathVariable long PostId){
        postService.deletePost(PostId);
        return new ResponseDto<>(HttpStatus.OK,1);
    }
}
