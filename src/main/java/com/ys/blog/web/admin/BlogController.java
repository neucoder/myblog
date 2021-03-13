package com.ys.blog.web.admin;


import com.ys.blog.po.Blog;
import com.ys.blog.service.BlogService;
import com.ys.blog.service.TagService;
import com.ys.blog.service.TypeService;
import com.ys.blog.vo.BlogQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/admin")
public class BlogController {


    @Autowired
    private BlogService blogService;
    @Autowired
    private TypeService typeService;
    @Autowired
    private TagService tagService;

    // direct to admin blog page
    @GetMapping("/blogs")
    public String blogs(@PageableDefault(size = 5, sort = {"updateTime"},
            direction = Sort.Direction.DESC) Pageable pageable,
                        BlogQuery blog,
                        Model model) {
        model.addAttribute("types", typeService.listType());
        model.addAttribute("page", blogService.listBlog(pageable, blog));
        return "admin/blogs";
    }

//    @GetMapping("/blogs")
//    public String blogs(){
//        return "admin/blogs";
//    }


    // return a fragment (the list of blog) instead of the whole html page
    @PostMapping("/blogs/search")
    public String search(@PageableDefault(size = 5, sort = {"updateTime"}, direction = Sort.Direction.DESC) Pageable pageable,
                         BlogQuery blog, Model model) {
        model.addAttribute("page", blogService.listBlog(pageable, blog));
        // System.out.println("------------blog search--------" + blog.getTitle());
        return "admin/blogs :: blogList";
    }
}
