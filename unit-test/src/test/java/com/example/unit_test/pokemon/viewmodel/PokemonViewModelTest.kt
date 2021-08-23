package com.example.unit_test.pokemon.viewmodel

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.example.details_public.presentation.DetailPokemonNavigation
import com.example.pokemon.presentation.PokemonViewModel
import com.example.unit_test.TestCoroutineRule
import com.example.unit_test.getOrAwaitValueTest
import com.example.unit_test.pokemon.repository.FakePokemonRepository
import com.google.common.truth.Truth.assertThat
import kotlinx.coroutines.ExperimentalCoroutinesApi
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.rules.TestRule
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito.`when`
import org.mockito.Mockito.spy
import org.mockito.junit.MockitoJUnitRunner

@ExperimentalCoroutinesApi
@RunWith(MockitoJUnitRunner::class)
class PokemonViewModelTest {

    @get:Rule
    val testInstantTaskExecutorRule: TestRule = InstantTaskExecutorRule()

    @get:Rule
    val testCoroutineRule = TestCoroutineRule()

    @Mock
    private lateinit var navigation: DetailPokemonNavigation

    private lateinit var fakeRepository: FakePokemonRepository

    private lateinit var viewModel: PokemonViewModel

    @Before
    fun setUp() {
        fakeRepository = spy(FakePokemonRepository())
        viewModel = PokemonViewModel(navigation, fakeRepository)
    }

    @Test
    fun `get pokemon MAY BE NOT null`() {
        testCoroutineRule.runBlockingTest {
            viewModel.getPokemon()
            val pokemonValue = viewModel.pokemon.getOrAwaitValueTest()
            assertThat(pokemonValue).isNotNull()
        }
    }

    @Test
    fun `get pokemon MAY BE null`() {
        testCoroutineRule.runBlockingTest {
            `when`(fakeRepository.getPokemon()).thenReturn(null)
            viewModel.getPokemon()
            val pokemonValue = viewModel.pokemon.getOrAwaitValueTest()
            assertThat(pokemonValue).isNull()
        }
    }
}