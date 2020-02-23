package com.example.notekeeper.activities

import androidx.test.espresso.Espresso
import androidx.test.espresso.action.ViewActions
import androidx.test.espresso.assertion.ViewAssertions
import androidx.test.espresso.contrib.DrawerActions
import androidx.test.espresso.contrib.NavigationViewActions
import androidx.test.espresso.contrib.RecyclerViewActions
import androidx.test.espresso.matcher.ViewMatchers
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.rule.ActivityTestRule
import com.example.notekeeper.R
import com.example.notekeeper.adapters.views.ViewHolder
import com.example.notekeeper.models.Note
import com.example.notekeeper.services.DataManager
import org.hamcrest.Matchers
import org.junit.Assert
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class ItemsActivityTest {

    @JvmField
    @Rule
    val activityTestRule = ActivityTestRule(ItemsActivity::class.java)

    var testNotes: List<Note> = emptyList()

    @Before
    fun setUp() {
        testNotes = DataManager.notes
    }

    @Test
    fun shouldCreateNote() {
        val title = "title"
        val text = "text"

        Espresso.onView(ViewMatchers.withId(R.id.fab)).perform(ViewActions.click())

        val anyMatch = Matchers.anything()
        Espresso.onView(ViewMatchers.withId(R.id.noteBodyText))
            .check(ViewAssertions.matches(anyMatch))
        Espresso.onView(ViewMatchers.withId(R.id.noteTitleText))
            .check(ViewAssertions.matches(anyMatch))

        Espresso.onView(ViewMatchers.withId(R.id.fab)).check(ViewAssertions.doesNotExist())

        Espresso.onView(ViewMatchers.withId(R.id.noteTitleText))
            .perform(ViewActions.typeText(title))
        Espresso.onView(ViewMatchers.withId(R.id.noteBodyText))
            .perform(ViewActions.typeText(text), ViewActions.closeSoftKeyboard())
        Espresso.pressBack()

        val note = testNotes.last()
        Assert.assertEquals(title, note.title)
        Assert.assertEquals(text, note.text)
    }

    @Test
    fun shouldSelectNote() {
        Espresso.onView(ViewMatchers.withId(R.id.drawer_layout)).perform(DrawerActions.open())
        Espresso.onView(ViewMatchers.withId(R.id.nav_view))
            .perform(NavigationViewActions.navigateTo(R.id.nav_notes))
        Espresso.onView(ViewMatchers.withId(R.id.listItems))
            .perform(RecyclerViewActions.actionOnItemAtPosition<ViewHolder>(0, ViewActions.click()))

        Espresso.onView(ViewMatchers.withId(R.id.noteTitleText))
            .check(ViewAssertions.matches(ViewMatchers.withText(DataManager.notes[0].title)))
        Espresso.onView(ViewMatchers.withId(R.id.noteBodyText))
            .check(ViewAssertions.matches(ViewMatchers.withText(DataManager.notes[0].text)))
    }
}