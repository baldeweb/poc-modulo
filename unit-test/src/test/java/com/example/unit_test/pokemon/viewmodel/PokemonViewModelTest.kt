package com.example.unit_test.pokemon.viewmodel

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.example.details_public.data.model.DetailPokemonDTO
import com.example.pokemon.presentation.PokemonViewModel
import com.example.pokemon_public.model.PokemonDTO
import com.example.unit_test.TestCoroutineRule
import com.example.unit_test.getOrAwaitValueTest
import com.example.unit_test.pokemon.repository.FakePokemonRepository
import com.google.common.truth.Truth.assertThat
import kotlinx.coroutines.ExperimentalCoroutinesApi
import okhttp3.ResponseBody
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.rules.TestRule
import org.junit.runner.RunWith
import org.mockito.Mockito.*
import org.mockito.junit.MockitoJUnitRunner
import retrofit2.Response
import java.net.HttpURLConnection

@ExperimentalCoroutinesApi
@RunWith(MockitoJUnitRunner::class)
class PokemonViewModelTest {

    @get:Rule
    val testInstantTaskExecutorRule: TestRule = InstantTaskExecutorRule()

    @get:Rule
    val testCoroutineRule = TestCoroutineRule()

    private lateinit var fakeRepository: FakePokemonRepository

    private lateinit var viewModel: PokemonViewModel

    @Before
    fun setUp() {
        fakeRepository = spy(FakePokemonRepository())
        viewModel = PokemonViewModel(fakeRepository)
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
    fun `get pokemon MAY BE get error`() {
        testCoroutineRule.runBlockingTest {
            doReturn(
                Response.error<PokemonDTO>(
                    HttpURLConnection.HTTP_INTERNAL_ERROR,
                    mock(ResponseBody::class.java)
                )
            ).`when`(fakeRepository).getPokemon()

            viewModel.getPokemon()
            val pokemonDetailValue = viewModel.pokemon.value

            assertThat(pokemonDetailValue).isNull()
        }
    }

    @Test
    fun `get pokemon MAY BE get error INTERNAL ERROR SERVER`() {
        testCoroutineRule.runBlockingTest {
            doReturn(
                Response.error<DetailPokemonDTO>(
                    HttpURLConnection.HTTP_INTERNAL_ERROR,
                    mock(ResponseBody::class.java)
                )
            ).`when`(fakeRepository).getPokemon()

            viewModel.getPokemon()

            assertThat(viewModel.errorResponse.value?.httpCode)
                .isEqualTo(HttpURLConnection.HTTP_INTERNAL_ERROR)
        }
    }
}