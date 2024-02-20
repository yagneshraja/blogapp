package com.yagnesh.blogapp.articles;

import com.yagnesh.blogapp.articles.dtos.CreateArticleRequestDto;
import com.yagnesh.blogapp.articles.dtos.UpdateArticleRequestDto;
import com.yagnesh.blogapp.users.UsersRepository;
import com.yagnesh.blogapp.users.UsersService;
import lombok.*;
import org.springframework.stereotype.Service;

import static org.springframework.data.jpa.domain.AbstractPersistable_.id;

@Service
public class ArticleService {
    private ArticleRepository articleRepository;
    private UsersService usersService;
    public ArticleService(ArticleRepository articleRepository,UsersService usersService) {
        this.articleRepository = articleRepository;
        this.usersService = usersService;
    }


    public Iterable<Article> getAllArticles(){
        return articleRepository.findAll();
    }

    public Article getArticleBySlug(String slug){
        var article =  articleRepository.findBySlug(slug);
        if(article == null){
            throw new ArticleNotFoundException(slug);
        }
        return article;
    }

    public Article createArticle(CreateArticleRequestDto req,Long authorId){
        var author = usersService.getUserById(authorId);
        if(author == null){
            throw new UsersService.UserNotFoundException(authorId);
        }

        Article article = Article.builder()
                .title(req.getTitle())
                .body(req.getBody())
                .slug(req.getTitle().toLowerCase().replaceAll("\\s+","-"))
                .subtitle(req.getSubtitle())
                .author(author)
                .build();
        return new Article();

    }

    public Article updateArticle(Long articleId, UpdateArticleRequestDto req){
        var article = articleRepository.findById(articleId).orElseThrow(() ->new ArticleNotFoundException(articleId));

        if(req.getBody() != null){
            article.setBody(req.getBody());
            article.setSlug(req.getTitle().toLowerCase().replaceAll("\\s+","-"));
        }
        if(req.getTitle() != null){
            article.setBody(req.getBody());
        }
        if(req.getSubtitle() != null){
            article.setBody(req.getBody());
        }

        return articleRepository.save(article);

    }

    static class ArticleNotFoundException extends IllegalArgumentException {
        public ArticleNotFoundException(String slug) {
            super("Article " + slug + " Not Found");
        }

        public ArticleNotFoundException(Long articleId) {
            super("Article " + articleId + " Not Found");
        }
    }

}
