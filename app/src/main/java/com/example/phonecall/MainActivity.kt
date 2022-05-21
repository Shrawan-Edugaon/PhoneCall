package com.example.phonecall

import android.content.Intent
import android.content.Intent.ACTION_CALL
import android.content.pm.PackageManager
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.TextUtils
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.core.app.ActivityCompat

class MainActivity : AppCompatActivity() {

    var callBtn : Button? = null
    var editBtn : EditText? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        callBtn = findViewById(R.id.call)
        editBtn = findViewById(R.id.phoneNo)

    }
    fun callFunc(v: View)
    {
       val intent = Intent(Intent.ACTION_CALL)
        val pNum = editBtn!!.text.toString()
        if (TextUtils.isEmpty(editBtn!!.text.toString()))
        {
            editBtn!!.error = "Enter Phone No"
            return
        }
        else
        {
            intent.data = Uri.parse("tel:"+pNum)
        }
        if (ActivityCompat.checkSelfPermission(this@MainActivity,android.Manifest.permission.CALL_PHONE)!=PackageManager.PERMISSION_GRANTED)
        {
            Toast.makeText(this@MainActivity,"Grant permission to call",Toast.LENGTH_SHORT).show()
            requestPermissionsFunc()
        }
        else
        {
            startActivity(intent)
        }
    }
    private fun requestPermissionsFunc(){
        ActivityCompat.requestPermissions(this@MainActivity, arrayOf(android.Manifest.permission.CALL_PHONE),1)
    }
}