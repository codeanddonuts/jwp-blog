package techcourse.myblog.domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.util.Optional;

@Entity
public class Article {
    private static final String defaultCoverUrl = "images/pages/index/study.jpg";

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private String title;
    private String coverUrl;
    private String contents;

    public Article(String title, String coverUrl, String contents) {
        this.title = title;
        this.coverUrl = validateCoverUrl(coverUrl);
        this.contents = contents;
    }

    public Article() {}

    public Article update(Article toUpdate) {
        this.title = toUpdate.title;
        this.coverUrl = validateCoverUrl(toUpdate.coverUrl);
        this.contents = toUpdate.contents;
        return this;
    }

    private String validateCoverUrl(String coverUrl) {
        return Optional.ofNullable(coverUrl)
                        .filter(x -> x.length() > 0)
                        .orElse(defaultCoverUrl);
    }

    public long getId() {
        return this.id;
    }

    public Article setId(long id) {
        this.id = id;
        return this;
    }

    public String getTitle() {
        return this.title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getCoverUrl() {
        return this.coverUrl;
    }

    public void setCoverUrl(String coverUrl) {
        this.coverUrl = validateCoverUrl(coverUrl);
    }

    public String getContents() {
        return this.contents;
    }

    public void setContents(String contents) {
        this.contents = contents;
    }
}