package com.appimage.utils.adapter

import android.annotation.SuppressLint
import androidx.recyclerview.widget.DiffUtil

internal class DelegateAdapterItemDiffCallback : DiffUtil.ItemCallback<DelegateItem>() {

    override fun areItemsTheSame(
        oldDelegateItem: DelegateItem,
        newDelegateItem: DelegateItem
    ): Boolean = oldDelegateItem::class == newDelegateItem::class && oldDelegateItem.id() == newDelegateItem.id()

    @SuppressLint("DiffUtilEquals")
    override fun areContentsTheSame(
        oldDelegateItem: DelegateItem,
        newDelegateItem: DelegateItem
    ): Boolean = oldDelegateItem.content() == newDelegateItem.content()

    override fun getChangePayload(
        oldDelegateItem: DelegateItem,
        newDelegateItem: DelegateItem
    ) = oldDelegateItem.payload(newDelegateItem)
}