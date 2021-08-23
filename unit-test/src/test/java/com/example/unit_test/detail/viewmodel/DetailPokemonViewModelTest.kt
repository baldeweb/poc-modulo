package com.example.unit_test.detail.viewmodel

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.example.details.presentation.DetailPokemonViewModel
import com.example.unit_test.TestCoroutineRule
import com.example.unit_test.detail.repository.FakeDetailPokemonRepository
import com.example.unit_test.getOrAwaitValueTest
import com.google.common.truth.Truth
import kotlinx.coroutines.ExperimentalCoroutinesApi
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.rules.TestRule
import org.junit.runner.RunWith
import org.mockito.Mockito.`when`
import org.mockito.Mockito.spy
import org.mockito.junit.MockitoJUnitRunner

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
            `when`(fakeRepository.getPokemonDetail("")).thenReturn(null)
            viewModel.getPokemonDetail("")
            val pokemonDetailValue = viewModel.pokemonDetail.getOrAwaitValueTest()
            Truth.assertThat(pokemonDetailValue).isNull()
        }
    }
}