package com.example.myapplication.Screens

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController

@Composable
fun Lists(navController: NavController) {

    var nombre by remember { mutableStateOf("") }
    val listaNombres = remember { mutableStateListOf<String>() }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {

        // Campo de texto
        OutlinedTextField(
            value = nombre,
            onValueChange = { nombre = it },
            label = { Text("Nombre") },
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(10.dp))

        // Botón Guardar
        Button(
            onClick = {
                if (nombre.isNotEmpty()) {
                    listaNombres.add(nombre)
                    nombre = ""
                }
            },
            modifier = Modifier.align(Alignment.CenterHorizontally)
        ) {
            Text("Guardar")
        }

        Spacer(modifier = Modifier.height(20.dp))

        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.fillMaxWidth()
        ) {

            Text(
                text = "Listado de nombres y posición en la lista",
                modifier = Modifier.weight(1f)
            )

            Spacer(modifier = Modifier.width(10.dp))

            Button(
                onClick = {
                    listaNombres.clear()
                }
            ) {
                Text("Limpiar")
            }
        }

        Spacer(modifier = Modifier.height(10.dp))

        // Caja azul de la lista
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(350.dp)
                .border(3.dp, Color.Blue)
                .padding(10.dp)
        ) {

            LazyColumn {

                itemsIndexed(listaNombres) { index, item ->

                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween
                    ) {

                        Text(item)

                        Text("${index + 1}")
                    }

                    Spacer(modifier = Modifier.height(8.dp))
                }
            }
        }
    }
}