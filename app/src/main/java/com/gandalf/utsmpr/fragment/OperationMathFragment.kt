package com.gandalf.utsmpr.fragment

import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.gandalf.utsmpr.R

class OperationMathFragment : Fragment() {

    private lateinit var sharedPreferences: SharedPreferences
    private lateinit var editor: SharedPreferences.Editor
    private var currentScore: Int = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_operation_math, container, false)
        val operasi1 = view.findViewById<Button>(R.id.operasi1)
        val operasi2 = view.findViewById<Button>(R.id.operasi2)
        val operasi3 = view.findViewById<Button>(R.id.operasi3)

        sharedPreferences = requireActivity().getSharedPreferences("SHARED_PREF", Context.MODE_PRIVATE)
        editor = sharedPreferences.edit()

        fun generateMathOps(): Triple<Int, Int, Int> {
            val rand = kotlin.random.Random(System.currentTimeMillis())
            val n1 = rand.nextInt(20 - 1) + 1
            val n2 = rand.nextInt(20 - 1) + 1
            val n3 = rand.nextInt(20 - 1) + 1
            val n4 = rand.nextInt(20 - 1) + 1
            val n5 = rand.nextInt(50 - 30) + 30
            val n6 = rand.nextInt(20 - 1) + 1

            val mathOp1 = "$n1 + $n2"
            val mathOp2 = "$n3 + $n4"
            val mathOp3 = "$n5 - $n6"

            operasi1.text = mathOp1
            operasi2.text = mathOp2
            operasi3.text = mathOp3

            val hasil1 = n1 + n2
            val hasil2 = n3 + n4
            val hasil3 = n5 - n6

            return Triple(hasil1, hasil2, hasil3)
        }
        generateMathOps()
        val (hasil1, hasil2, hasil3) = generateMathOps()

        operasi1.setOnClickListener {
        if (hasil1 > hasil2 && hasil1 > hasil3){
            currentScore += 1
            editor.putInt("skor",currentScore)
            editor.apply()
            generateMathOps()
            Toast.makeText(requireContext(),"Benar",Toast.LENGTH_SHORT).show()
        }
        else{
            editor.putInt("skor",0)
            editor.apply()
            generateMathOps()
            Toast.makeText(requireContext(),"Salah",Toast.LENGTH_SHORT).show()
        }
    }
    operasi2.setOnClickListener {
        if (hasil2 > hasil1 && hasil2 > hasil3){
            currentScore += 1
            editor.putInt("skor",currentScore)
            editor.apply()
            generateMathOps()
            Toast.makeText(requireContext(),"Benar",Toast.LENGTH_SHORT).show()
        }
        else{
            editor.putInt("skor",0)
            editor.apply()
            generateMathOps()
            Toast.makeText(requireContext(),"Salah",Toast.LENGTH_SHORT).show()
        }
    }
    operasi3.setOnClickListener {
        if (hasil3 > hasil1 && hasil3 > hasil2){
            currentScore += 1
            editor.putInt("skor",currentScore)
            editor.apply()
            generateMathOps()
            Toast.makeText(requireContext(),"Benar",Toast.LENGTH_SHORT).show()
        }
        else{
            editor.putInt("skor",0)
            editor.apply()
            generateMathOps()
            Toast.makeText(requireContext(),"Salah",Toast.LENGTH_SHORT).show()
        }
    }


        return view
    }


}