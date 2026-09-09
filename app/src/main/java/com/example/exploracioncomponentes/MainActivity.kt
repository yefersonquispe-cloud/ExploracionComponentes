@file:OptIn(
    androidx.compose.material3.ExperimentalMaterial3Api::class,
    androidx.compose.foundation.ExperimentalFoundationApi::class,
    androidx.compose.foundation.layout.ExperimentalLayoutApi::class
)

package com.example.exploracioncomponentes

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items as gridItems
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import androidx.constraintlayout.compose.ConstraintLayout
import com.example.exploracioncomponentes.ui.theme.ExploracionComponentesTheme
import kotlinx.coroutines.launch

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ExploracionComponentesTheme {
                Surface(Modifier.fillMaxSize()) { ComponentExplorerApp() }
            }
        }
    }
}

@Preview(name = "Exploración de componentes", showBackground = true, showSystemUi = true)
@Composable
private fun AppPreview() {
    ExploracionComponentesTheme(darkTheme = false) {
        ComponentExplorerApp()
    }
}

@Composable
fun ComponentPreview(name: String) = when (name) {
    "LazyColumn" -> LazyColumnDemo(); "LazyRow" -> LazyRowDemo(); "Grid" -> GridDemo()
    "ConstraintLayout" -> ConstraintLayoutDemo(); "Scaffold" -> ScaffoldDemo(); "Surface" -> SurfaceDemo()
    "Chip" -> ChipDemo(); "BackdropScaffold" -> BackdropScaffoldDemo(); "FlowRow" -> FlowRowDemo()
    "FlowColumn" -> FlowColumnDemo(); "AlertDialog" -> AlertDialogDemo(); "Card" -> CardDemo()
    "Checkbox" -> CheckboxDemo(); "FloatingActionButton" -> FloatingActionButtonDemo(); "Icon" -> IconDemo()
    "Image" -> ImageDemo(); "ProgressBar" -> ProgressBarDemo(); "RadioButton" -> RadioButtonDemo()
    "Slider" -> SliderDemo(); "Spacer" -> SpacerDemo(); "Switch" -> SwitchDemo(); "TopAppBar" -> TopAppBarDemo()
    "BottomNavigation" -> BottomNavigationDemo(); "Dialog" -> DialogDemo(); "Divider" -> DividerDemo()
    "DropdownMenu" -> DropdownMenuDemo(); "LazyVerticalGrid" -> LazyVerticalGridDemo(); "NavigationRail" -> NavigationRailDemo()
    "OutlinedTextField" -> OutlinedTextFieldDemo(); "Pager" -> PagerDemo(); "Snackbar" -> SnackbarDemo()
    "TabRow" -> TabRowDemo(); "Tooltip" -> TooltipDemo(); else -> Unit
}

@Composable fun LazyColumnDemo() = LazyColumn(Modifier.height(112.dp)) {
    items(listOf("Perfil", "Notificaciones", "Privacidad")) { DemoRow(it) }
}

@Composable fun LazyRowDemo() = LazyRow(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
    items(listOf("Kotlin", "Compose", "Material")) { AssistChip({}, { Text(it) }) }
}

@Composable fun GridDemo() = Column(verticalArrangement = Arrangement.spacedBy(7.dp)) {
    repeat(2) { row -> Row(horizontalArrangement = Arrangement.spacedBy(7.dp)) {
        repeat(3) { cell -> DemoTile("${row * 3 + cell + 1}", Modifier.weight(1f)) }
    } }
}

@Composable fun ConstraintLayoutDemo() = ConstraintLayout(Modifier.fillMaxWidth().height(74.dp)) {
    val (text, button) = createRefs()
    Text("Alineado por restricciones", Modifier.constrainAs(text) { start.linkTo(parent.start); top.linkTo(parent.top) })
    Button({}, Modifier.constrainAs(button) { end.linkTo(parent.end); bottom.linkTo(parent.bottom) }) { Text("Acción") }
}

@Composable fun ScaffoldDemo() = Column(Modifier.fillMaxWidth().height(108.dp), Arrangement.SpaceBetween) {
    Surface(color = MaterialTheme.colorScheme.primaryContainer) { Text("Barra superior", Modifier.fillMaxWidth().padding(9.dp), fontWeight = FontWeight.Bold) }
    Text("Contenido", Modifier.padding(horizontal = 9.dp))
    Text("Barra inferior", Modifier.fillMaxWidth().padding(9.dp), color = MaterialTheme.colorScheme.primary)
}

@Composable fun SurfaceDemo() = Surface(
    Modifier.fillMaxWidth(), shape = RoundedCornerShape(18.dp), tonalElevation = 6.dp, shadowElevation = 2.dp
) { Text("Superficie con forma y elevación", Modifier.padding(18.dp), fontWeight = FontWeight.Medium) }

@Composable fun ChipDemo() = AssistChip(onClick = {}, label = { Text("Compose") }, leadingIcon = { Icon(Icons.Outlined.Star, null) })

