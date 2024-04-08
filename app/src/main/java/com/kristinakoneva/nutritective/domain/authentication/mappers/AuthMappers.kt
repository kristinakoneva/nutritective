package com.kristinakoneva.nutritective.domain.authentication.mappers

import com.google.firebase.auth.FirebaseUser
import com.kristinakoneva.nutritective.domain.authentication.models.User

fun FirebaseUser.toUser() = User(
    uid = uid,
    email = email.orEmpty(),
    displayName = displayName.orEmpty()
)
