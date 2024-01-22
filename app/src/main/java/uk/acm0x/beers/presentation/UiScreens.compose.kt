package uk.acm0x.beers.presentation

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.rememberAsyncImagePainter
import coil.request.ImageRequest
import uk.acm0x.beers.presentation.theme.HoldMyBeerTheme


@Composable
fun MainScreen(
    navigator: AppNavigator
) {
    Surface(
        modifier = Modifier.fillMaxSize(),
        color = MaterialTheme.colorScheme.background
    ) {
        when (val state = navigator.dataState()) {
            DataState.Loading -> LoadingScreen()
            is DataState.ShowDetail -> DetailScreen(state.beer, navigator)
            is DataState.ShowList -> ListScreen(state.beers, navigator)
            is DataState.ShowError -> ErrorScreen(state.message)
        }

    }
    LaunchedEffect(key1 = navigator) {
        navigator.start()
    }
}

@Composable
fun ErrorScreen(message: String) {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.Red.copy(alpha = 0.5f))

    ) {
        Text(text = message, style = MaterialTheme.typography.headlineLarge,
            modifier = Modifier
                .align(Alignment.Center))
    }
}

@Composable
fun LoadingScreen() {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.Gray.copy(alpha = 0.5f))

    ) {

        CircularProgressIndicator(
            modifier = Modifier
                .size(70.dp)
                .align(Alignment.Center)
        )
    }

}

@Composable
fun DownloadAndShowImage(imageUrl: String, modifier: Modifier = Modifier) {
    Image(
        painter = rememberAsyncImagePainter(
            ImageRequest.Builder(LocalContext.current).data(data = imageUrl)
                .apply(block = fun ImageRequest.Builder.() {
                    crossfade(true)
                    placeholder(android.R.drawable.ic_media_play)
                }).build()
        ),
        contentDescription = "Image of the bottle",
        modifier = modifier,
        contentScale = ContentScale.Fit
    )
}

@Preview(showBackground = true)
@Composable
fun LoadingPreview() {
    HoldMyBeerTheme {
        MainScreen(navigator = PreviewNavigator(DataState.Loading))
    }
}

@Preview(showBackground = true)
@Composable
fun ErrorPreview() {
    HoldMyBeerTheme {
        MainScreen(navigator = PreviewNavigator(DataState.ShowError("not good")))
    }
}


@Preview(showBackground = true)
@Composable
fun DetailPreview() {
    HoldMyBeerTheme {
        MainScreen(navigator = PreviewNavigator(
            DataState.ShowDetail(BeerDetailUI("Beer", "details", "v", "malts", "hops"))))
    }
}


@Preview(showBackground = true)
@Composable
fun ListPreview() {
    HoldMyBeerTheme {
        MainScreen(
            navigator = PreviewNavigator(
                DataState.ShowList(
                    listOf(
                        BeerUI(1, "Beer1", "details", "v"),
                        BeerUI(2, "Beer2", "details", "v")
                    )
                )
            )
        )
    }
}



