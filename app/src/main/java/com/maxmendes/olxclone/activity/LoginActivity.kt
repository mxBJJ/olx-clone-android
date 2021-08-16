package com.maxmendes.olxclone.activity

import android.content.DialogInterface
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import com.google.android.gms.tasks.OnCompleteListener
import com.google.firebase.auth.FirebaseAuth
import com.maxmendes.olxclone.R

class LoginActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
    }


    fun btnAccess(view: View) {

        val firebaseAuth: FirebaseAuth = FirebaseAuth.getInstance()

        val emailTextView: TextView = findViewById(R.id.editTextEmailAddress)
        val passwordTextView: TextView = findViewById(R.id.editTextPassword)

        val email = emailTextView.text.toString()
        val password = passwordTextView.text.toString()

        if (firebaseAuth.currentUser == null) {
            firebaseAuth.signInWithEmailAndPassword(email, password)
                .addOnCompleteListener(OnCompleteListener {
                    if (it.isSuccessful) {
                        startActivity(Intent(applicationContext, AnnouncementsActivity::class.java))
                    } else {
                    }
                }).addOnFailureListener {
                    val builder = AlertDialog.Builder(this)
                    builder.setMessage(
                        "Não foi possível efetuar o login." + "\n" +
                                "Confira seu usuário e senha e tente novamente."
                    )
                    builder.setTitle("Credenciais inválidas")
                    builder.setPositiveButton("Ok",
                        DialogInterface.OnClickListener { dialogInterface, i ->

                        })

                    builder.create()
                    builder.show()
                    Toast.makeText(applicationContext, "Erro", Toast.LENGTH_LONG)
                    Log.d("LOG EXCEPTION: ", it.message.toString())
                }
        }
    }
}