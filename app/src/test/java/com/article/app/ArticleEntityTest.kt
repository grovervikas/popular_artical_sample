package com.article.app

import com.article.app.model.Article
import org.junit.After
import org.junit.Assert
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.MockitoAnnotations

/**
 * @author vikas.grover
 * Article Entity Test - Model Test
 */
@RunWith(JUnit4::class)
class ArticleEntityTest {

    val id: String = "1";
    val title: String = "Freedy Khambhata Is on long vacations";
    val thumbnail: String = "https:\\";
    val created_at: String = "5 December 2019";
    val updated_at: String = "5 December 2019";
    val about: String = "article about";
    val author: String = "Moorey Valian";

    @Mock
    lateinit var article: Article

    @Before
    fun setUp() {
        MockitoAnnotations.initMocks(this)
        Mockito.`when`(article.id).thenReturn(id)
        Mockito.`when`(article.title).thenReturn(title)
        Mockito.`when`(article.thumbnail).thenReturn(thumbnail)
        Mockito.`when`(article.created_at).thenReturn(created_at)
        Mockito.`when`(article.updated_at).thenReturn(updated_at)
        Mockito.`when`(article.about).thenReturn(about)
        Mockito.`when`(article.author).thenReturn(author)
    }

    @Test
    fun testArticleId() {
        Mockito.`when`<String>(article.id).thenReturn(id)
        Assert.assertEquals("1", article.id)
    }

    @Test
    fun testArticleTitle() {
        Mockito.`when`<String>(article.title).thenReturn(title)
        Assert.assertEquals("Freedy Khambhata Is on long vacations", article.title)
    }

    @Test
    fun testArticleThumbnail() {
        Mockito.`when`<String>(article.thumbnail).thenReturn(thumbnail)
        Assert.assertEquals("https:\\", article.thumbnail)
    }

    @Test
    fun testArticleCreateAt() {
        Mockito.`when`<String>(article.created_at).thenReturn(created_at)
        Assert.assertEquals("5 December 2019", article.created_at)
    }

    @Test
    fun testArticleUpdatedAt() {
        Mockito.`when`<String>(article.updated_at).thenReturn(updated_at)
        Assert.assertEquals("5 December 2019", article.updated_at)
    }

    @Test
    fun testArticleAbout() {
        Mockito.`when`<String>(article.about).thenReturn(about)
        Assert.assertEquals("article about", article.about)
    }

    @Test
    fun testArticleAuthor() {
        Mockito.`when`<String>(article.author).thenReturn(author)
        Assert.assertEquals("Moorey Valian", article.author)
    }


    @Test
    fun invalidTestArticleId() {
        Mockito.`when`<String>(article.id).thenReturn(id)
        Assert.assertEquals("", article.id)
    }

    @Test
    fun invalidArticleTitle() {
        Mockito.`when`<String>(article.title).thenReturn(title)
        Assert.assertEquals("", article.title)
    }

    @Test
    fun invalidArticleThumbnail() {
        Mockito.`when`<String>(article.thumbnail).thenReturn(thumbnail)
        Assert.assertEquals("", article.thumbnail)
    }

    @Test
    fun invalidArticleCreateAt() {
        Mockito.`when`<String>(article.created_at).thenReturn(created_at)
        Assert.assertEquals("", article.created_at)
    }

    @Test
    fun invalidArticleUpdatedAt() {
        Mockito.`when`<String>(article.updated_at).thenReturn(updated_at)
        Assert.assertEquals("", article.updated_at)
    }

    @Test
    fun invalidArticleAbout() {
        Mockito.`when`<String>(article.about).thenReturn(about)
        Assert.assertEquals("", article.about)
    }

    @Test
    fun invalidArticleAuthor() {
        Mockito.`when`<String>(article.author).thenReturn(author)
        Assert.assertEquals("", article.author)
    }

    @After
    @Throws(Exception::class)
    fun tearDown() {
        article = Article()
    }
}