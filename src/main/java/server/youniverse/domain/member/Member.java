package server.youniverse.domain.member;

import lombok.*;
import server.youniverse.domain.common.AuditingTimeEntity;

import javax.persistence.*;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Member extends AuditingTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(nullable = false, length = 50)
    private String nickname;

    @Column(nullable = false)
    private String password;

    private Member(String nickname, String password) {
        this.nickname = nickname;
        this.password = password;
    }

    public static Member of(String nickname, String password) {
        return new Member(nickname, password);
    }
}