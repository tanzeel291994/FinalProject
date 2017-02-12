package com.example.androidfetchlibrary;

import android.support.test.runner.AndroidJUnit4;

import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.concurrent.TimeUnit;

import static junit.framework.Assert.assertTrue;
import static junit.framework.Assert.fail;
import static org.hamcrest.core.IsNull.notNullValue;
import static org.junit.Assert.assertThat;

@RunWith(AndroidJUnit4.class)
public class GetJokeAsyncTaskTest  {

    @Test
    public void testJokeDownload() {

        try {
            DisplayActivity.GetJokeAsyncTask task = new DisplayActivity.GetJokeAsyncTask();
            task.execute();
            String joke = task.get(30, TimeUnit.SECONDS);

            assertThat(joke, notNullValue());
            assertTrue(joke.length() > 0);

        } catch (Exception e) {
            fail("Operation timed out");
        }
    }


}
