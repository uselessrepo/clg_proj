package `in`.ac.charusat.onetimelogindemo1

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_login.*
import okhttp3.*
import org.json.JSONObject
import java.io.IOException
import java.lang.Exception

class LoginActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
        button.setOnClickListener {
            if(editText.text.toString().equals("") && editText2.text.toString().equals(""))
            {
                Toast.makeText(this,"Please Enter Required Data!!",Toast.LENGTH_LONG).show()
            }
            else
            {

                    callService(editText.text.toString(),editText2.text.toString())
            }
        }
        textView2.setOnClickListener {
            var intent=Intent(this,UserRegistrationActivity::class.java)
            startActivity(intent)

        }
    }
    fun callService(user:String,password:String)
    {
        try{
            val client = OkHttpClient()

            val formBody = FormBody.Builder()
                .add("uname", user)
                .add("password",password)
                .build()
            val request = Request.Builder()
                .url("https://10.0.2.2/productmanagement/login.php")
                .post(formBody)
                .build()
            client.newCall(request).enqueue(object : Callback {
                override fun onFailure(call: Call, e: IOException) {
                    call.cancel()
                }

                override fun onResponse(call: Call, response: Response) {
                    response.use {
                        if (!response.isSuccessful) throw IOException("Unexpected code $response")
                        var str = response.body!!.string()
                        Log.e("test", str)

                        val jsonObj = JSONObject(str)
                        var flag = jsonObj.getInt("success")
                        var message = jsonObj.getString("message")

                        if (flag == 1) {
                            Toast.makeText(applicationContext, message, Toast.LENGTH_LONG).show()
                            var preference: SharedPreferences =
                                getSharedPreferences("MyPref", Context.MODE_PRIVATE)
                            var editor = preference.edit()
                            editor.putString("user", editText.text.toString())
                            editor.commit()


                            Toast.makeText(
                                applicationContext,
                                "Login Successful",
                                Toast.LENGTH_LONG
                            ).show()
                            var intent = Intent(applicationContext, HomeActivity::class.java)
                            startActivity(intent)
                            finish()
                        } else {
                            Toast.makeText(applicationContext,   message,Toast.LENGTH_LONG).show()
                        }

                    }



                }
            })

        }catch (e: Exception)
        {
            e.printStackTrace()
        }
    }
}
