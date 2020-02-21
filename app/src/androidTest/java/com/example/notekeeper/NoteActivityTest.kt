package com.example.notekeeper

import androidx.test.espresso.Espresso
import androidx.test.espresso.action.ViewActions
import androidx.test.espresso.assertion.ViewAssertions
import androidx.test.espresso.matcher.ViewMatchers
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.rule.ActivityTestRule
import com.example.notekeeper.activities.NoteListActivity
import com.example.notekeeper.models.Note
import com.example.notekeeper.services.DataManager
import junit.framework.Assert.assertEquals
import org.hamcrest.Matchers
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class NoteActivityTest {

    @JvmField
    @Rule // tells the test system this rule exists
    val noteListActivity = ActivityTestRule(NoteListActivity::class.java) // starts the activity

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
        assertEquals(title, note.title)
        assertEquals(text, note.text)
    }

    @Test
    fun shouldViewNote() {
        val note = testNotes[0]
        Espresso.onData(
            Matchers.allOf(
                Matchers.instanceOf(Note::class.java),
                Matchers.equalTo(note)
            )
        ).perform(ViewActions.click())

        Espresso.onView(ViewMatchers.withId(R.id.noteTitleText))
            .check(ViewAssertions.matches(ViewMatchers.withText(note.title)))
        Espresso.onView(ViewMatchers.withId(R.id.noteBodyText))
            .check(ViewAssertions.matches(ViewMatchers.withText(note.text)))
    }

    @Test
    fun shouldNavigateToNextNote() {
        Espresso.onData(
            Matchers.allOf(
                Matchers.instanceOf(Note::class.java),
                Matchers.equalTo(testNotes[0])
            )
        ).perform(ViewActions.click())

        Espresso.onView(
            Matchers.allOf(
                ViewMatchers.withId(R.id.action_next),
                ViewMatchers.isEnabled()
            )
        ).perform(ViewActions.click())

        Espresso.onView(ViewMatchers.withId(R.id.noteTitleText))
            .check(ViewAssertions.matches(ViewMatchers.withText(testNotes[1].title)))
        Espresso.onView(ViewMatchers.withId(R.id.noteBodyText))
            .check(ViewAssertions.matches(ViewMatchers.withText(testNotes[1].text)))
    }

    @Test
    fun shouldNavigateToPreviousNote() {
        Espresso.onData(
            Matchers.allOf(
                Matchers.instanceOf(Note::class.java),
                Matchers.equalTo(testNotes[1])
            )
        ).perform(ViewActions.click())

        Espresso.onView(
            Matchers.allOf(
                ViewMatchers.withId(R.id.action_back),
                ViewMatchers.isEnabled()
            )
        ).perform(ViewActions.click())

        Espresso.onView(ViewMatchers.withId(R.id.noteTitleText))
            .check(ViewAssertions.matches(ViewMatchers.withText(testNotes[0].title)))
        Espresso.onView(ViewMatchers.withId(R.id.noteBodyText))
            .check(ViewAssertions.matches(ViewMatchers.withText(testNotes[0].text)))

        Espresso.onView(ViewMatchers.withId(R.id.action_back))
            .check(ViewAssertions.matches(ViewMatchers.isEnabled()))
    }
}