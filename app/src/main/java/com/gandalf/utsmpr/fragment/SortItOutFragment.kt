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


class SortItOutFragment : Fragment() {

    private lateinit var sharedPreferences: SharedPreferences
    var maxangka = 19
    var minangka = 1
    var deret = 0
    var randomGenerator = Random(System.currentTimeMillis())
    lateinit var buttons : Array<Button>
    lateinit var arrayInt : Array<Int>
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
        val view = inflater.inflate(R.layout.fragment_sort_it_out, container, false)

        sharedPreferences = requireActivity().getSharedPreferences("SHARED_PREF", Context.MODE_PRIVATE)
        editor = sharedPreferences.edit()

        buttons = arrayOf(view.findViewById(R.id.angka1),
                    view.findViewById(R.id.angka2),
                    view.findViewById(R.id.angka3),
                    view.findViewById(R.id.angka4),
                    view.findViewById(R.id.angka5),
                    view.findViewById(R.id.angka6),
                    view.findViewById(R.id.angka7),
                    view.findViewById(R.id.angka8),
                    view.findViewById(R.id.angka9))

        arrayInt = arrayOf(0,0,0,0,0,0,0,0,0)


        fun generateGame(){
            deret = 0
            for (i in 0..8){
                arrayInt[i] = rand()
                buttons[i].text = arrayInt[i].toString()
            }

            arrayInt.sort()
        }

        generateGame()

        for (button in buttons)
        {
            button.setOnClickListener()
            {

            if(deret != 8){
                    check(button.text.toString(),button)
                }
                else{

                    currentScore += 1
                    editor.putInt("skor",currentScore)
                    editor.apply()
                    for (button in buttons) {
                        button.isEnabled = true
                        button.setBackgroundResource(R.drawable.bg_button)
                    }
                    generateGame()

                    Toast.makeText(requireContext(),"ntapss",Toast.LENGTH_SHORT).show()
                }
            }
        }



        return view
    }

    fun check(angka:String,button: Button){
        if (angka == arrayInt[deret].toString()){
            button.isEnabled = false
            button.setBackgroundResource(R.drawable.bg_roundedgrey)
            deret++
        }
        else{
            Toast.makeText(requireContext(),"salah",Toast.LENGTH_SHORT).show()
        }
    }


    fun rand() : Int {
        return randomGenerator.nextInt(maxangka - minangka) + minangka
    }

}