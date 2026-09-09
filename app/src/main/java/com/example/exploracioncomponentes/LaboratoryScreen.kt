package com.example.exploracioncomponentes

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Code
import androidx.compose.material.icons.outlined.ExpandLess
import androidx.compose.material.icons.outlined.ExpandMore
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp

@Composable
fun LaboratoryScreen(padding: PaddingValues) {
    var selectedCategory by remember { mutableStateOf<Category?>(null) }
    var expandedName by remember { mutableStateOf<String?>(null) }
    val visible = componentCatalog.filter { selectedCategory == null || it.category == selectedCategory }
    LazyColumn(
        Modifier.fillMaxSize().padding(padding),
        contentPadding = PaddingValues(horizontal = 18.dp, vertical = 14.dp),
        verticalArrangement = Arrangement.spacedBy(12.dp)
    ) {
        item {
            Row(verticalAlignment = Alignment.CenterVertically) {
                Surface(shape = RoundedCornerShape(16.dp), color = MaterialTheme.colorScheme.primaryContainer) {
                    Icon(Icons.Outlined.Code, null, Modifier.padding(13.dp), tint = MaterialTheme.colorScheme.primary)
                }
                Spacer(Modifier.width(13.dp))
                Column(Modifier.weight(1f)) {
                    Text("Laboratorio", style = MaterialTheme.typography.headlineSmall, fontWeight = FontWeight.Bold)
                    Text("Toca una ficha para probarla", color = MaterialTheme.colorScheme.onSurfaceVariant)
                }
                AssistChip(onClick = {}, label = { Text("33 demos") })
            }
        }
        item {
            LazyRow(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
                item {
                    FilterChip(
                        selected = selectedCategory == null,
                        onClick = { selectedCategory = null },
                        label = { Text("Todos") }
                    )
                }
                items(Category.entries) { category ->
                    FilterChip(
                        selected = selectedCategory == category,
                        onClick = { selectedCategory = category },
                        label = { Text(category.label) }
                    )
                }
            }
        }
        items(visible, key = { it.name }) { component ->
            ComponentCard(
                component = component,
                expanded = expandedName == component.name,
                onToggle = { expandedName = if (expandedName == component.name) null else component.name }
            )
        }
    }
}

@Composable
private fun ComponentCard(component: ComponentInfo, expanded: Boolean, onToggle: () -> Unit) {
    val accent = when (component.category) {
        Category.CONTAINER -> MaterialTheme.colorScheme.primary
        Category.CONTROL -> MaterialTheme.colorScheme.secondary
        Category.NAVIGATION -> MaterialTheme.colorScheme.tertiary
    }
    Card(onClick = onToggle, shape = RoundedCornerShape(22.dp), elevation = CardDefaults.cardElevation(1.dp)) {
        Column(Modifier.padding(18.dp)) {
            Row(verticalAlignment = Alignment.CenterVertically) {
                Surface(shape = RoundedCornerShape(10.dp), color = accent.copy(alpha = .12f)) {
                    Text(
                        "%02d".format(componentCatalog.indexOf(component) + 1),
                        Modifier.padding(horizontal = 9.dp, vertical = 6.dp),
                        color = accent,
                        style = MaterialTheme.typography.labelMedium,
                        fontWeight = FontWeight.Bold
                    )
                }
                Spacer(Modifier.width(12.dp))
                Column(Modifier.weight(1f)) {
                    Text(component.name, style = MaterialTheme.typography.titleMedium, fontWeight = FontWeight.Bold)
                    Text(component.category.label, style = MaterialTheme.typography.labelSmall, color = accent)
                }
                Icon(if (expanded) Icons.Outlined.ExpandLess else Icons.Outlined.ExpandMore, null)
            }
            Spacer(Modifier.height(10.dp))
            Text(component.description, style = MaterialTheme.typography.bodyMedium, color = MaterialTheme.colorScheme.onSurfaceVariant)
            Spacer(Modifier.height(10.dp))
            Row(horizontalArrangement = Arrangement.spacedBy(7.dp)) {
                component.attributes.forEach { SuggestionChip(onClick = {}, label = { Text(it) }) }
            }
            if (expanded) {
                HorizontalDivider(Modifier.padding(vertical = 14.dp))
                Surface(Modifier.fillMaxWidth(), shape = RoundedCornerShape(16.dp), color = MaterialTheme.colorScheme.surfaceVariant) {
                    Box(Modifier.padding(14.dp)) { ComponentPreview(component.name) }
                }
            }
        }
    }
}
