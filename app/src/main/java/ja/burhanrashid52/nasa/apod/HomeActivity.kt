package ja.burhanrashid52.nasa.apod

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import ja.burhanrashid52.nasa.apod.dataSource.ApodRepository
import org.koin.android.ext.android.inject
import timber.log.Timber

class HomeActivity : AppCompatActivity() {

    private val apodRepository: ApodRepository by inject()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        Timber.e(apodRepository.toString())
    }
}