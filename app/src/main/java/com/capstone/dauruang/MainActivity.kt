package com.capstone.dauruang

import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import com.capstone.dauruang.model.User
import com.capstone.dauruang.ui.screen.home.HomeScreen
import com.capstone.dauruang.ui.theme.DauRuangTheme
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.launch
import kotlinx.coroutines.suspendCancellableCoroutine
import kotlin.coroutines.resumeWithException

class MainActivity : ComponentActivity() {

    private lateinit var sharedPreferences: SharedPreferences
    private lateinit var auth: FirebaseAuth
    private lateinit var firebaseUser: FirebaseUser

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val context: Context = this

        auth = FirebaseAuth.getInstance()
        firebaseUser = FirebaseAuth.getInstance().currentUser!!

        sharedPreferences = getSharedPreferences("LoginPrefs", Context.MODE_PRIVATE)
        val token = sharedPreferences.getString("token", null)

        val name = firebaseUser?.displayName
        val photo = firebaseUser?.photoUrl

        // Config untuk custom user
        val database = FirebaseDatabase.getInstance()
        val uid = FirebaseAuth.getInstance().currentUser?.uid
        val userRef = database.getReference("USERS/$uid")

        suspend fun getUserData(userRef: DatabaseReference): User? {
            return suspendCancellableCoroutine { continuation ->
                userRef.addListenerForSingleValueEvent(object : ValueEventListener {
                    override fun onDataChange(dataSnapshot: DataSnapshot) {
                        if (dataSnapshot.exists()) {
                            val userData = dataSnapshot.getValue(User::class.java)
                            continuation.resume(userData, null)
                        } else {
                            println("Data pengguna tidak ditemukan")
                            continuation.resume(null, null)
                        }
                    }

                    override fun onCancelled(databaseError: DatabaseError) {
                        println("Error: ${databaseError.message}")
                        continuation.resumeWithException(databaseError.toException())
                    }
                })
            }
        }



        CoroutineScope(Dispatchers.Main).launch {
            val userData = getUserData(userRef)

            setContent {
                DauRuangTheme {
                    Surface(
                        modifier = Modifier.fillMaxSize(),
                        color = MaterialTheme.colorScheme.background
                    ) {
                        HomeScreen(context, name = if(name != null) name else userData?.fullname, photo = photo)
                    }
                }
            }
        }

    }
}

interface NameCustomCallback {
    fun onNameCustomReceived(name: String)
}