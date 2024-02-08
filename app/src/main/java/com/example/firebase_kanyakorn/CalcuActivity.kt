package com.example.firebase_kanyakorn

import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity


class CalcuActivity : AppCompatActivity() {
    //ตัวแปรต่าง ๆ ที่จะถูกใช้ในการจัดการข้อมูลและผลลัพธ์ของการคำนวณ
    private lateinit var resultTextView: TextView
    private var currentInput: String = ""   //ตัวเลข0-9
    private var currentOperator: String? = ""   //ที่เก็บตัวแปรทางคณิตศาสตร์ (+ - x / %)
    private var firstOperand: Double? =
        0.0 //ตัวแปรที่เก็บค่าตัวหารที่ผู้ใช้ป้อนก่อนหน้า เมื่อเริ่มการคำนวณใหม่ จะถูกกำหนดค่าเริ่มต้นเป็น 0.0
    private var result: Double? = 0.0   //เก็บผลลัพธ์ของการคำนวณ จะถูกอัปเดตเมื่อผู้ใช้กดปุ่มเท่ากับ

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_calcu)
        resultTextView = findViewById(R.id.textView)

        //เพิ่ม OnClickListener ให้กับปุ่มทุกปุ่มตัวเลขและจุด(.)
        //ประกาศตัวแปรให้ปุ่มตัวเลข0-9และจุด(.)
        val numberButtons = arrayOf(
            R.id.button5, R.id.button6, R.id.button7, R.id.button9,
            R.id.button10, R.id.button11, R.id.button13, R.id.button14,
            R.id.button15, R.id.button17, R.id.button18
        )

        for (buttonId in numberButtons) {
            findViewById<Button>(buttonId).setOnClickListener {
                onNumberButtonClick(it)
            }
        }

        // เพิ่ม OnClickListener ให้กับปุ่มลบทีละตัว
        findViewById<Button>(R.id.button2).setOnClickListener {
            onDeleteButtonClick()
        }

        // เพิ่ม OnClickListener ให้กับปุ่มลบทั้งหมด
        findViewById<Button>(R.id.button1).setOnClickListener {
            clearAll()
        }


        // เพิ่ม OnClickListener ให้กับปุ่ม บวก, ลบ, คูณ, หาร, modulo
        val operatorButtons = arrayOf(
            R.id.button16, R.id.button12, R.id.button8, R.id.button4, R.id.button3
        )

        for (buttonId in operatorButtons) {
            findViewById<Button>(buttonId).setOnClickListener {
                onOperatorButtonClick(it)
            }
        }

        // เพิ่ม OnClickListener ให้กับปุ่มเท่ากับ
        findViewById<Button>(R.id.button19).setOnClickListener {
            onEqualsButtonClick()
        }

    }

    // ล้างข้อมูลทั้งหมดและเตรียมพร้อมสำหรับการคำนวณใหม่
    private fun clearAll() {
        currentInput = "0"  //เลขที่ป้อนเข้ามาเป็นเลข 0.0
        currentOperator = null  //ไม่มีตัวดำเนินการทางคณิตศาสตร์ที่กำลังใช้งาน
        firstOperand = null //ไม่มีตัวหารที่กำลังใช้งาน
        updateResult()
    }

    private fun onNumberButtonClick(view: View) {
        val buttonText = (view as Button).text.toString()

        if (currentInput == "0") {
            // กรณีที่เป็นเลข 0 เลขเดียวบนหน้าจอ ไม่เพิ่มจำนวนเลข 0
            currentInput = buttonText
        } else {
            // อนุญาตให้มีเพียงหนึ่งจุดทศนิยม
            if (buttonText == "." && !currentInput.contains(".")) {
                currentInput += buttonText
            } else if (buttonText != ".") {
                currentInput += buttonText
            }
        }

        updateResult()
    }

    //เรียกใช้ ปุ่มลบทีละตัว
    private fun onDeleteButtonClick() {
        if (currentInput.length > 1) {
            // ลบตัวเลขที่พิมพ์ล่าสุดทีละ 1 หลัก
            currentInput = currentInput.substring(0, currentInput.length - 1)
        } else {
            // หากเหลือเลขหลักเดียว กดลบซ้ำเป็นเลข 0
            currentInput = "0"
        }

        updateResult()
    }

    //เรียกใช้ ปุ่ม บวก, ลบ, คูณ, หาร, modulo
    private fun onOperatorButtonClick(view: View) {
        currentOperator = (view as Button).text.toString()
        if (firstOperand == null) {
            firstOperand = currentInput.toDouble()
        } else {
            // ถ้ามี firstOperand แล้วแสดงว่าเป็นการคำนวณต่อเนื่อง
            onEqualsButtonClick()  // ทำการคำนวณก่อนหน้ากับ currentOperator ที่กด
            currentOperator = (view as Button).text.toString()
        }
        currentInput = "0"
    }

    //ทำหน้าที่คำนวณผลลัพธ์ขึ้นอยู่กับตัวดำเนินการที่กำลังใช้งานและแสดงผลลัพธ์นั้นบนหน้าจอของแอป
    private fun onEqualsButtonClick() {
        if (currentOperator != null && firstOperand != null) {
            val secondOperand = currentInput.toDouble()
            val result = when (currentOperator) {
                "+" -> firstOperand!! + secondOperand
                "-" -> firstOperand!! - secondOperand
                "x" -> firstOperand!! * secondOperand
                "/" -> firstOperand!! / secondOperand
                "%" -> firstOperand!! % secondOperand
                else -> throw IllegalArgumentException("Invalid operator")
            }

            currentInput = result.toString()
            firstOperand = result

            updateResult()
        }
    }


    private fun updateResult() {
        resultTextView.text = currentInput
    }
}