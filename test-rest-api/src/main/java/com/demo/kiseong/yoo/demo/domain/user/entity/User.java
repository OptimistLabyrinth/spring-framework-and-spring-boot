package com.demo.kiseong.yoo.demo.domain.user.entity;

import com.demo.kiseong.yoo.demo.entity.CreatedAtAndUpdatedAt;
import jakarta.persistence.*;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
public class User extends CreatedAtAndUpdatedAt {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false)
    private String firstName;
    @Column(nullable = false)
    private String lastName;
    @Column(nullable = false, unique = true)
    private String email;

    public User(String firstName, String lastName, String email) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
    }

    public User(Long id, String firstName, String lastName, String email) {
        this(firstName, lastName, email);
        this.id = id;
    }

}
