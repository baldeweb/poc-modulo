package com.example.unit_test.detail.viewmodel

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.example.details.presentation.DetailPokemonViewModel
import com.example.details_public.data.model.DetailPokemonDTO
import com.example.unit_test.TestCoroutineRule
import com.example.unit_test.detail.repository.FakeDetailPokemonRepository
import com.example.unit_test.getOrAwaitValueTest
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
    fun `get pokemon detail MAY BE null`() {
        testCoroutineRule.runBlockingTest {
//            `when`(fakeRepository.getPokemonDetail("")).thenReturn(Response.success(null))
            val responseError = Response.error<DetailPokemonDTO>(500, mock(ResponseBody::class.java))
            doReturn(responseError).`when`(fakeRepository).getPokemonDetail("")
            viewModel.getPokemonDetail("")
            val pokemonDetailValue = viewModel.pokemonDetail.getOrAwaitValueTest()
            Truth.assertThat(pokemonDetailValue)
        }
    }
}