package com.example.teample_learn.post.dto;

import com.example.teample_learn.post.domain.Posts;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Data
public class PostSaveRequestDto {

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

    @Builder
    public PostSaveRequestDto(String author, String title, String content, String tags, Boolean meeting, String category, String className,
                 int classDivision, String deadline, String duration, int quota, String skills, String contact) {
        this.author = author;
        this.title = title;
        this.content = content;
        this.tags = tags;
        this.meeting = meeting;
        this.category = category;
        this.className = className;
        this.classDivision = classDivision;
        this.deadline = deadline;
        this.duration = duration;
        this.quota = quota;
        this.skills = skills;
        this.contact = contact;
    }

    public Posts toEntity() {
        return Posts.builder()
                .author(author)
                .title(title)
                .content(content)
                .tags(tags)
                .meeting(meeting)
                .category(category)
                .className(className)
                .classDivision(classDivision)
                .deadline(deadline)
                .duration(duration)
                .quota(quota)
                .skills(skills)
                .contact(contact)
                .build();
    }
}
