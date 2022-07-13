package com.hugo.desafio_backend.config;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import com.hugo.desafio_backend.entities.Post;
import com.hugo.desafio_backend.repositories.PostRepository;

@Configuration
@Profile("test")
public class TestConfig implements CommandLineRunner{
    
    @Autowired
    private PostRepository repository;

    @Override
    public void run(String... args) throws Exception {

        LocalDateTime now = LocalDateTime.now();
        
        Post post1 = new Post(null, "Bom dia", "Mensagem de bom dia", "Indo para a faculdade", now, now);
        Post post2 = new Post(null, "Bom dia", "Mensagem de bom dia", "Mais uma semana de trabalho pela frente", now.minusHours(3), now.minusHours(3));
        Post post3 = new Post(null, "Aff segunda", "Não aguento mais", "Dormi muito mal", now.minusHours(4), now.minusHours(4));
        Post post4 = new Post(null, "Mais uma semana", "Acordei mais ou menos", "Não sei", now, now);
        Post post5 = new Post(null, "Bom dia", "Mensagem de bom dia", "Estou motivado", now.minusHours(2), now.minusHours(2));
        repository.saveAll(Arrays.asList(post1,post2,post3,post4,post5));
    }
}
