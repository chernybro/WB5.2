package com.chernybro.wb52.data.remote.models

import com.chernybro.wb52.domain.models.HeroItem

data class HeroDTO(
    val id: String,
    val image: Image,
    val name: String
)

data class Image(
    val url: String
)

fun HeroDTO.toHeroItem() : HeroItem = HeroItem(id = id, image = image.url, name = name)