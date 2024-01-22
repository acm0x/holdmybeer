package uk.acm0x.beers.data

import uk.acm0x.beers.model.Beer
import uk.acm0x.beers.model.BeerIngredients
import uk.acm0x.beers.model.BeerRepository

class PunkBeerRepository(private val beersService: BeersService = PunkClient.beersService) :
    BeerRepository {

    private lateinit var beersCache: MutableList<Beer>

    override suspend fun getBeers(): List<Beer> {
        if (!beersInCache()) {
            beersCache = mutableListOf()
            beersCache.addAll(fetchBeersList())
        }
        return beersCache.toList()
    }

    override suspend fun getBeer(id: Int) = getBeers().firstOrNull { it.id == id}


    private suspend fun fetchBeersList(): List<Beer> {
        return beersService.getBeers().map(BeersResponse::asBeer)
    }

    private fun beersInCache(): Boolean {
        return ::beersCache.isInitialized
    }
}

fun BeersResponse.asBeer(): Beer {
    return Beer(id, name, description, imageUrl, ingredients.asBeerIngredients())
}

fun BeerIngredientsResponse.asBeerIngredients(): BeerIngredients {
    return BeerIngredients(malt.map { it.name }, hops.map { it.name })
}