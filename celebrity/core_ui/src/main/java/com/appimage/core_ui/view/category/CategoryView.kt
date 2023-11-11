package com.appimage.core_ui.view.category

import android.content.Context
import android.content.res.ColorStateList
import android.util.AttributeSet
import android.view.LayoutInflater
import android.view.View
import androidx.annotation.ColorRes
import androidx.constraintlayout.widget.ConstraintLayout
import com.appimage.core_ui.R
import com.appimage.core_ui.databinding.CategoryViewBinding
import com.appimage.core_ui.view.Populatable
import com.appimage.utils.dpToPx
import com.google.android.material.shape.CornerFamily
import com.google.android.material.shape.MaterialShapeDrawable
import com.google.android.material.shape.ShapeAppearanceModel

class CategoryView @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0,
    defStyleRes: Int = 0
) : ConstraintLayout(context, attrs, defStyleAttr, defStyleRes),
    Populatable<CategoryViewModel>
{

    private val categoryViewBinding: CategoryViewBinding
    private var mainTextColor: Int
    private var countColor: Int
    private var mainContainerColor: ColorStateList
    private var countContainerColor: ColorStateList

    companion object {
        const val CORNER_MAIN_CONTAINER_DP = 8
        const val CORNER_COUNT_CONTAINER_DP = 16
        const val PADDING_MEDIUM_DP = 16
        const val PADDING_SMALL_DP = 8
        const val PADDING_NON_DP = 0
        const val DEFAULT_VALUE_FOR_COUNT = -1
    }

    enum class Style(
        @ColorRes val countColor: Int,
        @ColorRes val countContainerColor: Int,
        @ColorRes val mainContainerColor: Int,
        @ColorRes val mainTextColor: Int
    ){
        Activated(
            R.color.category_active_count_text,
            R.color.category_active_count_container,
            R.color.category_active_main_container,
            R.color.category_active_main_text
        ),
        Disabled(
            R.color.category_disable_count_text,
            R.color.category_disable_count_container,
            R.color.category_disable_main_container,
            R.color.category_disable_main_text
        )
    }

    init {
        val inflater = LayoutInflater.from(context)
        inflater.inflate(R.layout.category_view, this, true)
        categoryViewBinding = CategoryViewBinding.bind(this)
        mainTextColor = context.getColor(R.color.default_color)
        countColor = context.getColor(R.color.default_color)
        mainContainerColor = context.getColorStateList(R.color.default_color)
        countContainerColor = context.getColorStateList(R.color.default_color)
    }

    override fun populate(model: CategoryViewModel) {
        setLayoutParam()
        setStyle(model.isActive)
        setCategoryName(model.nameCategory)
        setCategoryNameColor()
        setCategoryCount(model.countInCategory)
        setCategoryCountColor()
        setMainBackground()
        setMainPaddingContainer()
        setMainBackgroundColor()
        setCountBackground()
        setCountPaddingContainer()
        setCountBackgroundColor()
    }

    private fun setLayoutParam() {
        categoryViewBinding.mainContainer.layoutParams = LayoutParams(
            LayoutParams.MATCH_PARENT,
            LayoutParams.WRAP_CONTENT
        )
    }

    internal fun setIsActivateState(model: CategoryViewModel) {
        setStyle(model.isActive)
        setCategoryNameColor()
        setCategoryCountColor()
        setMainBackgroundColor()
        setCountBackgroundColor()
    }

    private fun setMainBackground() {
        categoryViewBinding.mainContainer.background = MaterialShapeDrawable(
            getDrawableBackgroundWithAllCorner(CORNER_MAIN_CONTAINER_DP)
        )
    }

    private fun setMainBackgroundColor() {
        (categoryViewBinding.mainContainer.background as MaterialShapeDrawable).apply {
            fillColor = mainContainerColor
        }
    }

    private fun setMainPaddingContainer() {
        categoryViewBinding.mainContainer.setPadding(
            PADDING_MEDIUM_DP.dpToPx(),
            PADDING_SMALL_DP.dpToPx(),
            PADDING_MEDIUM_DP.dpToPx(),
            PADDING_SMALL_DP.dpToPx()
        )
    }

    private fun setCountBackground() {
        categoryViewBinding.countContainer.background = MaterialShapeDrawable(
            getDrawableBackgroundWithAllCorner(CORNER_COUNT_CONTAINER_DP)
        )
    }

    private fun setCountBackgroundColor() {
        (categoryViewBinding.countContainer.background as MaterialShapeDrawable).apply {
            fillColor = countContainerColor
        }
    }

    private fun setCountPaddingContainer() {
        categoryViewBinding.countContainer.setPadding(
            PADDING_SMALL_DP.dpToPx(),
            PADDING_NON_DP.dpToPx(),
            PADDING_SMALL_DP.dpToPx(),
            PADDING_NON_DP.dpToPx()
        )
    }

    private fun getDrawableBackgroundWithAllCorner(cornerRadius: Int): ShapeAppearanceModel {
        return ShapeAppearanceModel.builder()
            .setAllCorners(CornerFamily.ROUNDED, cornerRadius.dpToPx().toFloat())
            .build()
    }

    internal fun setCategoryName(nameCategory: String) {
        categoryViewBinding.categoryName.text = nameCategory
    }

    private fun setCategoryNameColor() {
        categoryViewBinding.categoryName.setTextColor(mainTextColor)
    }

    internal fun setCategoryCount(countInCategory: Int) {
        if (countInCategory == DEFAULT_VALUE_FOR_COUNT) {
            categoryViewBinding.countContainer.visibility = View.GONE
        } else {
            categoryViewBinding.countContainer.visibility = View.VISIBLE
            categoryViewBinding.countInCategory.text = countInCategory.toString()
        }
    }

    private fun setCategoryCountColor() {
        categoryViewBinding.countInCategory.setTextColor(countColor)
    }

    private fun setStyle(style: Style) {
        countColor = context.getColor(style.countColor)
        countContainerColor = context.getColorStateList(style.countContainerColor)
        mainContainerColor = context.getColorStateList(style.mainContainerColor)
        mainTextColor = context.getColor(style.mainTextColor)
    }
}