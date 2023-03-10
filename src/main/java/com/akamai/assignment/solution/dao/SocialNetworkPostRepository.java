package com.akamai.assignment.solution.dao;

import com.akamai.assignment.solution.model.SocialNetworkPost;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SocialNetworkPostRepository extends JpaRepository<SocialNetworkPost, Long> {
}
