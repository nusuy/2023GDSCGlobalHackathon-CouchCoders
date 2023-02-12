package com.example.barrier_free

import android.content.ActivityNotFoundException
import android.content.Intent
import android.content.pm.PackageManager
import android.graphics.Bitmap
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.MediaStore
import android.widget.Button
import android.widget.ImageView
import android.widget.Toast
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import java.io.ByteArrayOutputStream

class CameraActivity : AppCompatActivity() {

    lateinit var imageView: ImageView
    lateinit var button: Button
    val REQUEST_IMAGE_CAPTURE = 100
    companion object {
        private const val CAMERA_PERMISSION_REQUEST_CODE = 1
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_camera)

        imageView = findViewById(R.id.imageSave)
        button = findViewById(R.id.btnTakeAgain)

        if (ContextCompat.checkSelfPermission(this, android.Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this, arrayOf(android.Manifest.permission.CAMERA), CameraActivity.CAMERA_PERMISSION_REQUEST_CODE)
        } else {
            button.setOnClickListener {
                val takePictureIntent = Intent(MediaStore.ACTION_IMAGE_CAPTURE)
                try {
                    startActivityForResult(takePictureIntent, REQUEST_IMAGE_CAPTURE)
                } catch (e: ActivityNotFoundException) {
                    Toast.makeText(this, "Error: Activity Not Found", Toast.LENGTH_SHORT).show()
                }
            }
        }
    }


    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        if (requestCode == REQUEST_IMAGE_CAPTURE && resultCode == RESULT_OK) {
            // send info to next activity
            var intent = Intent(this, MainActivity3::class.java)
            val imageBitMap = data?.extras?.get("data") as Bitmap // get the bitmap image
            val bs = ByteArrayOutputStream()
            imageBitMap.compress(Bitmap.CompressFormat.PNG, 50, bs)
            intent.putExtra("myImage", bs.toByteArray())
            startActivity(intent)
            bs.close()
            Toast.makeText(this, "Picture set", Toast.LENGTH_SHORT).show()

        } else {
            super.onActivityResult(requestCode, resultCode, data)
        }
    }

    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<String>, grantResults: IntArray) {
        when (requestCode) {
            CameraActivity.CAMERA_PERMISSION_REQUEST_CODE -> {
                if (grantResults.isNotEmpty() && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    // Permission has been granted by the user
                    Toast.makeText(this, "Permission granted", Toast.LENGTH_SHORT).show()

                } else {
                    Toast.makeText(this, "Permission not granted", Toast.LENGTH_SHORT).show()
                    // Permission has been denied by the user
                }
                return
            }
        }
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
    }
}