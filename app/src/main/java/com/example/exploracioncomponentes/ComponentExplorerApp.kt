package com.example.exploracioncomponentes

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Home
import androidx.compose.material.icons.outlined.Widgets
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.graphics.vector.ImageVector

private enum class Destination(val label: String, val icon: ImageVector) {
    HOME("Inicio", Icons.Outlined.Home), LAB("Laboratorio", Icons.Outlined.Widgets)
}

@Composable
fun ComponentExplorerApp() {
    var destination by rememberSaveable { mutableStateOf(Destination.HOME) }
    Scaffold(
        bottomBar = {
            NavigationBar {
                Destination.entries.forEach { item ->
                    NavigationBarItem(
                        selected = destination == item,
                        onClick = { destination = item },
                        icon = { Icon(item.icon, null) },
                        label = { Text(item.label) }
                    )
                }
            }
        }
    ) { padding ->
        when (destination) {
            Destination.HOME -> HomeScreen(padding) { destination = Destination.LAB }
            Destination.LAB -> LaboratoryScreen(padding)
        }
    }
}
