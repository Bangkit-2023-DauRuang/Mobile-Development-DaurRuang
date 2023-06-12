package com.capstone.dauruang.ui.screen.welcome

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.OnBackPressedCallback
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Devices
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.capstone.dauruang.MainActivity
import com.capstone.dauruang.R
import com.capstone.dauruang.data.local.PreferenceAuthManager
import com.capstone.dauruang.ui.components.button.ButtonLargePrimary
import com.capstone.dauruang.ui.components.button.ButtonLargeSecondary
import com.capstone.dauruang.ui.components.content.ContentSplash
import com.capstone.dauruang.ui.screen.login.LoginScreen
import com.capstone.dauruang.ui.screen.register.RegisterScreen
import com.capstone.dauruang.ui.theme.DauRuangTheme
import com.google.android.gms.auth.api.identity.BeginSignInRequest
import com.google.android.gms.auth.api.identity.Identity
import com.google.android.gms.auth.api.identity.SignInClient
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInClient
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.android.gms.common.api.ApiException
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.GoogleAuthProvider
import com.google.firebase.database.BuildConfig
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import kotlinx.coroutines.delay

class WelcomeActivity : ComponentActivity() {

    private var auth: FirebaseAuth = FirebaseAuth.getInstance()
    lateinit var ref: DatabaseReference

    private lateinit var googleSignInClient: GoogleSignInClient

