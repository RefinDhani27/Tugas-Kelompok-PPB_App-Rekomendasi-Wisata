package com.refin.aplikasikelompok

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Patterns
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth
import com.refin.aplikasikelompok.databinding.ActivityLoginBinding

class LoginActivity : AppCompatActivity() {

    lateinit var binding: ActivityLoginBinding
    lateinit var auth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        binding = ActivityLoginBinding.inflate(layoutInflater)
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        auth = FirebaseAuth.getInstance()

        binding.btnReg.setOnClickListener {
            val intent = Intent(this, RegisterActivity::class.java)
            startActivity(intent)
        }

        binding.btnLog.setOnClickListener {
            val email = binding.edtEmailReg.text.toString()
            val password = binding.edtPassReg.text.toString()

            //validasi email
            if(email.isEmpty()){
                binding.edtEmailReg.error = "Email harus diisi"
                binding.edtEmailReg.requestFocus()
                return@setOnClickListener
            }

            //validasi email tidak valid
            if(!Patterns.EMAIL_ADDRESS.matcher(email).matches()){
                binding.edtEmailReg.error = "Email tidak valid"
                binding.edtEmailReg.requestFocus()
                return@setOnClickListener
            }

            //validasi password
            if(password.isEmpty()){
                binding.edtPassReg.error = "Password harus diisi"
                binding.edtPassReg.requestFocus()
                return@setOnClickListener
            }

            if(password.length < 8){
                binding.edtPassReg.error = "Password minimal 8 karakter"
                binding.edtPassReg.requestFocus()
                return@setOnClickListener
            }

            RegisterFirebase(email, password)
        }
    }

    private fun RegisterFirebase(email: String, password: String) {
        auth.signInWithEmailAndPassword(email, password)
            .addOnCompleteListener(this){
                if (it.isSuccessful){
                    Toast.makeText(this,"Selamat Datang $email", Toast.LENGTH_SHORT).show()
                    val intent = Intent(this,MainActivity::class.java)
                    startActivity(intent)
                } else {
                    Toast.makeText(this, "${it.exception?.message}", Toast.LENGTH_SHORT).show()
                }
            }
    }
}