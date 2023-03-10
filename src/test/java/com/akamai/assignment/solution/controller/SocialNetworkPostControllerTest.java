package com.akamai.assignment.solution.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.any;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.akamai.assignment.solution.dao.SocialNetworkPostRepository;
import com.akamai.assignment.solution.model.SocialNetworkPost;
import com.akamai.assignment.solution.service.SocialNetworkPostService;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;

class SocialNetworkPostControllerTest {
    /**
     * Method under test: {@link SocialNetworkPostController#getAllPosts()}
     */
    @Test
    void testGetAllPosts ( ) {
        SocialNetworkPostRepository socialNetworkPostRepository = mock ( SocialNetworkPostRepository.class );
        when ( socialNetworkPostRepository.findAll ( ) ).thenReturn ( new ArrayList<> ( ) );
        ResponseEntity<List<SocialNetworkPost>> actualAllPosts = (new SocialNetworkPostController (
                new SocialNetworkPostService ( socialNetworkPostRepository ) )).getAllPosts ( );
        assertTrue ( actualAllPosts.hasBody ( ) );
        assertEquals ( HttpStatusCode.valueOf ( 200 ), actualAllPosts.getStatusCode());
        assertTrue ( actualAllPosts.getHeaders ( ).isEmpty ( ) );
        verify ( socialNetworkPostRepository ).findAll ( );
    }

    /**
     * Method under test: {@link SocialNetworkPostController#getAllPosts()}
     */
    @Test
    void testGetAllPosts2 ( ) {
        ResponseEntity<List<SocialNetworkPost>> actualAllPosts = (new SocialNetworkPostController ( null )).getAllPosts ( );
        assertNull ( actualAllPosts.getBody ( ) );
        assertEquals ( HttpStatusCode.valueOf ( 500 ), actualAllPosts.getStatusCode());
        assertTrue ( actualAllPosts.getHeaders ( ).isEmpty ( ) );
    }

    /**
     * Method under test: {@link SocialNetworkPostController#getAllPosts()}
     */
    @Test
    void testGetAllPosts3 ( ) {
        SocialNetworkPostService socialNetworkPostService = mock ( SocialNetworkPostService.class );
        when ( socialNetworkPostService.getAllPosts ( ) ).thenReturn ( new ArrayList<> ( ) );
        ResponseEntity<List<SocialNetworkPost>> actualAllPosts = (new SocialNetworkPostController (
                socialNetworkPostService )).getAllPosts ( );
        assertTrue ( actualAllPosts.hasBody ( ) );
        assertEquals ( HttpStatusCode.valueOf ( 200) , actualAllPosts.getStatusCode ());
        assertTrue ( actualAllPosts.getHeaders ( ).isEmpty ( ) );
        verify ( socialNetworkPostService ).getAllPosts ( );
    }

    /**
     * Method under test: {@link SocialNetworkPostController#getPostById(Long)}
     */
    @Test
    void testGetPostById ( ) {
        SocialNetworkPostRepository socialNetworkPostRepository = mock ( SocialNetworkPostRepository.class );
        when ( socialNetworkPostRepository.findById ( any ( ) ) ).thenReturn ( Optional.of ( new SocialNetworkPost ( ) ) );
        ResponseEntity<SocialNetworkPost> actualPostById = (new SocialNetworkPostController (
                new SocialNetworkPostService ( socialNetworkPostRepository ) )).getPostById ( 1L );
        assertTrue ( actualPostById.hasBody ( ) );
        assertTrue ( actualPostById.getHeaders ( ).isEmpty ( ) );
        assertEquals ( HttpStatusCode.valueOf ( 200 ), actualPostById.getStatusCode());
        verify ( socialNetworkPostRepository ).findById ( any ( ) );
    }

    /**
     * Method under test: {@link SocialNetworkPostController#getPostById(Long)}
     */
    @Test
    void testGetPostById2 ( ) {
        SocialNetworkPostRepository socialNetworkPostRepository = mock ( SocialNetworkPostRepository.class );
        when ( socialNetworkPostRepository.findById ( any ( ) ) ).thenReturn ( Optional.empty ( ) );
        ResponseEntity<SocialNetworkPost> actualPostById = (new SocialNetworkPostController (
                new SocialNetworkPostService ( socialNetworkPostRepository ) )).getPostById ( 1L );
        assertNull ( actualPostById.getBody ( ) );
        assertEquals ( HttpStatusCode.valueOf ( 404 ), actualPostById.getStatusCode());
        assertTrue ( actualPostById.getHeaders ( ).isEmpty ( ) );
        verify ( socialNetworkPostRepository ).findById ( any ( ) );
    }

