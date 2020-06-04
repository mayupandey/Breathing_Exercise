package com.gamingfella.breathingexcerciese.screen.breathing

import android.media.MediaPlayer
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.fragment.NavHostFragment

import com.gamingfella.breathingexcerciese.R
import com.gamingfella.breathingexcerciese.databinding.FragmentBreathingBinding
import kotlinx.coroutines.*
import kotlin.system.measureTimeMillis

/**
 * A simple [Fragment] subclass.
 */
class BreathingFragment : Fragment() {
private lateinit var mediaPlayer: MediaPlayer
    private lateinit var mediaPlayer2: MediaPlayer
    private lateinit var binding:FragmentBreathingBinding
    private lateinit var viewModel: BreathingFramentViewModel
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = DataBindingUtil.inflate(
            inflater,
            R.layout.fragment_breathing,
            container,
            false
        )
        viewModel = ViewModelProviders.of(this).get(BreathingFramentViewModel::class.java)



        // Set the viewModel for databinding - this allows the bound layout access
        // to all the data in the VieWModel
        binding.gameViewModel = viewModel
        // Specify the fragment view as the lifecycle owner of the binding.
        // This is used so that the binding can observe LiveData updates
        binding.lifecycleOwner = viewLifecycleOwner


        // Observer for the Game finished event
        viewModel.eventGameFinish.observe(this, Observer<Boolean> { hasFinished ->
            if (hasFinished) gameFinished()
        })
        omchant()
        //coroutine code
        fakeApi()

        return binding.root
    }
    private  fun fakeApi() {
        CoroutineScope(Dispatchers.IO).launch {
            val job1=launch {
                val time1= measureTimeMillis {
                    Log.d("launching","${Thread.currentThread().name}")
                    val result=getResult()
                    setTextonUi(result)

                }
                Log.d("com","ho gya firest")
            }
        }
    }
    private fun gameFinished() {

        NavHostFragment.findNavController(this).navigate(R.id.action_breathingFragment_to_titleFragment)
        viewModel.onGameFinishComplete()
    }

    private suspend fun getResult():String{
        delay(50000)
        return "10 Seconds left"
    }
// sets the text
    private fun setText(input:String){
        var ab="$input"
        binding.timeleft.visibility=View.VISIBLE
        binding.timeleft.text=ab
        Log.i("a","i am being called $ab")

    }
    //stops the chant and plays ti sound
    private fun play(){
        mediaPlayer= MediaPlayer.create(context,R.raw.ti)
        mediaPlayer.start()
        mediaPlayer2.stop()
    }
    //plays om chanting
    private fun omchant(){
        mediaPlayer2=MediaPlayer.create(context,R.raw.om)
        mediaPlayer2.start()
    }
    //passes the text
    private suspend fun setTextonUi(input:String){
        withContext(Dispatchers.Main){
            setText(input)
            play()

        }
    }
}
