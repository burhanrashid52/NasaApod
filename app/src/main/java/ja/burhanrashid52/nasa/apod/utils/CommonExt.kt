package ja.burhanrashid52.nasa.apod.utils

import androidx.navigation.NavController
import androidx.navigation.NavDirections
import timber.log.Timber


fun NavController.safeNavigate(d: NavDirections) =
    currentDestination?.getAction(d.actionId)?.let { navigate(d) }
        ?: Timber.e("Invalid route for direction ${d} with id ${d.actionId}")