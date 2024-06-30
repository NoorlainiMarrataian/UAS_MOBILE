package com.example.recipeapp.activities



import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.room.Room
import com.example.recipeapp.R
import com.example.recipeapp.room.database.AppDatabase
import com.example.recipeapp.room.entity.UsersEntity

class LoginActivity : AppCompatActivity() {
    private lateinit var db: AppDatabase
    private lateinit var emailEditText: EditText
    private lateinit var passwordEditText: EditText
    private lateinit var loginButton: Button
    private lateinit var sharedPreferences: SharedPreferences

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        db = Room.databaseBuilder(applicationContext, AppDatabase::class.java, "app-db").build()
        sharedPreferences = getSharedPreferences("MyPrefs", Context.MODE_PRIVATE)

        emailEditText = findViewById(R.id.emailEditText)
        passwordEditText = findViewById(R.id.passwordEditText)
        loginButton = findViewById(R.id.loginButton)

        loginButton.setOnClickListener {
            val email = emailEditText.text.toString()
            val password = passwordEditText.text.toString()

            if (email.isNotEmpty() && password.isNotEmpty()) {
                login(email, password)
            } else {
                Toast.makeText(this, "Please enter email and password", Toast.LENGTH_SHORT).show()
            }
        }
    }

    private fun login(email: String, password: String) {
        val userLiveData = db.userDao().login(email, password)
        userLiveData.observe(this, Observer { user ->
            if (user != null) {
                saveUserName(user.name)
                // Login successful, navigate to next screen
                startActivity(Intent(this@LoginActivity, MainActivity::class.java))
                finish()
            } else {
                // Show error message, invalid credentials
                Toast.makeText(this@LoginActivity, "Invalid email or password", Toast.LENGTH_SHORT).show()
            }
        })
    }

    private fun saveUserName(name: String) {
        val editor = sharedPreferences.edit()
        editor.putString("userName", name)
        editor.apply()
    }
}
