package com.cse5236.minesweeper;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withText;

import androidx.test.ext.junit.rules.ActivityScenarioRule;
import androidx.test.ext.junit.runners.AndroidJUnit4;
import androidx.test.filters.LargeTest;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

@RunWith(AndroidJUnit4.class)
@LargeTest
public class MainMenuInstrumentedTest {

    @Rule
    public ActivityScenarioRule<MainMenu> activityRule = new ActivityScenarioRule<>(MainMenu.class);

    /* Test if the activity layout of MainMenu is visible/displayed. */
    @Test
    public void MainMenuVisibilityTest() {
        onView(withId(R.id.layout_main_menu)).check(matches(isDisplayed()));
    }

    /* Test if the welcome text is correct */
    @Test
    public void WelcomeTextTest() {
        onView(withId(R.id.welcome)).check(matches(withText(R.string.welcome_to_minesweeper)));
    }

    /* Test if the buttons in MainMenu are visible */
    @Test
    public void MainMenuBtnVisibilityTest() {
        onView(withId(R.id.start_game_button)).check(matches(isDisplayed()));
        onView(withId(R.id.leader_board_button)).check(matches(isDisplayed()));
        onView(withId(R.id.settings_btn)).check(matches(isDisplayed()));
    }

    /* Test if clicking the start game button in MainMenu navigates to MainActivity */
    @Test
    public void StartBtnClickTest() {
        onView(withId(R.id.start_game_button)).perform(click()); // Perform click
        onView(withId(R.id.layout_main_activity)).check(matches(isDisplayed())); // MainActivity display
    }

}