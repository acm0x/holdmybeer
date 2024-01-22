package uk.acm0x.beers

import io.mockk.coEvery
import io.mockk.every
import io.mockk.impl.annotations.MockK
import io.mockk.mockk
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import uk.acm0x.beers.model.Beer
import uk.acm0x.beers.model.BeerIngredients
import uk.acm0x.beers.model.BeerRepository
import uk.acm0x.beers.presentation.BeerUI
import uk.acm0x.beers.presentation.DataState
import uk.acm0x.beers.presentation.MainViewModel

class MainViewModelTest {

    @get:Rule
    val mainDispatcherRule = MainDispatcherRule()

    private lateinit var viewModel: MainViewModel

    @MockK
    private val mockBeerRepository: BeerRepository = mockk()

    @MockK
    private val mockBeerUI: BeerUI = mockk()

    private val beer = Beer(
        id = 1,
        name = "Beer1",
        description = "Beer description",
        image = "image url",
        beerIngredients = BeerIngredients(
            malt = listOf("malt"), hops = listOf("hops")
        )
    )

    @Before
    fun setup() {
        viewModel = MainViewModel(mockBeerRepository)
    }

    @Test
    fun justStart() = runTest {
        assertEquals(DataState.Loading, viewModel.screenState.value)
    }

    @Test
    fun dataLoaded() = runTest {
        coEvery { mockBeerRepository.getBeers() } returns listOf(beer)

        viewModel.showList()

        assert(viewModel.screenState.value is DataState.ShowList)
        val value = viewModel.screenState.value as DataState.ShowList
        assertEquals(1, value.beers.size)
        assertEquals("Beer1", value.beers[0].name)
        assertEquals(1, value.beers[0].id)

    }

    @Test
    fun detailsClicked() = runTest {
        every { mockBeerUI.id } returns 1
        coEvery { mockBeerRepository.getBeer(any()) } returns beer

        viewModel.showDetails(mockBeerUI)

        assert(viewModel.screenState.value is DataState.ShowDetail)
        val value = viewModel.screenState.value as DataState.ShowDetail
        assertEquals("Beer1", value.beer.name)

    }

    @Test
    fun error() = runTest {
        coEvery { mockBeerRepository.getBeer(any()) } returns null
        every { mockBeerUI.id } returns 1

        viewModel.showDetails(mockBeerUI)

        assert(viewModel.screenState.value is DataState.ShowError)
    }


}