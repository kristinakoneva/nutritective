package com.kristinakoneva.nutritective.ui.shared.accessibility

import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusDirection
import androidx.compose.ui.focus.FocusManager
import androidx.compose.ui.input.key.Key
import androidx.compose.ui.input.key.KeyEventType
import androidx.compose.ui.input.key.isShiftPressed
import androidx.compose.ui.input.key.key
import androidx.compose.ui.input.key.onPreviewKeyEvent
import androidx.compose.ui.input.key.type
import androidx.compose.ui.semantics.CollectionInfo
import androidx.compose.ui.semantics.CollectionItemInfo
import androidx.compose.ui.semantics.Role
import androidx.compose.ui.semantics.clearAndSetSemantics
import androidx.compose.ui.semantics.collectionInfo
import androidx.compose.ui.semantics.collectionItemInfo
import androidx.compose.ui.semantics.contentDescription
import androidx.compose.ui.semantics.heading
import androidx.compose.ui.semantics.isTraversalGroup
import androidx.compose.ui.semantics.role
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.semantics.stateDescription
import androidx.compose.ui.semantics.traversalIndex

fun Modifier.screenHeading(): Modifier = semantics { heading() }

fun Modifier.sectionHeading(): Modifier = semantics { heading() }

fun Modifier.semanticSection(traversalIndex: Float = 0f): Modifier = semantics {
    isTraversalGroup = true
    this.traversalIndex = traversalIndex
}

fun Modifier.semanticGrid(rowCount: Int, columnCount: Int): Modifier = semantics {
    collectionInfo = CollectionInfo(rowCount = rowCount, columnCount = columnCount)
}

fun Modifier.listItem(index: Int): Modifier = semantics {
    collectionItemInfo = CollectionItemInfo(
        rowIndex = index,
        rowSpan = 1,
        columnIndex = 0,
        columnSpan = 1,
    )
}

fun Modifier.gridItem(index: Int, columnCount: Int): Modifier = semantics {
    collectionItemInfo = CollectionItemInfo(
        rowIndex = index / columnCount,
        rowSpan = 1,
        columnIndex = index % columnCount,
        columnSpan = 1,
    )
}

/** Single TalkBack announcement for a non-interactive content group (children are not read separately). */
fun Modifier.informationSemantics(description: String): Modifier = clearAndSetSemantics {
    contentDescription = description
}

fun Modifier.mergedContentDescription(description: String): Modifier = informationSemantics(description)

/** Hides visual-only children so they are not announced when a parent already has a label. */
fun Modifier.hideFromSemanticTree(): Modifier = clearAndSetSemantics {}

fun Modifier.selectableSemantics(
    label: String,
    selected: Boolean,
    selectedDescription: String,
    notSelectedDescription: String,
): Modifier = semantics {
    contentDescription = label
    stateDescription = if (selected) selectedDescription else notSelectedDescription
    role = Role.Checkbox
}

/** Text fields consume Tab as a character by default; this moves focus instead so keyboard users can leave the field (WCAG 2.1.1, 2.1.2). */
fun Modifier.moveFocusOnTab(focusManager: FocusManager): Modifier = onPreviewKeyEvent { event ->
    if (event.key != Key.Tab) return@onPreviewKeyEvent false
    if (event.type == KeyEventType.KeyDown) {
        focusManager.moveFocus(if (event.isShiftPressed) FocusDirection.Previous else FocusDirection.Next)
    }
    true
}

fun Modifier.actionSemantics(description: String, role: Role = Role.Button): Modifier = semantics {
    contentDescription = description
    this.role = role
}
