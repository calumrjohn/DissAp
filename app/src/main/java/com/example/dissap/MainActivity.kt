package com.example.dissap

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.content.Intent
import android.os.AsyncTask
import android.util.Log
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    private var appDB: AppDB? = null //Initialize Database.

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        appDB = AppDB.getDatabase(this)!!
    }


    fun buttonRegister(view: View) {
        val registerIntent :Intent = Intent(this, RegisterActivity::class.java).apply {}
        startActivity(registerIntent)
    }
    fun buttonForgotPassword(view: View) {}

    fun buttonLogin(view: View) {
        if (!editUserName.text.toString().isEmpty() && !editPassword.text.toString().isEmpty()) {

            Log.i(R.string.log_app.toString(), R.string.log_login_attempt.toString()) //Log the attempt.

            //Run the SQL login query with the provided username and password.
            RequestLogin(
                this,
                editUserName.getText().toString(),
                editPassword.getText().toString()
            ).execute()


            if(Global.loggedUser != null) {
                val mainMenuIntent :Intent = Intent(this, MenuActivity::class.java).apply {}
                startActivity(mainMenuIntent)
            }

        }else { Toast.makeText(this, R.string.toast_missing_fields , Toast.LENGTH_LONG).show() }

    }

    private class RequestLogin(var context: MainActivity, var username :String, var password :String) :
        AsyncTask<Void, Void, List<User>>() {

        override fun doInBackground(vararg params: Void?): List<User> {
            return context.appDB!!.userDao().attemptLogin(username, password)
        }

        override fun onPostExecute(userList: List<User>?) {

            if(userList!!.isNotEmpty()) {
                Global.loggedUser = userList[0]
                println(Global.loggedUser.toString())
                Toast.makeText(context, R.string.toast_login_successful, Toast.LENGTH_LONG).show()
            }else{
                Toast.makeText(context, R.string.toast_login_unsuccessful, Toast.LENGTH_LONG).show()
            }
        }
    }

}
