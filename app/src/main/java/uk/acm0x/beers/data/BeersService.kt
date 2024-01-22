package uk.acm0x.beers.data

import retrofit2.http.GET

interface BeersService {
  @GET("beers")
  suspend fun getBeers(): List<BeersResponse>
}
