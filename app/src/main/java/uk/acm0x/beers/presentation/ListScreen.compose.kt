package uk.acm0x.beers.presentation

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import uk.acm0x.beers.presentation.theme.HoldMyBeerTheme


@Composable
fun ListScreen(beers: List<BeerUI>, navigator: AppNavigator) {
    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
    ) {
        items(items = beers) {
            BeerListItem(it, navigator.onListItemClick)
        }
    }
}


@Composable
fun BeerListItem(beer: BeerUI, onListItemClick: (BeerUI) -> Unit = {}) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(20.dp)
            .clickable {
                onListItemClick(beer)
            },
        verticalAlignment = Alignment.CenterVertically
    ) {
        DownloadAndShowImage(
            imageUrl = beer.imageUrl,
            modifier = Modifier.size(80.dp, 180.dp))
        Text(
            text = beer.name, style = MaterialTheme.typography.bodyLarge,
            modifier = Modifier
                .padding(end = 20.dp)
                .width(80.dp)
        )
        Text(text = beer.details, style = MaterialTheme.typography.bodySmall)
    }
}


@Preview(showBackground = true)
@Composable
fun BeerListItemPreview() {
    HoldMyBeerTheme {
        BeerListItem(beer = BeerUI(1, "quite a long text that should split", "details", "v"))
    }
}

class PreviewNavigator(private val dataState: DataState = DataState.Loading) : AppNavigator {
    override val onListItemClick: (BeerUI) -> Unit = {}

    override fun navigateBackToList() {
    }

    override fun start() {
    }

    @Composable
    override fun dataState() = dataState

}
