package com.kristinakoneva.nutritective.domain.authentication.models

data class User(
    val uid: String = "",
    val email: String = "",
    val displayName: String = ""
)
