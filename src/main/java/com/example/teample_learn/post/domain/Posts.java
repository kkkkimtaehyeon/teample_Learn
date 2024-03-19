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

    @Column(name = "author_id")
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

    @Builder
    public Posts(String author, String title, String content, String category) {
        this.author = author;
        this.title = title;
        this.content = content;
        this.category = category;
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
