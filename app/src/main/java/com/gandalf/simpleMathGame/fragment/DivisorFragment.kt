package com.gandalf.simpleMathGame.fragment

import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.gandalf.simpleMathGame.R
import kotlin.random.Random

class DivisorFragment : Fragment() {

    private lateinit var sharedPreferences: SharedPreferences
    private var maxangka = 49
    private var maxangkautama = 5
    private var minangka = 1
    private var randomGenerator = Random(System.currentTimeMillis())
    private lateinit var buttons : Array<Button>
    private lateinit var arrayInt : Array<Int>
    private var angkarand5 = 0
    private var currentScore: Int = 0
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
        val view = inflater.inflate(R.layout.fragment_divisor, container, false)

        sharedPreferences = requireActivity().getSharedPreferences("SHARED_PREF", Context.MODE_PRIVATE)
        editor = sharedPreferences.edit()

        buttons = arrayOf(view.findViewById(R.id.a1),
            view.findViewById(R.id.a2),
            view.findViewById(R.id.a3),
            view.findViewById(R.id.a4))

        val angkautama = view.findViewById<TextView>(R.id.angkautama)

        arrayInt = arrayOf(0,0,0,0)


        fun generateGame(){
            for (i in 0..3){
                arrayInt[i] = rand()
                buttons[i].text = arrayInt[i].toString()
            }
            angkarand5 = randutama()
            angkautama.text = "$angkarand5"
        }

        generateGame()

        fun check(angka:String){

            val hasil = angka.toInt()%angkarand5

            if (hasil == 0){
                Toast.makeText(requireContext(),"benar",Toast.LENGTH_SHORT).show()
                currentScore += 1
                editor.putInt("skor",currentScore)
                editor.apply()
                generateGame()

            }
            else{
                Toast.makeText(requireContext(),"salah",Toast.LENGTH_SHORT).show()
                generateGame()
            }
        }

        for (button in buttons)
        {
            button.setOnClickListener()
            {
                check(button.text.toString())
            }
        }


        return view
    }

    private fun rand() : Int {
        return randomGenerator.nextInt(maxangka - minangka) + minangka
    }

    private fun randutama() : Int {
        return randomGenerator.nextInt(maxangkautama - minangka) + minangka
    }



}