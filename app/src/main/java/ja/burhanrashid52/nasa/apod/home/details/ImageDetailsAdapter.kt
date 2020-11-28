package ja.burhanrashid52.nasa.apod.home.details

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.annotation.NonNull
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import ja.burhanrashid52.nasa.apod.databinding.RowDetailsBinding
import ja.burhanrashid52.nasa.apod.models.GalaxyUI

class ImageDetailsAdapter :
    ListAdapter<GalaxyUI, ImageDetailsAdapter.ImageDetailsViewHolder>(galaxyItemDiffCallback) {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ImageDetailsViewHolder {
        val rowDetailsBinding =
            RowDetailsBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ImageDetailsViewHolder(rowDetailsBinding)
    }

    override fun onBindViewHolder(holder: ImageDetailsViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    inner class ImageDetailsViewHolder(private val binding: RowDetailsBinding) :
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