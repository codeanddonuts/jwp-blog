package techcourse.myblog.domain;

import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class ArticleRepository {
    private static final String defaultCoverUrl = "images/pages/index/study.jpg";

    private final List<Article> articles = new ArrayList<>();
    private int nextArticleNumber = 0;

    public Article find(final int articleId) {
        int index = Math.min(articleId, this.articles.size()) - 1;
        for (; index >= 0; index--) {
            Article target = this.articles.get(index);
            if (target.getNumber() == articleId) {
                return target;
            }
        }
        throw new NoArticleFoundException();
    }

    public Article write(final Article article) {
        if (!article.validateCoverUrl()) {
            article.setCoverUrl(defaultCoverUrl);
        }
        article.numbering(this);
        this.articles.add(article);
        return article;
    }

    public Article edit(final Article edited, final int articleId) {
        return find(articleId).update(edited);
    }

    public void delete(final int articleId) {
        this.articles.remove(find(articleId));
    }

    public List<Article> findAll() {
        return this.articles;
    }

    public int nextArticleNumber() {
        return ++nextArticleNumber;
    }
}