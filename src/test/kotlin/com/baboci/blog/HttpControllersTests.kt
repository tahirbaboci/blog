package com.baboci.blog

import com.baboci.blog.model.Article
import com.baboci.blog.model.User
import com.baboci.blog.repository.ArticleRepository
import com.baboci.blog.repository.UserRepository
import com.ninjasquad.springmockk.MockkBean
import io.mockk.every
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest
import org.springframework.http.MediaType
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.*

@WebMvcTest
class HttpControllersTests(@Autowired val mockMvc: MockMvc) {

    @MockkBean
    private lateinit var userRepository: UserRepository

    @MockkBean
    private lateinit var articleRepository: ArticleRepository

    @Test
    fun `list articles`() {
        val tahir = User("tbaboci", "Tahir", "Baboci")
        val spring5Article = Article("Spring Framework 5.0 goes GA", "Dear Spring community ...", "Lorem ipsum", tahir)
        val spring43Article = Article("Spring Framework 4.3 goes GA", "Dear Spring community ...", "Lorem ipsum", tahir)
        every { articleRepository.findAllByOrderByAddedAtDesc() } returns listOf(spring5Article, spring43Article)
        mockMvc.perform(get("/api/article/").accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk)
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("\$.[0].author.login").value(tahir.login))
                .andExpect(jsonPath("\$.[0].slug").value(spring5Article.slug))
                .andExpect(jsonPath("\$.[1].author.login").value(tahir.login))
                .andExpect(jsonPath("\$.[1].slug").value(spring43Article.slug))
    }

    @Test
    fun `List users`() {
        val tahir = User("tbaboci", "Tahir", "Baboci")
        val smaldini = User("smaldini", "Stéphane", "Maldini")
        every { userRepository.findAll() } returns listOf(tahir, smaldini)
        mockMvc.perform(get("/api/user/").accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk)
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("\$.[0].login").value(tahir.login))
                .andExpect(jsonPath("\$.[1].login").value(smaldini.login))
    }
 }