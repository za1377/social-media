package com.webservice.restapiwebservice.post;

import com.webservice.restapiwebservice.user.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PostRepository extends JpaRepository<Post, Integer> {
}
