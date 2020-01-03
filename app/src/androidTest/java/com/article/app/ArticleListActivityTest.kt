package com.article.app

import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.contrib.RecyclerViewActions
import androidx.test.espresso.matcher.ViewMatchers
import androidx.test.espresso.matcher.ViewMatchers.isDisplayed
import androidx.test.espresso.matcher.ViewMatchers.withText
import androidx.test.ext.junit.rules.ActivityScenarioRule
import com.article.app.adapter.ArticleListAdapter
import com.article.app.ui.activities.ArticleList
import org.junit.Rule
import org.junit.Test


/**
 * @author vikas.grover
 * Article List Activity UI Test
 */
class ArticleListActivityTest {

    val cLIST_ITEM_POSITION_TO_SCROLL = 10

    @Rule
    @JvmField
    val rule = ActivityScenarioRule<ArticleList>(ArticleList::class.java)

    @Test
    fun scrollToItemBelowFold_checkItsText() {
        Thread.sleep(5000)
        onView(ViewMatchers.withId(R.id.list_articles))
            .perform(
                RecyclerViewActions.actionOnItemAtPosition<ArticleListAdapter.ArticleViewHolder>(
                    cLIST_ITEM_POSITION_TO_SCROLL,
                    click()
                )
            )
        val text = "The Year in Pictures 2019"
        onView(withText(text)).check(matches(isDisplayed()));
        Thread.sleep(5000)
    }

//    @Test
//    fun itemInMiddleOfList_hasSpecialText() { // First, scroll to the view holder using the isInTheMiddle matcher.
//        onView(ViewMatchers.withId(R.id.list_articles))
//            .perform(RecyclerViewActions.scrollToHolder(isInTheMiddle()))
//        // Check that the item has the special text.
//        val middleElementText = ""
//        onView(withText(middleElementText)).check(matches(isDisplayed()))
//    }
//
//    /**
//     * Matches the [CustomAdapter.ViewHolder]s in the middle of the list.
//     */
////    private fun isInTheMiddle(): Matcher<ArticleListAdapter.ArticleViewHolder?>? {
////        return object : TypeSafeMatcher<ArticleListAdapter.ArticleViewHolder?>() {
////            override fun matchesSafely(customHolder: ArticleListAdapter.ArticleViewHolder?): Boolean {
////                return customHolder.getIsInTheMiddle()
////            }
////
////            override fun describeTo(description: Description?) {
////                description!!.appendText("item in the middle")
////            }
////        }
////    }

}