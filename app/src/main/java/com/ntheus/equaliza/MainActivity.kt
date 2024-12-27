package com.ntheus.equaliza


import android.os.Bundle
import android.widget.Button
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.AppCompatEditText
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.ntheus.equaliza.controller.UsuarioControler

class MainActivity : AppCompatActivity() {

    private lateinit var usario: AppCompatEditText;
    private lateinit var password: AppCompatEditText;
    private lateinit var botaoDeCadastro: Button
    private lateinit var botaoDeLogin: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        val usuarioControler= UsuarioControler(applicationContext, this)
        usario = findViewById(R.id.usuario)
        password = findViewById(R.id.password)
        botaoDeCadastro = findViewById(R.id.botaoCadastrar)
        botaoDeLogin = findViewById(R.id.buttonLogin)
        botaoDeLogin.setOnClickListener(
            {
                usuarioControler.login(usario.text.toString(), password.text.toString())
            }
        )
        botaoDeCadastro.setOnClickListener({
            usuarioControler.cadastrar(usario.text.toString(), password.text.toString())
        })
    }
}