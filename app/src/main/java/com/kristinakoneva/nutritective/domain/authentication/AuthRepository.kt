package com.kristinakoneva.nutritective.domain.authentication

import com.kristinakoneva.nutritective.domain.models.User

interface AuthRepository {

    suspend fun registerUser(email: String, password: String)

    suspend fun loginUser(email: String, password: String)

    suspend fun getCurrentUser(): User?

    suspend fun updateUserDisplayName(displayName: String)

    suspend fun logoutUser()
}
