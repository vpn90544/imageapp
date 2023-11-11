package com.appimage.core_ui.view.category

import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.appimage.utils.adapter.DelegateAdapter
import com.appimage.utils.adapter.DelegateItem
import com.appimage.utils.adapter.utils.check

class CategoryDelegateAdapter(
    private val action: ((CategoryViewModel) -> Unit)? = null
) : DelegateAdapter<CategoryViewModel, CategoryDelegateAdapter.CategoryViewHolder>(CategoryViewModel::class.java) {

    inner class CategoryViewHolder(val view: CategoryView): RecyclerView.ViewHolder(view) {
        fun bind(item: CategoryViewModel) {
            view.populate(item)
        }
    }

    override fun createViewHolder(parent: ViewGroup): RecyclerView.ViewHolder {
        val customView = CategoryView(parent.context)
        return CategoryViewHolder(customView)
    }

    override fun bindViewHolder(
        model: CategoryViewModel,
        viewHolder: CategoryViewHolder,
        payloads: List<List<DelegateItem.Payloadable>>
    ) {
        when{
            payloads.isNotEmpty() -> payloads.check { payload ->
                when(payload){
                    is CategoryViewModel.ChangePayload.NameCategory -> {
                        viewHolder.view.setCategoryName(model.nameCategory)
                    }
                    is CategoryViewModel.ChangePayload.CountInCategory ->{
                        viewHolder.view.setCategoryCount(model.countInCategory)
                    }
                    is CategoryViewModel.ChangePayload.IsActive -> {
                        viewHolder.view.setIsActivateState(model)
                    }
                }
            }
            else -> {
                viewHolder.bind(item = model)
                action?.let {
                    viewHolder.view.setOnClickListener {
                        action.invoke(model)
                    }
                }
            }
        }
    }

    override fun onViewRecycled(viewHolder: CategoryViewHolder) {
        super.onViewRecycled(viewHolder)
    }
}