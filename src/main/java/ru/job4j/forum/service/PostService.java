package ru.job4j.forum.service;

import org.springframework.stereotype.Service;
import ru.job4j.forum.model.Comment;
import ru.job4j.forum.model.Post;
import ru.job4j.forum.model.User;
import ru.job4j.forum.repository.CommentMem;
import ru.job4j.forum.repository.PostMem;

import java.time.LocalDateTime;
import java.util.*;

@Service
public class PostService {
    private final PostMem postRepository;
    private final CommentMem commentRepository;

    public PostService(PostMem postRepository, CommentMem commentRepository) {
        this.postRepository = postRepository;
        this.commentRepository = commentRepository;
    }

    public Collection<Post> getAllPosts() {
        return postRepository.findAll();
    }

    public Post savePost(Post post, String username) {
        if (post.getId() != 0) {
            Post existingPost = postRepository.findById(post.getId()).get();
            post.setCreated(existingPost.getCreated());
            post.setAuthor(existingPost.getAuthor());
        } else {
            post.setCreated(LocalDateTime.now());
            User user = new User();
            user.setUsername(username);
            post.setAuthor(user);
        }
        return postRepository.save(post);
    }

    public Optional<Post> findPostById(int id) {
        return postRepository.findById(id);
    }

    public List<Comment> findCommentsByPostId(int id) {
        return commentRepository.findCommentsByPostId(id);
    }

    public Comment saveComment(Comment comment, String username, Post post) {
        comment.setCreated(LocalDateTime.now());
        User user = new User();
        user.setUsername(username);
        comment.setUser(user);
        comment.setPost(post);
        return commentRepository.save(comment);
    }
}
