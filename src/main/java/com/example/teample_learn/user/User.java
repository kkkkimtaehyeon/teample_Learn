package com.example.teample_learn.user;

import com.example.teample_learn.resume.domain.Resume;
import com.example.teample_learn.teamplay.domain.BaseTime;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@Entity
public class User extends BaseTime {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id")
    private Long id;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "email", nullable = false)
    private String email;

    @Column(name = "picture", nullable = true)
    private String picture;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Role role;

    /*@OneToOne
    @JoinColumn(name = "resume_id")
    private Resume resume;*/

    @OneToOne(mappedBy = "user")
    public Resume resume;

    @Builder
    public User(String name, String email, String picture, Role role, Resume resume) {
        this.name = name;
        this.email = email;
        this.picture = picture;
        this.role = role;
        this.resume = resume;
    }

    public User update(String name, String picture) {
        this.name = name;
        this.picture = picture;
        return this;
    }
    public void updateRoleCertificated() {
        this.role = Role.CERTIFICATED;
    }

    public void updateResume(Resume resume) {
        this.resume = resume;
    }

    public String getRoleKey() {
        return this.role.getKey();
    }
}
