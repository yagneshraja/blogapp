package com.yagnesh.blogapp.comments;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/articles/{slug}/comments")
public class CommentsController {

    @GetMapping
    public String getComments(@PathVariable("slug") String slug){
        return "comments";
    }
}
