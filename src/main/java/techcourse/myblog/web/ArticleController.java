package techcourse.myblog.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.view.RedirectView;
import techcourse.myblog.domain.Article;
import techcourse.myblog.domain.ArticleRepository;

@Controller
public class ArticleController {
    @Autowired
    private ArticleRepository articleRepository;

    @GetMapping("/")
    public String index(final Model model) {
        model.addAttribute("articles", articleRepository.findAll());
        return "index";
    }

    @GetMapping("/writing")
    public String writeArticle(final Model model) {
        model.addAttribute("url", "/articles");
        return "article-edit";
    }

    @ResponseBody
    @PostMapping("/articles")
    public RedirectView confirmWrite(final Article article) {
        articleRepository.save(article);
        return new RedirectView("/articles/" + article.getId());
    }

    @GetMapping("/articles/{articleId}")
    public String viewArticle(@PathVariable final long articleId, final Model model) {
        model.addAttribute("article", articleRepository.findById(articleId).get());
        return "article";
    }

    @GetMapping("/articles/{articleId}/edit")
    public String editArticle(@PathVariable final long articleId, final Model model) {
        model.addAttribute("article", articleRepository.findById(articleId).get());
        model.addAttribute("url", "/articles/" + articleId);
        return "article-edit";
    }

    @ResponseBody
    @PutMapping("/articles/{articleId}")
    public RedirectView confirmEdit(@PathVariable final int articleId, final Article article) {
        articleRepository.save(article.setId(articleId));
        return new RedirectView("/articles/" + articleId);
    }

    @ResponseBody
    @DeleteMapping("/articles/{articleId}")
    public RedirectView deleteArticle(@PathVariable final long articleId) {
        articleRepository.deleteById(articleId);
        return new RedirectView("/");
    }
}