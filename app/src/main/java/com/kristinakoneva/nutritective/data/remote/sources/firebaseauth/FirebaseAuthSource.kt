package com.kristinakoneva.nutritective.data.remote.sources.firebaseauth

import com.google.firebase.auth.FirebaseUser

interface FirebaseAuthSource {

    suspend fun registerUser(email: String, password: String)

    suspend fun loginUser(email: String, password: String)

    suspend fun getCurrentUser(): FirebaseUser?

    suspend fun updateUserDisplayName(displayName: String)

    suspend fun logoutUser()
}
