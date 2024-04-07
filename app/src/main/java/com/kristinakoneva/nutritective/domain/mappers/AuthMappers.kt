package com.kristinakoneva.nutritective.domain.mappers

import com.google.firebase.auth.FirebaseUser
import com.kristinakoneva.nutritective.domain.models.User

fun FirebaseUser.toUser() = User(
    uid = uid,
    email = email.orEmpty(),
    displayName = displayName.orEmpty()
)
