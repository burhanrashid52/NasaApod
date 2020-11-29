package ja.burhanrashid52.nasa.apod.home

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.lifecycle.Observer
import androidx.navigation.findNavController
import ja.burhanrashid52.nasa.apod.R
import ja.burhanrashid52.nasa.apod.dataSource.Resource
import ja.burhanrashid52.nasa.apod.databinding.ActivityMainBinding
import ja.burhanrashid52.nasa.apod.home.details.ImageDetailsFragment
import ja.burhanrashid52.nasa.apod.utils.safeNavigate
import org.koin.android.viewmodel.ext.android.viewModel
import timber.log.Timber

class HomeActivity : AppCompatActivity() {

    private val homeViewModel: HomeViewModel by viewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val bindings = ActivityMainBinding.inflate(layoutInflater)
        setContentView(bindings.root)
        Timber.d("onCreate() called with: savedInstanceState = $savedInstanceState")

        homeViewModel.fetchGalaxyImages()

        homeViewModel.selectedItemPosition.observe(this, {
            Timber.e("Position $it")
            if (it != null) {
                findNavController(R.id.hostFragmentContainer).safeNavigate(
                    ImageListFragmentDirections.actionImageListFragmentToImageDetailsFragment()
                        .setSelectedPosition(it)
                )
            }
        })
    }
}