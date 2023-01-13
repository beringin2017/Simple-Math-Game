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
import kotlin.random.Random

class OperationMathFragment : Fragment() {

    var maxangkatambah = 20
    var maxangkakurang = 50
    var minangka = 1
    val minangkakurang = 30
    private lateinit var sharedPreferences: SharedPreferences
    private lateinit var editor: SharedPreferences.Editor

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

        sharedPreferences = requireActivity().getSharedPreferences("SHARED_PREF", Context.MODE_PRIVATE)
        editor = sharedPreferences.edit()

        val operasi1 = view.findViewById<Button>(R.id.operasi1)
        val operasi2 = view.findViewById<Button>(R.id.operasi2)
        val operasi3 = view.findViewById<Button>(R.id.operasi3)

        val arrayInt = arrayOf(operasi1,operasi2,operasi3)

        val angka1 = randtambah()
        val angka2 = randtambah()
        val angka3 = randtambah()
        val angka4 = randtambah()
        val angka5 = randkurang()
        val angka6 = randtambah()

        val hasil1 = angka1 + angka2
        val hasil2 = angka3 + angka4
        val hasil3 = angka5 - angka6

        operasi1.setOnClickListener {
            if (hasil1 > hasil2 && hasil1 > hasil3){
                editor.putInt("skor",100)
                editor.apply()
                Toast.makeText(requireContext(),"Benar",Toast.LENGTH_SHORT).show()
            }
            else{
                editor.putInt("skor",0)
                editor.apply()
                Toast.makeText(requireContext(),"Salah",Toast.LENGTH_SHORT).show()
            }
        }
        operasi2.setOnClickListener {
            if (hasil2 > hasil1 && hasil2 > hasil3){
                editor.putInt("skor",100)
                editor.apply()
                Toast.makeText(requireContext(),"Benar",Toast.LENGTH_SHORT).show()
            }
            else{
                editor.putInt("skor",0)
                editor.apply()
                Toast.makeText(requireContext(),"Salah",Toast.LENGTH_SHORT).show()
            }
        }
        operasi3.setOnClickListener {
            if (hasil3 > hasil1 && hasil3 > hasil2){
                editor.putInt("skor",100)
                editor.apply()
                Toast.makeText(requireContext(),"Benar",Toast.LENGTH_SHORT).show()
            }
            else{
                editor.putInt("skor",0)
                editor.apply()
                Toast.makeText(requireContext(),"Salah",Toast.LENGTH_SHORT).show()
            }
        }

        arrayInt[0].text = "$angka1 + $angka2"
        arrayInt[1].text = "$angka3 + $angka4"
        arrayInt[2].text = "$angka5 - $angka6"

        return view
    }

   fun randtambah() : Int {
        return Random.nextInt(maxangkatambah - minangka) + minangka
    }

    fun randkurang() : Int {
        return Random.nextInt(maxangkakurang - minangkakurang) + minangkakurang
    }


}