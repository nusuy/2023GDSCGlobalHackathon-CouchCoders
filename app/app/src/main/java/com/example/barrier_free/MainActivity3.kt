package com.example.barrier_free

import android.content.DialogInterface
import android.content.Intent
import android.graphics.BitmapFactory
import android.graphics.Color
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.ImageView
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity

//import org.tensorflow.*
//import org.tensorflow.Session

class MainActivity3 : AppCompatActivity() {
    lateinit var imgView: ImageView
    lateinit var btnTakeAgain: Button
    lateinit var btnSubmitForCheck: Button
    lateinit var btn_yes: Button
    lateinit var btn_no: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main3)

        imgView = findViewById(R.id.imageSave)

        val byteArray = intent.getByteArrayExtra("myImage")
        val myBitmappedImage = BitmapFactory.decodeByteArray(byteArray, 0, byteArray!!.size)
        imgView.setImageBitmap(myBitmappedImage)

        btnTakeAgain = findViewById(R.id.btnTakeAgain)
        btnSubmitForCheck = findViewById(R.id.btnSubmitForCheck)

        btnTakeAgain.setOnClickListener {
            // send back to activity 2
            val intent = Intent(this, CameraActivity::class.java)
            startActivity(intent)
        }

        btnSubmitForCheck.setOnClickListener {
            // do ML check
            Toast.makeText(this, "Submitted to model", Toast.LENGTH_LONG).show()

            var result: Int = 1

            if (result == 1) {
//                Toast.makeText(this, "Image Passes", Toast.LENGTH_SHORT).show()

                val builder = AlertDialog.Builder(this)
                with(builder) {

                    val inflater = layoutInflater
                    val dialogView = inflater.inflate(R.layout.dialog_map3, null)
                    val imageView = dialogView.findViewById<ImageView>(R.id.imageView)
                    imageView.setImageResource(R.drawable.mapimage3)

                    builder.setView(dialogView)
                    btn_yes = dialogView.findViewById(R.id.btn_yes)
                    btn_no = dialogView.findViewById(R.id.btn_ignore)

                    btn_yes.setOnClickListener(object : View.OnClickListener {
                        override fun onClick(v: View?) {
                            Toast.makeText(this@MainActivity3, "yes", Toast.LENGTH_SHORT).show()
                            var intent = Intent(this@MainActivity3, AcceptedActivity::class.java)
                            startActivity(intent)
                        }
                    })

                    btn_no.setOnClickListener(object : View.OnClickListener {
                        override fun onClick(v: View?) {
                            Toast.makeText(this@MainActivity3, "no", Toast.LENGTH_SHORT).show()
                            var intent = Intent(this@MainActivity3, IgnoreActivity::class.java)
                            startActivity(intent)
                        }
                    })

                    val dialog = builder.create()
                    dialog.setView(dialogView)

                    dialogView
                }
                val dialog = builder.create()
                dialog.show()
//                val alertDialog = builder.create()
//                alertDialog.show()

                val button = dialog.getButton(DialogInterface.BUTTON_POSITIVE)
                with(button) {
                    setBackgroundColor(Color.BLACK)
                    setPadding(0, 0, 20, 0)
                    setTextColor(Color.WHITE)
                }

            } else if (result == 0) {
                Toast.makeText(this, "Image Failed", Toast.LENGTH_SHORT).show()
            }
        }

    }
}