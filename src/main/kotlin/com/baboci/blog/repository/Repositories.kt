package com.baboci.blog.repository

import com.baboci.blog.model.Article
import com.baboci.blog.model.User
import org.springframework.data.repository.CrudRepository

interface ArticleRepository: CrudRepository<Article, Long> {
    fun findBySlug(slug: String): Article?
    fun findAllByOrderByAddedAtDesc():Iterable<Article>
}
interface UserRepository: CrudRepository<User, Long> {
    fun findByLogin(login: String):User?
}