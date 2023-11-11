package com.appimage.core_ui.view.category

import com.appimage.utils.adapter.DelegateItem
import com.appimage.utils.adapter.utils.checkChanges

data class CategoryViewModel(
    val nameCategory: String,
    val groupId: Int = DEFAULT_GROUP_ID_CATEGORY_ITEMS,
    val countInCategory: Int = -1,
    val isActive: CategoryView.Style = CategoryView.Style.Disabled
): DelegateItem {

    companion object {
        const val DEFAULT_GROUP_ID_CATEGORY_ITEMS = 0
    }

    override fun id(): Any {
        return nameCategory
    }

    override fun content(): Any {
        return this
    }

    override fun payload(other: Any): List<DelegateItem.Payloadable> {
        val item = other as? CategoryViewModel
        return checkChanges(
            mapOf(
                ChangePayload.NameCategory to (nameCategory != item?.nameCategory),
                ChangePayload.CountInCategory to (countInCategory != item?.countInCategory),
                ChangePayload.IsActive to (isActive != item?.isActive)
            )
        )
    }

    sealed class ChangePayload : DelegateItem.Payloadable {

        object NameCategory : ChangePayload()
        object CountInCategory : ChangePayload()
        object IsActive : ChangePayload()
    }
}

