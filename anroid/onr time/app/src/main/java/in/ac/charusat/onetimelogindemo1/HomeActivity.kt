package `in`.ac.charusat.onetimelogindemo1

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_home.*

class HomeActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)
        var preference=getSharedPreferences("MyPref", Context.MODE_PRIVATE)
        var str=preference.getString("user","Wrong")
        textView.text="Welcome: $str"
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.mymenu,menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if(item.itemId==R.id.logoutitem)
        {
            var preference=getSharedPreferences("MyPref", Context.MODE_PRIVATE)
            var editor=preference.edit()
            editor.clear()
            editor.commit()
            var intent = Intent(this, LoginActivity::class.java)
            startActivity(intent)
            finish()
        }
        else if(item.itemId==R.id.random)
        {
            Toast.makeText(this,"Random cLICKED", Toast.LENGTH_LONG).show()
        }

        return super.onOptionsItemSelected(item)
    }
}