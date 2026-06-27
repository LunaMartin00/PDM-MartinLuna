package com.example.lab6.ui.viewmodel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope // Importante para usar viewModelScope
import com.example.lab6.api.RetrofitInstance
import com.example.lab6.data.MealResponse
import kotlinx.coroutines.launch

class MealsViewModel : ViewModel() {

    var meals by mutableStateOf<List<MealResponse>>(emptyList())
        private set

    var isLoading by mutableStateOf(false)
        private set

    var errorMessage by mutableStateOf<String?>(null)
        private set

    fun loadMeals() {
        viewModelScope.launch {
            isLoading = true
            errorMessage = null
            try {
                val response = RetrofitInstance.getMeals()
                meals = response
            } catch (e: Exception) {
                errorMessage = "Error al cargar las recetas: ${e.localizedMessage}"
            } finally {
                isLoading = false
            }
        }
    }
}


