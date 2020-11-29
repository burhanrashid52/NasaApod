package ja.burhanrashid52.nasa.apod.utils

import android.annotation.SuppressLint
import androidx.recyclerview.widget.DiffUtil
import ja.burhanrashid52.nasa.apod.models.GalaxyUI

val galaxyItemDiffCallback = ItemDiffCallback<GalaxyUI>()

class ItemDiffCallback<T> : DiffUtil.ItemCallback<T>() {
    override fun areItemsTheSame(oldItem: T, newItem: T): Boolean {
        return oldItem == newItem
    }

    @SuppressLint("DiffUtilEquals")  // equals() is OK for data classes
    override fun areContentsTheSame(oldItem: T, newItem: T): Boolean {
        return oldItem == newItem
    }
}