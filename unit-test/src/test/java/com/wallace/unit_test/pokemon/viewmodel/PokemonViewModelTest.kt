package com.wallace.unit_test.pokemon.viewmodel

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.wallace.pokemon.presentation.PokemonViewModel
import com.wallace.shared_domain.detail_pokemon.DetailPokemonDTO
import com.wallace.shared_domain.pokemon.PokemonDTO
import com.wallace.unit_test.TestCoroutineRule
import com.wallace.unit_test.getOrAwaitValueTest
import com.wallace.unit_test.pokemon.dao.FakePokemonDAO
import com.wallace.unit_test.pokemon.repository.FakePokemonRepository
import com.google.common.truth.Truth.assertThat
import kotlinx.coroutines.ExperimentalCoroutinesApi
import okhttp3.ResponseBody
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.rules.TestRule
import org.junit.runner.RunWith
import org.mockito.Mockito.*
import org.mockito.Spy
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

    @Spy
    private lateinit var fakeRepository: FakePokemonRepository

    @Spy
    private lateinit var fakeDAO: FakePokemonDAO

    private lateinit var viewModel: PokemonViewModel

    @Before
    fun setUp() {
        viewModel = PokemonViewModel(fakeRepository, fakeDAO)
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
    fun `get pokemon MAY BE gets an error`() {
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
    fun `get pokemon MAY BE gets an INTERNAL ERROR SERVER`() {
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