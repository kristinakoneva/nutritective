package com.kristinakoneva.nutritective.domain.user

import com.kristinakoneva.nutritective.data.remote.sources.firebaseauth.FirebaseAuthSource
import com.kristinakoneva.nutritective.data.remote.sources.firestore.FirestoreSource
import com.kristinakoneva.nutritective.domain.session.SessionRepository
import com.kristinakoneva.nutritective.domain.user.mappers.toUser
import com.kristinakoneva.nutritective.domain.user.models.User
import javax.inject.Inject
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class UserRepositoryImpl @Inject constructor(
    private val authSource: FirebaseAuthSource,
    private val firestoreSource: FirestoreSource,
    private val sessionRepository: SessionRepository
) : UserRepository {
    override suspend fun registerUser(email: String, password: String) = withContext(Dispatchers.IO) {
        authSource.registerUser(email, password)
        firestoreSource.addNewUser(authSource.getCurrentUser()?.uid.orEmpty())
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
        sessionRepository.clearLastScannedFoodProductBarcode()
        authSource.logoutUser()
    }

    override suspend fun getUserAllergensList(): List<String> = withContext(Dispatchers.IO) {
        firestoreSource.getAllergens(getCurrentUser()?.uid.orEmpty())
    }

    override suspend fun setUserAllergensList(allergens: List<String>) {
        firestoreSource.setAllergens(getCurrentUser()?.uid.orEmpty(), allergens)
    }
}