    companion object {
        private const val RC_SIGN_IN = 1001
        fun newIntent(context: Context) = Intent(context, WelcomeActivity::class.java)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        ref = FirebaseDatabase.getInstance().getReference("USERS")

        setContent {
            val navController = rememberNavController()
            var isErrorDisplayed by remember { mutableStateOf(false) }

            val backCallback = object : OnBackPressedCallback(true) {
                override fun handleOnBackPressed() {
                    val currentDestination = navController.currentBackStackEntry?.destination?.route

                    if (currentDestination == "welcome") {
                        onBackPressed()
                    } else {
                        navController.popBackStack("welcome", inclusive = false)
                    }
                }
            }

            onBackPressedDispatcher.addCallback(this, backCallback)

            var username by remember { mutableStateOf("") }
            var fullname by remember { mutableStateOf("") }
            var email by remember { mutableStateOf("") }
            var password by remember { mutableStateOf("") }
            var noHp by remember { mutableStateOf("") }

            fun reset() {
                username = "";
                fullname = "";
                email = "";
                password = "";
                noHp = "";
            }

            fun registerKlik() {
                val isFull = username.isEmpty() && fullname.isEmpty() && email.isEmpty() && password.isEmpty() && noHp.isEmpty()

                if (!isFull) {
                    registerUser(
                        username.trim(),
                        fullname.trim(),
                        email.trim(),
                        password.trim(),
                        noHp.trim(),
                        navController
                    )
                    reset()
                } else {
                    isErrorDisplayed = true
                }
            }

            fun loginKlik() {
                val isFull = email.isNotEmpty() && password.isNotEmpty()

                if (isFull) {
                    loginUser(email.trim(), password.trim())
                } else {
                    isErrorDisplayed = true
                }
            }


            fun registerGoogle() {
                val signInIntent = googleSignInClient.signInIntent
                startActivityForResult(signInIntent, RC_SIGN_IN)
            }

            fun loginGoogle() {
                val signInIntent = googleSignInClient.signInIntent
                startActivityForResult(signInIntent, RC_SIGN_IN)
            }

            DauRuangTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    NavHost(navController = navController, startDestination = "welcome") {
                        composable("welcome") {
                            WelcomeScreen(
                                navigateLogin = { navController.navigate("login") },
                                navigateRegister = { navController.navigate("register") },
                            )
                        }
                        composable("login") {
                            LoginScreen(
                                email = email,
                                password = password,
                                onEmailChange = { newValue ->
                                    email = newValue; isErrorDisplayed = false
                                },
                                onPassChange = { newValue ->
                                    password = newValue; isErrorDisplayed = false
                                },

                                isErrorDisplayed = isErrorDisplayed,

                                onLoginClick = {
                                    loginKlik()
                                },

                                onLoginGoogle = {
                                    loginGoogle()
                                },

                                navigateToRegister = {
                                    navController.navigate("register") {
                                        popUpTo("login") {
                                            inclusive = true; isErrorDisplayed = false
                                        }
                                    }
                                },

                                onClear = { email = "" }
                            )
                        }
                        composable("register") {
                            RegisterScreen(
                                username = username,
                                fullname = fullname,
                                email = email,
                                password = password,
                                noHp = noHp,

                                onUsernameChange = { newValue ->
                                    username = newValue; isErrorDisplayed = false
                                },
                                onFullNameChange = { newValue ->
                                    fullname = newValue; isErrorDisplayed = false
                                },
                                onEmailChange = { newValue ->
                                    email = newValue; isErrorDisplayed = false
                                },
                                onPassChange = { newValue ->
                                    password = newValue; isErrorDisplayed = false
                                },
                                onNoHpChange = { newValue ->
                                    noHp = newValue; isErrorDisplayed = false
                                },

                                isErrorDisplayed = isErrorDisplayed,

                                onRegisterClick = {
                                    registerKlik()
                                },

                                onRegisterGoogle = {
                                    registerGoogle()
                                },

                                onClearEmail = { email = "" },
                                onClearUsername = { username = "" },
                                onClearFullname = { fullname = "" },
                                onClearHp = { noHp = "" },
                                navigateToLogin = {
                                    navController.navigate("login") {
                                        popUpTo("register") {
                                            inclusive = true; isErrorDisplayed = false
                                        }
                                    }
                                }
                            )
                        }
                    }
                }
            }
        }


        val gso = GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
            .requestIdToken(getString(R.string.your_web_client_id))
            .requestEmail()
            .build()

        googleSignInClient = GoogleSignIn.getClient(this, gso)

    }


    // Login Google
    @Deprecated("Deprecated in Java")
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if (requestCode == RC_SIGN_IN) {
            // Menangani proses login googlekemarin
            val task = GoogleSignIn.getSignedInAccountFromIntent(data)
            try {
                val account = task.getResult(ApiException::class.java)!!
                PreferenceAuthManager.saveToken(this, account.idToken!!)
                firebaseAuthWithGoogle(account.idToken!!)
            } catch (e: ApiException) {
                e.printStackTrace()
                Toast.makeText(applicationContext, e.localizedMessage, Toast.LENGTH_SHORT).show()
            }

        }
    }

    fun firebaseAuthWithGoogle(idToken: String) {
        val credential = GoogleAuthProvider.getCredential(idToken, null)
        auth.signInWithCredential(credential)
            .addOnCompleteListener(this) { login ->
                if (login.isSuccessful) {
                    Intent(this, MainActivity::class.java).also {
                        it.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
                        startActivity(it)
                        Toast.makeText(this, "Login Google Berhasil", Toast.LENGTH_SHORT).show()
                    }
                } else {
                    Toast.makeText(this, "Login Google Gagal", Toast.LENGTH_SHORT).show()
                }
            }
    }


    // Login custom
    private fun loginUser(email: String, password: String) {
        auth.signInWithEmailAndPassword(email, password)
            .addOnCompleteListener(this) { login ->
                if (login.isSuccessful) {
                    Intent(this, MainActivity::class.java).also {
                        it.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
                        startActivity(it)
                        Toast.makeText(this, "Login Berhasil", Toast.LENGTH_SHORT).show()
                    }
                } else {
                    Toast.makeText(this, "Login Gagal", Toast.LENGTH_SHORT).show()
                }
            }

    }

    // Register custom
    private fun registerUser(
        username: String,
        fullname: String,
        email: String,
        password: String,
        nohp: String,
        navController: NavController
    ) {
        auth.createUserWithEmailAndPassword(email, password)
            .addOnCompleteListener(this) {
                if (it.isSuccessful) {
                    auth.currentUser?.sendEmailVerification()
                    Toast.makeText(this, "Verifikasi Email Anda", Toast.LENGTH_SHORT).show()
                    saveUser(username, fullname, email, password, nohp, navController)
                } else {
                    Toast.makeText(this, "Registrasi Gagal", Toast.LENGTH_SHORT).show()
                }
            }
    }

    private fun saveUser(
        username: String,
        fullname: String,
        email: String,
        password: String,
        nohp: String,
        navController: NavController
    ) {
        val currentUserId = auth.currentUser!!.uid
        ref = FirebaseDatabase.getInstance().reference.child("USERS")
        val userMap = HashMap<String, Any>()

        userMap["id"] = currentUserId
        userMap["username"] = username
        userMap["fullname"] = fullname
        userMap["email"] = email
        userMap["password"] = password
        userMap["nohp"] = nohp
        ref.child(currentUserId).setValue(userMap).addOnCompleteListener {
            if (it.isSuccessful) {
                Toast.makeText(this, "Registrasi Berhasil", Toast.LENGTH_SHORT).show()
                navController.navigate("login") {
                    popUpTo("register") { inclusive = true }
                }
            } else {
                Toast.makeText(this, "Registrasi Gagal", Toast.LENGTH_SHORT).show()
            }
        }

    }


}


