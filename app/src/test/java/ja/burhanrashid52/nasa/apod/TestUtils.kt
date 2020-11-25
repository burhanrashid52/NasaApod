package ja.burhanrashid52.nasa.apod

import org.json.JSONArray
import org.json.JSONException
import org.json.JSONObject


fun isJSONValid(test: String?): Boolean {
    if (test == null) return false
    try {
        JSONObject(test)
    } catch (ex: JSONException) {
        // e.g. in case JSONArray is valid as well...
        try {
            JSONArray(test)
        } catch (ex1: JSONException) {
            return false
        }
    }
    return true
}