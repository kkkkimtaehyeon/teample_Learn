package com.example.teample_learn.resume.domain;

import com.example.teample_learn.resume.dto.ResumeResponseDto;
import com.example.teample_learn.resume.dto.ResumeUpdateRequestDto;
import com.example.teample_learn.user.User;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Getter
@Entity
@Table(name = "resume")
public class Resume {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "resume_id")
    private Long id;

    @Column(name = "real_name")
    private String realName;

    @Column(name = "phone")
    private String phone;

    @Column(name = "email")
    private String email;

    @Column(name = "location")
    private String location;

    @Column(name = "work")
    private String work;

    @Column(name = "portfolios")
    private String portfolios;

    @Column(name = "skills")
    private String skills;

    @Column(name = "title")
    private String title;

    @Column(name = "content")
    private String content;

    /*@OneToOne(mappedBy = "resume")
    private User user;*/

    @OneToOne
    @JoinColumn(name = "user_id")
    private User user;

    @Builder
    public Resume(User user, String realName, String title, String content, String phone, String email, String location, String work, String portfolios, String skills) {
        this.user = user;
        this.realName = realName;
        this.title = title;
        this.content = content;
        this.phone = phone;
        this.email = email;
        this.location = location;
        this.work = work;
        this.portfolios = portfolios;
        this.skills = skills;
    }

    public ResumeResponseDto toDto() {
        return new ResumeResponseDto(this);
    }

    public void update(ResumeUpdateRequestDto requestDto) {
        this.realName = requestDto.getRealName();
        this.title = requestDto.getTitle();;
        this.content = requestDto.getContent();
        this.phone = requestDto.getPhone();
        this.email = requestDto.getEmail();
        this.location = requestDto.getLocation();
        this.work= requestDto.getWork();
        this.portfolios = requestDto.getPortfolios();
        this.skills = requestDto.getSkills();
    }

}
