package ru.job4j.forum.repository;

import org.springframework.stereotype.Repository;
import ru.job4j.forum.model.Comment;
import ru.job4j.forum.model.User;

import java.util.*;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;

@Repository
public class CommentMem {
    private final Map<Integer, Comment> comments = new HashMap<>();
    private final AtomicInteger currentCommentId = new AtomicInteger(0);

    public CommentMem() {
        PostMem postMem = new PostMem();
        User user1 = new User();
        user1.setUsername("user1");
        User user2 = new User();
        user2.setUsername("user2");
        Comment comment1 = new Comment();
        comment1.setId(currentCommentId.incrementAndGet());
        comment1.setText("Text1");
        comment1.setPost(postMem.findById(3).get());
        comment1.setUser(user1);
        Comment comment2 = new Comment();
        comment2.setId(currentCommentId.incrementAndGet());
        comment2.setText("Text2");
        comment2.setPost(postMem.findById(3).get());
        comment2.setUser(user2);
        comments.put(1, comment1);
        comments.put(2, comment2);
    }

    public List<Comment> findCommentsByPostId(int id) {
        return comments.values()
                .stream()
                .filter(comment -> comment.getPost().getId() == id)
                .collect(Collectors.toList());
    }

    public Comment save(Comment comment) {
        int id = currentCommentId.incrementAndGet();
        comment.setId(id);
        comments.put(id, comment);
        return comment;
    }
}