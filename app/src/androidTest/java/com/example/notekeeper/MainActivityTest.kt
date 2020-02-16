package com.example.notekeeper

import androidx.test.espresso.Espresso
import androidx.test.espresso.action.ViewActions
import androidx.test.espresso.assertion.ViewAssertions
import androidx.test.espresso.matcher.ViewMatchers
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.rule.ActivityTestRule
import com.example.notekeeper.activities.NoteListActivity
import org.hamcrest.Matchers
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class MainActivityTest {

    @JvmField
    @Rule // tells the test system this rule exists
    val noteListActivity = ActivityTestRule(NoteListActivity::class.java) // starts the activity

    @Test
    fun createNewNote() {
        val title = "title"
        val text = "text"

        Espresso.onView(ViewMatchers.withId(R.id.fab)).perform(ViewActions.click())

        val anyMatch = Matchers.anything()
        Espresso.onView(ViewMatchers.withId(R.id.noteBodyText)).check(ViewAssertions.matches(anyMatch))
        Espresso.onView(ViewMatchers.withId(R.id.noteTitleText)).check(ViewAssertions.matches(anyMatch))
        Espresso.onView(ViewMatchers.withId(R.id.fab)).check(ViewAssertions.doesNotExist())

        Espresso.onView(ViewMatchers.withId(R.id.noteTitleText)).perform(ViewActions.typeText(title))
        Espresso.onView(ViewMatchers.withId(R.id.noteBodyText)).perform(ViewActions.typeText(text))
    }
}