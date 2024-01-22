package uk.acm0x.beers

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import uk.acm0x.beers.presentation.MainScreen
import uk.acm0x.beers.presentation.MainScreenNavigator
import uk.acm0x.beers.presentation.MainViewModel
import uk.acm0x.beers.presentation.theme.HoldMyBeerTheme

class MainActivity : ComponentActivity() {
    private val viewModel: MainViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            val navigator = MainScreenNavigator(viewModel)
            HoldMyBeerTheme {
                MainScreen(navigator)
            }
        }
    }
}


