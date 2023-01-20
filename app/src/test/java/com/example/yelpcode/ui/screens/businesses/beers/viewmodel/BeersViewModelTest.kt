package com.example.yelpcode.ui.screens.businesses.beers.viewmodel

import com.example.yelpcode.domain.model.BusinessModel
import com.example.yelpcode.domain.repository.BusinessRepository
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runBlockingTest
import org.junit.Assert
import org.junit.Assert.assertTrue
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.mockito.kotlin.mock
import org.mockito.kotlin.whenever

@ExperimentalCoroutinesApi
class BeersViewModelTest {

    @get:Rule
    val mainCoroutineRule = MainCoroutineRule()

    private val repository = mock<BusinessRepository>()
    private lateinit var subject: BeersViewModel

    /*private val testDispatcher = AppDispatchers(
        IO = TestCoroutineDispatcher()
    )*/

    @Before
    fun setup() {
        subject = BeersViewModel(repository)
    }

    @Test
    fun `loading state works`() {
        runBlockingTest {
            whenever(repository.fetchBusiness("nothing")).thenReturn(emptyList<BusinessModel?>().toMutableList())
            assertTrue(subject.state.value.loading)
            assertTrue(subject.state.value.data.isEmpty())
        }
    }

}