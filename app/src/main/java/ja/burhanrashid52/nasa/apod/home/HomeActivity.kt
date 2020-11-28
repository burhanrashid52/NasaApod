package ja.burhanrashid52.nasa.apod.home

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.Observer
import ja.burhanrashid52.nasa.apod.R
import ja.burhanrashid52.nasa.apod.dataSource.Resource
import ja.burhanrashid52.nasa.apod.databinding.ActivityMainBinding
import org.koin.android.viewmodel.ext.android.viewModel
import timber.log.Timber

class HomeActivity : AppCompatActivity() {

    private val homeViewModel: HomeViewModel by viewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val bindings = ActivityMainBinding.inflate(layoutInflater)
        setContentView(bindings.root)

        supportFragmentManager
            .beginTransaction()
            .add(R.id.hostFragmentContainer, ImageListFragment())
            .commit()

        homeViewModel.fetchGalaxyImages()
    }
}