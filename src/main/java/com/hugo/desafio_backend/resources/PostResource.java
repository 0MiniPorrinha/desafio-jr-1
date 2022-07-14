package com.hugo.desafio_backend.resources;

import java.net.URI;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.hugo.desafio_backend.entities.Post;
import com.hugo.desafio_backend.entities.dto.PostDTO;
import com.hugo.desafio_backend.services.PostService;

@RestController
@RequestMapping(value = "/posts")
public class PostResource {
    
    @Autowired
    private PostService service;

    @GetMapping
    public ResponseEntity<List<Post>> findAll(
        @RequestParam(defaultValue = "0") Integer pageNumber, 
        @RequestParam(defaultValue = "5") Integer pageSize,
        @RequestParam(defaultValue = "id") String sortBy){

        List<Post> list = service.findAll(pageNumber, pageSize, sortBy);
        
        return new ResponseEntity<>(list, new HttpHeaders(), HttpStatus.OK);
    }

    @GetMapping(value = "/data")
    public ResponseEntity<List<Post>> findAllDesc(
        @RequestParam(defaultValue = "0") Integer pageNumber, 
        @RequestParam(defaultValue = "5") Integer pageSize,
        @RequestParam(defaultValue = "asc") String sortByData){

        List<Post> list = service.findAllData(pageNumber, pageSize, sortByData);
        
        return new ResponseEntity<>(list, new HttpHeaders(), HttpStatus.OK);
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<Post> findById(@PathVariable Long id){
        Post post = service.findById(id);
        return ResponseEntity.ok(post);
    }

    @PostMapping
    public ResponseEntity<Post> insert(@RequestBody PostDTO postDTO){
        
        Post post = service.insert(postDTO);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest()
            .path("/{id}").buildAndExpand(post.getId()).toUri();

        return ResponseEntity.created(uri).body(post);
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id){
        service.delete(id);

        return ResponseEntity.noContent().build();
    }

    @PutMapping(value = "/{id}")
    public ResponseEntity<Void> update(@RequestBody PostDTO postDTO, @PathVariable Long id){
        service.update(id, postDTO);
        return ResponseEntity.noContent().build();
    }
}
