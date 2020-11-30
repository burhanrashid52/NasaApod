package ja.burhanrashid52.nasa.apod.home.details

import android.os.Bundle
import android.view.View
import androidx.core.view.isGone
import androidx.core.view.isVisible
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

    companion object {
        private const val SCROLLED_POSITION = "position"
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        Timber.e(this.toString())
        bindings = FragmentImageDetailsBinding.bind(view)
        val rvImageDetails = bindings.rvImageDetails

        /*
        * Using save instance bundle to retain the position on config change. The same problem can be solved
        * by view model, but creating a view model just for one state is too much and unnecessary.
        * */
        val selectedItemPosition =
            savedInstanceState?.getInt(SCROLLED_POSITION) ?: args.selectedPosition

        layoutManager = LinearLayoutManager(context, RecyclerView.HORIZONTAL, false)
        rvImageDetails.layoutManager = layoutManager

        // Snap each linear item into the center when scrolling.
        LinearSnapHelper().apply { attachToRecyclerView(rvImageDetails) }
        rvImageDetails.adapter = imageDetailsAdapter

        homeViewModel.galaxyUI.observe(viewLifecycleOwner, Observer {
            bindings.rvImageDetails.isVisible = it is Resource.Success
            bindings.txtEmptyView.isGone = it is Resource.Success

            when (it) {
                is Resource.Loading -> {
                    bindings.txtEmptyView.text = getString(R.string.label_loading)
                }
                is Resource.Success -> {
                    imageDetailsAdapter.submitList(it.data)
                    rvImageDetails.scrollToPosition(selectedItemPosition)
                }

                is Resource.Failure -> {
                    bindings.txtEmptyView.text = it.throwable.message
                }
            }
        })
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        outState.putInt(SCROLLED_POSITION, layoutManager.findFirstVisibleItemPosition())
    }
}