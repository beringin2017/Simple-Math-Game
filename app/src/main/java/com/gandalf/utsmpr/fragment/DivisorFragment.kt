package com.gandalf.utsmpr.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.gandalf.utsmpr.R
import kotlin.random.Random

class DivisorFragment : Fragment() {

    var maxangka = 49
    var maxangkautama = 5
    var minangka = 1
    var randomGenerator = Random(System.currentTimeMillis())
    lateinit var buttons : Array<Button>
    lateinit var arrayInt : Array<Int>
    var angkarand5 = 0

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

        buttons = arrayOf(view.findViewById(R.id.a1),
            view.findViewById(R.id.a2),
            view.findViewById(R.id.a3),
            view.findViewById(R.id.a4))

        val angkautama = view.findViewById<TextView>(R.id.angkautama)
        angkarand5 = randutama()
        arrayInt = arrayOf(0,0,0,0)

        for (i in 0..3){
            arrayInt[i] = rand()
            buttons[i].text = arrayInt[i].toString()
        }

        for (button in buttons)
        {
            button.setOnClickListener()
            {
                    view : View ->
                    check(button.text.toString())
            }
        }

        angkautama.text = "$angkarand5"

        return view
    }

    fun check(angka:String){

        val hasil = angka.toInt()%angkarand5

        if (hasil == 0){
            Toast.makeText(requireContext(),"benar",Toast.LENGTH_SHORT).show()

        }
        else{
            Toast.makeText(requireContext(),"salah",Toast.LENGTH_SHORT).show()
            activity?.finish()
        }
    }

    fun rand() : Int {
        return randomGenerator.nextInt(maxangka - minangka) + minangka
    }

    fun randutama() : Int {
        return randomGenerator.nextInt(maxangkautama - minangka) + minangka
    }



}