package com.geekster.instragramDesignProject.repo;

import com.geekster.instragramDesignProject.model.AuthenticationToken;
import com.geekster.instragramDesignProject.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IAuthenticationToken extends JpaRepository<AuthenticationToken,Long> {


        AuthenticationToken findFirstByUser(User userObj);
        }
