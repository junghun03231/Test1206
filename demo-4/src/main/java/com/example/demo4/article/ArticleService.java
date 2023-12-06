package com.example.demo4.article;


import com.example.demo4.DataNotFoundException;
import com.example.demo4.user.SiteUser;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.security.Principal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ArticleService {

    private final ArticleRepository articleRepository;


    public List<Article> getList() {
        return this.articleRepository.findAll();
    }

    public void create(String subject, String content, SiteUser user) {
        Article article = new Article();
        article.setSubject(subject);
        article.setContent(content);
        article.setCreateDate(LocalDateTime.now());
        article.setAuthor(user);
        this.articleRepository.save(article);
    }

    public Article getArticle(Integer id) {
        Optional<Article> article = this.articleRepository.findById(id);
        if (article.isPresent()) {
            return article.get();
        }
        else {
            throw new DataNotFoundException("article not found");
        }
    }
    public void delete(Article article) {
        this.articleRepository.delete(article);
    }
}
