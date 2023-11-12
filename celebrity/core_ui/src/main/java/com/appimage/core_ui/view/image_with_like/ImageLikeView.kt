package com.appimage.core_ui.view.image_with_like

import android.content.Context
import android.content.res.ColorStateList
import android.util.AttributeSet
import android.view.LayoutInflater
import androidx.constraintlayout.widget.ConstraintLayout
import com.appimage.core_ui.R
import com.appimage.core_ui.databinding.ImageLikeViewBinding
import com.appimage.core_ui.view.Populatable
import com.appimage.core_ui.view.category.CategoryView
import com.appimage.core_ui.view.category.CategoryView.Companion.CORNER_MAIN_CONTAINER_DP
import com.appimage.utils.dpToPx
import com.google.android.material.shape.CornerFamily
import com.google.android.material.shape.MaterialShapeDrawable
import com.google.android.material.shape.ShapeAppearanceModel
import com.squareup.picasso.Picasso

class ImageLikeView @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0,
    defStyleRes: Int = 0
) : ConstraintLayout(context, attrs, defStyleAttr, defStyleRes),
    Populatable<ImageLikeViewModel>
{
    private val imageLikeViewBinding: ImageLikeViewBinding
    private var mainContainerColor: ColorStateList

    init {
        val inflater = LayoutInflater.from(context)
        inflater.inflate(R.layout.image_like_view, this, true)
        imageLikeViewBinding = ImageLikeViewBinding.bind(this)
        mainContainerColor = context.getColorStateList(R.color.image_like_main_container)
    }

    companion object {
        const val CORNER_COUNT_CONTAINER_DP = 16
    }

    override fun populate(model: ImageLikeViewModel) {
        setLayoutParams()
        setMainBackground()
        setMainBackgroundColor()
        setMainImage(model)
        setImageIsLike(model)
    }

    private fun setLayoutParams(){
        resources.displayMetrics.heightPixels/4
        imageLikeViewBinding.mainContainer.layoutParams = LayoutParams(
            LayoutParams.MATCH_PARENT,
            resources.displayMetrics.heightPixels/4
            //LayoutParams.WRAP_CONTENT
        )
    }

    internal fun setMainImage(model: ImageLikeViewModel){
        Picasso.get().load(model.imageUrl).into(imageLikeViewBinding.imageMain)
    }

    internal fun setImageIsLike(model: ImageLikeViewModel){
        if (model.isLike) {
            imageLikeViewBinding.imageIsLike.setImageResource(R.drawable.like)
        } else {
            imageLikeViewBinding.imageIsLike.setImageResource(R.drawable.unlike)
        }

    }

    internal fun setClickListenerForLike(action: (ImageLikeViewModel)->Unit, model: ImageLikeViewModel) {
        imageLikeViewBinding.imageIsLike.setOnClickListener {
            action.invoke(model)
        }
    }

    private fun setMainBackground() {
        imageLikeViewBinding.mainContainer.background = MaterialShapeDrawable(
            getDrawableBackgroundWithAllCorner(CORNER_MAIN_CONTAINER_DP)
        )
    }

    private fun setMainBackgroundColor() {
        (imageLikeViewBinding.mainContainer.background as MaterialShapeDrawable).apply {
            fillColor = mainContainerColor
        }
    }

    private fun getDrawableBackgroundWithAllCorner(cornerRadius: Int): ShapeAppearanceModel {
        return ShapeAppearanceModel.builder()
            .setAllCorners(CornerFamily.ROUNDED, cornerRadius.dpToPx().toFloat())
            .build()
    }
}