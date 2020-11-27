package ja.burhanrashid52.nasa.apod.dataSource

import android.content.Context
import androidx.annotation.StringRes
import java.io.IOException
import java.io.InputStream


interface AssetResource {
    fun loadJson(fileName: String): String?

    fun getString(@StringRes id: Int): String
}

fun createAssetResource(context: Context): AssetResource = AssetResourceImpl(context)

class AssetResourceImpl(private val context: Context) : AssetResource {
    override fun loadJson(fileName: String): String? {
        return try {
            val inputStream: InputStream = context.assets.open(fileName)
            val size: Int = inputStream.available()
            val buffer = ByteArray(size)
            inputStream.read(buffer)
            inputStream.close()
            String(buffer, Charsets.UTF_8)
        } catch (ex: IOException) {
            ex.printStackTrace()
            return null
        }
    }

    override fun getString(@StringRes id: Int): String = context.getString(id)
}