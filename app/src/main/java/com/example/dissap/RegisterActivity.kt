package com.example.dissap

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.os.AsyncTask
import android.util.Log
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_register.*

private const val TAG = "RegisterActivity"

class RegisterActivity : AppCompatActivity() {

    private var appDB: AppDB? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)
        appDB = AppDB.getDatabase(this)!!
    }

    fun createUser(): User {
        Log.d(TAG, "usercreation")
        return User(

            editTextName.getText().toString(),
            editTextPassword.getText().toString(),
            editTextEmail.getText().toString(),
            editTextStudNo.getText().toString(),
            editTextPhone.getText().toString()

        )
    }

    fun buttonRegister(view: View) {
        Log.d(TAG, "button pressed")
        if(editTextPassword.text.toString().equals(editTextPasswordConfirm.text.toString()) && !editTextPassword.text.toString().isEmpty()) {
            Log.d(TAG, "if statement working")
            InsertUser(this, createUser()).execute()
        }
    }

    private class InsertUser(var context: RegisterActivity, var user: User) :
        AsyncTask<Void, Void, Boolean>() {

        override fun doInBackground(vararg params: Void?): Boolean {
            Log.d(TAG, "do in background working")
            println(user.toString())
            context.appDB!!.userDao().insert(user)
            return true
        }

        override fun onPostExecute(bool: Boolean?) {
            if(bool!!) {
                Toast.makeText(context, context.resources.getString(R.string.user_created)  , Toast.LENGTH_LONG).show()
            }
        }
    }
}

