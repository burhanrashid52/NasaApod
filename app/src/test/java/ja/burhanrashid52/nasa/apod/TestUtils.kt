package ja.burhanrashid52.nasa.apod

import androidx.lifecycle.LiveData
import androidx.lifecycle.Observer
import ja.burhanrashid52.nasa.apod.models.GalaxyDetailItem
import org.json.JSONArray
import org.json.JSONException
import org.json.JSONObject
import java.util.concurrent.CountDownLatch
import java.util.concurrent.TimeUnit
import java.util.concurrent.TimeoutException

fun <T> LiveData<T>.getOrAwaitValue(
    time: Long = 2,
    timeUnit: TimeUnit = TimeUnit.SECONDS
): T {
    var data: T? = null
    val latch = CountDownLatch(1)
    val observer = object : androidx.lifecycle.Observer<T> {
        override fun onChanged(o: T?) {
            data = o
            latch.countDown()
            this@getOrAwaitValue.removeObserver(this)
        }
    }

    this.observeForever(observer)

    // Don't wait indefinitely if the LiveData is not set.
    if (!latch.await(time, timeUnit)) {
        throw TimeoutException("LiveData value was never set.")
    }

    @Suppress("UNCHECKED_CAST")
    return data as T
}


@Throws(InterruptedException::class)
fun <T> getValueForLiveData(liveData: LiveData<T>): T {
    val data = arrayOfNulls<Any>(1)
    val latch = CountDownLatch(1)
    val observer = object : Observer<T> {
        override fun onChanged(o: T?) {
            data[0] = o
            latch.countDown()
            liveData.removeObserver(this)
        }
    }
    liveData.observeForever(observer)
    latch.await(2, TimeUnit.SECONDS)

    @Suppress("UNCHECKED_CAST")
    return data[0] as T
}

fun isJSONValid(test: String?): Boolean {
    if (test == null) return false
    try {
        JSONObject(test)
    } catch (ex: JSONException) {
        // e.g. in case JSONArray is valid as well...
        isJSONArrayValid(test)
    }
    return true
}

fun isJSONArrayValid(test: String?): Boolean {
    try {
        JSONArray(test)
    } catch (ex1: JSONException) {
        return false
    }
    return true
}

const val testJson = "[" +
        "{\n" +
        "        \"date\": \"2019-12-29\",\n" +
        "        \"explanation\": \"If this is Saturn, where are the rings?  When Saturn's \\\"appendages\\\" disappeared in 1612, Galileo did not understand why.   Later that century, it became understood that Saturn's unusual protrusions were rings and that when the Earth crosses the ring plane, the edge-on rings will appear to disappear.  This is because Saturn's rings are confined to a plane many times thinner, in proportion, than a razor blade.  In modern times, the robot Cassini spacecraft orbiting Saturn frequently crossed Saturn's ring plane during its mission to Saturn, from 2004 to 2017.  A series of plane crossing images from 2005 February was dug out of the vast online Cassini raw image archive by interested Spanish amateur Fernando Garcia Navarro.  Pictured here, digitally cropped and set in representative colors, is the striking result.  Saturn's thin ring plane appears in blue, bands and clouds in Saturn's upper atmosphere appear in gold. Details of Saturn's rings can be seen in the high dark shadows across the top of this image, taken back in 2005. The moons Dione and Enceladus appear as bumps in the rings.    Free Presentation: APOD Editor to show best astronomy images of 2019 -- and the decade -- in NYC on January 3\",\n" +
        "        \"hdurl\": \"https://apod.nasa.gov/apod/image/1912/saturnplane_cassini_1004.jpg\",\n" +
        "        \"media_type\": \"image\",\n" +
        "        \"service_version\": \"v1\",\n" +
        "        \"title\": \"Cassini Spacecraft Crosses Saturn's Ring Plane\",\n" +
        "        \"url\": \"https://apod.nasa.gov/apod/image/1912/saturnplane_cassini_1004.jpg\"\n" +
        "    },\n" +
        "    {\n" +
        "        \"copyright\": \"Stanislav Volskiy\",\n" +
        "        \"date\": \"2019-12-30\",\n" +
        "        \"explanation\": \"The beautiful Trifid Nebula, also known as Messier 20, is easy to find with a small telescope in the nebula rich constellation Sagittarius. About 5,000 light-years away, the colorful study in cosmic contrasts shares this well-composed, nearly 1 degree wide field with open star cluster Messier 21 (top left). Trisected by dust lanes the Trifid itself is about 40 light-years across and a mere 300,000 years old. That makes it one of the youngest star forming regions in our sky, with newborn and embryonic stars embedded in its natal dust and gas clouds. Estimates of the distance to open star cluster M21 are similar to M20's, but though they share this gorgeous telescopic skyscape there is no apparent connection between the two. In fact, M21's stars are much older, about 8 million years old.\",\n" +
        "        \"hdurl\": \"https://apod.nasa.gov/apod/image/1912/M20_volskiy.jpg\",\n" +
        "        \"media_type\": \"image\",\n" +
        "        \"service_version\": \"v1\",\n" +
        "        \"title\": \"Messier 20 and 21\",\n" +
        "        \"url\": \"https://apod.nasa.gov/apod/image/1912/M20_volskiy1024.jpg\"\n" +
        "    },\n" +
        "    {\n" +
        "        \"copyright\": \"Rui Liao\",\n" +
        "        \"date\": \"2019-12-31\",\n" +
        "        \"explanation\": \"The small, northern constellation Triangulum harbors this magnificent face-on spiral galaxy, M33. Its popular names include the Pinwheel Galaxy or just the Triangulum Galaxy. M33 is over 50,000 light-years in diameter, third largest in the Local Group of galaxies after the Andromeda Galaxy (M31), and our own Milky Way. About 3 million light-years from the Milky Way, M33 is itself thought to be a satellite of the Andromeda Galaxy and astronomers in these two galaxies would likely have spectacular views of each other's grand spiral star systems. As for the view from planet Earth, this sharp image shows off M33's blue star clusters and pinkish star forming regions along the galaxy's loosely wound spiral arms. In fact, the cavernous NGC 604 is the brightest star forming region, seen here at about the 7 o'clock position from the galaxy center. Like M31, M33's population of well-measured variable stars have helped make this nearby spiral a cosmic yardstick for establishing the distance scale of the Universe.\",\n" +
        "        \"hdurl\": \"https://apod.nasa.gov/apod/image/1912/M33-HaLRGB-RayLiao.jpg\",\n" +
        "        \"media_type\": \"image\",\n" +
        "        \"service_version\": \"v1\",\n" +
        "        \"title\": \"M33: The Triangulum Galaxy\",\n" +
        "        \"url\": \"https://apod.nasa.gov/apod/image/1912/M33-HaLRGB-RayLiao1024.jpg\"\n" +
        "    }" +
        "]"


val testGalaxyItem = GalaxyDetailItem(
    copyright = "CR",
    date = "12/2/2020",
    explanation = "Explantion",
    hdurl = "https://example.com/hd_image.jpg",
    media_type = "image",
    service_version = "1.1",
    title = "Hello World",
    url = "https://example.com/normal_image.jpg"
)