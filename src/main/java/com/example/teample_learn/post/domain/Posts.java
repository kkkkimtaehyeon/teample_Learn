package com.example.teample_learn.post.domain;

import com.example.teample_learn.post.dto.PostUpdateRequestDto;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import java.util.List;
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

    @Column(nullable = false, name = "author")
    private String author;

    @Column(nullable = false, name = "title")
    private String title;

    @Column(nullable = false, name = "content")
    private String content;

    @Column(name = "view_count")
    @ColumnDefault("0")
    private int viewCount;

    @Column(name = "scrap_count")
    @ColumnDefault("0")
    private int scrapCount;

    @Column(nullable = false, name = "category")
    private String category;

    @Column(nullable = true, name = "required_skills")
    private String requiredSkills;

    @Column(nullable = true, name = "tags")
    private String tags;

    @Column(nullable = false, name = "contact")
    private Boolean contact;

    @Column(nullable = true, name = "class_name")
    private String className;

    @Column(nullable = true, name = "class_division")
    private int classDivision;


    @Builder
    public Posts(String author, String title, String content, String category, String requiredSkills, String tags, Boolean contact, String className, int classDivision) {
        this.author = author;
        this.title = title;
        this.content = content;
        this.category = category;
        this.requiredSkills = requiredSkills;
        this.tags = tags;
        this.contact = contact;
        this.className = className;
        this.classDivision = classDivision;
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
