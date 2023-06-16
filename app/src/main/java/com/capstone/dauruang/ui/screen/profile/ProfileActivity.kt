package com.capstone.dauruang.ui.screen.profile

import android.content.Context
import android.content.Intent
import android.graphics.ImageDecoder
import android.net.Uri
import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.CreditCard
import androidx.compose.material.icons.filled.DarkMode
import androidx.compose.material.icons.filled.Info
import androidx.compose.material.icons.filled.LocationOn
import androidx.compose.material.icons.filled.Logout
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Devices
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.core.content.ContentProviderCompat.requireContext
import coil.compose.AsyncImagePainter.State.Empty.painter
import coil.compose.ImagePainter
import coil.compose.rememberAsyncImagePainter
import com.capstone.dauruang.R
import com.capstone.dauruang.data.dummy.HistoryDataProvider
import com.capstone.dauruang.data.local.PreferenceAuthManager
import com.capstone.dauruang.model.User
import com.capstone.dauruang.ui.components.content.SettingItem
import com.capstone.dauruang.ui.components.content.TitlePage
import com.capstone.dauruang.ui.screen.history.HistoryActivity
import com.capstone.dauruang.ui.screen.history.HistoryContent
import com.capstone.dauruang.ui.screen.welcome.WelcomeActivity
import com.capstone.dauruang.ui.theme.DauRuangTheme
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.suspendCancellableCoroutine
import kotlin.coroutines.resumeWithException

class ProfileActivity: ComponentActivity() {

    private lateinit var auth: FirebaseAuth
    private lateinit var firebaseUser: FirebaseUser

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        auth = FirebaseAuth.getInstance()
        firebaseUser = FirebaseAuth.getInstance().currentUser!!

        val name = firebaseUser.displayName
        val email = firebaseUser.email
        val photo = firebaseUser.photoUrl

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


        fun logOut(){
            val googleSignInClient = GoogleSignIn.getClient(this, GoogleSignInOptions.DEFAULT_SIGN_IN)
            googleSignInClient.signOut()
                .addOnCompleteListener { task ->
                    if (task.isSuccessful) {
                        Intent(this, WelcomeActivity::class.java).also {
                            it.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
                            startActivity(it)
                            PreferenceAuthManager.clearToken(this)
                            Toast.makeText(this, "Logout Berhasil", Toast.LENGTH_SHORT).show()
                        }
                    } else {
                        Toast.makeText(this, "Logout Gagal", Toast.LENGTH_SHORT).show()
                    }
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
                        ProfileContent(
                            navigateBack = { onBackPressed() },
                            nameProfile = if(name != null) name.toString() else userData?.fullname.toString(),
                            imageProfile = if(photo != null) rememberAsyncImagePainter(photo) else painterResource(
                                R.drawable.image_default
                            ) ,
                            emailProfile = email.toString(),
                            point = 23,
                            logOut = {
                                auth.signOut()
                                val uid = null
                                logOut()
                            }
                        )
                    }
                }
            }

        }

    }

    companion object {
        fun newIntent(context: Context) = Intent(context, ProfileActivity::class.java)
    }
}

@Composable
fun ProfileContent(
    modifier: Modifier = Modifier
        .fillMaxSize()
        .background(Color.White),
    imageProfile: Painter,
    nameProfile: String,
    emailProfile: String,
    point: Int = 0,
    navigateBack: () -> Unit,
    color: Color = colorResource(R.color.text_primary),
    logOut: () -> Unit
){
    Column(
        modifier = modifier
            .padding(horizontal = 16.dp, vertical = 12.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        TitlePage(
            navigateBack = navigateBack,
            title = "My Profile"
        )
        Column(
            modifier = Modifier
                .padding(top = 24.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Image(
                painter = imageProfile,
                contentDescription = "image_profile",
                modifier = Modifier
                    .size(80.dp)
                    .clip(shape = RoundedCornerShape(100.dp))
            )
            Spacer(modifier = Modifier.padding(vertical = 4.dp))
            Text(
                text = nameProfile,
                fontWeight = FontWeight.Bold,
                fontSize = 20.sp,
                color = color
            )
            Text(
                text = emailProfile,
                fontWeight = FontWeight.Normal,
                fontSize = 16.sp,
                color = color
            )
            Text(
                text = "$point Point",
                fontWeight = FontWeight.Light,
                fontSize = 14.sp,
                color = color
            )
        }

        Column(
            modifier = Modifier
                .fillMaxWidth()
                .align(Alignment.Start),
            horizontalAlignment = Alignment.Start
        ) {
            Text(
                text = "My Profile Setting",
                modifier = Modifier
                    .padding(top = 24.dp, bottom = 4.dp),
                fontSize = 14.sp,
                fontWeight = FontWeight.Bold,
                color = color
            )
            SettingItem(
                icons = Icons.Filled.Person,
                title = "Ubah profile",
                navigateToMenu = {}
            )
            SettingItem(
                icons = Icons.Filled.LocationOn,
                title = "Ubah default location",
                navigateToMenu = {}
            )
            SettingItem(
                icons = Icons.Filled.CreditCard,
                title = "Ubah data bank",
                navigateToMenu = {}
            )

            Text(
                text = "Setting App",
                modifier = Modifier
                    .padding(top = 28.dp, bottom = 4.dp),
                fontSize = 14.sp,
                fontWeight = FontWeight.Bold,
                color = color
            )
            SettingItem(
                icons = Icons.Filled.DarkMode,
                title = "Ubah theme",
                navigateToMenu = {}
            )
            SettingItem(
                icons = Icons.Filled.Info,
                title = "Info app",
                navigateToMenu = {}
            )
            SettingItem(
                icons = Icons.Filled.Logout,
                title = "Logout App",
                navigateToMenu = logOut
            )
        }
    }


}

@Preview(showBackground = true, device = Devices.PIXEL_3)
@Composable
fun ProfileScreenPreview(){
//    ProfileContent(
//        navigateBack = {},
//        imageProfile = painterResource(R.drawable.david),
//        nameProfile = "David Nasrulloh"
//    )
}

interface NameCustomCallback {
    fun onNameCustomReceived(name: String)
}