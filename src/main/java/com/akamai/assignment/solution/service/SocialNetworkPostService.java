package com.akamai.assignment.solution.service;

import com.akamai.assignment.solution.dao.SocialNetworkPostRepository;
import com.akamai.assignment.solution.model.SocialNetworkPost;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class SocialNetworkPostService {
    private final SocialNetworkPostRepository socialNetworkPostRepository;

    public SocialNetworkPostService ( SocialNetworkPostRepository socialNetworkPostRepository ) {
        this.socialNetworkPostRepository = socialNetworkPostRepository;
    }

    public List<SocialNetworkPost> getAllPosts() {
        return socialNetworkPostRepository.findAll();
    }

    public Optional<SocialNetworkPost> getPostById( Long id ) {
        return socialNetworkPostRepository.findById( id );
    }

    public List<SocialNetworkPost> getTopPostsByViewCount() {
        final int postsCount = 10;
        final String sortProperty = "viewCount";

        Page<SocialNetworkPost> page = socialNetworkPostRepository.findAll(
                PageRequest.of(0, postsCount, Sort.by(Sort.Direction.DESC, sortProperty)));

        return page.getContent();
    }



    public SocialNetworkPost createPost( SocialNetworkPost newSocialNetworkPost ) {
        return socialNetworkPostRepository.save( newSocialNetworkPost );
    }

    public Optional<SocialNetworkPost> updatePostById( Long id, SocialNetworkPost updatedSocialNetworkPost ) {
        Optional<SocialNetworkPost> oldPostData = socialNetworkPostRepository.findById(id);

        if (oldPostData.isEmpty ()) {
            return oldPostData;
        }

        SocialNetworkPost updatedPostObj = oldPostData.get();

        updatedPostObj.setAuthor (updatedSocialNetworkPost.getAuthor ());
        updatedPostObj.setContent(updatedSocialNetworkPost.getContent());
        updatedPostObj.setViewCount(updatedSocialNetworkPost.getViewCount());

        return  Optional.of (socialNetworkPostRepository.save(updatedPostObj));
    }

    public void deletePostById( Long id ) {
        socialNetworkPostRepository.deleteById (id);
    }
}
