package ja.burhanrashid52.nasa.apod

import android.content.Context
import androidx.test.core.app.ApplicationProvider
import androidx.test.platform.app.InstrumentationRegistry
import ja.burhanrashid52.nasa.apod.dataSource.AssetResource
import org.junit.Assert.*
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.RobolectricTestRunner
import org.robolectric.annotation.Config

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
@RunWith(RobolectricTestRunner::class)
@Config(sdk = [28])
class AssetResourceTest {

    @Test
    @Throws(Exception::class)
    fun read_Json_From_AssetResource_Successfully() {
        val ctx: Context = ApplicationProvider.getApplicationContext()
        val assetResource = AssetResource(ctx)
        val loadJson = assetResource.loadJson("data.json")
        assertNotNull(loadJson)
        assertNotNull(loadJson?.startsWith("["))
        assertNotNull(loadJson?.endsWith("]"))
    }

    @Test
    @Throws(Exception::class)
    fun return_Valid_Json_From_AssetResource() {
        val ctx: Context = ApplicationProvider.getApplicationContext()
        val assetResource = AssetResource(ctx)
        val loadJson = assetResource.loadJson("data.json")
        assertTrue(isJSONValid(loadJson))
    }

    @Test
    @Throws(Exception::class)
    fun read_Json_From_AssetResource_InvalidFileName_Returns_Null() {
        val ctx: Context = InstrumentationRegistry.getInstrumentation().context
        val assetResource = AssetResource(ctx)
        val loadJson = assetResource.loadJson("no_data.json")
        assertNull(loadJson)
    }

}