package com.example.laboratorio_1

data class Estudiante(
    val nombre: String,
    val carnet: String,
    val asignatura: String
)

fun main() {
    println("=== SISTEMA DE ASISTENCIA: CICLO 01 ===\n")

    val ciclo01 = mutableListOf(
        Estudiante("Juan Perez", "00301524", "Programación de Dispositivos Móviles"),
        Estudiante("Ana Gomez", "00308325", "Programación de Dispositivos Móviles"),
        Estudiante("Carlos Lopez", "00309926", "Programación de Dispositivos Móviles"),
        Estudiante("Maria Diaz", "00301123", "Análisis Numérico"),
        Estudiante("Luis Ramos", "00302224", "Análisis Numérico"),
        Estudiante("Sofia Castro", "00304425", "Análisis Numérico"),
        Estudiante("Diego Ruiz", "00305526", "Análisis Numérico")
    )

    var opcion = 0

    do {
        println("\n--- MENÚ DE DOCENTE ---")
        println("1. Ver toda la lista del Ciclo 01")
        println("2. Filtrar estudiantes por asignatura")
        println("3. Matricular nuevo estudiante (Input)")
        println("4. Salir")
        print("Selecciona una opción: ")

        opcion = readlnOrNull()?.toIntOrNull() ?: 0

        when (opcion) {
            1 -> {
                println("\n--- LISTA COMPLETA CICLO 01 ---")
                for (estudiante in ciclo01) {
                    println("- ${estudiante.nombre} | Carnet: ${estudiante.carnet} | Asignatura: ${estudiante.asignatura}")
                }
            }
            2 -> {
                print("\nIngresa el nombre de la asignatura a buscar (ej. Programación de Dispositivos Móviles): ")
                val busqueda = readlnOrNull() ?: ""

                val filtrados = ciclo01.filter { it.asignatura.equals(busqueda, ignoreCase = true) }

                if (filtrados.isNotEmpty()) {
                    println("\n--- ESTUDIANTES EN: ${busqueda.uppercase()} ---")
                    for (estudiante in filtrados) {
                        println("- ${estudiante.nombre} (Carnet: ${estudiante.carnet})")
                    }
                    println("Total en esta materia: ${filtrados.size} estudiantes.")
                } else {
                    println("\nNo se encontraron estudiantes matriculados en esa asignatura.")
                }
            }
            3 -> {
                print("\nIngresa el nombre del estudiante: ")
                val nombre = readlnOrNull()?.takeIf { it.isNotBlank() } ?: "Desconocido"

                print("Ingresa los últimos 4 dígitos del carnet (ej. 8324, donde 24 es el año): ")
                val ultimosDigitos = readlnOrNull()?.takeIf { it.isNotBlank() } ?: "0000"

                val carnetCompleto = "0030$ultimosDigitos"

                print("Ingresa la asignatura: ")
                val asignatura = readlnOrNull()?.takeIf { it.isNotBlank() } ?: "Materia Genérica"

                ciclo01.add(Estudiante(nombre, carnetCompleto, asignatura))
                println("\n¡Éxito! Estudiante $nombre matriculado con el carnet $carnetCompleto.")
            }
            4 -> println("\nCerrando sistema de asistencia... ¡Feliz día, docente!")
            else -> println("\nOpción inválida. Intenta de nuevo.")
        }
    } while (opcion != 4)
}