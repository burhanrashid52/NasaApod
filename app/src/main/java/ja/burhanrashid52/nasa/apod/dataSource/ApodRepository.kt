package ja.burhanrashid52.nasa.apod.dataSource

import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import ja.burhanrashid52.nasa.apod.R
import ja.burhanrashid52.nasa.apod.models.GalaxyDetailItem


private class ApodRepositoryImpl(private val assetResource: AssetResource) : ApodRepository {
    override fun fetchImages(): Resource<List<GalaxyDetailItem>> {
        val loadedJson = assetResource.loadJson("data.json")
        return if (loadedJson == null) {
            Resource.Failure(Exception(assetResource.getString(R.string.msg_failed_load)))
        } else {
            try {
                val galaxyList: List<GalaxyDetailItem> =
                    Gson().fromJson(
                        loadedJson,
                        object : TypeToken<List<GalaxyDetailItem>>() {}.type
                    )
                Resource.Success(galaxyList)
            } catch (e: Exception) {
                Resource.Failure(Exception(assetResource.getString(R.string.msg_failed_load)))
            }
        }
    }
}

fun createApodRepository(assertResource: AssetResource): ApodRepository =
    ApodRepositoryImpl(assertResource)

interface ApodRepository {
    fun fetchImages(): Resource<List<GalaxyDetailItem>>
}