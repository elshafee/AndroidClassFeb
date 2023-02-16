package com.elshafee.androiden.firestoreapp.ui

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.elshafee.androiden.databinding.ActivityFireStoreAppBinding
import com.elshafee.androiden.firestoreapp.model.ProfileDetails
import com.google.firebase.firestore.SetOptions
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.firestore.ktx.toObject
import com.google.firebase.ktx.Firebase
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.tasks.await
import kotlinx.coroutines.withContext

class FireStoreApp : AppCompatActivity() {
    lateinit var binding: ActivityFireStoreAppBinding
    private val prfileDetailsCollectionRef = Firebase.firestore.collection("profileDetails")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityFireStoreAppBinding.inflate(layoutInflater)
        setContentView(binding.root)

        realTimeChanges()
        binding.btnSave.setOnClickListener {
            val oldProfileDetails = getOldProfileDetails()
            saveProfileDetailsData(oldProfileDetails)
        }
        binding.btnRetrieve.setOnClickListener {
            retreiveProfileDetails()
        }

        binding.btnupdate.setOnClickListener {
            val oldProfileDetails = getOldProfileDetails()
            val newProfileDetails = getNewProfileDetails()
            updateProfileDetails(oldProfileDetails, newProfileDetails)
        }
        binding.btnDelete.setOnClickListener {
            val oldProfileDetails = getOldProfileDetails()
            deleteProfileDetails(oldProfileDetails)
        }
    }

    fun getOldProfileDetails(): ProfileDetails {
        val oldFirstName = binding.etOldFirstName.text.toString()
        val oldLastName = binding.etOldLastName.text.toString()
        val oldAge = binding.etOldAge.text.toString().toInt()
        return ProfileDetails(oldFirstName, oldLastName, oldAge)
    }

    fun getNewProfileDetails(): Map<String, Any> {
        val newFirstName = binding.etNewFirstName.text.toString()
        val newLastName = binding.etNewLastName.text.toString()
        val newAge = binding.etNewAge.text.toString()

        val map = mutableMapOf<String, Any>()

        if (newFirstName.isNotEmpty()) {
            map["firstNAme"] = newFirstName
        }

        if (newLastName.isNotEmpty()) {
            map["lastName"] = newLastName
        }
        if (newAge.isNotEmpty()) {
            map["age"] = newAge.toInt()
        }

        return map

    }

    private fun saveProfileDetailsData(profileDetails: ProfileDetails) {
        CoroutineScope(Dispatchers.IO).launch {
            try {
                prfileDetailsCollectionRef.add(profileDetails)
                withContext(Dispatchers.Main) {
                    Toast.makeText(this@FireStoreApp, "Data Saved Successfully", Toast.LENGTH_LONG)
                        .show()
                }

            } catch (e: Exception) {
                withContext(Dispatchers.Main) {
                    Toast.makeText(this@FireStoreApp, e.message, Toast.LENGTH_LONG).show()
                }
            }
        }
    }

    private fun retreiveProfileDetails() {
        CoroutineScope(Dispatchers.IO).launch {
            try {
                val querSnapshot = prfileDetailsCollectionRef.get().await()
                val stringBuilder = StringBuilder()
                for (document in querSnapshot.documents) {
                    val profileInformation = document.toObject<ProfileDetails>()
                    stringBuilder.append("$profileInformation\n")
                }

                withContext(Dispatchers.Main) {
                    binding.tvShowProfileDetails.text = stringBuilder.toString()
                }

            } catch (e: Exception) {
                withContext(Dispatchers.Main) {
                    Toast.makeText(this@FireStoreApp, e.message, Toast.LENGTH_LONG).show()
                }
            }
        }
    }

    private fun deleteProfileDetails(profileDetails: ProfileDetails) {
        CoroutineScope(Dispatchers.IO).launch {
            val profileDetailsQuery = prfileDetailsCollectionRef
                .whereEqualTo("firstNAme", profileDetails.firstNAme)
                .whereEqualTo("lastName", profileDetails.lastName)
                .whereEqualTo("age", profileDetails.age)
                .get().await()

            if (profileDetailsQuery.documents.isNotEmpty()) {
                for (document in profileDetailsQuery)
                    try {
                        prfileDetailsCollectionRef.document(document.id).delete().await()

                        withContext(Dispatchers.Main) {
                            Toast.makeText(
                                this@FireStoreApp,
                                "Data Deleted Successfully",
                                Toast.LENGTH_LONG
                            ).show()

                        }
                    } catch (e: Exception) {

                        Toast.makeText(this@FireStoreApp, e.message, Toast.LENGTH_LONG).show()

                    }
            } else {
                Toast.makeText(
                    this@FireStoreApp,
                    "No Profile Details Matched in Query",
                    Toast.LENGTH_LONG
                ).show()

            }
        }
    }

    private fun updateProfileDetails(
        oldProfileDetails: ProfileDetails,
        newProfileDetails: Map<String, Any>
    ) {
        CoroutineScope(Dispatchers.IO).launch {
            val profileDetailsQuery = prfileDetailsCollectionRef
                .whereEqualTo("firstNAme", oldProfileDetails.firstNAme)
                .whereEqualTo("lastName", oldProfileDetails.lastName)
                .whereEqualTo("age", oldProfileDetails.age)
                .get().await()

            if (profileDetailsQuery.documents.isNotEmpty()) {
                for (document in profileDetailsQuery)
                    try {
                        prfileDetailsCollectionRef.document(document.id).set(
                            newProfileDetails,
                            SetOptions.merge()
                        ).await()
                        Toast.makeText(
                            this@FireStoreApp,
                            "Data Updated Successfully",
                            Toast.LENGTH_LONG
                        ).show()
                    } catch (e: Exception) {

                        Toast.makeText(this@FireStoreApp, e.message, Toast.LENGTH_LONG).show()

                    }
            }
        }
    }

    private fun realTimeChanges() {
        prfileDetailsCollectionRef.addSnapshotListener { querySnapshot, firebaseFirestoreExceptions ->
            firebaseFirestoreExceptions?.let {
                Toast.makeText(this@FireStoreApp, it.message, Toast.LENGTH_LONG).show()
                return@addSnapshotListener
            }
            querySnapshot?.let {
                val stringBuilder = StringBuilder()
                for (document in it) {
                    val profileDetails = document.toObject<ProfileDetails>()
                    stringBuilder.append("$profileDetails\n")
                }

                binding.tvShowProfileDetails.text = stringBuilder.toString()
            }
        }
    }
}