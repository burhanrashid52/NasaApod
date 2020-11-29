package ja.burhanrashid52.nasa.apod.home

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.GridLayoutManager
import ja.burhanrashid52.nasa.apod.R
import ja.burhanrashid52.nasa.apod.dataSource.Resource
import ja.burhanrashid52.nasa.apod.databinding.FragmentImageListBinding
import ja.burhanrashid52.nasa.apod.utils.isPortrait
import timber.log.Timber

class ImageListFragment : Fragment(R.layout.fragment_image_list) {

    private lateinit var bindings: FragmentImageListBinding
    private val homeViewModel: HomeViewModel by activityViewModels()
    private val imagesAdapter = ImagesAdapter {
        homeViewModel.setItemSelectedPosition(it)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        Timber.e(this.toString())
        bindings = FragmentImageListBinding.bind(view)

        val spanCount = if (requireContext().isPortrait()) 2 else 4
        val layoutManager = GridLayoutManager(context, spanCount)
        bindings.rvImages.layoutManager = layoutManager
        bindings.rvImages.adapter = imagesAdapter


        homeViewModel.galaxyUI.observe(viewLifecycleOwner, Observer {
            when (it) {
                is Resource.Loading -> {
                    //TODO
                }
                is Resource.Success -> {
                    imagesAdapter.submitList(it.data)
                }

                is Resource.Failure -> {
                    //TODO
                }
            }
        })
    }
}