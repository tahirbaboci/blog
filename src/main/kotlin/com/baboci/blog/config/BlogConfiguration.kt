package com.baboci.blog.config

import com.baboci.blog.model.Article
import com.baboci.blog.model.User
import com.baboci.blog.repository.ArticleRepository
import com.baboci.blog.repository.UserRepository
import org.springframework.boot.ApplicationRunner
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration
class BlogConfiguration {
    @Bean
    fun databaseInitializer(userRepository: UserRepository,
                            articleRepository: ArticleRepository) = ApplicationRunner {

        val tahir = userRepository.save(User("tbaboci", "Tahir", "Baboci"))
        articleRepository.save(Article(
                title = "Reactor Bismuth is out",
                headline = "Lorem ipsum",
                content = "dolor sit amet",
                author = tahir
        ))
        articleRepository.save(Article(
                title = "Reactor Aluminium has landed",
                headline = "Lorem ipsum",
                content = "dolor sit amet",
                author = tahir
        ))
    }
}