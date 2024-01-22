package uk.acm0x.beers.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import uk.acm0x.beers.data.PunkBeerRepository
import uk.acm0x.beers.model.Beer
import uk.acm0x.beers.model.BeerRepository

// Cheap substitute for DI
class MainViewModel(private val beerRepository: BeerRepository = PunkBeerRepository()) :
    ViewModel() {
    fun showDetails(beerUI: BeerUI) {
        viewModelScope.launch {
            val beer = beerRepository.getBeer(beerUI.id)
            if (beer == null) {
                _screenState.value = DataState.ShowError("Segfault")
            } else {
                _screenState.value = DataState.ShowDetail(beerDetailUIfromModel(beer))
            }
        }
    }

    fun showList() {
        viewModelScope.launch {
            val beers = beerRepository.getBeers().map { beerUIfromModel(it) }
            _screenState.value = DataState.ShowList(beers)
        }
    }

    private val _screenState: MutableStateFlow<DataState> = MutableStateFlow(DataState.Loading)
    val screenState = _screenState.asStateFlow()


}


sealed class DataState {
    data object Loading : DataState()
    class ShowList(val beers: List<BeerUI>) : DataState()
    class ShowDetail(val beer: BeerDetailUI) : DataState()
    class ShowError(val message: String) : DataState()
}


fun beerUIfromModel(beer: Beer): BeerUI {
    return BeerUI(beer.id, beer.name, beer.description, beer.image)
}

fun beerDetailUIfromModel(beer: Beer): BeerDetailUI {
    return BeerDetailUI(
        name = beer.name,
        details = beer.description,
        imageUrl = beer.image,
        hops = beer.beerIngredients.hops.joinToString(", "),
        malt = beer.beerIngredients.malt.joinToString(", ")
    )
}