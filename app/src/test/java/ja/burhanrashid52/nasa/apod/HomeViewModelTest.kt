package ja.burhanrashid52.nasa.apod

import com.nhaarman.mockitokotlin2.doReturn
import com.nhaarman.mockitokotlin2.mock
import ja.burhanrashid52.nasa.apod.dataSource.ApodRepository
import ja.burhanrashid52.nasa.apod.dataSource.Resource
import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import junit.framework.TestCase.assertTrue
import org.junit.Assert.assertEquals
import org.junit.Rule
import org.junit.Test
import java.lang.Exception

class HomeViewModelTest {

    @Rule
    @JvmField
    val rule = InstantTaskExecutorRule()

    @Test
    fun get_galaxy_ui_list_on_success() {
        val mock = mock<ApodRepository>() {
            on { fetchImages() } doReturn Resource.Success(listOf(testGalaxyItem, testGalaxyItem))
        }
        val homeViewModel = HomeViewModel(mock)
        homeViewModel.fetchGalaxyImages()
        val galaxyUIResource = homeViewModel.galaxyUI.getOrAwaitValue()
        assertTrue(galaxyUIResource is Resource.Success)
        assertEquals((galaxyUIResource as Resource.Success).data[0].title, testGalaxyItem.title)
    }

    @Test
    fun convert_to_exception_on_failure() {
        val failedMessage = "Failed to load"
        val mock = mock<ApodRepository>() {
            on { fetchImages() } doReturn Resource.Failure(Exception(failedMessage))
        }
        val homeViewModel = HomeViewModel(mock)
        homeViewModel.fetchGalaxyImages()
        val galaxyUIResource = homeViewModel.galaxyUI.getOrAwaitValue()
        assertTrue(galaxyUIResource is Resource.Failure)
        assertEquals((galaxyUIResource as Resource.Failure).throwable.message, failedMessage)
    }
}