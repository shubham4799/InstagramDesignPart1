package com.geekster.instragramDesignProject.service;


import com.geekster.instragramDesignProject.dto.SignInInput;
import com.geekster.instragramDesignProject.dto.SignInOutput;
import com.geekster.instragramDesignProject.dto.SignUpInput;
import com.geekster.instragramDesignProject.dto.SignUpOutput;
import com.geekster.instragramDesignProject.model.AuthenticationToken;
import com.geekster.instragramDesignProject.model.User;
import com.geekster.instragramDesignProject.repo.IAuthenticationToken;
import com.geekster.instragramDesignProject.repo.IUserRepo;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;



@Service
public class UserService {
    @Autowired
    private IUserRepo iUserRepo;

    @Autowired
    private IAuthenticationToken authenticationTokenRepo;

    public SignUpOutput signUp(SignUpInput signUpInput) {
        User userObj = (User) iUserRepo.findFirstByUserEmail(signUpInput.getEmail());
        if (userObj != null){
            return new SignUpOutput("User already exist","Error!!");
        }
        String encryptedPassword;
        try {
            encryptedPassword = encryptPassword(signUpInput.getPassword());
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        }


        User userObject = new User(signUpInput.getFirstName() , signUpInput.getLastName() , signUpInput.getAge() ,signUpInput.getEmail() , encryptedPassword ,signUpInput.getPhoneNumber());

        iUserRepo.save(userObject);

        AuthenticationToken authenticationTokenObj = new AuthenticationToken(userObject);

        authenticationTokenRepo.save(authenticationTokenObj);
        return new SignUpOutput("SignUn Successfully" , "SignIn Now!!");
    }

    public String encryptPassword(String password) throws NoSuchAlgorithmException {
        MessageDigest md5 = MessageDigest.getInstance("MD5");
        md5.update(password.getBytes());
        byte[] encryptPassword = md5.digest();
        String encryptedPassword = new String(encryptPassword);
        return encryptedPassword;
    }

    public SignInOutput signIn(SignInInput signInInput) {

        try {
            User userObj = (User) iUserRepo.findFirstByUserEmail(signInInput.getEmail());
            String token = authenticationTokenRepo.findFirstByUser(userObj).getToken();

            String encryptedPassword;
            try {
                encryptedPassword = encryptPassword(signInInput.getPassword());
            } catch (NoSuchAlgorithmException e) {
                throw new RuntimeException(e);
            }

            if ((userObj.getUserEmail()).equals(signInInput.getEmail()) && encryptedPassword.equals(userObj.getUserPassword())) {
                return new SignInOutput("SignIn Successfully", token);    //user_user_id
            } else {
                return new SignInOutput("Something Wrong!!", "Try again!!");    //user_user_id
            }

        }catch (Exception e){
            System.out.println(e);
            return new SignInOutput("Something Wrong!!", "Try again!!");
        }
    }
    @Transactional
    public String updateUser(User userObj) {

        User userObject = (User) iUserRepo.findFirstByUserEmail(userObj.getUserEmail());


        if (userObject == null){
            return "Something Wrong!! Try again!! may be null!!";
        }

        String encryptedPassword;
        try {
            encryptedPassword = encryptPassword(userObj.getUserPassword());
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        }
        if (userObject.getUserEmail().equals(userObj.getUserEmail()) && encryptedPassword.equals(userObject.getUserPassword())) {

            iUserRepo.updateUser( userObj.getUserFirstName(),userObj.getUserLastName(),userObj.getUserAge(),userObj.getUserEmail(),encryptedPassword,userObj.getUserPhoneNumber() );
            return "User Updated Successfull!! Sign In Now!!";
        } else {
            return "Something Wrong!! Try again!!";
        }

    }

    public boolean verify(String emailId, String token) {
        User userObj = (User) iUserRepo.findFirstByUserEmail(emailId);
        if(userObj == null){
            return false;
        }
        AuthenticationToken authenticationObj = authenticationTokenRepo.findFirstByUser(userObj);

        if(userObj.getUserEmail().equals(emailId)  && authenticationObj.getToken().equals(token)){
            return true;
        }else {
            return false;
        }

    }
}
