package com.example.gprod;

import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.activity.result.ActivityResultLauncher
import androidx.appcompat.app.AppCompatActivity

import com.example.gprod.databinding.ActivitySignInBinding;
import com.google.firebase.auth.FirebaseAuth
//import com.google.firebase.codelab.friendlychat.databinding.ActivitySignInBinding

public class SignInActivity extends AppCompatActivity {

    private ActivitySignInBinding binding;

    private final ActivityResultLauncher<Intent> signInLauncher = registerForActivityResult(
            new FirebaseAuthUIActivityResultContract(),
            new ActivityResultCallback<FirebaseAuthUIAuthenticationResult>() {
                @Override
                public void onActivityResult(FirebaseAuthUIAuthenticationResult result) {
                    onSignInResult(result);
                }
            }
    );

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_in);
        signIn = registerForActivityResult(
            registerForActivityResult(FirebaseAuthUIActivityResultContract(), this::onSignInResult)

    }
}





        override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // This codelab uses View Binding
        // See: https://developer.android.com/topic/libraries/view-binding
        binding = ActivitySignInBinding.inflate(layoutInflater)
        setContentView(binding.root)
        }

public override fun onStart() {
        super.onStart()

        // If there is no signed in user, launch FirebaseUI
        // Otherwise head to MainActivity
        if (Firebase.auth.currentUser == null) {
        // Sign in with FirebaseUI, see docs for more details:
        // https://firebase.google.com/docs/auth/android/firebaseui
        val signInIntent = AuthUI.getInstance()
        .createSignInIntentBuilder()
        .setLogo(R.mipmap.ic_launcher)
        .setAvailableProviders(listOf(
        AuthUI.IdpConfig.EmailBuilder().build(),
        AuthUI.IdpConfig.GoogleBuilder().build(),
        ))
        .build()

        signIn.launch(signInIntent)
        } else {
        goToMainActivity()
        }
        }

private fun onSignInResult(result: FirebaseAuthUIAuthenticationResult) {
        if (result.resultCode == RESULT_OK) {
        Log.d(TAG, "Sign in successful!")
        goToMainActivity()
        } else {
        Toast.makeText(
        this,
        "There was an error signing in",
        Toast.LENGTH_LONG).show()

        val response = result.idpResponse
        if (response == null) {
        Log.w(TAG, "Sign in canceled")
        } else {
        Log.w(TAG, "Sign in error", response.error)
        }
        }
        }

private fun goToMainActivity() {
        startActivity(Intent(this, MainActivity::class.java))
        finish()
        }

        companion object {
private const val TAG = "SignInActivity"
        }
        }