@Composable fun BackdropScaffoldDemo() = Column {
    Surface(color = MaterialTheme.colorScheme.primary, shape = RoundedCornerShape(topStart = 14.dp, topEnd = 14.dp)) {
        Text("Panel posterior", Modifier.fillMaxWidth().padding(12.dp), color = Color.White)
    }
    Surface(Modifier.fillMaxWidth(), shape = RoundedCornerShape(bottomStart = 14.dp, bottomEnd = 14.dp), tonalElevation = 6.dp) {
        Text("Contenido frontal", Modifier.padding(18.dp), fontWeight = FontWeight.Bold)
    }
}

@Composable fun FlowRowDemo() = FlowRow(horizontalArrangement = Arrangement.spacedBy(6.dp)) {
    listOf("UI", "UX", "Android", "Kotlin").forEach { SuggestionChip({}, { Text(it) }) }
}

@Composable fun FlowColumnDemo() = FlowColumn(Modifier.height(90.dp), verticalArrangement = Arrangement.spacedBy(5.dp)) {
    repeat(6) { Surface(shape = RoundedCornerShape(8.dp), color = MaterialTheme.colorScheme.primaryContainer) { Text("Item ${it + 1}", Modifier.padding(7.dp)) } }
}

@Composable fun AlertDialogDemo() {
    var open by remember { mutableStateOf(false) }; Button({ open = true }) { Text("Abrir alerta") }
    if (open) AlertDialog({ open = false }, { Button({ open = false }) { Text("Aceptar") } }, title = { Text("Confirmación") }, text = { Text("Este es un AlertDialog de Material 3.") })
}

@Composable fun CardDemo() = Card(colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.secondaryContainer)) {
    Row(Modifier.fillMaxWidth().padding(16.dp), verticalAlignment = Alignment.CenterVertically) { Icon(Icons.Outlined.AutoAwesome, null); Spacer(Modifier.width(10.dp)); Text("Contenido agrupado", fontWeight = FontWeight.Bold) }
}

@Composable fun CheckboxDemo() {
    var checked by remember { mutableStateOf(true) }
    Row(verticalAlignment = Alignment.CenterVertically) { Checkbox(checked, { checked = it }); Text(if (checked) "Seleccionado" else "Sin seleccionar") }
}

@Composable fun FloatingActionButtonDemo() = Row(verticalAlignment = Alignment.CenterVertically) {
    FloatingActionButton({}) { Icon(Icons.Outlined.Add, null) }; Spacer(Modifier.width(14.dp)); Text("Acción principal")
}

@Composable fun IconDemo() = Row(verticalAlignment = Alignment.CenterVertically) {
    Icon(Icons.Outlined.Favorite, "Favorito", Modifier.size(40.dp), tint = MaterialTheme.colorScheme.primary); Spacer(Modifier.width(12.dp)); Text("Vector escalable con tinte")
}

@Composable fun ImageDemo() = Row(verticalAlignment = Alignment.CenterVertically) {
    Image(
        painterResource(R.drawable.developer_workspace), "Persona programando en Android Studio",
        Modifier.size(96.dp).clip(RoundedCornerShape(16.dp)), contentScale = ContentScale.Crop
    )
    Spacer(Modifier.width(14.dp)); Column { Text("Imagen real", fontWeight = FontWeight.Bold); Text("Fotografía local con recorte y descripción accesible.", style = MaterialTheme.typography.bodySmall) }
}

@Composable fun ProgressBarDemo() = Column {
    LinearProgressIndicator({ .72f }, Modifier.fillMaxWidth()); Spacer(Modifier.height(8.dp)); Text("Progreso · 72%", style = MaterialTheme.typography.labelMedium)
}

@Composable fun RadioButtonDemo() {
    var selected by remember { mutableIntStateOf(0) }
    Row { listOf("Claro", "Oscuro").forEachIndexed { index, label -> Row(verticalAlignment = Alignment.CenterVertically) { RadioButton(selected == index, { selected = index }); Text(label) } } }
}

@Composable fun SliderDemo() {
    var value by remember { mutableFloatStateOf(65f) }
    Column { Slider(value, { value = it }, valueRange = 0f..100f); Text("Valor: ${value.toInt()}") }
}

@Composable fun SpacerDemo() = Row(verticalAlignment = Alignment.CenterVertically) {
    DemoTile("A", Modifier.weight(1f)); Spacer(Modifier.width(28.dp)); DemoTile("B", Modifier.weight(1f))
}

@Composable fun SwitchDemo() {
    var active by remember { mutableStateOf(true) }
    Row(verticalAlignment = Alignment.CenterVertically) { Switch(active, { active = it }); Spacer(Modifier.width(12.dp)); Text(if (active) "Notificaciones activas" else "Notificaciones pausadas") }
}

