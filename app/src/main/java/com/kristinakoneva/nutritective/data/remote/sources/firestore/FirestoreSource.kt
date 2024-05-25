package com.kristinakoneva.nutritective.data.remote.sources.firestore

interface FirestoreSource {

    suspend fun addNewUser(userId: String)

    suspend fun setAllergens(userId: String, allergens: List<String>)

    suspend fun getAllergens(userId: String): List<String>
}
