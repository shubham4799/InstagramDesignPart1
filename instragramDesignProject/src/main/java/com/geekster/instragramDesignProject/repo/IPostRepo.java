package com.geekster.instragramDesignProject.repo;

import com.geekster.instragramDesignProject.model.Post;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IPostRepo extends JpaRepository<Post, Integer> {
}
