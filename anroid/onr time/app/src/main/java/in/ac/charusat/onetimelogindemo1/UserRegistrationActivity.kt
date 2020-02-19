package `in`.ac.charusat.onetimelogindemo1

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.ContactsContract
import android.util.Log
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_user_registration.*
import okhttp3.*
import org.json.JSONObject
import java.io.IOException
import java.lang.Exception
import okhttp3.FormBody
import okhttp3.RequestBody
import androidx.core.app.ComponentActivity.ExtraData
import androidx.core.content.ContextCompat.getSystemService
import android.icu.lang.UCharacter.GraphemeClusterBreak.T



class UserRegistrationActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_user_registration)
        button3.setOnClickListener {
            if(editText3.text.toString().equals("")||editText4.text.toString().equals("")||editText5.text.toString().equals("")||editText6.text.toString().equals(""))
            {
                Toast.makeText(this,"Required Fields Missing!!",Toast.LENGTH_LONG).show()

            }
            else
            {
                if(editText5.text.toString().equals(editText6.text.toString()))
                {
                            var user=editText3.text.toString()
                            var email=editText4.text.toString()
                            var password=editText5.text.toString()
                            callService(user,email,password)

                }
                else
                {
                    Toast.makeText(this,"Password and Confirm Password Mismatch!!",Toast.LENGTH_LONG).show()
                    editText5.text.clear()
                    editText6.text.clear()
                }
            }
        }
    }
    fun callService(user:String,email:String,password:String)
    {
          try{
                val client = OkHttpClient()

                val formBody = FormBody.Builder()
                    .add("uname", user)
                    .add("email",email)
                    .add("password",password)
                    .build()
                val request = Request.Builder()
                    .url("http://10.0.2.2/productmanagement/registeruser.php")
                    .post(formBody)
                    .build()
                client.newCall(request).enqueue(object : Callback {



                    override fun onFailure(call: Call, e: IOException) {
                        Log.d("Exception",e.toString())
                    }

                    override fun onResponse(call: Call, response: Response) {
                        response.use {
                            if (!response.isSuccessful) throw IOException("Unexpected code $response")
                            var str=response.body!!.string()
                            Log.e("test",str)

                            val jsonObj = JSONObject(str)
                            var flag=jsonObj.getInt("success")
                            var message=jsonObj.getString("message")

                            if(flag==1)
                            {
                                Toast.makeText(applicationContext,message,Toast.LENGTH_LONG).show()
                                finish();

                            }
                            else
                            {
                                Toast.makeText(applicationContext,message,Toast.LENGTH_LONG).show()

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

