package com.hugo.desafio_backend.services;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
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

    public List<Post> findAll(Integer pageNumber, Integer pageSize, String sortBy){

        Pageable paging = PageRequest.of(pageNumber, pageSize, Sort.by(sortBy));

        Page<Post> pagedResult = repository.findAll(paging);

        if(pagedResult.hasContent()){
            return pagedResult.getContent();
        }else{
            return new ArrayList<>();
        }
    }

    public List<Post> findAllDesc(Integer pageNumber, Integer pageSize, String sortBy){

        Pageable paging = PageRequest.of(pageNumber, pageSize, Sort.by(sortBy).descending());

        Page<Post> pagedResult = repository.findAll(paging);

        if(pagedResult.hasContent()){
            return pagedResult.getContent();
        }else{
            return new ArrayList<>();
        }
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
