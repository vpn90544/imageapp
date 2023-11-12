package com.appimage.allimage.data.models

import com.google.gson.annotations.SerializedName

data class ImagesInfoPage (
    @SerializedName("info"    ) var info    : Info?              = null,
    @SerializedName("results" ) var results : ArrayList<ImageInfo>? = null
)
data class Info (
    @SerializedName("next"  ) var next  : String? = null,
    @SerializedName("prev"  ) var prev  : String? = null
)

data class ImageInfo(
    @SerializedName("id"       ) var id       : Int?              = null,
    @SerializedName("image"    ) var image    : String?           = null
)