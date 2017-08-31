package com.example.mayman.bakingtestjson;


import android.support.test.espresso.ViewInteraction;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;
import android.test.suitebuilder.annotation.LargeTest;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.Espresso.pressBack;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.action.ViewActions.closeSoftKeyboard;
import static android.support.test.espresso.action.ViewActions.replaceText;
import static android.support.test.espresso.contrib.RecyclerViewActions.actionOnItemAtPosition;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withContentDescription;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withParent;
import static android.support.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.Matchers.allOf;

@LargeTest
@RunWith(AndroidJUnit4.class)
public class EspressoTest {

    @Rule
    public ActivityTestRule<MainActivity> mActivityTestRule = new ActivityTestRule<>(MainActivity.class);

    @Test
    public void espressoTest() {
        ViewInteraction recyclerView = onView(
                allOf(withId(R.id.main_recycler_id), isDisplayed()));
        recyclerView.perform(actionOnItemAtPosition(0, click()));

        ViewInteraction appCompatTextView = onView(
                allOf(withId(R.id.ingredients_id), withText("ingredients"), isDisplayed()));
        appCompatTextView.perform(replaceText("ingredientsGraham Cracker crumbs\t2\tCUP\nunsalted butter, melted\t6\tTBLSP\ngranulated sugar\t0.5\tCUP\nsalt\t1.5\tTSP\nvanilla\t5\tTBLSP\nNutella or other chocolate-hazelnut spread\t1\tK\nMascapone Cheese(room temperature)\t500\tG\nheavy cream(cold)\t1\tCUP\ncream cheese(softened)\t4\tOZ\n"), closeSoftKeyboard());

        ViewInteraction recyclerView2 = onView(
                allOf(withId(R.id.detail_recyclerView), isDisplayed()));
        recyclerView2.perform(actionOnItemAtPosition(1, click()));

        ViewInteraction appCompatButton = onView(
                allOf(withId(R.id.button2), withText("next"), isDisplayed()));
        appCompatButton.perform(click());

        // Added a sleep statement to match the app's execution delay.
        // The recommended way to handle such scenarios is to use Espresso idling resources:
        // https://google.github.io/android-testing-support-library/docs/espresso/idling-resource/index.html
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        ViewInteraction appCompatButton2 = onView(
                allOf(withId(R.id.button2), withText("next"), isDisplayed()));
        appCompatButton2.perform(click());

        // Added a sleep statement to match the app's execution delay.
        // The recommended way to handle such scenarios is to use Espresso idling resources:
        // https://google.github.io/android-testing-support-library/docs/espresso/idling-resource/index.html
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        ViewInteraction appCompatButton3 = onView(
                allOf(withId(R.id.button), withText("back"), isDisplayed()));
        appCompatButton3.perform(click());

        // Added a sleep statement to match the app's execution delay.
        // The recommended way to handle such scenarios is to use Espresso idling resources:
        // https://google.github.io/android-testing-support-library/docs/espresso/idling-resource/index.html
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        ViewInteraction appCompatImageButton = onView(
                allOf(withContentDescription("Navigate up"),
                        withParent(allOf(withId(R.id.action_bar),
                                withParent(withId(R.id.action_bar_container)))),
                        isDisplayed()));
        appCompatImageButton.perform(click());

        ViewInteraction recyclerView3 = onView(
                allOf(withId(R.id.main_recycler_id), isDisplayed()));
        recyclerView3.perform(actionOnItemAtPosition(2, click()));

        ViewInteraction appCompatTextView2 = onView(
                allOf(withId(R.id.ingredients_id), withText("ingredients"), isDisplayed()));
        appCompatTextView2.perform(replaceText("ingredientssifted cake flour\t400\tG\ngranulated sugar\t700\tG\nbaking powder\t4\tTSP\nsalt\t1.5\tTSP\nvanilla extract, divided\t2\tTBLSP\negg yolks\t8\tUNIT\nwhole milk\t323\tG\nunsalted butter, softened and cut into 1 in. cubes\t961\tG\negg whites\t6\tUNIT\nmelted and cooled bittersweet or semisweet chocolate\t283\tG\n"), closeSoftKeyboard());

        pressBack();

        pressBack();

        ViewInteraction recyclerView4 = onView(
                allOf(withId(R.id.main_recycler_id), isDisplayed()));
        recyclerView4.perform(actionOnItemAtPosition(1, click()));

        ViewInteraction appCompatTextView3 = onView(
                allOf(withId(R.id.ingredients_id), withText("ingredients"), isDisplayed()));
        appCompatTextView3.perform(replaceText("ingredientsBittersweet chocolate (60-70% cacao)\t350\tG\nunsalted butter\t226\tG\ngranulated sugar\t300\tG\nlight brown sugar\t100\tG\nlarge eggs\t5\tUNIT\nvanilla extract\t1\tTBLSP\nall purpose flour\t140\tG\ncocoa powder\t40\tG\nsalt\t1.5\tTSP\nsemisweet chocolate chips\t350\tG\n"), closeSoftKeyboard());

        pressBack();

        pressBack();

    }

}
