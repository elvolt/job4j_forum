package ru.job4j.forum.service;

import org.springframework.stereotype.Service;
import ru.job4j.forum.model.Comment;
import ru.job4j.forum.model.Post;
import ru.job4j.forum.model.User;
import ru.job4j.forum.repository.PostMem;

import java.time.LocalDateTime;
import java.util.*;

@Service
public class PostService {
    private final PostMem repository;

    public PostService(PostMem repository) {
        this.repository = repository;
    }

    public Collection<Post> getAllPosts() {
        return repository.findAll();
    }

    public Post savePost(Post post, String username) {
        if (post.getId() != 0) {
            Post existingPost = repository.findById(post.getId()).get();
            post.setCreated(existingPost.getCreated());
            post.setAuthor(existingPost.getAuthor());
        } else {
            post.setCreated(LocalDateTime.now());
            User user = new User();
            user.setUsername(username);
            post.setAuthor(user);
        }
        return repository.save(post);
    }

    public Optional<Post> findPostById(int id) {
        return repository.findById(id);
    }

    public Comment saveComment(Comment comment, String username, Post post) {
        comment.setCreated(LocalDateTime.now());
        User user = new User();
        user.setUsername(username);
        comment.setUser(user);
        comment.setPost(post);
        return repository.save(comment);
    }
}
