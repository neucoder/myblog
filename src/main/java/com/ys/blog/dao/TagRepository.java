package com.ys.blog.dao;

import com.ys.blog.po.Tag;
import com.ys.blog.po.Type;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TagRepository extends JpaRepository<Tag, Long> {

    Tag findByName(String name);
}

