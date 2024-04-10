package com.kristinakoneva.nutritective.ui.shared.composables

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import com.kristinakoneva.nutritective.ui.theme.instruction_step_image_size
import com.kristinakoneva.nutritective.ui.theme.spacing_2

@Composable
fun InstructionStep(
    imageResId: Int,
    description: String
) {
    Row(
        verticalAlignment = Alignment.Top
    ) {
        Image(
            painter = painterResource(id = imageResId),
            contentDescription = "Step image",
            modifier = Modifier.size(instruction_step_image_size)
        )
        Text(
            description, modifier = Modifier
                .fillMaxWidth()
                .padding(start = spacing_2)
        )
    }
}
