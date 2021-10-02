package ru.job4j.forum.service;

import org.springframework.stereotype.Service;
import ru.job4j.forum.model.Comment;
import ru.job4j.forum.model.Post;
import ru.job4j.forum.repository.CommentRepository;
import ru.job4j.forum.repository.PostRepository;
import ru.job4j.forum.repository.UserRepository;

import java.time.LocalDateTime;
import java.util.*;
import java.util.stream.Collectors;

@Service
public class PostService {
    private final PostRepository postRepository;
    private final UserRepository userRepository;
    private final CommentRepository commentRepository;

    public PostService(PostRepository postRepository,
                       UserRepository userRepository,
                       CommentRepository commentRepository) {
        this.postRepository = postRepository;
        this.userRepository = userRepository;
        this.commentRepository = commentRepository;
    }

    public Collection<Post> getAllPosts() {
        return ((Collection<Post>) postRepository.findAll())
                .stream()
                .sorted(Comparator.comparingInt(Post::getId))
                .collect(Collectors.toList());
    }

    public Post savePost(Post post, String username) {
        int postId = post.getId();
        if (postId == 0) {
            post.setCreated(LocalDateTime.now());
        } else {
            post.setCreated(findPostById(postId).get().getCreated());
        }
        post.setAuthor(userRepository.findByUsername(username));
        return postRepository.save(post);
    }

    public Optional<Post> findPostById(int id) {
        return postRepository.findById(id);
    }

    public Comment saveComment(Comment comment, String username, Post post) {
        comment.setUser(userRepository.findByUsername(username));
        comment.setCreated(LocalDateTime.now());
        comment.setPost(post);
        comment = commentRepository.save(comment);
        post.addComment(comment);
        return comment;
    }
}
