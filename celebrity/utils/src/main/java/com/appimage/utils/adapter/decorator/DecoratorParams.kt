package com.appimage.utils.adapter.decorator

data class DecoratorParams(
    val left_dp:Int,
    val right_dp:Int,
    val top_dp:Int,
    val bottom_dp:Int
) {
    companion object {
        const val DEFAULT_VALUE_DP = 8
    }
}