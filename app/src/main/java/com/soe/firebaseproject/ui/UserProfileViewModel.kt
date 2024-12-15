package com.soe.firebaseproject.ui

import android.net.Uri
import androidx.lifecycle.ViewModel
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import com.google.firebase.storage.ktx.storage
import com.soe.firebaseproject.data.UserProfile


class UserProfileViewModel : ViewModel() {

    private val firestore = Firebase.firestore
    private val storage = Firebase.storage
    private val userProfileCollection = firestore.collection("user_profiles")


    fun addUserProfile(){
        firestore.collection("Books")
            .document().set(mapOf("name" to "Android"))
    }

//    fun getUserProfile(userId: String, onSuccess: (UserProfile) -> Unit, onFailure: (Exception) -> Unit) {
//        userProfileCollection.document(userId).get()
//            .addOnSuccessListener { document ->
//                val profile = document.toObject(UserProfile::class.java)
//                profile?.let(onSuccess) ?: onFailure(Exception("Profile not found"))
//            }
//            .addOnFailureListener(onFailure)
//    }
//
//    fun updateUserProfile(
//        userId: String,
//        userProfile: UserProfile,
//        onSuccess: () -> Unit,
//        onFailure: (Exception) -> Unit
//    ) {
//        userProfileCollection.document(userId).set(userProfile)
//            .addOnSuccessListener { onSuccess() }
//            .addOnFailureListener(onFailure)
//    }
//
//    fun uploadImage(
//        userId: String,
//        imageUri: Uri,
//        isProfileImage: Boolean,
//        onSuccess: (String) -> Unit,
//        onFailure: (Exception) -> Unit
//    ) {
//        val path = if (isProfileImage) "profile_images/$userId" else "backdrop_images/$userId"
//        val storageRef = storage.reference.child(path)
//        storageRef.putFile(imageUri)
//            .addOnSuccessListener { taskSnapshot ->
//                storageRef.downloadUrl
//                    .addOnSuccessListener { onSuccess(it.toString()) }
//                    .addOnFailureListener(onFailure)
//            }
//            .addOnFailureListener(onFailure)
//    }
}
