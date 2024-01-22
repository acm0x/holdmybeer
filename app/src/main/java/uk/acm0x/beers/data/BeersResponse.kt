package uk.acm0x.beers.data

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class BeersResponse(
    val id: Int,
    val name: String,
    val description: String,
    @SerialName("image_url")
    val imageUrl: String,
    val ingredients: BeerIngredientsResponse
)

@Serializable
data class BeerIngredientsResponse(
    val malt: List<NameResponse>,
    val hops: List<NameResponse>
)

@Serializable
class NameResponse(
    val name: String
)


