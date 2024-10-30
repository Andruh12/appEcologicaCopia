package com.example.app1

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import com.example.app1.databinding.ActivitySecondBinding
import com.google.firebase.auth.FirebaseAuth

class SecondActivity : AppCompatActivity() {

    private lateinit var binding: ActivitySecondBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // Inicializa el View Binding
        binding = ActivitySecondBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setup()
    }

    private fun setup() {
        title = "Autenticación"
        binding.Registrobutton.setOnClickListener {
            if (binding.emailEditText.text.isNotEmpty() &&
                binding.passwordEditText.text.isNotEmpty()) {
                FirebaseAuth.getInstance().createUserWithEmailAndPassword(
                    binding.emailEditText.text.toString(),
                    binding.passwordEditText.text.toString()).addOnCompleteListener {
                    if (it.isSuccessful) {
                        // Llama a showHome al registrarse exitosamente
                        showHome(it.result?.user?.email ?: "", ProviderType.BASIC)
                    } else {
                        showAlert()
                    }
                }
            }
        }
        binding.btnSesion.setOnClickListener {
            if (binding.emailEditText.text.isNotEmpty() &&
                binding.passwordEditText.text.isNotEmpty()) {
                FirebaseAuth.getInstance().signInWithEmailAndPassword(
                    binding.emailEditText.text.toString(),
                    binding.passwordEditText.text.toString()).addOnCompleteListener {
                    if (it.isSuccessful) {
                        // Llama a showHome al registrarse exitosamente
                        showHome(it.result?.user?.email ?: "", ProviderType.BASIC)
                    } else {
                        showAlert()
                    }
                }
            }
        }
    }


    private fun showAlert() {
        val builder = AlertDialog.Builder(this)
        builder.setTitle("Error")
        builder.setMessage("Se ha producido un error al registrar el usuario")
        builder.setPositiveButton("Aceptar", null)
        val dialog: AlertDialog = builder.create()
        dialog.show()
    }

    private fun showHome(email: String, provider: ProviderType) {
        val homeIntent = Intent(this, TiendaActivity::class.java).apply {
            putExtra("email", email)
            putExtra("provider", provider.name)
        }
        startActivity(homeIntent)
    }
}


