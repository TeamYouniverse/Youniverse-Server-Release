package server.youniverse.domain.entity;


import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.time.LocalDateTime;

import org.springframework.data.jpa.domain.support.AuditingEntityListener;
import server.youniverse.domain.entity.Planet;

@Entity
@Getter
@NoArgsConstructor
@AllArgsConstructor
@EntityListeners(AuditingEntityListener.class)
public class Post {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="post_id")
    private Long id;

    private String nickname;
    private String contents;

    @Enumerated(EnumType.STRING)
    private Planet emotion;
    private boolean active;

    @CreatedDate
    private LocalDateTime createdDate;
    @LastModifiedDate
    private LocalDateTime updatedDate;

}
