package com.gandalf.utsmpr.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import com.gandalf.utsmpr.HighScore
import com.gandalf.utsmpr.R
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import java.util.*
import kotlin.random.Random

class DivisorFragment : Fragment() {

    private var maxangka = 49
    private var maxangkautama = 5
    private lateinit var angkautama: TextView
    private var minangka = 1
    private var randomGenerator = Random(System.currentTimeMillis())
    private lateinit var buttons : Array<Button>
    private lateinit var arrayInt : Array<Int>
    private var angkarand5 = 0
    private var currentScore : Int = 0
    lateinit var coroutine: Job
    val GAMECODE = 1
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

        angkautama = view.findViewById(R.id.angkautama)



        GlobalScope.launch {
            val highScore = HighScore(UUID.randomUUID().toString(), "raj",GAMECODE, currentScore)
        }

        coroutine = lifecycleScope.launch{
            viewLifecycleOwner.lifecycle.repeatOnLifecycle(Lifecycle.State.CREATED)
            {
                generateGame()
            }
        }

        for (button in buttons){
            button.setOnClickListener{
                check(button.text.toString())
            }
        }


        return view

    }
    private fun generateGame(){
        angkarand5 = randutama()
        arrayInt = arrayOf(0,0,0,0)
        for (i in 0..3){
            arrayInt[i] = rand()
            buttons[i].text = arrayInt[i].toString()
        }
        angkautama.text = "$angkarand5"
    }
    private fun check(angka:String){

        val hasil = angka.toInt()%angkarand5

        if (hasil == 0){
            Toast.makeText(requireContext(),"Benar",Toast.LENGTH_SHORT).show()
            currentScore += 1
            generateGame()
        }
        else{
            Toast.makeText(requireContext(),"Salah",Toast.LENGTH_SHORT).show()
            generateGame()
        }
    }





    private fun rand() : Int {
        return randomGenerator.nextInt(maxangka - minangka) + minangka
    }

    private fun randutama() : Int {
        return randomGenerator.nextInt(maxangkautama - minangka) + minangka
    }



}