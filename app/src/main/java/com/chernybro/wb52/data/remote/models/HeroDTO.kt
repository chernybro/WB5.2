package com.chernybro.wb52.data.remote.models

import com.chernybro.wb52.domain.models.HeroDetailsItem
import com.chernybro.wb52.domain.models.HeroItem
import com.google.gson.annotations.SerializedName

data class HeroDTO(
    val id: String,
    val image: Image,
    val name: String,
    val powerstats: Powerstats,
    val appearance: Appearance,
    val biography: Biography
)

data class Biography(
    @SerializedName("full-name")
    val fullName: String,
    val publisher: String,
    @SerializedName("place-of-birth")
    val birthPlace: String
)

data class Image(
    val url: String
)

data class Powerstats(
    val intelligence: String,
    val strength: String,
    val speed: String,
    val durability: String,
    val power: String,
)


data class Appearance(
    val race: String,
    val height: List<String>,
    val weight: List<String>
)

fun HeroDTO.toHeroItem(): HeroItem = HeroItem(id = id, image = image.url, name = name)

fun HeroDTO.toHeroDetailsItem(): HeroDetailsItem =
    HeroDetailsItem(
        id = id,
        name = name,
        publisher = biography.publisher,
        imageUrl = image.url,
        intelligence = powerstats.intelligence,
        strength = powerstats.strength,
        speed = powerstats.speed,
        durability = powerstats.durability,
        power = powerstats.power,
        birthPlace = biography.birthPlace,
        height = appearance.height[1],
        weight = appearance.weight[1],
        race = appearance.race,
        fullName = biography.fullName
    )