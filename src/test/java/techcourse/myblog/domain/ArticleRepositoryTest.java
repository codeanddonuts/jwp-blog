package techcourse.myblog.domain;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.assertj.core.api.Assertions.assertThat;

@ExtendWith(SpringExtension.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class ArticleRepositoryTest {
    String title = "제목";
    String coverUrl = "링크";
    String contents = "내용";

    @Autowired
    private ArticleRepository articleRepository;

    Article testArticle;

    @BeforeEach
    void setUp() {
        testArticle = new Article(title, coverUrl, contents);
    }

    @Test
    void save() {
        Article persistentArticle = (Article) articleRepository.save(testArticle);
        assertThat(persistentArticle).isEqualTo(testArticle);
        Article retrievedArticle = (Article) articleRepository.findById(persistentArticle.getId()).get();
        assertThat(retrievedArticle).isEqualTo(testArticle);
    }

    @Test
    void find() {
        Article persistentArticle = (Article) articleRepository.save(testArticle);
        assertThat(articleRepository.findById(persistentArticle.getId()).get()).isEqualTo(testArticle);
    }

//    @Test
//    void edit() {
//        String newTitle = "새 제목";
//        String newCoverUrl = "새 링크";
//        String newContents = "새 내용";
//        Article persistentArticle = articleRepository.save(testArticle);
//        articleRepository.edit(new Article(newTitle, newCoverUrl, newContents), persistentArticle.getId());
//        Article editedArticle = articleRepository.findById(persistentArticle.getId()).get();
//        assertThat(
//            editedArticle.getTitle() == newTitle
//                && editedArticle.getCoverUrl() == newCoverUrl
//                && editedArticle.getContents() == newContents
//        );
//    }
}