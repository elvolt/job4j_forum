package ru.job4j.forum.control;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.job4j.forum.exceptionhandling.NoSuchPostException;
import ru.job4j.forum.model.Comment;
import ru.job4j.forum.model.Post;
import ru.job4j.forum.service.PostService;

@Controller
public class PostControl {
    private final PostService service;

    public PostControl(PostService service) {
        this.service = service;
    }

    @ModelAttribute
    public void addUser(Model model) {
        model.addAttribute("user", SecurityContextHolder.getContext()
                .getAuthentication().getPrincipal());
    }

    @GetMapping("/create")
    public String create() {
        return "edit";
    }

    @GetMapping("/update")
    public String update(@RequestParam("id") int id, Model model) {
        Post post = service.findPostById(id)
                .orElseThrow(() -> new NoSuchPostException("Post with id " + id + " not found"));
        model.addAttribute("post", post);
        return "edit";
    }

    @PostMapping("/save")
    public String save(@ModelAttribute Post post, Model model) {
        int postId = post.getId();
        if (postId != 0 && service.findPostById(postId).isEmpty()) {
           throw new NoSuchPostException("Post with id " + postId + " not found");
        }
        service.savePost(post, ((UserDetails) model.getAttribute("user")).getUsername());
        return "redirect:/";
    }

    @GetMapping("/{id}")
    public String getPost(@PathVariable("id") int id, Model model) {
        Post post = service.findPostById(id)
                .orElseThrow(() ->
                        new NoSuchPostException("Post with id " + id + " not found"));
        model.addAttribute("post", post);
        return "post";
    }

    @PostMapping("/create_comment")
    public String createComment(@ModelAttribute Comment comment,
                                @RequestParam("postId") int postId, Model model) {
        Post post = service.findPostById(postId)
                .orElseThrow(() ->
                        new NoSuchPostException("Post with id " + postId + " not found"));
        service.saveComment(comment,
                ((UserDetails) model.getAttribute("user")).getUsername(), post);
        return "redirect:/" + postId;
    }
}
