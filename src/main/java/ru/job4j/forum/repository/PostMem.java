package ru.job4j.forum.repository;

import org.springframework.stereotype.Repository;
import ru.job4j.forum.model.Comment;
import ru.job4j.forum.model.Post;
import ru.job4j.forum.model.User;

import java.time.LocalDateTime;
import java.util.*;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.IntStream;

@Repository
public class PostMem {
    private final Map<Integer, Post> posts = new HashMap<>();
    private final AtomicInteger currentPostId = new AtomicInteger(0);
    private final Map<Integer, Comment> comments = new HashMap<>();
    private final AtomicInteger currentCommentId = new AtomicInteger(0);

    public PostMem() {
        User user1 = new User();
        user1.setUsername("user1");
        IntStream.rangeClosed(1, 10)
                .forEach(i -> {
                    int id = currentPostId.incrementAndGet();
                    Post post = new Post();
                    post.setName("Name " + i);
                    post.setId(id);
                    post.setDesc("Description " + i);
                    post.setCreated(LocalDateTime.of(2000 + i, i, 1 + i, i, i));
                    post.setAuthor(user1);
                    posts.put(id, post);
                });
    }

    public Collection<Post> findAll() {
        return posts.values();
    }

    public Post save(Post post) {
        if (post.getId() == 0) {
            post.setId(currentPostId.incrementAndGet());
        }
        posts.put(post.getId(), post);
        return post;
    }

    public Optional<Post> findById(int id) {
        return Optional.ofNullable(posts.get(id));
    }

    public Comment save(Comment comment) {
        int commentId = currentCommentId.incrementAndGet();
        comment.setId(commentId);
        findById(comment.getPost().getId()).get().addComment(comment);
        comments.put(commentId, comment);
        return comment;
    }
}
