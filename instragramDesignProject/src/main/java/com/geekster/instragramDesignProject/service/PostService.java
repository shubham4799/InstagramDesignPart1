package com.geekster.instragramDesignProject.service;

import com.geekster.instragramDesignProject.dto.PostInput;
import com.geekster.instragramDesignProject.model.Post;
import com.geekster.instragramDesignProject.repo.IPostRepo;
import com.geekster.instragramDesignProject.repo.IUserRepo;
import org.apache.catalina.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;


@Service
public class PostService {
    @Autowired
    private IPostRepo iPostRepo;
    @Autowired
    private IUserRepo UserRepo;
    @Autowired
    private UserService userServiceObj;

    public String uploadNow(PostInput postInput, String emailId) {
        User userObj = UserRepo.findFirstByUserEmail(emailId);
        Post postObj = new Post(postInput.getPostData(), (com.geekster.instragramDesignProject.model.User) userObj);
        iPostRepo.save(postObj);

        return "Post Uploaded Successfully!!";
    }

    public Optional<Post> getAllPost(Integer postId) {

        return iPostRepo.findById(postId);
    }

}
