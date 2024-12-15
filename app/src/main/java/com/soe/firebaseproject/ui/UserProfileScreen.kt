package com.soe.firebaseproject.ui

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import com.soe.firebaseproject.data.UserProfile

@Composable
fun UserProfileScreen(
    modifier: Modifier = Modifier,
) {

    val firestore = Firebase.firestore
    val list = remember { mutableStateOf(emptyList<UserProfile>()) }

    // does not listen for real-time updates.
//    firestore.collection("Books").get().addOnCompleteListener { task ->
//        if (task.isSuccessful) {
//            list.value = task.result.toObjects(UserProfile::class.java)
//        }
//    }

    // Listen for real-time updates.
    firestore.collection("UserProfiles").addSnapshotListener { value, error ->
        list.value = value?.toObjects(UserProfile::class.java) ?: emptyList()
        if (error != null) {
            return@addSnapshotListener
        }
    }


    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.SpaceBetween
    ) {
        LazyColumn(
            modifier = Modifier
                .fillMaxWidth()
                .fillMaxHeight(0.9f)
        ) {
            items(list.value) {
                Card(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(16.dp)
                ) {
                    Text(
                        modifier = Modifier
                            .fillMaxWidth()
                            .wrapContentWidth(),
                        text = it.name
                    )
                    Text(
                        modifier = Modifier
                            .fillMaxWidth()
                            .wrapContentWidth(),
                        text = it.dateOfBirth
                    )
                    Text(
                        modifier = Modifier
                            .fillMaxWidth()
                            .wrapContentWidth(),
                        text = it.description
                    )
                    Text(
                        modifier = Modifier
                            .fillMaxWidth()
                            .wrapContentWidth(),
                        text = it.profileImageUrl
                    )
                }
            }
        }

        Button(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            onClick = {
                firestore.collection("UserProfiles")
                    .document().set(
                        UserProfile(
                            "David",
                            "31-October-2003",
                            "I am a student",
                            "www.google.com"
                        )
                    )
            }
        ) {
            Text(text = "Add User Profile")
        }
    }
}
