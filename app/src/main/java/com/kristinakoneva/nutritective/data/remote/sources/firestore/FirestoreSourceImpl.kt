package com.kristinakoneva.nutritective.data.remote.sources.firestore

import com.google.firebase.firestore.FirebaseFirestore
import javax.inject.Inject
import kotlinx.coroutines.tasks.await

@Suppress("UNCHECKED_CAST")
class FirestoreSourceImpl @Inject constructor(
    private val firestore: FirebaseFirestore
) : FirestoreSource {

    companion object {
        private const val COLLECTION_USERS = "users"
        private const val FIELD_ALLERGENS = "allergens"
    }

    override suspend fun addNewUser(userId: String) {
        firestore.collection(COLLECTION_USERS).document(userId).get().addOnSuccessListener {
            if (!it.exists()) {
                firestore.collection(COLLECTION_USERS).document(userId).set(hashMapOf(FIELD_ALLERGENS to emptyList<String>()))
            }
        }
    }

    override suspend fun setAllergens(userId: String, allergens: List<String>) {
        firestore.collection(COLLECTION_USERS).document(userId).update(FIELD_ALLERGENS, allergens)
    }

    override suspend fun getAllergens(userId: String): List<String> =
        firestore.collection(COLLECTION_USERS).document(userId).get().await()
            .get(FIELD_ALLERGENS) as? List<String> ?: emptyList()
}