    /**
     * Method under test: {@link SocialNetworkPostController#getPostById(Long)}
     */
    @Test
    void testGetPostById3 ( ) {
        ResponseEntity<SocialNetworkPost> actualPostById = (new SocialNetworkPostController ( null )).getPostById ( 1L );
        assertNull ( actualPostById.getBody ( ) );
        assertEquals ( HttpStatusCode.valueOf ( 500 ), actualPostById.getStatusCode());
        assertTrue ( actualPostById.getHeaders ( ).isEmpty ( ) );
    }

    /**
     * Method under test: {@link SocialNetworkPostController#getPostById(Long)}
     */
    @Test
    void testGetPostById4 ( ) {
        SocialNetworkPostService socialNetworkPostService = mock ( SocialNetworkPostService.class );
        when ( socialNetworkPostService.getPostById ( any ( ) ) ).thenReturn ( Optional.of ( new SocialNetworkPost ( ) ) );
        ResponseEntity<SocialNetworkPost> actualPostById = (new SocialNetworkPostController ( socialNetworkPostService ))
                .getPostById ( 1L );
        assertTrue ( actualPostById.hasBody ( ) );
        assertTrue ( actualPostById.getHeaders ( ).isEmpty ( ) );
        assertEquals ( HttpStatusCode.valueOf ( 200 ), actualPostById.getStatusCode());
        verify ( socialNetworkPostService ).getPostById ( any ( ) );
    }

    /**
     * Method under test: {@link SocialNetworkPostController#getTopPostsByViewCount()}
     */
    @Test
    void testGetTopPostsByViewCount ( ) {
        SocialNetworkPostRepository socialNetworkPostRepository = mock ( SocialNetworkPostRepository.class );
        when ( socialNetworkPostRepository.findAll ( (Pageable) any ( ) ) ).thenReturn ( new PageImpl<> ( new ArrayList<> ( ) ) );
        ResponseEntity<List<SocialNetworkPost>> actualTopPostsByViewCount = (new SocialNetworkPostController (
                new SocialNetworkPostService ( socialNetworkPostRepository ) )).getTopPostsByViewCount ( );
        assertTrue ( actualTopPostsByViewCount.hasBody ( ) );
        assertEquals ( HttpStatusCode.valueOf ( 200 ), actualTopPostsByViewCount.getStatusCode());
        assertTrue ( actualTopPostsByViewCount.getHeaders ( ).isEmpty ( ) );
        verify ( socialNetworkPostRepository ).findAll ( (Pageable) any ( ) );
    }

    /**
     * Method under test: {@link SocialNetworkPostController#getTopPostsByViewCount()}
     */
    @Test
    void testGetTopPostsByViewCount2 ( ) {
        SocialNetworkPostRepository socialNetworkPostRepository = mock ( SocialNetworkPostRepository.class );
        when ( socialNetworkPostRepository.findAll ( (Pageable) any ( ) ) ).thenReturn ( null );
        ResponseEntity<List<SocialNetworkPost>> actualTopPostsByViewCount = (new SocialNetworkPostController (
                new SocialNetworkPostService ( socialNetworkPostRepository ) )).getTopPostsByViewCount ( );
        assertNull ( actualTopPostsByViewCount.getBody ( ) );
        assertEquals ( HttpStatusCode.valueOf ( 500 ), actualTopPostsByViewCount.getStatusCode());
        assertTrue ( actualTopPostsByViewCount.getHeaders ( ).isEmpty ( ) );
        verify ( socialNetworkPostRepository ).findAll ( (Pageable) any ( ) );
    }

