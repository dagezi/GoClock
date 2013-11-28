package com.example.goclock.test;

import android.test.ActivityInstrumentationTestCase2;

import com.example.goclock.MainActivity;
import com.jayway.android.robotium.solo.Solo;


/**
 * Test for MainActivity.
 */
public class MainActivityTest extends ActivityInstrumentationTestCase2<MainActivity>
{
    private Solo solo;

    public MainActivityTest() {
        super(MainActivity.class);
    }

    @Override
    protected void setUp() throws Exception {
        super.setUp();
        solo = new Solo(getInstrumentation(), getActivity());
    }

    public void testClickStart() {
        solo.clickOnText("Start");
        assertTrue(solo.searchButton("Done", true /* onlyVisible */));
    }

    public void testClickHoge() {
        solo.clickOnText("Start");
        assertTrue(solo.searchButton("Hoge", true /* onlyVisible */));
    }
}
