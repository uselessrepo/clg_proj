package `in`.ac.charusat.onetimelogindemo1

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        Handler().postDelayed({
            var preference=getSharedPreferences("MyPref", Context.MODE_PRIVATE)
            var str=preference.getString("user","Wrong")

            if(str.equals("Wrong")) {
                var intent = Intent(this, LoginActivity::class.java)
                startActivity(intent)
                finish()
            }
            else
            {
                var intent = Intent(this, HomeActivity::class.java)
                startActivity(intent)
                finish()
            }
        },3000)
    }
}
