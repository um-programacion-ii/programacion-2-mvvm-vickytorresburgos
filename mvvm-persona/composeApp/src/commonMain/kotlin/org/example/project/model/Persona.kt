package org.example.project.model

class Persona (
    val nombre: String,
    val apellido: String,
    val edad: Int
) {
    /**
     * Valida que los datos de la persona sean correctos.
     *
     * @return true si todos los campos son válidos, false en caso contrario.
     */

    fun esValida(): Boolean {
        return nombre.isNotBlank() && apellido.isNotBlank() && edad > 0
    }

    /**
     * Crea una representación en cadena de la persona.
     *
     * @return Una cadena con el formato "Nombre Apellido, Edad años".
     */

    override fun toString(): String {
        return "$nombre $apellido, $edad años"
    }
}