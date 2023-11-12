package com.appimage.core_ui.view.image_with_like

import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.appimage.utils.adapter.DelegateAdapter
import com.appimage.utils.adapter.DelegateItem
import com.appimage.utils.adapter.utils.check

class ImageLikeDelegateAdapter (
    private val action: ((ImageLikeViewModel) -> Unit)? = null
) : DelegateAdapter<ImageLikeViewModel, ImageLikeDelegateAdapter.ImageLikeViewHolder>(ImageLikeViewModel::class.java) {

    inner class ImageLikeViewHolder(val view: ImageLikeView): RecyclerView.ViewHolder(view) {
        fun bind(item: ImageLikeViewModel) {
            view.populate(item)
        }
    }

    override fun createViewHolder(parent: ViewGroup): RecyclerView.ViewHolder {
        val customView = ImageLikeView(parent.context)
        return ImageLikeViewHolder(customView)
    }

    override fun bindViewHolder(
        model: ImageLikeViewModel,
        viewHolder: ImageLikeViewHolder,
        payloads: List<List<DelegateItem.Payloadable>>
    ) {
        when{
            payloads.isNotEmpty() -> payloads.check { payload ->
                when(payload){
                    is ImageLikeViewModel.ChangePayload.UrlImage -> {
                        viewHolder.view.setMainImage(model)
                    }
                    is ImageLikeViewModel.ChangePayload.IsLike ->{
                        viewHolder.view.setImageIsLike(model)
                    }
                }
            }
            else -> {
                viewHolder.bind(item = model)
                action?.let {
                    viewHolder.view.setClickListenerForLike(action, model)
                }
            }
        }
    }
}