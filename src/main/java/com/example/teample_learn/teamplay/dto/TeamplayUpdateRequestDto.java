package com.example.teample_learn.teamplay.dto;

import lombok.Builder;
import lombok.Data;

@Data
public class TeamplayUpdateRequestDto {

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
    public TeamplayUpdateRequestDto(String title, String content, String tags, Boolean meeting, String major, String className,
                                    Integer classDivision, String deadline, String duration, Integer quota, String skills, String contact) {
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
}
