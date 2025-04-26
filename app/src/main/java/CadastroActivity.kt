package com.example.telalogin

import android.content.Intent
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class CadastroActivity : AppCompatActivity() {

    private lateinit var etNome: EditText
    private lateinit var etEmailCadastro: EditText
    private lateinit var etSenhaCadastro: EditText
    private lateinit var etCPF: EditText
    private lateinit var etSexo: EditText
    private lateinit var etDataNascimento: EditText
    private lateinit var etRua: EditText
    private lateinit var etBairro: EditText
    private lateinit var etNumero: EditText
    private lateinit var etTelefone: EditText
    private lateinit var etCidade: EditText
    private lateinit var etEstado: EditText
    private lateinit var btnCadastrar: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_cadastro)

        // Inicializando os campos
        etNome = findViewById(R.id.etNome)
        etEmailCadastro = findViewById(R.id.etEmailCadastro)
        etSenhaCadastro = findViewById(R.id.etSenhaCadastro)
        etCPF = findViewById(R.id.etCPF)
        etSexo = findViewById(R.id.etSexo)
        etDataNascimento = findViewById(R.id.etDataNascimento)
        etRua = findViewById(R.id.etRua)
        etBairro = findViewById(R.id.etBairro)
        etNumero = findViewById(R.id.etNumero)
        etTelefone = findViewById(R.id.etTelefone)
        etCidade = findViewById(R.id.etCidade)
        etEstado = findViewById(R.id.etEstado)
        btnCadastrar = findViewById(R.id.btnCadastrar)

        // Aplicar máscaras
        aplicarMascara(etCPF, "###.###.###-##")
        aplicarMascara(etDataNascimento, "##/##/####")
        aplicarMascaraTelefone(etTelefone)

        btnCadastrar.setOnClickListener {
            // Capturando os valores
            val nome = etNome.text.toString().trim()
            val email = etEmailCadastro.text.toString().trim()
            val senha = etSenhaCadastro.text.toString().trim()
            val cpf = etCPF.text.toString().trim()
            val sexo = etSexo.text.toString().trim()
            val dataNascimento = etDataNascimento.text.toString().trim()
            val rua = etRua.text.toString().trim()
            val bairro = etBairro.text.toString().trim()
            val numero = etNumero.text.toString().trim()
            val telefone = etTelefone.text.toString().trim()
            val cidade = etCidade.text.toString().trim()
            val estado = etEstado.text.toString().trim()

            // Verificar se todos os campos foram preenchidos
            if (nome.isNotEmpty() && email.isNotEmpty() && senha.isNotEmpty()
                && cpf.isNotEmpty() && sexo.isNotEmpty() && dataNascimento.isNotEmpty()
                && rua.isNotEmpty() && bairro.isNotEmpty() && numero.isNotEmpty()
                && telefone.isNotEmpty() && cidade.isNotEmpty() && estado.isNotEmpty()) {

                Toast.makeText(this, "Cadastro realizado com sucesso!", Toast.LENGTH_SHORT).show()

                // Redirecionar para Tela Principal enviando o e-mail
                val intent = Intent(this, TelaPrincipalActivity::class.java)
                intent.putExtra("email_usuario", email)
                startActivity(intent)
                finish()

            } else {
                Toast.makeText(this, "Preencha todos os campos!", Toast.LENGTH_SHORT).show()
            }
        }
    }

    private fun aplicarMascara(editText: EditText, mask: String) {
        editText.addTextChangedListener(object : TextWatcher {
            var isUpdating: Boolean = false
            var oldText: String = ""

            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                val str = s.toString().replace(Regex("[^\\d]"), "")
                var masked = ""
                if (isUpdating) {
                    oldText = str
                    isUpdating = false
                    return
                }
                var i = 0
                for (m in mask.toCharArray()) {
                    if (m != '#' && str.length > oldText.length) {
                        masked += m
                        continue
                    }
                    try {
                        masked += str[i]
                    } catch (e: Exception) {
                        break
                    }
                    i++
                }
                isUpdating = true
                editText.setText(masked)
                editText.setSelection(masked.length)
            }

            override fun afterTextChanged(s: Editable?) {}
        })
    }

    private fun aplicarMascaraTelefone(editText: EditText) {
        editText.addTextChangedListener(object : TextWatcher {
            private var isUpdating = false
            private var oldText = ""

            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
                oldText = s.toString()
            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                if (isUpdating) return

                var str = s.toString().replace(Regex("[^\\d]"), "")
                var formatted = ""

                if (count == 0) {
                    isUpdating = true
                    editText.setText(str)
                    editText.setSelection(str.length)
                    isUpdating = false
                    return
                }

                if (str.length >= 2) {
                    formatted += "(${str.substring(0, 2)}) "
                    if (str.length >= 7) {
                        formatted += str.substring(2, 7) + "-" + str.substring(7)
                    } else if (str.length > 2) {
                        formatted += str.substring(2)
                    }
                } else {
                    formatted += str
                }

                isUpdating = true
                editText.setText(formatted)
                editText.setSelection(formatted.length)
                isUpdating = false
            }

            override fun afterTextChanged(s: Editable?) {}
        })
    }
}
