package com.example.teample_learn.post.dto;

import com.example.teample_learn.post.domain.Posts;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Getter
public class PostResponseDto {

    Long id;
    String author;
    String title;
    String content;
    String tags;
    Boolean meeting;
    String category;
    String className;
    int classDivision;
    String deadline;
    String duration;
    int quota;
    String skills;
    String contact;

    public PostResponseDto(Posts post) {
        this.id = post.getId();
        this.author = post.getAuthor();
        this.title = post.getTitle();
        this.content = post.getContent();
        this.tags = post.getTags();
        this.meeting = post.getMeeting();
        this.category = post.getCategory();
        this.className = post.getClassName();
        this.classDivision = post.getClassDivision();
        this.deadline = post.getDeadline();
        this.duration = post.getDuration();
        this.quota = post.getQuota();
        this.skills = post.getSkills();
        this.contact = post.getContact();
    }
}
