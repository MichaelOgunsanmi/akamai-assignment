package com.akamai.assignment.solution.model;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;

@Entity
@Table(name = "SocialNetworkPosts")
@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
@ToString
public class SocialNetworkPost {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @CreationTimestamp
    private LocalDateTime postDate;
    private String author;

    private String content;
    private Long viewCount;

    @PrePersist
    private void prePersist() {
        viewCount = 0L;
    }
}
