package com.article.app

import androidx.arch.core.executor.TaskExecutor
import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.LifecycleRegistry
import androidx.lifecycle.Observer
import com.article.app.model.Article
import com.article.app.netio.RetrofitInterface
import com.article.app.ui.activities.ArticleList
import com.article.app.vm.ArticleListViewModel
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import junit.framework.Assert.assertNotNull
import junit.framework.Assert.assertTrue
import org.junit.*
import org.junit.runner.RunWith
import org.junit.runners.JUnit4
import org.mockito.Mock
import org.mockito.Mockito.`when`
import org.mockito.Mockito.verify
import org.mockito.MockitoAnnotations


/**
 * @author vikas.grover
 * Article List View Model Test
 */
@RunWith(JUnit4::class)
class ArticleListViewModelTest {

    @Rule
    @JvmField
    val rule = InstantTaskExecutorRule()

    @Mock
    lateinit var retrofitInterface: RetrofitInterface

    lateinit var articleListViewModel: ArticleListViewModel

    @Mock
    lateinit var observer: Observer<List<Article>>

    @Mock
    lateinit var lifecycleOwner: LifecycleOwner

    lateinit var lifecycle: Lifecycle

    @Mock
    val article = Article()


    lateinit var list: List<Article>


    companion object {
        @ClassRule
        @JvmField
        val schedulers = RxImmediateSchedulerRule()
    }


    @Before
    fun setup() {
        MockitoAnnotations.initMocks(this)
        lifecycle = LifecycleRegistry(lifecycleOwner)
        articleListViewModel = ArticleListViewModel()
        articleListViewModel.setAppServiceInterface(
            appService = retrofitInterface,
            schedulers = AndroidSchedulers.mainThread()
        )
        articleListViewModel.mutablePostList.observeForever(observer)
        list = listOf(article)
    }

    @Test
    fun testNull() {
        `when`<Any?>(articleListViewModel.getAppServiceInterface().articles()).thenReturn(Observable.just(list))
        assertNotNull(articleListViewModel.articleList())
        assertTrue(articleListViewModel.mutablePostList.hasActiveObservers())
    }

    @Test
    fun testApiFetchDataSuccess() {
        `when`(articleListViewModel.getAppServiceInterface().articles()).thenReturn(Observable.just(list))
        articleListViewModel.articleList()
        verify(observer).onChanged(list)
    }


    @Test
    fun testApiFetchDataError() {
        `when`<Any>(articleListViewModel.getAppServiceInterface().articles()).thenReturn(Observable.just(null))
        articleListViewModel.articleList()
        verify(observer).onChanged(null)
    }
}
