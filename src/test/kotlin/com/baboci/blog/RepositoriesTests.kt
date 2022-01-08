package com.baboci.blog

import com.baboci.blog.model.Article
import com.baboci.blog.model.User
import com.baboci.blog.repository.ArticleRepository
import com.baboci.blog.repository.UserRepository
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager
import org.springframework.data.repository.findByIdOrNull

@DataJpaTest
class RepositoriesTests @Autowired constructor(
        val entityManager: TestEntityManager,
        val userRepository: UserRepository,
        val articleRepository: ArticleRepository
){
    @Test
    fun `When findByIdOrNull then return Article`() {
        val tahir = User("tbaboci", "Tahir", "Baboci")
        entityManager.persist(tahir)
        val article = Article("Spring Framework 5.0 goes GA", "Dear Spring comunity...", "Lorem ipsum", tahir)
        entityManager.persist(article)
        entityManager.flush()
        val found = articleRepository.findByIdOrNull(id = article.id!!)
        assertThat(found).isEqualTo(article)
    }

    @Test
    fun `When findByLogin then return User`() {
        val tahir = User("tbaboci", "Tahir", "Baboci")
        entityManager.persist(tahir)
        entityManager.flush()
        val user = userRepository.findByLogin(tahir.login)
        assertThat(user).isEqualTo(tahir)
    }
}