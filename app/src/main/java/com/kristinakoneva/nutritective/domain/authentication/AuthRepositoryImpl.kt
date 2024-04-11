package com.kristinakoneva.nutritective.domain.authentication

import com.kristinakoneva.nutritective.data.remote.sources.firebaseauth.FirebaseAuthSource
import com.kristinakoneva.nutritective.domain.authentication.mappers.toUser
import com.kristinakoneva.nutritective.domain.authentication.models.User
import javax.inject.Inject
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class AuthRepositoryImpl @Inject constructor(
    private val authSource: FirebaseAuthSource
) : AuthRepository {
    override suspend fun registerUser(email: String, password: String) = withContext(Dispatchers.IO) {
        authSource.registerUser(email, password)
    }

    override suspend fun loginUser(email: String, password: String) = withContext(Dispatchers.IO) {
        authSource.loginUser(email, password)
    }

    override suspend fun getCurrentUser(): User? = withContext(Dispatchers.IO) {
        authSource.getCurrentUser()?.toUser()
    }

    override suspend fun updateUserDisplayName(displayName: String) = withContext(Dispatchers.IO) {
        authSource.updateUserDisplayName(displayName)
    }

    override suspend fun logoutUser() = withContext(Dispatchers.IO) {
        authSource.logoutUser()
    }
}