    /**
     * Method under test: {@link SocialNetworkPostController#getTopPostsByViewCount()}
     */
    @Test
    void testGetTopPostsByViewCount3 ( ) {
        ResponseEntity<List<SocialNetworkPost>> actualTopPostsByViewCount = (new SocialNetworkPostController ( null ))
                .getTopPostsByViewCount ( );
        assertNull ( actualTopPostsByViewCount.getBody ( ) );
        assertEquals ( HttpStatusCode.valueOf ( 500 ), actualTopPostsByViewCount.getStatusCode());
        assertTrue ( actualTopPostsByViewCount.getHeaders ( ).isEmpty ( ) );
    }

    /**
     * Method under test: {@link SocialNetworkPostController#getTopPostsByViewCount()}
     */
    @Test
    void testGetTopPostsByViewCount4 ( ) {
        SocialNetworkPostService socialNetworkPostService = mock ( SocialNetworkPostService.class );
        when ( socialNetworkPostService.getTopPostsByViewCount ( ) ).thenReturn ( new ArrayList<> ( ) );
        ResponseEntity<List<SocialNetworkPost>> actualTopPostsByViewCount = (new SocialNetworkPostController (
                socialNetworkPostService )).getTopPostsByViewCount ( );
        assertTrue ( actualTopPostsByViewCount.hasBody ( ) );
        assertEquals ( HttpStatusCode.valueOf ( 200 ), actualTopPostsByViewCount.getStatusCode());
        assertTrue ( actualTopPostsByViewCount.getHeaders ( ).isEmpty ( ) );
        verify ( socialNetworkPostService ).getTopPostsByViewCount ( );
    }

    /**
     * Method under test: {@link SocialNetworkPostController#createPost(SocialNetworkPost)}
     */
    @Test
    void testCreatePost ( ) {
        SocialNetworkPostRepository socialNetworkPostRepository = mock ( SocialNetworkPostRepository.class );
        when ( socialNetworkPostRepository.save ( any ( ) ) ).thenReturn ( new SocialNetworkPost ( ) );
        SocialNetworkPostController socialNetworkPostController = new SocialNetworkPostController (
                new SocialNetworkPostService ( socialNetworkPostRepository ) );
        ResponseEntity<SocialNetworkPost> actualCreatePostResult = socialNetworkPostController
                .createPost ( new SocialNetworkPost ( ) );
        assertTrue ( actualCreatePostResult.hasBody ( ) );
        assertTrue ( actualCreatePostResult.getHeaders ( ).isEmpty ( ) );
        assertEquals ( HttpStatusCode.valueOf ( 201 ), actualCreatePostResult.getStatusCode());
        verify ( socialNetworkPostRepository ).save ( any ( ) );
    }

    /**
     * Method under test: {@link SocialNetworkPostController#createPost(SocialNetworkPost)}
     */
    @Test
    void testCreatePost2 ( ) {
        SocialNetworkPostController socialNetworkPostController = new SocialNetworkPostController ( null );
        ResponseEntity<SocialNetworkPost> actualCreatePostResult = socialNetworkPostController
                .createPost ( new SocialNetworkPost ( ) );
        assertNull ( actualCreatePostResult.getBody ( ) );
        assertEquals ( HttpStatusCode.valueOf ( 500 ), actualCreatePostResult.getStatusCode());
        assertTrue ( actualCreatePostResult.getHeaders ( ).isEmpty ( ) );
    }

    /**
     * Method under test: {@link SocialNetworkPostController#createPost(SocialNetworkPost)}
     */
    @Test
    void testCreatePost3 ( ) {
        SocialNetworkPostService socialNetworkPostService = mock ( SocialNetworkPostService.class );
        when ( socialNetworkPostService.createPost ( any ( ) ) ).thenReturn ( new SocialNetworkPost ( ) );
        SocialNetworkPostController socialNetworkPostController = new SocialNetworkPostController (
                socialNetworkPostService );
        ResponseEntity<SocialNetworkPost> actualCreatePostResult = socialNetworkPostController
                .createPost ( new SocialNetworkPost ( ) );
        assertTrue ( actualCreatePostResult.hasBody ( ) );
        assertTrue ( actualCreatePostResult.getHeaders ( ).isEmpty ( ) );
        assertEquals ( HttpStatusCode.valueOf ( 201 ), actualCreatePostResult.getStatusCode());
        verify ( socialNetworkPostService ).createPost ( any ( ) );
    }

