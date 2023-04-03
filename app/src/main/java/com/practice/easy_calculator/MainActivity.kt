package com.practice.easy_calculator

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import java.lang.ArithmeticException

class MainActivity : AppCompatActivity() {
    private var tvInput :TextView?= null
      var lastNumeric :Boolean =false
    var lastDot :Boolean =false
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        tvInput =findViewById(R.id.tvInput)

    }
    fun onDigit(view: View){
        // view doesn't use text property but button use text property
      tvInput?.append((view as Button).text)
        lastNumeric =true
        lastDot=false

    }
    fun onClear(view: View){
        tvInput?.text = " "
    }
    fun onDecimalPoint(view: View){
      if (lastNumeric && !lastDot){
          tvInput?.append(".")
          lastNumeric =false
          lastDot =true
      }
    }
    // this method for finding the equals to
    fun onEqual(view: View){
        if(lastNumeric){
            var prefix = " "
            var tvValue =tvInput?.text.toString()
            try {
                if(tvValue.startsWith("-")){
                    prefix = "-"
                    // if we give -99 it will change to 99 by this below code
                    tvValue=tvValue.substring(1)
                }
                if (tvValue.contains("-")){
                    val splitValue =tvValue.split("-")   // agr 99-1 hai to phle 99 ko use krna hai i.e
                    // 99-->1
                    var one = splitValue[0]// this for the 99
                    var two = splitValue[1]// this for the 1
                    // this is for subtraction only


                    if (prefix.isNotEmpty()){
                        one = prefix + one
                    }
                    var result =one.toDouble()-two.toDouble()
                    tvInput?.text = removezeroAfterDot(result.toString())


                }
                else  if (tvValue.contains("+")){
                    val splitValue =tvValue.split("+")   // agr 99-1 hai to phle 99 ko use krna hai i.e
                    // 99-->1
                    var one = splitValue[0]// this for the 99
                    var two = splitValue[1]// this for the 1
                    // this is for subtraction only


                    if (prefix.isNotEmpty()){
                        one = prefix + one
                    }
                    var result =one.toDouble()+two.toDouble()
                    tvInput?.text = removezeroAfterDot(result.toString())


                }
                else  if (tvValue.contains("/")){
                    val splitValue =tvValue.split("/")   // agr 99-1 hai to phle 99 ko use krna hai i.e
                    // 99-->1
                    var one = splitValue[0]// this for the 99
                    var two = splitValue[1]// this for the 1
                    // this is for subtraction only


                    if (prefix.isNotEmpty()){
                        one = prefix + one
                    }
                    var result =one.toDouble()/two.toDouble()
                    tvInput?.text = removezeroAfterDot(result.toString())


                }
                else  if (tvValue.contains("*")){
                    val splitValue =tvValue.split("*")   // agr 99-1 hai to phle 99 ko use krna hai i.e
                    // 99-->1
                    var one = splitValue[0]// this for the 99
                    var two = splitValue[1]// this for the 1
                    // this is for subtraction only


                    if (prefix.isNotEmpty()){
                        one = prefix + one
                    }
                    var result =one.toDouble()*two.toDouble()
                    tvInput?.text = removezeroAfterDot(result.toString())


                }
            }
            catch (e:ArithmeticException){
                e.printStackTrace()
            }

        }
    }
    private fun removezeroAfterDot(result: String):String{
        var value = result
        if (result.contains(".0"))
            value = result.substring(0,result.length-2)
        return value
    }
    fun onOperator(view: View){
        // here i am checking the text exit or not then proceed
        tvInput?.text?.let {
            //here the lastNumeric is checking the last digit must be numeric like:-9/9 so we can perform
            // !isOperatorAdded is checking the we can't add another operator i.e 9/9-+ it is not valid
            // means we can only add one operator at a time
            if (lastNumeric && !isOperatorAdded(it.toString())){
                tvInput?.append((view as Button).text)
                lastNumeric = false
                lastDot = false
            }
        }

    }
    private fun isOperatorAdded(value:String):Boolean{
        return if (value.startsWith("-")){
            false
        }
        else{
            value.contains("/")
                    ||value.contains("*")
                    ||value.contains("+")
                    ||value.contains("-")

        }
    }
}