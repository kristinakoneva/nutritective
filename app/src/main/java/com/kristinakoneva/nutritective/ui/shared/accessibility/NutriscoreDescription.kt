package com.kristinakoneva.nutritective.ui.shared.accessibility

import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import com.kristinakoneva.nutritective.R

@Composable
fun nutriscoreDescription(grade: String?): String =
    if (grade != null) stringResource(R.string.cd_nutriscore_grade, grade) else stringResource(R.string.cd_nutriscore_unavailable)
