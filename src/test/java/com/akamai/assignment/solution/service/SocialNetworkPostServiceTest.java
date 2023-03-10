package com.akamai.assignment.solution.service;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.any;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.akamai.assignment.solution.dao.SocialNetworkPostRepository;
import com.akamai.assignment.solution.model.SocialNetworkPost;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Disabled;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ContextConfiguration(classes = {SocialNetworkPostService.class})
@ExtendWith(SpringExtension.class)
class SocialNetworkPostServiceTest {
    @MockBean
    private SocialNetworkPostRepository socialNetworkPostRepository;

    @Autowired
    private SocialNetworkPostService socialNetworkPostService;

    /**
     * Method under test: {@link SocialNetworkPostService#getAllPosts()}
     */
    @Test
    void testGetAllPosts ( ) {
        ArrayList<SocialNetworkPost> socialNetworkPostList = new ArrayList<> ( );
        when ( socialNetworkPostRepository.findAll ( ) ).thenReturn ( socialNetworkPostList );
        List<SocialNetworkPost> actualAllPosts = socialNetworkPostService.getAllPosts ( );
        assertSame ( socialNetworkPostList, actualAllPosts );
        assertTrue ( actualAllPosts.isEmpty ( ) );
        verify ( socialNetworkPostRepository ).findAll ( );
    }

    /**
     * Method under test: {@link SocialNetworkPostService#getPostById(Long)}
     */
    @Test
    void testGetPostById ( ) {
        Optional<SocialNetworkPost> ofResult = Optional.of ( new SocialNetworkPost ( ) );
        when ( socialNetworkPostRepository.findById ( (Long) any ( ) ) ).thenReturn ( ofResult );
        Optional<SocialNetworkPost> actualPostById = socialNetworkPostService.getPostById ( 1L );
        assertSame ( ofResult, actualPostById );
        assertTrue ( actualPostById.isPresent ( ) );
        verify ( socialNetworkPostRepository ).findById ( (Long) any ( ) );
    }

    /**
     * Method under test: {@link SocialNetworkPostService#getTopPostsByViewCount()}
     */
    @Test
    void testGetTopPostsByViewCount ( ) {
        when ( socialNetworkPostRepository.findAll ( (Pageable) any ( ) ) ).thenReturn ( new PageImpl<> ( new ArrayList<> ( ) ) );
        assertTrue ( socialNetworkPostService.getTopPostsByViewCount ( ).isEmpty ( ) );
        verify ( socialNetworkPostRepository ).findAll ( (Pageable) any ( ) );
    }

    /**
     * Method under test: {@link SocialNetworkPostService#createPost(SocialNetworkPost)}
     */
    @Test
    void testCreatePost ( ) {
        SocialNetworkPost socialNetworkPost = new SocialNetworkPost ( );
        when ( socialNetworkPostRepository.save ( (SocialNetworkPost) any ( ) ) ).thenReturn ( socialNetworkPost );
        assertSame ( socialNetworkPost, socialNetworkPostService.createPost ( new SocialNetworkPost ( ) ) );
        verify ( socialNetworkPostRepository ).save ( (SocialNetworkPost) any ( ) );
    }

    /**
     * Method under test: {@link SocialNetworkPostService#updatePostById(Long, SocialNetworkPost)}
     */
    @Test
    void testUpdatePostById ( ) {
        when ( socialNetworkPostRepository.save ( (SocialNetworkPost) any ( ) ) ).thenReturn ( new SocialNetworkPost ( ) );
        when ( socialNetworkPostRepository.findById ( (Long) any ( ) ) ).thenReturn ( Optional.of ( new SocialNetworkPost ( ) ) );
        assertTrue ( socialNetworkPostService.updatePostById ( 1L, new SocialNetworkPost ( ) ).isPresent ( ) );
        verify ( socialNetworkPostRepository ).save ( (SocialNetworkPost) any ( ) );
        verify ( socialNetworkPostRepository ).findById ( (Long) any ( ) );
    }

    /**
     * Method under test: {@link SocialNetworkPostService#updatePostById(Long, SocialNetworkPost)}
     */
    @Test
    void testUpdatePostById3 ( ) {
        when ( socialNetworkPostRepository.save ( (SocialNetworkPost) any ( ) ) ).thenReturn ( new SocialNetworkPost ( ) );
        Optional<SocialNetworkPost> emptyResult = Optional.empty ( );
        when ( socialNetworkPostRepository.findById ( (Long) any ( ) ) ).thenReturn ( emptyResult );
        Optional<SocialNetworkPost> actualUpdatePostByIdResult = socialNetworkPostService.updatePostById ( 1L,
                new SocialNetworkPost ( ) );
        assertSame ( emptyResult, actualUpdatePostByIdResult );
        assertFalse ( actualUpdatePostByIdResult.isPresent ( ) );
        verify ( socialNetworkPostRepository ).findById ( (Long) any ( ) );
    }


    /**
     * Method under test: {@link SocialNetworkPostService#deletePostById(Long)}
     */
    @Test
    void testDeletePostById ( ) {
        doNothing ( ).when ( socialNetworkPostRepository ).deleteById ( (Long) any ( ) );
        socialNetworkPostService.deletePostById ( 1L );
        verify ( socialNetworkPostRepository ).deleteById ( (Long) any ( ) );
    }
}

