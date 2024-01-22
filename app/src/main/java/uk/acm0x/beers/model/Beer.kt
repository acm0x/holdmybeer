package uk.acm0x.beers.model

data class Beer(val id: Int, val name: String, val description: String, val image: String, val beerIngredients: BeerIngredients)

data class BeerIngredients(val malt: List<String>, val hops: List<String>)
