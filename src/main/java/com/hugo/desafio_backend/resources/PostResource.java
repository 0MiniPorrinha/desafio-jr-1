package com.hugo.desafio_backend.resources;

import java.net.URI;
import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.hugo.desafio_backend.entities.Post;
import com.hugo.desafio_backend.services.PostService;

@RestController
@RequestMapping(value = "/posts")
public class PostResource {
    
    @Autowired
    private PostService service;

    @GetMapping
    public ResponseEntity<List<Post>> findAll(){
        List<Post> list = service.findAll();
        return ResponseEntity.ok(list);
    }

    @GetMapping(value = "/ordered")
    public ResponseEntity<List<Post>> findAllByDate(){
        List<Post> list = service.findAllByDate();
        return ResponseEntity.ok(list);
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<Post> findById(@PathVariable Long id){
        Post post = service.findById(id);
        return ResponseEntity.ok(post);
    }

    @PostMapping
    public ResponseEntity<Post> insert(@RequestBody Post post){
        post.setCreatedAt(LocalDateTime.now());
        post.setUpdateAt(LocalDateTime.now());
        post = service.insert(post);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest()
            .path("/{id}").buildAndExpand(post.getId()).toUri();

        return ResponseEntity.created(uri).body(post);
    }
}
