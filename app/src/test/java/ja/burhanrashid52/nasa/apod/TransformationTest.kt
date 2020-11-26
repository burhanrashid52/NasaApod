package ja.burhanrashid52.nasa.apod

import ja.burhanrashid52.nasa.apod.models.toGalaxyUI
import org.junit.Assert.assertEquals
import org.junit.Test

class TransformationTest {

    @Test
    fun transform_GalaxyItem_into_GalaxyUI() {
        val galaxyUI = testGalaxyItem.toGalaxyUI()
        assertEquals(galaxyUI.title, testGalaxyItem.title)
        assertEquals(galaxyUI.imageUrl, testGalaxyItem.url)
    }
}