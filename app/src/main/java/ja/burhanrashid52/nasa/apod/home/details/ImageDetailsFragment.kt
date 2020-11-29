package ja.burhanrashid52.nasa.apod.home.details

import android.os.Bundle
import android.view.View
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.LinearSnapHelper
import androidx.recyclerview.widget.RecyclerView
import ja.burhanrashid52.nasa.apod.R
import ja.burhanrashid52.nasa.apod.dataSource.Resource
import ja.burhanrashid52.nasa.apod.databinding.FragmentImageDetailsBinding
import ja.burhanrashid52.nasa.apod.home.HomeViewModel
import timber.log.Timber

class ImageDetailsFragment : Fragment(R.layout.fragment_image_details) {

    private lateinit var layoutManager: LinearLayoutManager
    private lateinit var bindings: FragmentImageDetailsBinding
    private val homeViewModel: HomeViewModel by activityViewModels()
    private val imageDetailsAdapter = ImageDetailsAdapter()

    private val args: ImageDetailsFragmentArgs by navArgs()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        Timber.e(this.toString())
        bindings = FragmentImageDetailsBinding.bind(view)
        val rvImageDetails = bindings.rvImageDetails
        val selectedItemPosition = savedInstanceState?.getInt("position") ?: args.selectedPosition

        layoutManager = LinearLayoutManager(context, RecyclerView.HORIZONTAL, false)
        rvImageDetails.layoutManager = layoutManager
        // Snap each linear item into the center when scrolling.
        LinearSnapHelper().apply { attachToRecyclerView(rvImageDetails) }
        rvImageDetails.adapter = imageDetailsAdapter

        homeViewModel.galaxyUI.observe(viewLifecycleOwner, Observer {
            when (it) {
                is Resource.Loading -> {
                    //TODO
                }
                is Resource.Success -> {
                    imageDetailsAdapter.submitList(it.data)
                    rvImageDetails.scrollToPosition(selectedItemPosition)
                }

                is Resource.Failure -> {
                    //TODO
                }
            }
        })
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        outState.putInt("position", layoutManager.findFirstVisibleItemPosition())
    }
}