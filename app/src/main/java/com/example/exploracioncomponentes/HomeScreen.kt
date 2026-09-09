package com.example.exploracioncomponentes

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp

@Composable
fun HomeScreen(padding: PaddingValues, openLab: () -> Unit) {
    LazyColumn(
        modifier = Modifier.fillMaxSize().padding(padding),
        contentPadding = PaddingValues(20.dp),
        verticalArrangement = Arrangement.spacedBy(20.dp)
    ) {
        item { HeroCard(openLab) }
        item {
            Text("Tu laboratorio", style = MaterialTheme.typography.headlineSmall, fontWeight = FontWeight.Bold)
            Text("Una guía práctica de los elementos esenciales de Compose.", color = MaterialTheme.colorScheme.onSurfaceVariant)
        }
        item {
            SummaryMetrics()
        }
        item {
            Text("Explora por categoría", style = MaterialTheme.typography.titleLarge, fontWeight = FontWeight.Bold)
            Spacer(Modifier.height(12.dp))
            LazyRow(horizontalArrangement = Arrangement.spacedBy(12.dp)) {
                items(Category.entries) { category -> CategoryCard(category) }
            }
        }
    }
}

@Composable
private fun HeroCard(openLab: () -> Unit) {
    val gradient = Brush.linearGradient(listOf(Color(0xFF3949AB), Color(0xFF00897B)))
    Column(
        Modifier.fillMaxWidth().clip(RoundedCornerShape(30.dp)).background(gradient).padding(24.dp)
    ) {
        Surface(color = Color.White.copy(alpha = .16f), shape = RoundedCornerShape(12.dp)) {
            Text("COMPOSE LAB", Modifier.padding(horizontal = 12.dp, vertical = 7.dp), color = Color.White, style = MaterialTheme.typography.labelMedium)
        }
        Spacer(Modifier.height(24.dp))
        Text("Diseña. Prueba.\nComprende.", color = Color.White, style = MaterialTheme.typography.displaySmall, fontWeight = FontWeight.Bold)
        Spacer(Modifier.height(10.dp))
        Text("33 componentes de Jetpack Compose reunidos.", color = Color.White.copy(alpha = .88f))
        Spacer(Modifier.height(22.dp))
        Button(
            onClick = openLab,
            colors = ButtonDefaults.buttonColors(containerColor = Color.White, contentColor = Color(0xFF303F9F))
        ) {
            Text("Abrir laboratorio", fontWeight = FontWeight.SemiBold)
            Spacer(Modifier.width(8.dp)); Icon(Icons.Outlined.ArrowForward, null)
        }
    }
}

@Composable
private fun SummaryMetrics() {
    Card(Modifier.fillMaxWidth(), shape = RoundedCornerShape(22.dp)) {
        Row(Modifier.fillMaxWidth().padding(vertical = 18.dp), verticalAlignment = Alignment.CenterVertically) {
            Metric("33", "Componentes", Icons.Outlined.Widgets, Modifier.weight(1f))
            VerticalDivider(Modifier.height(62.dp))
            Metric("3", "Categorías", Icons.Outlined.Dashboard, Modifier.weight(1f))
            VerticalDivider(Modifier.height(62.dp))
            Metric("100%", "Interactivo", Icons.Outlined.TouchApp, Modifier.weight(1f))
        }
    }
}

@Composable
private fun Metric(value: String, label: String, icon: ImageVector, modifier: Modifier) {
    Column(modifier, horizontalAlignment = Alignment.CenterHorizontally) {
        Icon(icon, null, tint = MaterialTheme.colorScheme.primary)
        Spacer(Modifier.height(7.dp)); Text(value, style = MaterialTheme.typography.titleLarge, fontWeight = FontWeight.Bold)
        Text(label, maxLines = 1, style = MaterialTheme.typography.labelSmall, color = MaterialTheme.colorScheme.onSurfaceVariant)
    }
}

@Composable
private fun CategoryCard(category: Category) {
    val count = componentCatalog.count { it.category == category }
    val accent = when (category) {
        Category.CONTAINER -> MaterialTheme.colorScheme.primary
        Category.CONTROL -> MaterialTheme.colorScheme.secondary
        Category.NAVIGATION -> MaterialTheme.colorScheme.tertiary
    }
    Card(
        Modifier.width(172.dp), shape = RoundedCornerShape(22.dp),
        colors = CardDefaults.cardColors(containerColor = accent.copy(alpha = .09f))
    ) {
        Column(Modifier.padding(18.dp)) {
            Icon(
                when (category) { Category.CONTAINER -> Icons.Outlined.ViewQuilt; Category.CONTROL -> Icons.Outlined.Tune; Category.NAVIGATION -> Icons.Outlined.Navigation },
                null, tint = accent
            )
            Spacer(Modifier.height(18.dp)); Text(category.label, fontWeight = FontWeight.Bold)
            Text("$count ejemplos", style = MaterialTheme.typography.bodySmall, color = MaterialTheme.colorScheme.onSurfaceVariant)
        }
    }
}