@Composable
fun WelcomeScreen(
    modifier: Modifier = Modifier
        .fillMaxSize(),
    navigateLogin: () -> Unit,
    navigateRegister: () -> Unit,
) {
    Box(
        contentAlignment = Alignment.TopCenter,
        modifier = modifier
            .fillMaxSize()
            .background(Color.White),
    ) {
        Image(
            painter = painterResource(R.drawable.background_welcome),
            contentDescription = "background",
            modifier = modifier
                .fillMaxSize(),
            alignment = Alignment.TopCenter
        )
        Image(
            painter = painterResource(R.drawable.whitelogo_dauruang),
            contentDescription = "logo_white_dauruang",
            modifier = Modifier
                .fillMaxWidth()
                .height(80.dp)
                .padding(top = 40.dp)
                .align(alignment = Alignment.TopCenter)
        )
        Image(
            painter = painterResource(R.drawable.ilustration_welcome),
            contentDescription = "welcome_ilustration",
            alignment = Alignment.TopCenter,
            modifier = Modifier
                .fillMaxSize()
                .padding(top = 120.dp, start = 32.dp, end = 32.dp),
        )
        Box(
            modifier = modifier
                .padding(bottom = 20.dp)
                .fillMaxSize(),
            contentAlignment = Alignment.BottomCenter
        ) {
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                modifier = Modifier
                    .fillMaxWidth()
                    .align(Alignment.BottomCenter)
                    .padding(top = 20.dp, start = 16.dp, end = 16.dp)
            ) {
                ContentSplash(
                    title = "Selamat Datang",
                    content = "Peduli daur ulang sampah\n" +
                            "Peduli masa depan",
                    modifier = Modifier
                        .padding(bottom = 12.dp)
                )

                ButtonLargePrimary(
                    navigateButton = navigateLogin,
                    title = "LOGIN",
                    fontWeight = FontWeight.Bold
                )
                Spacer(
                    modifier = Modifier
                        .padding(vertical = 4.dp)
                )
                ButtonLargeSecondary(
                    navigateButton = navigateRegister,
                    title = "REGISTER",
                    fontWeight = FontWeight.Bold
                )

            }
        }
    }

}

@Preview(showBackground = true, device = Devices.PIXEL_3)
@Composable
fun WelcomeScreenPreview() {
//    WelcomeScreen(navigateLogin = {}, navigateRegister = {})
}