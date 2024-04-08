package com.kristinakoneva.nutritective.domain.authentication

import com.kristinakoneva.nutritective.data.remote.sources.firebaseauth.FirebaseAuthSource
import com.kristinakoneva.nutritective.domain.authentication.mappers.toUser
import com.kristinakoneva.nutritective.domain.authentication.models.User
import javax.inject.Inject

class AuthRepositoryImpl @Inject constructor(
    private val authSource: FirebaseAuthSource
) : AuthRepository {
    override suspend fun registerUser(email: String, password: String) = authSource.registerUser(email, password)

    override suspend fun loginUser(email: String, password: String) = authSource.loginUser(email, password)

    override suspend fun getCurrentUser(): User? = authSource.getCurrentUser()?.toUser()

    override suspend fun updateUserDisplayName(displayName: String) = authSource.updateUserDisplayName(displayName)

    override suspend fun logoutUser() = authSource.logoutUser()
}
