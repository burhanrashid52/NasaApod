package ja.burhanrashid52.nasa.apod

import androidx.test.platform.app.InstrumentationRegistry
import ja.burhanrashid52.nasa.apod.dataSource.AssetResource

class FakeAssetResource(private val testFileName: String) : AssetResource {
    override fun loadJson(fileName: String): String? {
        return InstrumentationRegistry.getInstrumentation().context.assets.open(testFileName)
            .bufferedReader().use { it.readText() }

    }

    override fun getString(id: Int): String {
        return "Failed"
    }
}