package ja.burhanrashid52.nasa.apod.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import ja.burhanrashid52.nasa.apod.dataSource.ApodRepository
import ja.burhanrashid52.nasa.apod.dataSource.Resource
import ja.burhanrashid52.nasa.apod.models.GalaxyUI
import ja.burhanrashid52.nasa.apod.models.toGalaxyUI

class HomeViewModel(private val apodRepository: ApodRepository) : ViewModel() {

    private val _galaxyUI = MutableLiveData<Resource<List<GalaxyUI>>>()

    val galaxyUI: LiveData<Resource<List<GalaxyUI>>>
        get() = _galaxyUI

    fun fetchGalaxyImages() {
        _galaxyUI.value = Resource.Loading()
        val fetchImages = apodRepository.fetchImages()
        _galaxyUI.value = when (fetchImages) {
            is Resource.Loading -> Resource.Loading()
            is Resource.Success -> Resource.Success(fetchImages.data.map { it.toGalaxyUI() })
            is Resource.Failure -> Resource.Failure(fetchImages.throwable)
        }
    }
}