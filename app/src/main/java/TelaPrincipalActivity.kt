package com.example.telalogin

import android.content.DialogInterface
import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity

class TelaPrincipalActivity : AppCompatActivity() {

    private lateinit var tvEmailUsuario: TextView
    private lateinit var btnSair: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_principal)

        tvEmailUsuario = findViewById(R.id.tvEmailUsuario)
        btnSair = findViewById(R.id.btnSair)

        val emailRecebido = intent.getStringExtra("email_usuario")

        if (!emailRecebido.isNullOrEmpty()) {
            tvEmailUsuario.text = "Bem-vindo: $emailRecebido"
        } else {
            tvEmailUsuario.text = "E-mail não informado"
        }

        btnSair.setOnClickListener {
            mostrarDialogoConfirmacao()
        }
    }

    private fun mostrarDialogoConfirmacao() {
        val builder = AlertDialog.Builder(this)
        builder.setTitle("Confirmação")
        builder.setMessage("Tem certeza que deseja sair?")

        builder.setPositiveButton("Sim") { dialog: DialogInterface, _: Int ->
            // Se o usuário confirmar, volta para a Tela de Login
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
            finish()
        }

        builder.setNegativeButton("Não") { dialog: DialogInterface, _: Int ->
            // Se o usuário cancelar, apenas fecha o diálogo
            dialog.dismiss()
        }

        val dialog = builder.create()
        dialog.show()
    }
}
