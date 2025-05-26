package me.unidok.random

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.selection.SelectionContainer
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Clear
import androidx.compose.material.icons.filled.Menu
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.rememberVectorPainter
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Window
import androidx.compose.ui.window.application

// Функция входа в программу
fun main() = application {
    Window(
        onCloseRequest = ::exitApplication,
        title = "Рандомайзер",
        icon = rememberVectorPainter(Icons.Default.Menu)
    ) {
        MaterialTheme {
            CategorySelectionScreen()
        }
    }
}



// Результативное поле, в котором можно копировать значение и очищать его.
@Composable
fun ResultField(
    result: String?,
    style: TextStyle = LocalTextStyle.current,
    onClear: () -> Unit
) {
    Row(
        verticalAlignment = Alignment.CenterVertically
    ) {
        Column {
            SelectionContainer { // Делает возможным выделить значение
                Text(
                    text = result ?: "",
                    style = style
                )
            }
        }

        Spacer(Modifier.width(20.dp))

        Column {
            if (result != null) { // "Крестик" для очистки значения
                IconButton(
                    onClick = onClear,
                    modifier = Modifier
                        .size(30.dp)
                ) {
                    Icon(
                        imageVector = Icons.Default.Clear,
                        contentDescription = null,
                        tint = MaterialTheme.colors.primary
                    )
                }
            }
        }
    }
}