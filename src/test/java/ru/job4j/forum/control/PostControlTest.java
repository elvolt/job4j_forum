package ru.job4j.forum.control;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import ru.job4j.forum.Main;
import ru.job4j.forum.model.Post;
import ru.job4j.forum.service.PostService;

import java.util.Optional;

import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;
import static org.mockito.Mockito.verify;

@SpringBootTest(classes = Main.class)
@AutoConfigureMockMvc
public class PostControlTest {

    @MockBean
    private PostService service;

    @Autowired
    private MockMvc mockMvc;

    @Test
    @WithMockUser
    public void createShouldReturnEditPage() throws Exception {
        this.mockMvc.perform(get("/create"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(view().name("edit"));
    }

    @Test
    @WithMockUser
    public void updateShouldReturnEditPage() throws Exception {
        when(service.findPostById(1)).thenReturn(Optional.of(new Post()));
        this.mockMvc.perform(get("/update")
                .param("id", "1"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(view().name("edit"));
    }

    @Test
    @WithMockUser
    public void shouldReturnPostPage() throws Exception {
        when(service.findPostById(1)).thenReturn(Optional.of(new Post()));
        this.mockMvc.perform(get("/1"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(view().name("post"));
    }

    @Test
    @WithMockUser
    public void whenCreateNewPostThenReturnPostName() throws Exception {
        this.mockMvc.perform(post("/save")
                .param("name", "Post")
                .param("desc", "Post description"))
                .andDo(print())
                .andExpect(status().is3xxRedirection());
        ArgumentCaptor<Post> argument = ArgumentCaptor.forClass(Post.class);
        verify(service).savePost(argument.capture(), eq("user"));
        Assertions.assertEquals("Post", argument.getValue().getName());
    }

    @Test
    @WithMockUser
    public void serviceShouldUpdateTopic() throws Exception {
        when(service.findPostById(1)).thenReturn(Optional.of(new Post()));
        this.mockMvc.perform(post("/save")
                .param("id", "1")
                .param("name", "Post")
                .param("desc", "Post description"))
                .andDo(print())
                .andExpect(status().is3xxRedirection());
        ArgumentCaptor<Post> argument = ArgumentCaptor.forClass(Post.class);
        verify(service).savePost(argument.capture(), eq("user"));
        Assertions.assertEquals(1, argument.getValue().getId());
        Assertions.assertEquals("Post", argument.getValue().getName());
    }
}