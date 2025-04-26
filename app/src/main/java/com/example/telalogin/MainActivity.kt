package com.example.telalogin

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {

    private lateinit var etEmail: EditText
    private lateinit var etSenha: EditText
    private lateinit var btnLogin: Button
    private lateinit var tvCadastro: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        etEmail = findViewById(R.id.etEmail)
        etSenha = findViewById(R.id.etSenha)
        btnLogin = findViewById(R.id.btnLogin)
        tvCadastro = findViewById(R.id.tvCadastro)

        btnLogin.setOnClickListener {
            val email = etEmail.text.toString().trim()
            val senha = etSenha.text.toString().trim()

            // Validação de campos vazios
            if (email.isEmpty()) {
                Toast.makeText(this, "Por favor, preencha o campo Email.", Toast.LENGTH_SHORT).show()
            } else if (senha.isEmpty()) {
                Toast.makeText(this, "Por favor, preencha o campo Senha.", Toast.LENGTH_SHORT).show()
            } else {
                // Validação de credenciais
                if (email == "heudes@heudes.com" && senha == "123") {
                    Toast.makeText(this, "Login realizado com sucesso!", Toast.LENGTH_SHORT).show()

                    // Redirecionar para a Tela Principal passando o email
                    val intent = Intent(this, TelaPrincipalActivity::class.java)
                    intent.putExtra("email_usuario", email)
                    startActivity(intent)
                    finish() // opcional: fecha a tela de login para não voltar ao pressionar "voltar"

                } else {
                    Toast.makeText(this, "Login inválido", Toast.LENGTH_SHORT).show()
                }
            }
        }

        tvCadastro.setOnClickListener {
            val intent = Intent(this, CadastroActivity::class.java)
            startActivity(intent)
        }
    }
}
