package com.example.exploracioncomponentes

enum class Category(val label: String) { CONTAINER("Contenedores"), CONTROL("Controles"), NAVIGATION("Navegación") }

data class ComponentInfo(
    val name: String,
    val category: Category,
    val description: String,
    val attributes: List<String>
)

val componentCatalog = listOf(
    ComponentInfo("LazyColumn", Category.CONTAINER, "Lista vertical que compone solo los elementos visibles.", listOf("items", "contentPadding")),
    ComponentInfo("LazyRow", Category.CONTAINER, "Lista horizontal eficiente para carruseles.", listOf("items", "horizontalArrangement")),
    ComponentInfo("Grid", Category.CONTAINER, "Organiza contenido en filas y columnas.", listOf("columns", "spacing")),
    ComponentInfo("ConstraintLayout", Category.CONTAINER, "Posiciona elementos mediante relaciones entre referencias.", listOf("constrainAs", "linkTo")),
    ComponentInfo("Scaffold", Category.CONTAINER, "Estructura una pantalla con barras y contenido.", listOf("topBar", "content")),
    ComponentInfo("Surface", Category.CONTAINER, "Aplica color, forma y elevación Material.", listOf("shape", "tonalElevation")),
    ComponentInfo("Chip", Category.CONTAINER, "Acción compacta para filtros o etiquetas.", listOf("label", "leadingIcon")),
    ComponentInfo("BackdropScaffold", Category.CONTAINER, "Relaciona un panel posterior con contenido frontal.", listOf("backLayer", "frontLayer")),
    ComponentInfo("FlowRow", Category.CONTAINER, "Distribuye elementos en filas y crea saltos automáticos.", listOf("spacing", "maxItemsInEachRow")),
    ComponentInfo("FlowColumn", Category.CONTAINER, "Distribuye elementos en columnas adaptables.", listOf("spacing", "maxItemsInEachColumn")),
    ComponentInfo("AlertDialog", Category.CONTROL, "Solicita confirmación sin abandonar la pantalla.", listOf("title", "confirmButton")),
    ComponentInfo("Card", Category.CONTROL, "Agrupa información relacionada en una superficie.", listOf("colors", "elevation")),
    ComponentInfo("Checkbox", Category.CONTROL, "Permite activar varias opciones independientes.", listOf("checked", "onCheckedChange")),
    ComponentInfo("FloatingActionButton", Category.CONTROL, "Destaca la acción principal de la pantalla.", listOf("onClick", "containerColor")),
    ComponentInfo("Icon", Category.CONTROL, "Representa acciones o estados de forma visual.", listOf("imageVector", "tint")),
    ComponentInfo("Image", Category.CONTROL, "Muestra recursos visuales con escala configurable.", listOf("painter", "contentScale")),
    ComponentInfo("ProgressBar", Category.CONTROL, "Comunica el avance de una tarea.", listOf("progress", "color")),
    ComponentInfo("RadioButton", Category.CONTROL, "Selecciona una opción dentro de un grupo.", listOf("selected", "onClick")),
    ComponentInfo("Slider", Category.CONTROL, "Elige un valor continuo dentro de un rango.", listOf("value", "valueRange")),
    ComponentInfo("Spacer", Category.CONTROL, "Reserva espacio para ordenar visualmente el contenido.", listOf("width", "height")),
    ComponentInfo("Switch", Category.CONTROL, "Alterna una preferencia entre dos estados.", listOf("checked", "thumbContent")),
    ComponentInfo("TopAppBar", Category.CONTROL, "Presenta título y acciones en la zona superior.", listOf("title", "actions")),
    ComponentInfo("BottomNavigation", Category.NAVIGATION, "Cambia entre destinos principales desde la base.", listOf("selected", "icon")),
    ComponentInfo("Dialog", Category.NAVIGATION, "Presenta contenido modal totalmente personalizable.", listOf("onDismissRequest", "content")),
    ComponentInfo("Divider", Category.NAVIGATION, "Separa grupos de contenido con una línea sutil.", listOf("thickness", "color")),
    ComponentInfo("DropdownMenu", Category.NAVIGATION, "Muestra opciones ancladas a un control.", listOf("expanded", "onDismissRequest")),
    ComponentInfo("LazyVerticalGrid", Category.NAVIGATION, "Cuadrícula vertical optimizada para colecciones.", listOf("columns", "items")),
    ComponentInfo("NavigationRail", Category.NAVIGATION, "Navegación lateral para pantallas anchas.", listOf("selected", "label")),
    ComponentInfo("OutlinedTextField", Category.NAVIGATION, "Captura texto con etiqueta y borde definido.", listOf("value", "supportingText")),
    ComponentInfo("Pager", Category.NAVIGATION, "Permite recorrer páginas mediante gestos.", listOf("state", "pageCount")),
    ComponentInfo("Snackbar", Category.NAVIGATION, "Informa resultados breves sin interrumpir la tarea.", listOf("hostState", "showSnackbar")),
    ComponentInfo("TabRow", Category.NAVIGATION, "Organiza contenido relacionado en pestañas.", listOf("selectedTabIndex", "indicator")),
    ComponentInfo("Tooltip", Category.NAVIGATION, "Explica una acción al mantener pulsado un elemento.", listOf("tooltip", "positionProvider"))
)
