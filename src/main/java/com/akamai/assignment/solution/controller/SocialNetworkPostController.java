package com.akamai.assignment.solution.controller;

import com.akamai.assignment.solution.model.SocialNetworkPost;
import com.akamai.assignment.solution.service.SocialNetworkPostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/v1/posts")
public class SocialNetworkPostController {
    @Autowired
    private final SocialNetworkPostService socialNetworkPostService;

    public SocialNetworkPostController ( SocialNetworkPostService socialNetworkPostService ) {
        this.socialNetworkPostService = socialNetworkPostService;
    }

    @GetMapping
    public ResponseEntity<List<SocialNetworkPost>> getAllPosts() {
        try {
            List<SocialNetworkPost> posts = socialNetworkPostService.getAllPosts ();

            return new ResponseEntity<>(posts, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>( HttpStatus.INTERNAL_SERVER_ERROR );
        }
    }
    @GetMapping("{id}")
    public ResponseEntity<SocialNetworkPost> getPostById(@PathVariable Long id ) {
        try {
            Optional<SocialNetworkPost> post = socialNetworkPostService.getPostById ( id );

            return post
                    .map ( socialNetworkPost -> new ResponseEntity<> ( socialNetworkPost, HttpStatus.OK ) )
                    .orElseGet ( ( ) -> new ResponseEntity<> ( HttpStatus.NOT_FOUND ) );
        } catch (Exception e) {
            return new ResponseEntity<>( HttpStatus.INTERNAL_SERVER_ERROR );
        }


    }

    @GetMapping("/top-posts")
    public ResponseEntity<List<SocialNetworkPost>> getTopPostsByViewCount() {
        try {
            List<SocialNetworkPost> topPosts = socialNetworkPostService.getTopPostsByViewCount ();

            return new ResponseEntity<>(topPosts, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>( HttpStatus.INTERNAL_SERVER_ERROR );
        }
    }

    @PostMapping
    public ResponseEntity<SocialNetworkPost> createPost( @RequestBody SocialNetworkPost newSocialNetworkPost) {
        try {
            SocialNetworkPost createdPost = socialNetworkPostService.createPost (newSocialNetworkPost);

            return new ResponseEntity<>(createdPost, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>( HttpStatus.INTERNAL_SERVER_ERROR );
        }
    }

    @PutMapping("{id}")
    public ResponseEntity<SocialNetworkPost> updatePostById( @PathVariable Long id, @RequestBody SocialNetworkPost updatedSocialNetworkPost) {
        try {
            Optional<SocialNetworkPost> updatedPost = socialNetworkPostService.updatePostById ( id, updatedSocialNetworkPost );

            return updatedPost.map ( socialNetworkPost -> new ResponseEntity<> ( socialNetworkPost, HttpStatus.OK ) )
                    .orElseGet ( ( ) -> new ResponseEntity<>( HttpStatus.NOT_FOUND ) );
        } catch (Exception e) {
            return new ResponseEntity<>( HttpStatus.INTERNAL_SERVER_ERROR );
        }
    }

    @DeleteMapping("{id}")
    public ResponseEntity<Object> deletePostById( @PathVariable Long id) {
        try {
            socialNetworkPostService.deletePostById (id);

            return new ResponseEntity<> ( HttpStatus.NO_CONTENT );
        } catch (Exception e) {
            return new ResponseEntity<>( HttpStatus.INTERNAL_SERVER_ERROR );
        }

    }
}
