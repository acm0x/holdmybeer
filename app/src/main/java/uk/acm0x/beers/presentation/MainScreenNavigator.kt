package uk.acm0x.beers.presentation

import androidx.compose.runtime.Composable
import androidx.lifecycle.compose.collectAsStateWithLifecycle

class MainScreenNavigator(private val viewModel: MainViewModel): AppNavigator {

    override val onListItemClick: (BeerUI) -> Unit = { beerUI ->
        viewModel.showDetails(beerUI)
    }

    override fun navigateBackToList() {
        viewModel.showList()
    }

    override fun start() {
        viewModel.showList()
    }

    @Composable
    override fun dataState(): DataState {
        return viewModel.screenState.collectAsStateWithLifecycle().value
    }

}