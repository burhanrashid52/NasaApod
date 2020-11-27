package ja.burhanrashid52.nasa.apod.home

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import ja.burhanrashid52.nasa.apod.R
import ja.burhanrashid52.nasa.apod.databinding.RowImageBinding
import ja.burhanrashid52.nasa.apod.models.GalaxyUI

class ImagesAdapter :
    ListAdapter<GalaxyUI, ImagesAdapter.ImagesViewHolder>(galaxyItemDiffCallback) {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ImagesViewHolder {
        val rowImageBinding =
            RowImageBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ImagesViewHolder(rowImageBinding)
    }

    override fun onBindViewHolder(holder: ImagesViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    inner class ImagesViewHolder(private val binding: RowImageBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(galaxyUI: GalaxyUI) {
            binding.txtTitle.text = galaxyUI.title
            binding.imgGalaxy.contentDescription = galaxyUI.title
            Glide.with(binding.imgGalaxy.context)
                .load(galaxyUI.imageUrl).fitCenter().centerCrop()
                /*.placeholder(CircularProgressDrawable(view.context).apply {
                    strokeWidth = 5f
                    centerRadius = 30f
                    start()
                })*/
                //.error(R.drawable.ic_launcher_background)
                .into(binding.imgGalaxy)
        }

    }
}

class ItemDiffCallback<T> : DiffUtil.ItemCallback<T>() {
    override fun areItemsTheSame(oldItem: T, newItem: T): Boolean {
        return oldItem == newItem
    }

    @SuppressLint("DiffUtilEquals")  // equals() is OK for data classes
    override fun areContentsTheSame(oldItem: T, newItem: T): Boolean {
        return oldItem == newItem
    }
}

val galaxyItemDiffCallback = ItemDiffCallback<GalaxyUI>()