package com.example.ui

import androidx.lifecycle.ViewModel
import com.example.model.CafeEvent
import com.example.model.CafeSampleData
import com.example.model.Ingredient
import com.example.model.MenuItem
import com.example.model.Review
import com.example.model.TableReservation
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import java.util.UUID

data class CafeUiState(
    val activeNavIndex: Int = 0,
    val isReservationDialogOpen: Boolean = false,
    val isFullMenuModalOpen: Boolean = false,
    val isAboutStoryModalOpen: Boolean = false,
    val selectedCategoryFilter: String = "All",
    val selectedMenuItem: MenuItem? = null,
    val selectedEvent: CafeEvent? = null,
    val selectedIngredient: Ingredient? = null,
    val recentReservations: List<TableReservation> = listOf(
        TableReservation(
            id = "RES-8492",
            guestName = "Priya Sharma",
            phoneNumber = "9823012345",
            date = "Today",
            timeSlot = "7:30 PM",
            guestCount = 2,
            seatingArea = "Window Aesthetic Table",
            specialRequest = "Romantic candle setup"
        )
    ),
    val toastMessage: String? = null
)

class CafeViewModel : ViewModel() {
    private val _uiState = MutableStateFlow(CafeUiState())
    val uiState: StateFlow<CafeUiState> = _uiState.asStateFlow()

    fun setActiveNav(index: Int) {
        _uiState.update { it.copy(activeNavIndex = index) }
    }

    fun openReservationDialog(selectedEvent: CafeEvent? = null) {
        _uiState.update { 
            it.copy(
                isReservationDialogOpen = true,
                selectedEvent = selectedEvent
            ) 
        }
    }

    fun closeReservationDialog() {
        _uiState.update { it.copy(isReservationDialogOpen = false, selectedEvent = null) }
    }

    fun openFullMenuModal(category: String = "All") {
        _uiState.update { 
            it.copy(
                isFullMenuModalOpen = true,
                selectedCategoryFilter = category
            ) 
        }
    }

    fun closeFullMenuModal() {
        _uiState.update { it.copy(isFullMenuModalOpen = false, selectedMenuItem = null) }
    }

    fun setCategoryFilter(category: String) {
        _uiState.update { it.copy(selectedCategoryFilter = category) }
    }

    fun openMenuItemDetails(item: MenuItem) {
        _uiState.update { it.copy(selectedMenuItem = item) }
    }

    fun closeMenuItemDetails() {
        _uiState.update { it.copy(selectedMenuItem = null) }
    }

    fun openStoryModal() {
        _uiState.update { it.copy(isAboutStoryModalOpen = true) }
    }

    fun closeStoryModal() {
        _uiState.update { it.copy(isAboutStoryModalOpen = false) }
    }

    fun openIngredientDetails(ingredient: Ingredient) {
        _uiState.update { it.copy(selectedIngredient = ingredient) }
    }

    fun closeIngredientDetails() {
        _uiState.update { it.copy(selectedIngredient = null) }
    }

    fun submitReservation(
        name: String,
        phone: String,
        date: String,
        time: String,
        guests: Int,
        seatingArea: String,
        specialRequest: String
    ): TableReservation {
        val newReservation = TableReservation(
            id = "RES-${(1000..9999).random()}",
            guestName = name,
            phoneNumber = phone,
            date = date,
            timeSlot = time,
            guestCount = guests,
            seatingArea = seatingArea,
            specialRequest = specialRequest
        )

        _uiState.update { state ->
            state.copy(
                recentReservations = listOf(newReservation) + state.recentReservations,
                toastMessage = "Table reserved successfully for ${newReservation.guestName}!"
            )
        }
        return newReservation
    }

    fun clearToast() {
        _uiState.update { it.copy(toastMessage = null) }
    }
}
