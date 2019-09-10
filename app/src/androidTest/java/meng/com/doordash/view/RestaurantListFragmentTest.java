package meng.com.doordash.view;

import androidx.test.espresso.matcher.ViewMatchers;
import androidx.test.rule.ActivityTestRule;
import androidx.test.runner.AndroidJUnit4;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import meng.com.doordash.MainActivity;
import meng.com.doordash.R;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.withEffectiveVisibility;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withText;

@RunWith(AndroidJUnit4.class)
public class RestaurantListFragmentTest {

    @Rule
    public ActivityTestRule<MainActivity> activityRule = new ActivityTestRule<>(MainActivity.class);

    @Test
    public void testUI() {
        onView(withId(R.id.gps_lat_edittext)).check(matches(withText("37.422740")));
        onView(withId(R.id.gps_search_button)).check(matches(withText("Search")));

        onView(withId(R.id.restaurant_recycleview)).check(matches(withEffectiveVisibility(ViewMatchers.Visibility.VISIBLE)));
    }
}