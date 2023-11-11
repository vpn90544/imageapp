package com.appimage.core_ui.view

/**
 * Should be implemented by custom views. The main idea is view should render itself according to
 * certain model.
 */
interface Populatable<Model> {

    fun populate(model: Model)
}