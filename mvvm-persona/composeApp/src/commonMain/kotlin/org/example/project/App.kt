package org.example.project

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.safeContentPadding
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import org.jetbrains.compose.resources.painterResource
import org.jetbrains.compose.ui.tooling.preview.Preview

import mvvm_persona.composeapp.generated.resources.Res
import mvvm_persona.composeapp.generated.resources.compose_multiplatform
import org.example.project.viewmodel.PersonaViewModel

/**
 * Función composable principal de la aplicación.
 *
 * Esta función representa la pantalla principal que permite al usuario
 * crear y visualizar instancias de Persona. Utiliza el patrón MVVM donde:
 * - La UI observa el estado del ViewModel a través de StateFlow
 * - Los eventos de usuario se comunican al ViewModel a través de llamadas a métodos
 * - El ViewModel actualiza el estado, lo que automáticamente refresca la UI
 */

@Composable
fun App() {
    // Obtener instancia del ViewModel. Compose lo crea automáticamente si no existe.
    val viewModel: PersonaViewModel = viewModel()

    // Observar el estado del ViewModel. collectAsState() convierte StateFlow a State.
    val uiState by viewModel.uiState.collectAsState()

    MaterialTheme {
        Column(
            modifier = Modifier
                .background(MaterialTheme.colorScheme.primaryContainer)
                .safeContentPadding()
                .fillMaxSize()
                .padding(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Top
        ) {
            // Título de la aplicación
            Text(
                text = "Gestión de Persona - MVVM",
                style = MaterialTheme.typography.headlineMedium,
                color = MaterialTheme.colorScheme.onPrimaryContainer
            )

            Spacer(modifier = Modifier.height(24.dp))

            // Campo de texto para el nombre
            OutlinedTextField(
                value = uiState.nombre,
                onValueChange = { viewModel.actualizarNombre(it) },
                label = { Text("Nombre") },
                modifier = Modifier.fillMaxWidth(),
                singleLine = true
            )

            Spacer(modifier = Modifier.height(16.dp))

            // Campo de texto para el apellido
            OutlinedTextField(
                value = uiState.apellido,
                onValueChange = { viewModel.actualizarApellido(it) },
                label = { Text("Apellido") },
                modifier = Modifier.fillMaxWidth(),
                singleLine = true
            )

            Spacer(modifier = Modifier.height(16.dp))

            // Campo de texto para la edad (solo números)
            OutlinedTextField(
                value = uiState.edadString,
                onValueChange = { viewModel.actualizarEdad(it) },
                label = { Text("Edad") },
                modifier = Modifier.fillMaxWidth(),
                singleLine = true,
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number)
            )

            Spacer(modifier = Modifier.height(24.dp))

            // Fila con botones de acción
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(16.dp)
            ) {
                // Botón para crear persona
                Button(
                    onClick = { viewModel.crearPersona() },
                    modifier = Modifier.weight(1f)
                ) {
                    Text("Crear Persona")
                }

                // Botón para limpiar formulario
                Button(
                    onClick = { viewModel.limpiarFormulario() },
                    modifier = Modifier.weight(1f)
                ) {
                    Text("Limpiar")
                }
            }

            Spacer(modifier = Modifier.height(24.dp))

            // Mostrar mensaje de error si existe
            uiState.errorMensaje?.let { error ->
                Text(
                    text = error,
                    color = MaterialTheme.colorScheme.error,
                    style = MaterialTheme.typography.bodyMedium
                )
                Spacer(modifier = Modifier.height(16.dp))
            }

            // Mostrar la persona creada si existe
            uiState.personaActual?.let { persona ->
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .background(
                            MaterialTheme.colorScheme.surfaceVariant,
                            shape = MaterialTheme.shapes.medium
                        )
                        .padding(16.dp),
                    horizontalAlignment = Alignment.Start
                ) {
                    Text(
                        text = "Persona Creada:",
                        style = MaterialTheme.typography.titleMedium,
                        color = MaterialTheme.colorScheme.onSurfaceVariant
                    )
                    Spacer(modifier = Modifier.height(8.dp))
                    Text(
                        text = persona.toString(),
                        style = MaterialTheme.typography.bodyLarge,
                        color = MaterialTheme.colorScheme.onSurfaceVariant
                    )
                }
            }
        }
    }
    }

