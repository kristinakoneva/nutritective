package com.kristinakoneva.nutritective.domain.user.mappers

import com.google.firebase.auth.FirebaseUser
import com.kristinakoneva.nutritective.domain.user.models.User

fun FirebaseUser.toUser() = User(
    uid = uid,
    email = email.orEmpty(),
    displayName = displayName.orEmpty()
)
