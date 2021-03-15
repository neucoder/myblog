package com.ys.blog.web;


import com.ys.blog.NotFoundException;
import com.ys.blog.service.BlogService;
import com.ys.blog.service.TagService;
import com.ys.blog.service.TypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.jws.WebParam;

@Controller
public class IndexController {

    @Autowired
    private BlogService blogService;
    @Autowired
    private TypeService typeService;

    @Autowired
    private TagService tagService;

    @Transactional
    @GetMapping("/")
    public String index(@PageableDefault(size = 5, sort = {"updateTime"}, direction = Sort.Direction.DESC) Pageable pageable,
                        Model model) {
        model.addAttribute("page", blogService.listBlog(pageable));
        model.addAttribute("types", typeService.listTypeTop(6));
        model.addAttribute("tags", tagService.listTagTop(10));
        model.addAttribute("recommendBlogs", blogService.listRecommendBlogTop(8));
        model.addAttribute("newblogs", blogService.listRecommendBlogTop(3));
        return "index";
    }

    @GetMapping("/blog")
    public String blog(Model model){
        model.addAttribute("newblogs", blogService.listRecommendBlogTop(3));
        return "blog";
    }

    // search page
    @PostMapping("/search")
    public String search(@PageableDefault(size = 8, sort = {"updateTime"}, direction = Sort.Direction.DESC) Pageable pageable,
                         @RequestParam String query, Model model) {
        model.addAttribute("page", blogService.listBlog("%" + query + "%", pageable));
        model.addAttribute("query", query);
        model.addAttribute("newblogs", blogService.listRecommendBlogTop(3));
        return "search";
    }

    // blog page
    // @Transactional
    @GetMapping("/blog/{id}")
    public String blog(@PathVariable Long id, Model model) {

//        model.addAttribute("blog",blogService.getBlog(id));

        model.addAttribute("blog", blogService.getAndConvert(id));
        model.addAttribute("newblogs", blogService.listRecommendBlogTop(3));
        return "blog";
    }

//    @GetMapping("/footer/newblog")
//    public String newblogs(Model model){
//
//        model.addAttribute("newblogs", blogService.listRecommendBlogTop(3));
//
//        return "_fragments :: newblogList";
//    }


}
