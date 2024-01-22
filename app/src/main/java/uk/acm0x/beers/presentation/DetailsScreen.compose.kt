package uk.acm0x.beers.presentation

import androidx.activity.compose.BackHandler
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import uk.acm0x.beers.presentation.theme.HoldMyBeerTheme


@Composable
fun DetailScreen(beer: BeerDetailUI, navigator: AppNavigator) {
    BackHandler {
        navigator.navigateBackToList()
    }
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(15.dp)


    ) {
        Row(modifier = Modifier.fillMaxWidth()) {
            DownloadAndShowImage(beer.imageUrl, modifier = Modifier.size(160.dp, 240.dp).padding(5.dp))
            Text(
                text = beer.name, style = MaterialTheme.typography.headlineLarge,
                modifier = Modifier
                    .align(Alignment.CenterVertically)
            )

        }
        Text(text = beer.details)
        Text(text = "Malts: ${beer.malt}", modifier = Modifier.padding(10.dp))
        Text(text = "Hops: ${beer.hops}", modifier = Modifier.padding(10.dp))

    }

}

@Composable
@Preview(showBackground = true)
fun DetailsScreenPreview() {
    HoldMyBeerTheme {
        DetailScreen(
            beer = BeerDetailUI("Beer", "details", "v", "malts", "hops"),
            navigator = PreviewNavigator()
        )
    }
}

