package com.example.proyectosisvitag3.ui.viewmodel // O tu paquete de ViewModels

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import com.example.proyectosisvitag3.ui.view.TestItemData // Importa tu data class

class AgregaTestViewModel : ViewModel() {
    var nombreTest by mutableStateOf("")
    var autorTest by mutableStateOf("")
    var descripcionTest by mutableStateOf("")
    var numeroPreguntas by mutableStateOf("") // Se tomará como String inicialmente para el TextField

    // Puedes añadir lógica de validación aquí si es necesario
    fun esFormularioValido(): Boolean {
        return nombreTest.isNotBlank() &&
                autorTest.isNotBlank() &&
                descripcionTest.isNotBlank() &&
                numeroPreguntas.toIntOrNull() != null && (numeroPreguntas.toIntOrNull() ?: 0) > 0
    }

    fun crearTestItemData(): TestItemData? {
        if (!esFormularioValido()) return null
        // En una app real, el ID sería generado por la base de datos o de forma única
        // Por ahora, usamos un timestamp o un UUID simple para simular.
        return TestItemData(
            id = System.currentTimeMillis().toInt(), // ID simple para el ejemplo
            nombreTest = nombreTest,
            autorTest = autorTest,
            descripcionTest = descripcionTest,
            numberOfQuestions = numeroPreguntas.toInt()
        )
    }

    fun limpiarCampos() {
        nombreTest = ""
        autorTest = ""
        descripcionTest = ""
        numeroPreguntas = ""
    }
}
