package service;

import domain.Post;
import javax.transaction.Transactional;
import org.springframework.stereotype.Service;
import repository.PostRepository;

@Service
public class PostService {
    private final PostRepository postRepository;

    public PostService(PostRepository postRepository) {
        this.postRepository = postRepository;
    }
    @Transactional
    public void createPost(Post post){
        postRepository.save(post);
    }
    @Transactional
    public void deletePost(long PostId){
       postRepository.deleteById(PostId);
    }
}
