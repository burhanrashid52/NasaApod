package ja.burhanrashid52.nasa.apod.utils

import android.content.Context
import android.content.res.Configuration.ORIENTATION_PORTRAIT
import androidx.navigation.NavController
import androidx.navigation.NavDirections
import timber.log.Timber


fun NavController.safeNavigate(d: NavDirections) =
    currentDestination?.getAction(d.actionId)?.let { navigate(d) }
        ?: Timber.e("Invalid route for direction $d with id ${d.actionId}")

fun Context.isPortrait(): Boolean {
    return resources.configuration.orientation == ORIENTATION_PORTRAIT
}