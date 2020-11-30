package ja.burhanrashid52.nasa.apod

import androidx.test.platform.app.InstrumentationRegistry
import ja.burhanrashid52.nasa.apod.dataSource.AssetResource

class FakeAssetResource(private val testFileName: String) : AssetResource {
    private val context = InstrumentationRegistry.getInstrumentation().context
    override fun loadJson(fileName: String): String? {
        return try {
            context.assets.open(testFileName)
                .bufferedReader().use { it.readText() }
        } catch (e: Exception) {
            null
        }
    }

    override fun getString(id: Int): String {
        return "Failed to load"
    }
}