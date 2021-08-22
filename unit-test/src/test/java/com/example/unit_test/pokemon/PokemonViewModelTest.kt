package com.example.unit_test.pokemon

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.Observer
import com.example.details_public.presentation.DetailPokemonNavigation
import com.example.pokemon.presentation.PokemonViewModel
import com.example.pokemon_public.domain.PokemonRepository
import com.example.pokemon_public.model.PokemonDTO
import com.example.unit_test.TestCoroutineRule
import com.example.unit_test.getOrAwaitValueTest
import junit.framework.TestCase.assertNotNull
import kotlinx.coroutines.ExperimentalCoroutinesApi
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.rules.TestRule
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito.*
import org.mockito.junit.MockitoJUnitRunner
import retrofit2.Response

@ExperimentalCoroutinesApi
@RunWith(MockitoJUnitRunner::class)
class PokemonViewModelTest {

    @get:Rule
    val testInstantTaskExecutorRule: TestRule = InstantTaskExecutorRule()

    @get:Rule
    val testCoroutineRule = TestCoroutineRule()

    @Mock
    private lateinit var navigation: DetailPokemonNavigation

    @Mock
    private lateinit var repository: PokemonRepository

    @Mock
    private lateinit var apiPokemonObserver: PokemonDTO

    @Before
    fun setUp() {

    }

    @Test
    fun getPokemonNOTnull() {
        val mockDTO = mock(PokemonDTO::class.java)
        testCoroutineRule.runBlockingTest {
            doReturn(mockDTO).`when`(repository).getPokemon()

            val viewModel = PokemonViewModel(navigation, repository)
            val pokemonValue = viewModel.pokemon.value
            assertNotNull(pokemonValue)
        }
    }
}