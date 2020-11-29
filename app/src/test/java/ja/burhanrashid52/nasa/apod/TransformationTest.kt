package ja.burhanrashid52.nasa.apod

import ja.burhanrashid52.nasa.apod.models.toGalaxyUI
import ja.burhanrashid52.nasa.apod.utils.formatDate
import org.junit.Assert.assertEquals
import org.junit.Test

class TransformationTest {

    @Test
    fun transform_GalaxyItem_into_GalaxyUI() {
        val galaxyUI = testGalaxyItem.toGalaxyUI()
        assertEquals(galaxyUI.title, testGalaxyItem.title)
        assertEquals(galaxyUI.description, testGalaxyItem.explanation)
        assertEquals(galaxyUI.imageUrl, testGalaxyItem.url)
        assertEquals(galaxyUI.hdImageUrl, testGalaxyItem.hdurl)
        assertEquals(galaxyUI.createdOn, "February 12, 2020")
        assertEquals(galaxyUI.copyrightBy, "\u00A9 CR")
    }

    @Test
    fun transform_given_format_into_another_format() {
        val givenDate = "2021-01-01"
        val formatDate = givenDate.formatDate("yyyy-MM-dd", "MMMM dd, yyyy")
        assertEquals(formatDate, "January 01, 2021")
    }
}