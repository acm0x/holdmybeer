package uk.acm0x.beers.presentation

import androidx.compose.runtime.Composable

interface AppNavigator {
    val onListItemClick: (BeerUI) -> Unit
    fun navigateBackToList()
    fun start()

    @Composable
    fun dataState(): DataState

}
