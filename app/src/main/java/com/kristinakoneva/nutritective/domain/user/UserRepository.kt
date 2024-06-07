package com.kristinakoneva.nutritective.domain.user

import com.kristinakoneva.nutritective.domain.user.models.User

interface UserRepository {

    suspend fun registerUser(email: String, password: String)

    suspend fun loginUser(email: String, password: String)

    suspend fun getCurrentUser(): User?

    suspend fun updateUserDisplayName(displayName: String)

    suspend fun logoutUser()

    suspend fun getUserAllergensList(): List<String>

    suspend fun setUserAllergensList(allergens: List<String>)
}
