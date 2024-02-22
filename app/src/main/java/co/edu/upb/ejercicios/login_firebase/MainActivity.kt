package co.edu.upb.ejercicios.login_firebase

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.ContactsContract.CommonDataKinds.Email
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import co.edu.upb.ejercicios.login_firebase.databinding.ActivityMainBinding
import com.google.android.material.textfield.TextInputLayout
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase

class MainActivity : AppCompatActivity() {

    private lateinit var firebaseAuth: FirebaseAuth
    private lateinit var binding: ActivityMainBinding
    private lateinit var btnLogin: Button
    private lateinit var etEmail: EditText
    private lateinit var etPass: EditText
    private lateinit var tilEmail: TextInputLayout
    private lateinit var tilPass: TextInputLayout


    override fun onCreate(savedInstanceState: Bundle?) {




        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(R.layout.activity_main)

        initView()


        // Voy a in
        btnLogin.setOnClickListener {

            signIn(etEmail.text.toString(), etPass.text.toString())

        }

    }


    // Encargar de validar el usuario y contraseña

    private fun signIn (email: String, pass: String){

        firebaseAuth.signInWithEmailAndPassword(email,pass)  // metodo
            .addOnCompleteListener(this){ task ->
                if(task.isSuccessful){

                    val user = firebaseAuth.currentUser

                    Toast.makeText(this, "Bienvenido",Toast.LENGTH_LONG).show()

                    val intent = Intent(this, HomePage::class.java)

                    startActivity(intent)


                } else {

                    Toast.makeText(this, "Error en el usuario o contraseña",Toast.LENGTH_LONG).show()

                }


            }


    }



    private fun initView(){


        firebaseAuth = Firebase.auth  // Para autenticar la BBDD
        btnLogin = findViewById(R.id.btnLogin)
        etEmail = findViewById(R.id.etEmail)
        etPass = findViewById(R.id.etPassword)
        tilEmail = findViewById(R.id.tilContEmail)
        tilPass = findViewById(R.id.tilContPass)
    }



}