package com.zackyasgar.at_tauba

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth
import com.zackyasgar.at_tauba.admin.AdminActivity
import kotlinx.android.synthetic.main.activity_login.*

class LoginActivity : AppCompatActivity() {

    private val firebase = FirebaseAuth.getInstance()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        btn_login.setOnClickListener {
            val email = et_login_email.text.toString().trim()
            val password = et_login_password.text.toString().trim()


            when {
                email.isEmpty() -> et_login_email.error = "Email tidak boleh kosong"
                password.isEmpty() -> et_login_password.error = "Password tidak boleh kosong"
                else -> {
                    progres_bar.visibility = View.VISIBLE
                    loginToFirebase(email, password)
                }
            }
        }
    }

    private fun loginToFirebase(email: String, password: String) {
        firebase.signInWithEmailAndPassword(email, password)
            .addOnSuccessListener {
                val intent = Intent(this@LoginActivity, AdminActivity::class.java)
                startActivity(intent)
                progres_bar.visibility = View.GONE
            }
            .addOnFailureListener {
                progres_bar.visibility = View.GONE
                Toast.makeText(this@LoginActivity,"Username dan password salah",Toast.LENGTH_SHORT).show()
            }
            .addOnCanceledListener {
                progres_bar.visibility = View.GONE
                Toast.makeText(this@LoginActivity,"Tidak terhubung internet",Toast.LENGTH_SHORT).show()
            }
    }


}