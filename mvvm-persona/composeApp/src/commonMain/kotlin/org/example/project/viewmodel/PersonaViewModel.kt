package org.example.project.viewmodel

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import org.example.project.model.Persona

/**
 * ViewModel que maneja el estado y la lógica de negocio para la entidad Persona.
 *
 * Este ViewModel sigue el patrón MVVM y utiliza StateFlow para exponer el estado
 * de manera reactiva. Maneja la creación, actualización y validación de instancias de Persona.
 *
 * Principios MVVM aplicados:
 * - El ViewModel contiene la lógica de negocio y el estado de la aplicación.
 * - No tiene referencias directas a la UI (Views).
 * - Expone el estado a través de StateFlow para que la UI pueda observarlo.
 * - Proporciona métodos para que la UI actualice el estado.
 */

class PersonaViewModel : ViewModel() {

    /**
     * Estado interno mutable del ViewModel.
     * Contiene los campos editables de la persona y mensajes de error.
     */
    private val _uiState = MutableStateFlow(PersonaUiState())
    val uiState: StateFlow<PersonaUiState> = _uiState.asStateFlow()

    /**
     * Actualiza el nombre en el estado.
     *
     * @param nombre El nuevo nombre a establecer.
     */
    fun actualizarNombre(nombre: String) {
        // copy() crea una nueva instancia de PersonaUiState copiando el estado actual
        // pero cambiando solo las propiedades especificadas (nombre y errorMensaje)
        // Esto mantiene la inmutabilidad del estado mientras permite actualizaciones
        _uiState.value = _uiState.value.copy(
            nombre = nombre,
            errorMensaje = null // Limpiar error al cambiar el valor
        )
    }

    /**
     * Actualiza el apellido en el estado.
     *
     * @param apellido El nuevo apellido a establecer.
     */
    fun actualizarApellido(apellido: String) {
        // copy() crea una nueva instancia de PersonaUiState copiando el estado actual
        // pero cambiando solo las propiedades especificadas (apellido y errorMensaje)
        // Esto mantiene la inmutabilidad del estado mientras permite actualizaciones
        _uiState.value = _uiState.value.copy(
            apellido = apellido,
            errorMensaje = null // Limpiar error al cambiar el valor
        )
    }

    /**
     * Actualiza la edad en el estado.
     *
     * @param edadString La edad como cadena de texto. Se intenta convertir a Int.
     */
    fun actualizarEdad(edadString: String) {
        val edad = edadString.toIntOrNull() // Convierte string a Int, retorna null si falla
        // copy() crea una nueva instancia de PersonaUiState copiando el estado actual
        // pero cambiando edadString, edad y limpiando errorMensaje
        _uiState.value = _uiState.value.copy(
            edadString = edadString,
            edad = edad,
            errorMensaje = null // Limpiar error al cambiar el valor
        )
    }

    /**
     * Crea una nueva instancia de Persona con los valores actuales del estado.
     * Valida los datos antes de crear la persona.
     *
     * @return Una instancia de Persona si los datos son válidos, null en caso contrario.
     */
    fun crearPersona(): Persona? {
        val estado = _uiState.value // Captura el estado actual para evitar race conditions

        // Crea una nueva instancia de Persona con los datos del estado actual
        val persona = Persona(
            nombre = estado.nombre,
            apellido = estado.apellido,
            edad = estado.edad ?: 0 // Usa 0 si edad es null (aunque la validación debería prevenir esto)
        )

        // Estructura condicional: si la persona es válida, actualiza el estado exitosamente
        // Si no es válida, actualiza el estado con un mensaje de error
        return if (persona.esValida()) {
            // copy() actualiza el estado: mantiene todos los valores previos pero cambia
            // personaActual a la nueva persona y limpia cualquier errorMensaje
            _uiState.value = estado.copy(
                personaActual = persona,
                errorMensaje = null
            )
            persona // Retorna la persona creada
        } else {
            // copy() actualiza el estado: mantiene todos los valores previos pero establece
            // un mensaje de error para mostrar en la UI
            _uiState.value = estado.copy(
                errorMensaje = "Datos inválidos. Verifique que todos los campos estén completos y la edad sea positiva."
            )
            null // Retorna null indicando que no se pudo crear la persona
        }
    }

    /**
     * Limpia todos los campos del formulario y restablece el estado.
     */
    fun limpiarFormulario() {
        _uiState.value = PersonaUiState()
    }
}

/**
 * Estado de la UI para el ViewModel de Persona.
 *
 * Esta clase de datos representa todo el estado necesario para la interfaz de usuario
 * relacionada con la gestión de personas.
 *
 * @property nombre El nombre actual en el formulario.
 * @property apellido El apellido actual en el formulario.
 * @property edadString La representación en cadena de la edad.
 * @property edad La edad como número entero, null si no es válida.
 * @property personaActual La persona creada más recientemente, null si no hay ninguna.
 * @property errorMensaje Mensaje de error actual, null si no hay errores.
 */
data class PersonaUiState(
    val nombre: String = "",
    val apellido: String = "",
    val edadString: String = "",
    val edad: Int? = null,
    val personaActual: Persona? = null,
    val errorMensaje: String? = null
)