    /**
     * Method under test: {@link SocialNetworkPostController#updatePostById(Long, SocialNetworkPost)}
     */
    @Test
    void testUpdatePostById ( ) {
        SocialNetworkPostRepository socialNetworkPostRepository = mock ( SocialNetworkPostRepository.class );
        when ( socialNetworkPostRepository.save ( any ( ) ) ).thenReturn ( new SocialNetworkPost ( ) );
        when ( socialNetworkPostRepository.findById ( any ( ) ) ).thenReturn ( Optional.of ( new SocialNetworkPost ( ) ) );
        SocialNetworkPostController socialNetworkPostController = new SocialNetworkPostController (
                new SocialNetworkPostService ( socialNetworkPostRepository ) );
        ResponseEntity<SocialNetworkPost> actualUpdatePostByIdResult = socialNetworkPostController.updatePostById ( 1L,
                new SocialNetworkPost ( ) );
        assertTrue ( actualUpdatePostByIdResult.hasBody ( ) );
        assertTrue ( actualUpdatePostByIdResult.getHeaders ( ).isEmpty ( ) );
        assertEquals ( HttpStatusCode.valueOf ( 200 ), actualUpdatePostByIdResult.getStatusCode());
        verify ( socialNetworkPostRepository ).save ( any ( ) );
        verify ( socialNetworkPostRepository ).findById ( any ( ) );
    }

    /**
     * Method under test: {@link SocialNetworkPostController#updatePostById(Long, SocialNetworkPost)}
     */
    @Test
    void testUpdatePostById2 ( ) {
        SocialNetworkPostRepository socialNetworkPostRepository = mock ( SocialNetworkPostRepository.class );
        when ( socialNetworkPostRepository.save ( any ( ) ) ).thenReturn ( null );
        when ( socialNetworkPostRepository.findById ( any ( ) ) ).thenReturn ( Optional.of ( new SocialNetworkPost ( ) ) );
        SocialNetworkPostController socialNetworkPostController = new SocialNetworkPostController (
                new SocialNetworkPostService ( socialNetworkPostRepository ) );
        ResponseEntity<SocialNetworkPost> actualUpdatePostByIdResult = socialNetworkPostController.updatePostById ( 1L,
                new SocialNetworkPost ( ) );
        assertNull ( actualUpdatePostByIdResult.getBody ( ) );
        assertEquals ( HttpStatusCode.valueOf ( 500 ), actualUpdatePostByIdResult.getStatusCode());
        assertTrue ( actualUpdatePostByIdResult.getHeaders ( ).isEmpty ( ) );
        verify ( socialNetworkPostRepository ).save ( any ( ) );
        verify ( socialNetworkPostRepository ).findById ( any ( ) );
    }

    /**
     * Method under test: {@link SocialNetworkPostController#updatePostById(Long, SocialNetworkPost)}
     */
    @Test
    void testUpdatePostById3 ( ) {
        SocialNetworkPostRepository socialNetworkPostRepository = mock ( SocialNetworkPostRepository.class );
        when ( socialNetworkPostRepository.save ( any ( ) ) ).thenReturn ( new SocialNetworkPost ( ) );
        when ( socialNetworkPostRepository.findById ( any ( ) ) ).thenReturn ( Optional.empty ( ) );
        SocialNetworkPostController socialNetworkPostController = new SocialNetworkPostController (
                new SocialNetworkPostService ( socialNetworkPostRepository ) );
        ResponseEntity<SocialNetworkPost> actualUpdatePostByIdResult = socialNetworkPostController.updatePostById ( 1L,
                new SocialNetworkPost ( ) );
        assertNull ( actualUpdatePostByIdResult.getBody ( ) );
        assertEquals ( HttpStatusCode.valueOf ( 404 ), actualUpdatePostByIdResult.getStatusCode());
        assertTrue ( actualUpdatePostByIdResult.getHeaders ( ).isEmpty ( ) );
        verify ( socialNetworkPostRepository ).findById ( any ( ) );
    }

