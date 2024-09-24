package com.blog.entity;

import com.blog.RoleType;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;

import java.sql.Timestamp;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name = "users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 100, unique = true)
    private String username;

    @Column(nullable = false, length = 100)
    private String password;

    @Column(nullable = false, length = 50)
    private String email;

    @CreationTimestamp
    private Timestamp createDate;

    @Enumerated(EnumType.STRING)
    private RoleType role;

    public User(String username, String password, String email) {

        this.username = username;
        this.password = password;
        this.email = email;
        this.role = RoleType.USER;
    }

    public void updatePassword(String password) {
        this.password = password;
    }
    public void updateEmail(String email) {
        this.email = email;
    }
}
