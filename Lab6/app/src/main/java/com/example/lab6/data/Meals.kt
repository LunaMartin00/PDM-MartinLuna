package com.example.lab6.data

data class MealsResponse(
    val meals: List<MealResponse>
)

data class MealResponse(
    val idMeal: String,
    val strMeal: String,
    val strCategory: String,
    val strArea: String,
    val strMealThumb: String,
    val strCountry: String
)

//- Nombre de la receta.
//- Categoría.
//- País de origen.
//- Imagen de la receta