package com.hugo.desafio_backend.services;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.hugo.desafio_backend.entities.Post;
import com.hugo.desafio_backend.entities.dto.PostDTO;
import com.hugo.desafio_backend.repositories.PostRepository;
import com.hugo.desafio_backend.services.exception.ResourceNotFoundException;


@Service
public class PostService {
    
    @Autowired
    private PostRepository repository;

    public List<Post> findAll(){
        return repository.findAll();
    }

    public List<Post> findAllByDateASC(){
        return repository.findAll(Sort.by(Sort.Direction.ASC, "updateAt"));
    }

    public List<Post> findAllByDateDESC(){
        return repository.findAll(Sort.by(Sort.Direction.DESC, "updateAt"));
    }

    public Post findById(Long id){
        Optional<Post> post = repository.findById(id);
        return post.orElseThrow(() -> new ResourceNotFoundException(id));
    }

    public Post insert(PostDTO postDTO){
        Post post = new Post(null, postDTO.getTitle(), postDTO.getDescription()
            , postDTO.getBody(), LocalDateTime.now(), LocalDateTime.now());

        return repository.save(post);
    }

    public void delete(Long id){
        findById(id);
        repository.deleteById(id);
    }

    public Post update(Long id, PostDTO postDTO){
        findById(id);
        Post post = repository.getReferenceById(id);
        updateData(post, postDTO);
        return repository.save(post);
    }

    private void updateData(Post post, PostDTO postDTO) {
        post.setTitle(postDTO.getTitle());
        post.setDescription(postDTO.getDescription());
        post.setBody(postDTO.getBody());
        post.setUpdateAt(LocalDateTime.now());
    }
}
