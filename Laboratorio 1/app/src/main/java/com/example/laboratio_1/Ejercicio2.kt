package com.example.laboratorio_1

class Calculadora(
    val marca: String,
    val aniosDeVida: Int,
    var precio: Double
) {
    fun sumar(num1: Double, num2: Double): Double {
        return num1 + num2
    }

    fun restar(num1: Double, num2: Double): Double {
        return num1 - num2
    }

    fun multiplicar(num1: Double, num2: Double): Double {
        return num1 * num2
    }

    fun dividir(num1: Double, num2: Double): Double {
        if (num2 == 0.0) {
            println("¡Alerta de error! Operación inválida: No se puede dividir entre cero.")
            return 0.0
        }
        return num1 / num2
    }

    fun mostrarDetalles() {
        println("\n--- Ficha Técnica ---")
        println("Marca: $marca (Inmutable)")
        println("Vida útil: $aniosDeVida años (Inmutable)")
        println("Precio de mercado: $$precio")
        println("---------------------\n")
    }
}

fun main() {
    println("=== LANZAMIENTO DE NUEVA CALCULADORA ===\n")

    print("Ingresa la marca de la calculadora: ")
    val marca = readlnOrNull()?.takeIf { it.isNotBlank() } ?: "Genérica"

    print("Ingresa los años de vida útil (número entero): ")
    val anios = readlnOrNull()?.toIntOrNull() ?: 5

    print("Ingresa el precio inicial: $")
    val precio = readlnOrNull()?.toDoubleOrNull() ?: 15.99

    val miCalculadora = Calculadora(marca, anios, precio)
    var opcion = 0

    do {
        println("--- MENÚ DE LA CALCULADORA ---")
        println("1. Sumar")
        println("2. Restar")
        println("3. Multiplicar")
        println("4. Dividir")
        println("5. Ver detalles del aparato")
        println("6. Cambiar precio de mercado")
        println("7. Apagar y salir")
        print("Selecciona una operación: ")

        opcion = readlnOrNull()?.toIntOrNull() ?: 0

        when (opcion) {
            1, 2, 3, 4 -> {
                print("Ingresa el primer número: ")
                val num1 = readlnOrNull()?.toDoubleOrNull() ?: 0.0

                print("Ingresa el segundo número: ")
                val num2 = readlnOrNull()?.toDoubleOrNull() ?: 0.0

                when (opcion) {
                    1 -> println("Resultado de la suma: ${miCalculadora.sumar(num1, num2)}\n")
                    2 -> println("Resultado de la resta: ${miCalculadora.restar(num1, num2)}\n")
                    3 -> println("Resultado de la multiplicación: ${miCalculadora.multiplicar(num1, num2)}\n")
                    4 -> println("Resultado de la división: ${miCalculadora.dividir(num1, num2)}\n")
                }
            }
            5 -> miCalculadora.mostrarDetalles()
            6 -> {
                print("Ingresa el nuevo precio: $")
                val nuevoPrecio = readlnOrNull()?.toDoubleOrNull()

                if (nuevoPrecio != null && nuevoPrecio >= 0) {
                    miCalculadora.precio = nuevoPrecio
                    println("¡Precio actualizado exitosamente!\n")
                } else {
                    println("Error: Valor inválido.\n")
                }
            }
            7 -> println("Apagando calculadora... ¡Hasta pronto!")
            else -> println("Opción inválida. Intenta de nuevo.\n")
        }
    } while (opcion != 7)
}