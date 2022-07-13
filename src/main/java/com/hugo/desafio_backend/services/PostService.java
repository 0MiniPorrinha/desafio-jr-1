package com.hugo.desafio_backend.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.hugo.desafio_backend.entities.Post;
import com.hugo.desafio_backend.repositories.PostRepository;
import com.hugo.desafio_backend.services.exception.ResourceNotFoundException;


@Service
public class PostService {
    
    @Autowired
    private PostRepository repository;

    public List<Post> findAll(){
        return repository.findAll();
    }

    public List<Post> findAllByDate(){
        return repository.findAll(Sort.by(Sort.Direction.ASC, "updateAt"));
    }

    public Post findById(Long id){
        Optional<Post> post = repository.findById(id);
        return post.orElseThrow(() -> new ResourceNotFoundException(id));
    }

    public Post insert(Post post){
        return repository.save(post);
    }
}
