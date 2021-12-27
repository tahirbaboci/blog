package com.baboci.blog.model

import com.baboci.blog.until.toSlug
import java.time.LocalDateTime
import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.Id
import javax.persistence.ManyToOne


//TODO: Stopped the tutorial here
//https://spring.io/guides/tutorials/spring-boot-kotlin/

@Entity
class Article(
        var title: String,
        var headline: String,
        var content: String,
        @ManyToOne var author: User,
        var slug: String = title.toSlug(),
        var addedAt: LocalDateTime = LocalDateTime.now(),
        @Id @GeneratedValue var id: Long? = null)

@Entity
class User(
        var login: String,
        var firstname: String,
        var lastname: String,
        var description: String? = null,
        @Id @GeneratedValue var id: Long? = null)