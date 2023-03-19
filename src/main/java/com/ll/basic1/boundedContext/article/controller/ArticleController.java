package com.ll.basic1.boundedContext.article.controller;

import com.ll.basic1.base.rsData.RsData;
import com.ll.basic1.boundedContext.article.entity.Article;
import com.ll.basic1.boundedContext.article.repository.ArticleRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.time.LocalDateTime;

@Controller
@RequestMapping("/article")
@RequiredArgsConstructor // 필드 중에서 final 붙은것만 인자로 생성자를 생성
public class ArticleController {

    private final ArticleRepository articleRepository;

    @GetMapping("/write")
    @ResponseBody
    public RsData write(@RequestParam String title,
                        @RequestParam String body){
        // builder 패턴
        Article article = Article.builder()
                .createDate(LocalDateTime.now())
                .modifyDate(LocalDateTime.now())
                .title(title)
                .body(body).build();

        // 위 코드는 아래 코드와 비슷한 의미이다.
//        Article article1 = new Article(title, body);
//        article1.setTitle(title);
//        article1.setBody(body);

        articleRepository.save(article);

        return RsData.of("S-1", "1번 글이 생성되었습니다.", article);

    }
}
