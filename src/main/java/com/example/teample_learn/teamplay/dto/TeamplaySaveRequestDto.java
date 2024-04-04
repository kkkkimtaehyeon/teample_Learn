package com.example.teample_learn.teamplay.dto;

import com.example.teample_learn.teamplay.domain.Posts;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Data
public class TeamplaySaveRequestDto {

    String author;
    String title;
    String content;
    String tags;
    Boolean meeting;
    String major;
    String className;
    Integer classDivision;
    String deadline;
    String duration;
    Integer quota;
    String skills;
    String contact;

    @Builder
    public TeamplaySaveRequestDto(String author, String title, String content, String tags, Boolean meeting, String major, String className,
                                  Integer classDivision, String deadline, String duration, Integer quota, String skills, String contact) {
        this.author = author;
        this.title = title;
        this.content = content;
        this.tags = tags;
        this.meeting = meeting;
        this.major = major;
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
                .major(major)
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
