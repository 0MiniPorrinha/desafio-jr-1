package com.hugo.desafio_backend.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.hugo.desafio_backend.entities.Post;

public interface PostRepository extends JpaRepository<Post, Long>{
    
}
