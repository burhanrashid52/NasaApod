package ja.burhanrashid52.nasa.apod.models

data class GalaxyUI(val title: String, val imageUrl: String)

fun GalaxyDetailItem.toGalaxyUI(): GalaxyUI {
    return GalaxyUI(
        title = title,
        imageUrl = url
    )
}