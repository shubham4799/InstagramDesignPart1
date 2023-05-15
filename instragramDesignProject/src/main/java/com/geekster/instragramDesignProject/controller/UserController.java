package com.geekster.instragramDesignProject.controller;

import com.geekster.instragramDesignProject.dto.*;
import com.geekster.instragramDesignProject.model.Post;
import com.geekster.instragramDesignProject.model.User;
import com.geekster.instragramDesignProject.service.PostService;
import com.geekster.instragramDesignProject.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;


@RestController
public class UserController {
    @Autowired
    private PostService postServiceObj;

    @Autowired
    private UserService userService;
    @PostMapping("/signUp")
    public SignUpOutput signUp(@RequestBody SignUpInput signUpInput){
        return userService.signUp(signUpInput);
    }
    @PostMapping("/signIn")
    public SignInOutput signIn(@RequestBody SignInInput signInInput){
        return userService.signIn(signInInput);
    }

    @PutMapping("/updateUser")
    public String updateUser(@RequestBody User userObj){
        return userService.updateUser(userObj);
    }

    //Post
    @PostMapping("savePost/{emailId}/{token}")
    public String post(@RequestBody PostInput postInput, @PathVariable String emailId , @PathVariable String token){
        // return postServiceObj.uplodePost(postInput,emailId,token);
        String message;
        if(userService.verify(emailId,token)){
            message = postServiceObj.uploadNow(postInput , emailId);
        }else {
            message = "Something Wrong!! Try again!!";
        }
        return message;
    }

    @GetMapping("getPost/{postId}/{emailId}/{token}")
    public Optional<Post> getPost(@PathVariable String emailId , @PathVariable String token , @PathVariable Integer postId){



        if(userService.verify(emailId,token)){

            return postServiceObj.getAllPost(postId);

        }else {
            throw new IllegalStateException("Post not Found Error!!");
        }

    }
}
