package ja.burhanrashid52.nasa.apod

import com.nhaarman.mockitokotlin2.any
import com.nhaarman.mockitokotlin2.doReturn
import com.nhaarman.mockitokotlin2.mock
import ja.burhanrashid52.nasa.apod.dataSource.AssetResource
import ja.burhanrashid52.nasa.apod.dataSource.Resource
import ja.burhanrashid52.nasa.apod.dataSource.createApodRepository
import org.junit.Assert.assertEquals
import org.junit.Assert.assertTrue
import org.junit.Test

class ApodRepositoryTest {

    @Test
    fun convert_json_into_galaxy_item_list() {
        val assetResourceMock = mock<AssetResource> {
            on { loadJson(any()) } doReturn testJson
            on { getString(any()) } doReturn "Failed"
        }
        val apodRepository = createApodRepository(assetResourceMock)
        val fetchImages = apodRepository.fetchImages()
        assertTrue(fetchImages is Resource.Success)
        assertEquals((fetchImages as Resource.Success).data.size, 3)
    }

    @Test
    fun failed_to_load_json_into_galaxy_item_list() {
        val failedMessage = "Failed"
        val assetResourceMock = mock<AssetResource> {
            on { loadJson(any()) } doReturn null
            on { getString(any()) } doReturn failedMessage
        }
        val apodRepository = createApodRepository(assetResourceMock)
        val fetchImages = apodRepository.fetchImages()
        assertTrue(fetchImages is Resource.Failure)
        assertEquals((fetchImages as Resource.Failure).throwable.message, failedMessage)
    }
}