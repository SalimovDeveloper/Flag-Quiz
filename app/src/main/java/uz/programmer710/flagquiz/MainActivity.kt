package uz.programmer710.flagquiz

import Models.Flag
import android.graphics.Color
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.LinearLayout
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.children
import androidx.core.view.isVisible
import androidx.core.view.marginEnd
import androidx.core.view.marginLeft
import kotlinx.android.synthetic.main.activity_main.*
import java.util.*

class MainActivity : AppCompatActivity(), View.OnClickListener {

    lateinit var flagArrayList: ArrayList<Flag>
    var count = 0
    var countryName = ""
    lateinit var buttonArrayList: ArrayList<Button>

    var togri = 0
    var notogri = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        buttonArrayList = ArrayList()
        obyektYaratish()
        btnJoylaCount()
    }

    private fun obyektYaratish() {
        flagArrayList = java.util.ArrayList()
        flagArrayList.add(Flag("india", R.drawable.india))
        flagArrayList.add(Flag("france", R.drawable.france))
        flagArrayList.add(Flag("china", R.drawable.china))
        flagArrayList.add(Flag("uzbekistan", R.drawable.uzbekistan))
        flagArrayList.add(Flag("germany", R.drawable.germany))
        flagArrayList.add(Flag("italy", R.drawable.italian))
    }

    fun btnJoylaCount() {
        flag_View.setImageResource(flagArrayList[count].image!!)
        liner_Layout.removeAllViews()  // liner_Layout
        liner_Layout_btn_1.removeAllViews() //liner_layout_btn1
        liner_Layout_btn_2.removeAllViews() //liner_layout_btn1
        countryName = ""
        btnJoyla(flagArrayList[count].name)
    }

    private fun btnJoyla(countryName: String?) {
        val btnArray: ArrayList<Button> = randomBtn(countryName)
        for (i in 0..5) {
            liner_Layout_btn_1.addView(btnArray[i])
        }
        for (i in 6..11) {
            liner_Layout_btn_2.addView(btnArray[i])
        }
    }

    private fun randomBtn(countryName: String?): ArrayList<Button> {
        val array = ArrayList<Button>()
        val arrayText = java.util.ArrayList<String>()

        for (c in countryName!!) {
            arrayText.add(c.toString())
        }
        if (arrayText.size != 12) {
            val str = "ABCDEFGHIJKLMNOPQRSTUVXYZ"
            for (i in arrayText.size until 12) {
                val random = Random().nextInt(str.length)
                arrayText.add(str[random].toString())
            }
        }
        arrayText.shuffle()

        for (i in 0 until arrayText.size) {
            val button = Button(this)
            button.layoutParams = LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.WRAP_CONTENT,
                LinearLayout.LayoutParams.WRAP_CONTENT,
                1.0f
            )
            button.setBackgroundColor(Color.parseColor("#FBC21C"))
            button.text = arrayText[i]
            array.add(button)
            button.setOnClickListener(this)
        }
        return array
    }

    override fun onClick(v: View?) {
        val button1 = v as Button
        if (buttonArrayList.contains(button1)) {
            liner_Layout.removeView(button1)
            var hasC = false
            liner_Layout_btn_1.children.forEach { button ->
                if ((button as Button).text.toString() == button1.text.toString()) {
                    button.visibility = View.VISIBLE
                    countryName = countryName.substring(0, countryName.length - 1)
                    hasC = true
                }
            }
            liner_Layout_btn_2.children.forEach { button ->
                if ((button as Button).text.toString() == button1.text.toString()) {
                    button.visibility = View.VISIBLE
                    countryName = countryName.substring(0, countryName.length - 1)
                    hasC = true

                }
            }
        } else {
            button1.visibility = View.INVISIBLE
            countryName += button1.text.toString().toUpperCase()
            val button2 = Button(this)
            button2.layoutParams = LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.WRAP_CONTENT,
                LinearLayout.LayoutParams.WRAP_CONTENT,
                1.0f
            )
            button2.text = button1.text
            button2.setOnClickListener(this)
            button2.setBackgroundColor(Color.parseColor("#FBC21C"))
            buttonArrayList.add(button2)
            liner_Layout.addView(button2)
            matnTogri()
        }
    }

    private fun matnTogri() {
        if (countryName == flagArrayList[count].name?.toUpperCase()) {
            Toast.makeText(this, "Correct !!!", Toast.LENGTH_SHORT).show()
            togri++
            togrI.text = togri.toString()
            klass.visibility = View.VISIBLE
            if (count == flagArrayList.size - 1) {
                count = 0

            } else {

                count++
            }
            btnJoylaCount()
        } else {
            klass.visibility = View.INVISIBLE
            if (countryName.length == flagArrayList[count].name?.length) {
                Toast.makeText(this, "Incorrect !!!", Toast.LENGTH_SHORT).show()
                notogri++
                notogrI.text = notogri.toString()
                liner_Layout.removeAllViews()
                liner_Layout_btn_1.removeAllViews()
                liner_Layout_btn_2.removeAllViews()
                btnJoyla(flagArrayList[count].name)
                countryName = ""
                dizzklass.visibility = View.VISIBLE
            }else{
                dizzklass.visibility = View.INVISIBLE
            }
        }
    }


}