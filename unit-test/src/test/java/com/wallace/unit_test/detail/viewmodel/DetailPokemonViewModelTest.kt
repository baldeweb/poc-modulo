package com.wallace.unit_test.detail.viewmodel

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.wallace.details.presentation.DetailPokemonViewModel
import com.wallace.shared_domain.detail_pokemon.DetailPokemonDTO
import com.wallace.unit_test.TestCoroutineRule
import com.wallace.unit_test.detail.repository.FakeDetailPokemonRepository
import com.wallace.unit_test.getOrAwaitValueTest
import com.google.common.truth.Truth
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
import java.net.HttpURLConnection.HTTP_INTERNAL_ERROR

@ExperimentalCoroutinesApi
@RunWith(MockitoJUnitRunner::class)
class DetailPokemonViewModelTest {

    @get:Rule
    val testInstantTaskExecutorRule: TestRule = InstantTaskExecutorRule()

    @get:Rule
    val testCoroutineRule = TestCoroutineRule()

    private lateinit var fakeRepository: FakeDetailPokemonRepository

    private lateinit var viewModel: DetailPokemonViewModel

    @Before
    fun setUp() {
        fakeRepository = spy(FakeDetailPokemonRepository())
        viewModel = DetailPokemonViewModel(fakeRepository)
    }

    @Test
    fun `get pokemon detail MAY BE NOT null`() {
        testCoroutineRule.runBlockingTest {
            viewModel.getPokemonDetail("")
            val pokemonDetailValue = viewModel.pokemonDetail.getOrAwaitValueTest()
            Truth.assertThat(pokemonDetailValue).isNotNull()
        }
    }

    @Test
    fun `get pokemon detail MAY BE get error`() {
        testCoroutineRule.runBlockingTest {
            doReturn(
                Response.error<com.wallace.shared_domain.detail_pokemon.DetailPokemonDTO>(
                    HTTP_INTERNAL_ERROR,
                    mock(ResponseBody::class.java)
                )
            )
                .`when`(fakeRepository).getPokemonDetail("")

            viewModel.getPokemonDetail("")

            val pokemonDetailValue = viewModel.pokemonDetail.value
            Truth.assertThat(pokemonDetailValue).isNull()
        }
    }

    @Test
    fun `get pokemon detail MAY BE get error INTERNAL ERROR SERVER`() {
        testCoroutineRule.runBlockingTest {
            doReturn(
                Response.error<com.wallace.shared_domain.detail_pokemon.DetailPokemonDTO>(
                    HTTP_INTERNAL_ERROR,
                    mock(ResponseBody::class.java)
                )
            ).`when`(fakeRepository).getPokemonDetail("")

            viewModel.getPokemonDetail("")

            Truth.assertThat(viewModel.errorResponse.value?.httpCode).isEqualTo(HTTP_INTERNAL_ERROR)
        }
    }
}