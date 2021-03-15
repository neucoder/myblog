package com.ys.blog.web;

import com.ys.blog.service.BlogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class AboutAhowController {

    @Autowired
    private BlogService blogService;
    @GetMapping("/about")
    public String about(Model model){

        model.addAttribute("newblogs", blogService.listRecommendBlogTop(3));
        return "about";
    }
}
