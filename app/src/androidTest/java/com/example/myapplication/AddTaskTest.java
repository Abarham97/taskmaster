package com.example.myapplication;


import static androidx.test.espresso.Espresso.onData;
import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.action.ViewActions.closeSoftKeyboard;
import static androidx.test.espresso.action.ViewActions.pressImeActionButton;
import static androidx.test.espresso.action.ViewActions.replaceText;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withClassName;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.Matchers.allOf;
import static org.hamcrest.Matchers.anything;
import static org.hamcrest.Matchers.is;

import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;

import androidx.test.espresso.DataInteraction;
import androidx.test.espresso.ViewInteraction;
import androidx.test.ext.junit.rules.ActivityScenarioRule;
import androidx.test.ext.junit.runners.AndroidJUnit4;
import androidx.test.filters.LargeTest;

import org.hamcrest.Description;
import org.hamcrest.Matcher;
import org.hamcrest.TypeSafeMatcher;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

@LargeTest
@RunWith(AndroidJUnit4.class)
public class AddTaskTest {

    @Rule
    public ActivityScenarioRule<MainActivity> mActivityScenarioRule =
            new ActivityScenarioRule<>(MainActivity.class);

    @Test
    public void addTaskTest() {
        ViewInteraction materialButton = onView(
allOf(withId(R.id.ADD_TASK), withText("ADD TASK"),
childAtPosition(
childAtPosition(
withId(android.R.id.content),
0),
0),
isDisplayed()));
        materialButton.perform(click());
        
        ViewInteraction appCompatEditText = onView(
allOf(withId(R.id.Dosomthing), withText("Do Something"),
childAtPosition(
childAtPosition(
withId(android.R.id.content),
0),
4),
isDisplayed()));
        appCompatEditText.perform(click());
        
        ViewInteraction appCompatEditText2 = onView(
allOf(withId(R.id.Dosomthing), withText("Do Something"),
childAtPosition(
childAtPosition(
withId(android.R.id.content),
0),
4),
isDisplayed()));
        appCompatEditText2.perform(replaceText("Do Somethin2134324qqsda"));
        
        ViewInteraction appCompatEditText3 = onView(
allOf(withId(R.id.Dosomthing), withText("Do Somethin2134324qqsda"),
childAtPosition(
childAtPosition(
withId(android.R.id.content),
0),
4),
isDisplayed()));
        appCompatEditText3.perform(closeSoftKeyboard());
        
        ViewInteraction appCompatEditText4 = onView(
allOf(withId(R.id.Mytask), withText("My Task"),
childAtPosition(
childAtPosition(
withId(android.R.id.content),
0),
1),
isDisplayed()));
        appCompatEditText4.perform(click());
        
        ViewInteraction appCompatEditText5 = onView(
allOf(withId(R.id.Mytask), withText("My Task"),
childAtPosition(
childAtPosition(
withId(android.R.id.content),
0),
1),
isDisplayed()));
        appCompatEditText5.perform(click());
        
        ViewInteraction appCompatEditText6 = onView(
allOf(withId(R.id.Mytask), withText("My Task"),
childAtPosition(
childAtPosition(
withId(android.R.id.content),
0),
1),
isDisplayed()));
        appCompatEditText6.perform(replaceText("My Taskad"));
        
        ViewInteraction appCompatEditText7 = onView(
allOf(withId(R.id.Mytask), withText("My Taskad"),
childAtPosition(
childAtPosition(
withId(android.R.id.content),
0),
1),
isDisplayed()));
        appCompatEditText7.perform(closeSoftKeyboard());
        
        ViewInteraction appCompatEditText8 = onView(
allOf(withId(R.id.Mytask), withText("My Taskad"),
childAtPosition(
childAtPosition(
withId(android.R.id.content),
0),
1),
isDisplayed()));
        appCompatEditText8.perform(click());
        
        ViewInteraction appCompatEditText9 = onView(
allOf(withId(R.id.Mytask), withText("My Taskad"),
childAtPosition(
childAtPosition(
withId(android.R.id.content),
0),
1),
isDisplayed()));
        appCompatEditText9.perform(replaceText("My Taskadas"));
        
        ViewInteraction appCompatEditText10 = onView(
allOf(withId(R.id.Mytask), withText("My Taskadas"),
childAtPosition(
childAtPosition(
withId(android.R.id.content),
0),
1),
isDisplayed()));
        appCompatEditText10.perform(closeSoftKeyboard());
        
        ViewInteraction appCompatEditText11 = onView(
allOf(withId(R.id.Mytask), withText("My Taskadas"),
childAtPosition(
childAtPosition(
withId(android.R.id.content),
0),
1),
isDisplayed()));
        appCompatEditText11.perform(click());
        
        ViewInteraction appCompatEditText12 = onView(
allOf(withId(R.id.Mytask), withText("My Taskadas"),
childAtPosition(
childAtPosition(
withId(android.R.id.content),
0),
1),
isDisplayed()));
        appCompatEditText12.perform(click());
        
        ViewInteraction appCompatEditText13 = onView(
allOf(withId(R.id.Mytask), withText("My Taskadas"),
childAtPosition(
childAtPosition(
withId(android.R.id.content),
0),
1),
isDisplayed()));
        appCompatEditText13.perform(click());
        
        ViewInteraction appCompatEditText14 = onView(
allOf(withId(R.id.Mytask), withText("My Taskadas"),
childAtPosition(
childAtPosition(
withId(android.R.id.content),
0),
1),
isDisplayed()));
        appCompatEditText14.perform(replaceText("My Taskadasdasd"));
        
        ViewInteraction appCompatEditText15 = onView(
allOf(withId(R.id.Mytask), withText("My Taskadasdasd"),
childAtPosition(
childAtPosition(
withId(android.R.id.content),
0),
1),
isDisplayed()));
        appCompatEditText15.perform(closeSoftKeyboard());
        
        ViewInteraction appCompatEditText16 = onView(
allOf(withId(R.id.Mytask), withText("My Taskadasdasd"),
childAtPosition(
childAtPosition(
withId(android.R.id.content),
0),
1),
isDisplayed()));
        appCompatEditText16.perform(click());
        
        ViewInteraction appCompatEditText17 = onView(
allOf(withId(R.id.Mytask), withText("My Taskadasdasd"),
childAtPosition(
childAtPosition(
withId(android.R.id.content),
0),
1),
isDisplayed()));
        appCompatEditText17.perform(replaceText("My Taskadasdasdasda"));
        
        ViewInteraction appCompatEditText18 = onView(
allOf(withId(R.id.Mytask), withText("My Taskadasdasdasda"),
childAtPosition(
childAtPosition(
withId(android.R.id.content),
0),
1),
isDisplayed()));
        appCompatEditText18.perform(closeSoftKeyboard());
        
        ViewInteraction appCompatEditText19 = onView(
allOf(withId(R.id.Mytask), withText("My Taskadasdasdasda"),
childAtPosition(
childAtPosition(
withId(android.R.id.content),
0),
1),
isDisplayed()));
        appCompatEditText19.perform(pressImeActionButton());
        
        ViewInteraction appCompatEditText20 = onView(
allOf(withId(R.id.Mytask), withText("My Taskadasdasdasda"),
childAtPosition(
childAtPosition(
withId(android.R.id.content),
0),
1),
isDisplayed()));
        appCompatEditText20.perform(replaceText("My Taskadasdasdasdasdadsad"));
        
        ViewInteraction appCompatEditText21 = onView(
allOf(withId(R.id.Mytask), withText("My Taskadasdasdasdasdadsad"),
childAtPosition(
childAtPosition(
withId(android.R.id.content),
0),
1),
isDisplayed()));
        appCompatEditText21.perform(closeSoftKeyboard());
        
        ViewInteraction appCompatSpinner = onView(
allOf(withId(R.id.spinner),
childAtPosition(
childAtPosition(
withId(android.R.id.content),
0),
8),
isDisplayed()));
        appCompatSpinner.perform(click());
        
        DataInteraction materialTextView = onData(anything())
.inAdapterView(childAtPosition(
withClassName(is("android.widget.PopupWindow$PopupBackgroundView")),
0))
.atPosition(3);
        materialTextView.perform(click());
        
        ViewInteraction materialButton2 = onView(
allOf(withId(R.id.addTaskbtn), withText("ADD TASK"),
childAtPosition(
childAtPosition(
withId(android.R.id.content),
0),
5),
isDisplayed()));
        materialButton2.perform(click());
        }
    
    private static Matcher<View> childAtPosition(
            final Matcher<View> parentMatcher, final int position) {

        return new TypeSafeMatcher<View>() {
            @Override
            public void describeTo(Description description) {
                description.appendText("Child at position " + position + " in parent ");
                parentMatcher.describeTo(description);
            }

            @Override
            public boolean matchesSafely(View view) {
                ViewParent parent = view.getParent();
                return parent instanceof ViewGroup && parentMatcher.matches(parent)
                        && view.equals(((ViewGroup)parent).getChildAt(position));
            }
        };
    }
    }
