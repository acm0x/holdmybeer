package uk.acm0x.beers.model

interface BeerRepository {
    suspend fun getBeers(): List<Beer>

    suspend fun getBeer(id: Int): Beer?
}