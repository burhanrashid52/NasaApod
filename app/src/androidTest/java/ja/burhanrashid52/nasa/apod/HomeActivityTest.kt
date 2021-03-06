package ja.burhanrashid52.nasa.apod

import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.action.ViewActions.scrollTo
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.contrib.RecyclerViewActions
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.rule.ActivityTestRule
import ja.burhanrashid52.nasa.apod.dataSource.ApodRepository
import ja.burhanrashid52.nasa.apod.dataSource.createApodRepository
import ja.burhanrashid52.nasa.apod.home.HomeActivity
import ja.burhanrashid52.nasa.apod.home.ImagesAdapter
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.koin.core.context.loadKoinModules
import org.koin.dsl.module

/**
 * Instrumented test, which will execute on an Android device.
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
@RunWith(AndroidJUnit4::class)
class HomeActivityTest {

    @get:Rule
    val activityTestRule = ActivityTestRule(HomeActivity::class.java, false, false)

    @Test
    fun launches() {
        activityTestRule.launchActivity(null)
    }

    @Test
    fun scroll_to_the_list_of_images_and_open_image_details_on_item_click() {
        loadFakeJson("sample_data.json")
        activityTestRule.launchActivity(null)
        onView(withId(R.id.rvImages)).perform(
            RecyclerViewActions.actionOnItem<ImagesAdapter.ImagesViewHolder>(
                hasDescendant(withContentDescription("Geminid Meteors over Chile")),
                scrollTo()
            )
        )

        onView(withId(R.id.rvImages)).perform(
            RecyclerViewActions.actionOnItem<ImagesAdapter.ImagesViewHolder>(
                hasDescendant(withContentDescription("Geminid Meteors over Chile")),
                click()
            )
        )

        onView(withContentDescription("Geminid Meteors over Chile")).check(matches(isDisplayed()))
        onView(withText("Geminid Meteors over Chile")).check(matches(isDisplayed()))
    }

    @Test
    fun show_error_message_when_app_fail_to_load_data() {
        loadFakeJson("invalid_data.json")
        activityTestRule.launchActivity(null)
        onView(withText("Failed to load")).check(matches(isDisplayed()))
    }

    private fun loadFakeJson(fileName: String) {
        loadKoinModules(module {
            single<ApodRepository>(override = true) {
                createApodRepository(FakeAssetResource(fileName))
            }
        })
    }
}