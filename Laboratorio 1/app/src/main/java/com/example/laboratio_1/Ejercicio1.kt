package com.example.laboratorio_1

data class Programa(
    val nombre: String,
    val fechaInstalacion: Int
)

class Computadora(
    var ram: Int,
    var almacenamiento: Int,
    var sistemaOperativo: String
) {
    private var encendida = false
    private val programasInstalados = mutableListOf<Programa>()

    fun encender() {
        if (!encendida) {
            encendida = true
            println("Computadora encendida.")
        } else {
            println("La computadora ya está encendida.")
        }
    }

    fun apagar() {
        if (encendida) {
            encendida = false
            println("Computadora apagada.")
        } else {
            println("La computadora ya está apagada.")
        }
    }

    fun actualizarRam(nuevaRam: Int) {
        if (encendida) {
            ram = nuevaRam
            println("RAM actualizada a $ram GB.")
        } else {
            println("Error: Debes encender la computadora primero.")
        }
    }

    fun actualizarAlmacenamiento(nuevoAlmacenamiento: Int) {
        if (encendida) {
            almacenamiento = nuevoAlmacenamiento
            println("Almacenamiento actualizado a $almacenamiento GB.")
        } else {
            println("Error: Debes encender la computadora primero.")
        }
    }

    fun cambiarSistemaOperativo(nuevoSO: String) {
        if (encendida) {
            sistemaOperativo = nuevoSO
            println("Sistema operativo cambiado a $sistemaOperativo.")
        } else {
            println("Error: Debes encender la computadora primero.")
        }
    }

    fun instalarPrograma(programa: Programa) {
        if (encendida) {
            programasInstalados.add(programa)
            println("Programa '${programa.nombre}' instalado.")
        } else {
            println("Error: Debes encender la computadora primero.")
        }
    }

    fun obtenerProgramasDelAnioActual(): List<Programa> {
        val anioActual = 2026
        return programasInstalados.filter { it.fechaInstalacion == anioActual }
    }

    fun mostrarEstado() {
        println("----------------------------------")
        println("Estado de la computadora:")
        println("Encendida: $encendida")
        println("RAM: $ram GB")
        println("Almacenamiento: $almacenamiento GB")
        println("SO: $sistemaOperativo")
        println("Programas instalados: ${programasInstalados.size}")

        val nombresProgramasNuevos = obtenerProgramasDelAnioActual().map { it.nombre }
        println("Programas del año 2026: $nombresProgramasNuevos")
        println("----------------------------------")
    }
}

fun main() {
    println("=== CREANDO TU COMPUTADORA ===\n")

    print("Ingresa la cantidad de RAM inicial (GB): ")
    val ram = readlnOrNull()?.toIntOrNull() ?: 8

    print("Ingresa el almacenamiento inicial (GB): ")
    val almacenamiento = readlnOrNull()?.toIntOrNull() ?: 512

    print("Ingresa el sistema operativo inicial: ")
    val so = readlnOrNull()?.takeIf { it.isNotBlank() } ?: "Windows 11"

    val miPc = Computadora(ram, almacenamiento, so)
    var opcion = 0

    do {
        println("\n--- MENÚ ---")
        println("1. Encender computadora")
        println("2. Apagar computadora")
        println("3. Actualizar RAM")
        println("4. Actualizar almacenamiento")
        println("5. Cambiar sistema operativo")
        println("6. Instalar programa")
        println("7. Mostrar estado")
        println("8. Salir")
        print("Selecciona una opción: ")

        opcion = readlnOrNull()?.toIntOrNull() ?: 0

        when (opcion) {
            1 -> miPc.encender()
            2 -> miPc.apagar()
            3 -> {
                print("Nueva cantidad de RAM (GB): ")
                val nuevaRam = readlnOrNull()?.toIntOrNull()
                if (nuevaRam != null) miPc.actualizarRam(nuevaRam)
                else println("Valor inválido.")
            }
            4 -> {
                print("Nueva cantidad de almacenamiento (GB): ")
                val nuevoAlmacenamiento = readlnOrNull()?.toIntOrNull()
                if (nuevoAlmacenamiento != null) miPc.actualizarAlmacenamiento(nuevoAlmacenamiento)
                else println("Valor inválido.")
            }
            5 -> {
                print("Nuevo sistema operativo: ")
                val nuevoSO = readlnOrNull()
                if (!nuevoSO.isNullOrBlank()) miPc.cambiarSistemaOperativo(nuevoSO)
                else println("Valor inválido.")
            }
            6 -> {
                print("Nombre del programa: ")
                val nombre = readlnOrNull()
                print("Año de instalación: ")
                val anio = readlnOrNull()?.toIntOrNull()

                if (!nombre.isNullOrBlank() && anio != null) {
                    miPc.instalarPrograma(Programa(nombre, anio))
                } else {
                    println("Datos inválidos. Asegúrate de ingresar el texto y el año correctamente.")
                }
            }
            7 -> miPc.mostrarEstado()
            8 -> println("Saliendo del sistema...")
            else -> println("Opción inválida. Intenta de nuevo.")
        }
    } while (opcion != 8)
}