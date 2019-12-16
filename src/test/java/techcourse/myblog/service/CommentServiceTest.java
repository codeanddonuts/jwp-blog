package techcourse.myblog.service;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;
import techcourse.myblog.domain.article.Article;
import techcourse.myblog.domain.article.ArticleRepository;
import techcourse.myblog.domain.comment.Comment;
import techcourse.myblog.domain.comment.CommentRepository;
import techcourse.myblog.domain.user.User;
import techcourse.myblog.domain.user.UserRepository;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class CommentServiceTest {
    static final User user = new User("TEST", "test@test.com", "test1234");
    static final Article article = new Article(user, "Title", "Background", "Content");
    static final Comment comment = new Comment(article, user, "salkgjabske;fas");

    @Autowired
    private ArticleRepository articleRepository;
    @Autowired
    private CommentRepository commentRepository;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    CommentService commentService;


    @BeforeEach
    void setUp() {
        userRepository.save(user);
        articleRepository.save(article);
    }

    @AfterEach
    void tearDown() {
        userRepository.deleteAll();
        articleRepository.deleteAll();
    }

    @Test
    @Transactional
    void findTest() {
        Comment written = commentRepository.save(comment);
        assertThat(commentService.find(written.getId()).isPresent()).isTrue();

    }

    @Test
    @Transactional
    void writeTest() {
        Comment written = commentService.write(article, user, "ㅎㅇㅎㅇ");
        assertThat(commentRepository.findById(written.getId()).get().getContents()).isEqualTo("ㅎㅇㅎㅇ");
    }

    @Test
    @Transactional
    void updateTest() {
        Comment written = commentRepository.save(comment);
        commentService.update(written.getId(), "ㅎㅇㅎㅇ", user);
        assertThat(commentRepository.findById(written.getId()).get().getContents()).isEqualTo("ㅎㅇㅎㅇ");
    }

    @Test
    @Transactional
    void deleteTest() {
        Comment written = commentRepository.save(comment);
        commentService.delete(written.getId(), written.getAuthor());
        assertThat(commentRepository.findById(written.getId()).isPresent()).isFalse();

    }
}