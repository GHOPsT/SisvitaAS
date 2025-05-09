package com.example.proyectosisvitag3.ui.theme.iu

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController

@Composable
fun TermsAndConditionsScreen(navController: NavHostController) {
    var accepted by remember { mutableStateOf(false) }

    Surface(
        modifier = Modifier.fillMaxSize(),
        color = Color(0xFFEFFFFF)
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp),
            verticalArrangement = Arrangement.SpaceBetween,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = "Términos y Condiciones",
                style = MaterialTheme.typography.titleLarge.copy(fontSize = 24.sp, color = Color.Black),
                modifier = Modifier.padding(vertical = 16.dp)
            )

            val scrollState = rememberScrollState()
            Column(
                modifier = Modifier
                    .weight(1f)
                    .verticalScroll(scrollState)
                    .padding(16.dp)
            ) {
                Text(
                    text = """
                    1. Aceptación de los Términos
                    Al acceder y utilizar esta aplicación, usted acepta estar sujeto a estos Términos y Condiciones de Uso. Si no está de acuerdo con alguna parte de los términos, no debe utilizar nuestra aplicación.

                    2. Descripción del Servicio
                    Nuestra aplicación proporciona un test para evaluar el nivel de ansiedad del usuario. Este test está diseñado con fines informativos y no debe considerarse como un diagnóstico médico ni sustituir la consulta con un profesional de la salud.

                    3. Uso de la Aplicación
                    Usted acepta utilizar la aplicación únicamente con fines legales y de acuerdo con estos términos. No debe utilizar la aplicación de ninguna manera que pueda dañar, deshabilitar, sobrecargar o deteriorar nuestra aplicación o interferir con el uso de la aplicación por parte de otros.

                    4. Privacidad y Datos Personales
                    Respetamos su privacidad y nos comprometemos a proteger sus datos personales. Al utilizar esta aplicación, usted acepta la recopilación y el uso de la información de acuerdo con nuestra Política de Privacidad, disponible en [Enlace a la Política de Privacidad].

                    5. Limitación de Responsabilidad
                    Esta aplicación y su contenido se proporcionan "tal cual" y "según disponibilidad". No ofrecemos garantías de ningún tipo, ya sean explícitas o implícitas, sobre la integridad, exactitud, fiabilidad, idoneidad o disponibilidad con respecto a la aplicación o la información, productos, servicios o gráficos relacionados contenidos en la aplicación para cualquier propósito. Cualquier confianza que usted deposite en dicha información es, por tanto, estrictamente bajo su propio riesgo.

                    6. Propiedad Intelectual
                    Todos los derechos de propiedad intelectual sobre la aplicación y su contenido, incluyendo pero no limitado a textos, gráficos, logotipos, iconos, imágenes, clips de audio y software, son propiedad de [Nombre de la Empresa] o sus licenciantes.

                    7. Modificaciones de los Términos
                    Nos reservamos el derecho de modificar estos términos en cualquier momento. Las modificaciones serán efectivas inmediatamente después de su publicación en la aplicación. Se recomienda revisar estos términos periódicamente para estar al tanto de cualquier cambio.

                    8. Ley Aplicable
                    Estos Términos y Condiciones se regirán e interpretarán de acuerdo con las leyes de [País/Estado], sin dar efecto a ningún principio de conflicto de leyes.

                    9. Contacto
                    Si tiene alguna pregunta sobre estos Términos y Condiciones, por favor contáctenos en [Dirección de correo electrónico].
                    """.trimIndent(),
                    style = MaterialTheme.typography.bodyMedium.copy(fontSize = 16.sp, color = Color.Gray)
                )
            }

            Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier.padding(vertical = 16.dp)
            ) {
                Checkbox(
                    checked = accepted,
                    onCheckedChange = { accepted = it }
                )
                Spacer(modifier = Modifier.width(8.dp))
                Text(text = "Acepto los términos y condiciones")
            }
            Button(
                onClick = { if (accepted) navController.navigate("testScreen") },
                enabled = accepted,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 32.dp, vertical = 16.dp)
            ) {
                Text(text = "Aceptar y Continuar")
            }
        }
    }
}