@Composable fun TopAppBarDemo() = TopAppBar(
    title = { Text("Explorador") }, navigationIcon = { IconButton({}) { Icon(Icons.Outlined.Menu, null) } },
    actions = { IconButton({}) { Icon(Icons.Outlined.MoreVert, null) } }, colors = TopAppBarDefaults.topAppBarColors(containerColor = Color.Transparent)
)

@Composable fun BottomNavigationDemo() {
    var selected by remember { mutableIntStateOf(0) }
    NavigationBar { listOf(Icons.Outlined.Home, Icons.Outlined.Search, Icons.Outlined.Person).forEachIndexed { index, icon ->
        NavigationBarItem(selected == index, { selected = index }, { Icon(icon, null) })
    } }
}

@Composable fun DialogDemo() {
    var open by remember { mutableStateOf(false) }; OutlinedButton({ open = true }) { Text("Mostrar diálogo") }
    if (open) Dialog({ open = false }) { Card(shape = RoundedCornerShape(24.dp)) { Column(Modifier.padding(24.dp)) { Text("Contenido personalizado", fontWeight = FontWeight.Bold); Spacer(Modifier.height(14.dp)); Button({ open = false }) { Text("Cerrar") } } } }
}

@Composable fun DividerDemo() = Column {
    Text("Preferencias"); HorizontalDivider(Modifier.padding(vertical = 12.dp)); Text("Cuenta")
}

@Composable fun DropdownMenuDemo() {
    var open by remember { mutableStateOf(false) }; var value by remember { mutableStateOf("Tecnología") }
    Box { OutlinedButton({ open = true }) { Text(value); Icon(Icons.Outlined.ArrowDropDown, null) }
        DropdownMenu(open, { open = false }) { listOf("Android", "Kotlin", "Compose").forEach { DropdownMenuItem({ Text(it) }, { value = it; open = false }) } }
    }
}

@Composable fun LazyVerticalGridDemo() = LazyVerticalGrid(
    GridCells.Fixed(3), Modifier.height(108.dp), horizontalArrangement = Arrangement.spacedBy(6.dp), verticalArrangement = Arrangement.spacedBy(6.dp)
) { gridItems((1..6).toList()) { DemoTile(it.toString(), Modifier.height(48.dp)) } }

@Composable fun NavigationRailDemo() {
    var selected by remember { mutableIntStateOf(0) }
    NavigationRail(Modifier.height(185.dp)) { listOf(Icons.Outlined.Home, Icons.Outlined.Favorite, Icons.Outlined.Settings).forEachIndexed { index, icon ->
        NavigationRailItem(selected == index, { selected = index }, { Icon(icon, null) })
    } }
}

@Composable fun OutlinedTextFieldDemo() {
    var text by remember { mutableStateOf("") }
    OutlinedTextField(text, { text = it }, Modifier.fillMaxWidth(), label = { Text("Nombre") }, supportingText = { Text("Escribe al menos 3 caracteres") }, singleLine = true)
}

@Composable fun PagerDemo() {
    val state = rememberPagerState { 3 }
    Column { HorizontalPager(state, Modifier.fillMaxWidth().height(82.dp)) { DemoTile("Página ${it + 1}", Modifier.fillMaxSize()) }; Text("${state.currentPage + 1} de 3", style = MaterialTheme.typography.labelMedium) }
}

@Composable fun SnackbarDemo() {
    val host = remember { SnackbarHostState() }; val scope = rememberCoroutineScope()
    Column { Button({ scope.launch { host.showSnackbar("Cambios guardados") } }) { Text("Mostrar mensaje") }; SnackbarHost(host) }
}

@Composable fun TabRowDemo() {
    var selected by remember { mutableIntStateOf(0) }
    Column { TabRow(selected) { listOf("Diseño", "Código").forEachIndexed { index, label -> Tab(selected == index, { selected = index }, text = { Text(label) }) } }; Text("Contenido: ${if (selected == 0) "Diseño" else "Código"}", Modifier.padding(12.dp)) }
}

@Composable fun TooltipDemo() = TooltipBox(
    TooltipDefaults.rememberPlainTooltipPositionProvider(), { PlainTooltip { Text("Añadir a favoritos") } }, rememberTooltipState()
) { IconButton({}) { Icon(Icons.Outlined.Info, "Información") } }

@Composable
private fun DemoRow(label: String) = Row(Modifier.fillMaxWidth().padding(7.dp), verticalAlignment = Alignment.CenterVertically) {
    Icon(Icons.Outlined.CheckCircle, null, Modifier.size(18.dp), tint = MaterialTheme.colorScheme.primary); Spacer(Modifier.width(9.dp)); Text(label)
}

@Composable
private fun DemoTile(label: String, modifier: Modifier) = Surface(modifier, shape = RoundedCornerShape(12.dp), color = MaterialTheme.colorScheme.primaryContainer) {
    Box(contentAlignment = Alignment.Center) { Text(label, Modifier.padding(8.dp), fontWeight = FontWeight.SemiBold) }
}
