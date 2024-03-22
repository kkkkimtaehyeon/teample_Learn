package com.example.teample_learn.post.domain;

import com.example.teample_learn.post.dto.PostUpdateRequestDto;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.ColumnDefault;
@NoArgsConstructor
@Getter
@Entity
public class Posts extends BaseTime{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = true, name = "author")
    private String author;

    @Column(nullable = false, name = "title")
    private String title;

    @Column(nullable = false, name = "content")
    private String content;

    @Column(nullable = true, name = "view_count")
    @ColumnDefault("0")
    private int viewCount;

    @Column(nullable = true, name = "scrap_count")
    @ColumnDefault("0")
    private int scrapCount;

    @Column(nullable = true, name = "tags")
    private String tags;

    @Column(nullable = false, name = "meeting")
    @ColumnDefault("0")
    private Boolean meeting;

    @Column(nullable = false, name = "category")
    private String category;

    @Column(nullable = true, name = "class_name")
    private String className;

    @Column(nullable = true, name = "class_division")
    private int classDivision;

    @Column(nullable = false, name = "deadline")
    private String deadline;

    @Column(nullable = false, name = "duration")
    private String duration;

    @Column(nullable = false, name = "quota")
    private int quota;

    @Column(nullable = true, name = "skills")
    private String skills;

    @Column(nullable = true, name = "contact")
    private String contact;








    @Builder
    public Posts(String author, String title, String content, String tags, Boolean meeting, String category, String className,
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

    public void update(PostUpdateRequestDto requestDto) {
        this.title = requestDto.getTitle();
        this.content = requestDto.getContent();
        this.category = requestDto.getCategory();
    }

    public void addViewCount() {
        viewCount++;
    }
    public void addScrapCount() {
        scrapCount++;
    }
    public void subScrapCount() {
        scrapCount--;
    }
}