    /**
     * Method under test: {@link SocialNetworkPostController#updatePostById(Long, SocialNetworkPost)}
     */
    @Test
    void testUpdatePostById4 ( ) {
        SocialNetworkPostController socialNetworkPostController = new SocialNetworkPostController ( null );
        ResponseEntity<SocialNetworkPost> actualUpdatePostByIdResult = socialNetworkPostController.updatePostById ( 1L,
                new SocialNetworkPost ( ) );
        assertNull ( actualUpdatePostByIdResult.getBody ( ) );
        assertEquals ( HttpStatusCode.valueOf ( 500 ), actualUpdatePostByIdResult.getStatusCode());
        assertTrue ( actualUpdatePostByIdResult.getHeaders ( ).isEmpty ( ) );
    }

    /**
     * Method under test: {@link SocialNetworkPostController#updatePostById(Long, SocialNetworkPost)}
     */
    @Test
    void testUpdatePostById5 ( ) {
        SocialNetworkPostService socialNetworkPostService = mock ( SocialNetworkPostService.class );
        when ( socialNetworkPostService.updatePostById ( any ( ), any ( ) ) )
                .thenReturn ( Optional.of ( new SocialNetworkPost ( ) ) );
        SocialNetworkPostController socialNetworkPostController = new SocialNetworkPostController (
                socialNetworkPostService );
        ResponseEntity<SocialNetworkPost> actualUpdatePostByIdResult = socialNetworkPostController.updatePostById ( 1L,
                new SocialNetworkPost ( ) );
        assertTrue ( actualUpdatePostByIdResult.hasBody ( ) );
        assertTrue ( actualUpdatePostByIdResult.getHeaders ( ).isEmpty ( ) );
        assertEquals ( HttpStatusCode.valueOf ( 200 ), actualUpdatePostByIdResult.getStatusCode());
        verify ( socialNetworkPostService ).updatePostById ( any ( ), any ( ) );
    }

    /**
     * Method under test: {@link SocialNetworkPostController#deletePostById(Long)}
     */
    @Test
    void testDeletePostById ( ) {
        SocialNetworkPostRepository socialNetworkPostRepository = mock ( SocialNetworkPostRepository.class );
        doNothing ( ).when ( socialNetworkPostRepository ).deleteById ( any ( ) );
        ResponseEntity<Object> actualDeletePostByIdResult = (new SocialNetworkPostController (
                new SocialNetworkPostService ( socialNetworkPostRepository ) )).deletePostById ( 1L );
        assertNull ( actualDeletePostByIdResult.getBody ( ) );
        assertEquals ( HttpStatusCode.valueOf ( 204 ), actualDeletePostByIdResult.getStatusCode());
        assertTrue ( actualDeletePostByIdResult.getHeaders ( ).isEmpty ( ) );
        verify ( socialNetworkPostRepository ).deleteById ( any ( ) );
    }

    /**
     * Method under test: {@link SocialNetworkPostController#deletePostById(Long)}
     */
    @Test
    void testDeletePostById2 ( ) {
        ResponseEntity<Object> actualDeletePostByIdResult = (new SocialNetworkPostController ( null )).deletePostById ( 1L );
        assertNull ( actualDeletePostByIdResult.getBody ( ) );
        assertEquals ( HttpStatusCode.valueOf ( 500 ), actualDeletePostByIdResult.getStatusCode());
        assertTrue ( actualDeletePostByIdResult.getHeaders ( ).isEmpty ( ) );
    }

    /**
     * Method under test: {@link SocialNetworkPostController#deletePostById(Long)}
     */
    @Test
    void testDeletePostById3 ( ) {
        SocialNetworkPostService socialNetworkPostService = mock ( SocialNetworkPostService.class );
        doNothing ( ).when ( socialNetworkPostService ).deletePostById ( (Long) any ( ) );
        ResponseEntity<Object> actualDeletePostByIdResult = (new SocialNetworkPostController ( socialNetworkPostService ))
                .deletePostById ( 1L );
        assertNull ( actualDeletePostByIdResult.getBody ( ) );
        assertEquals ( HttpStatusCode.valueOf ( 204 ), actualDeletePostByIdResult.getStatusCode());
        assertTrue ( actualDeletePostByIdResult.getHeaders ( ).isEmpty ( ) );
        verify ( socialNetworkPostService ).deletePostById ( (Long) any ( ) );
    }
}

