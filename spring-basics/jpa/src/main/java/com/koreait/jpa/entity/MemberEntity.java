package com.koreait.jpa.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
@Table(name = "member")
public class MemberEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)  //자동증가, 유일한 값
    private Long id;

    @Column(nullable = false, unique = true)
    private String username;

    @Column(nullable = false, length = 100)
    private String password;

    @Column(nullable = false)
    private String name;

    public MemberEntity() {}

    public MemberEntity(String username, String password, String name) {
        this.username = username;
        this.password = password;
        this.name = name;
    }
}