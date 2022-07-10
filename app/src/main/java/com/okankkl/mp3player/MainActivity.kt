package com.okankkl.mp3player

import android.media.MediaPlayer
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.okankkl.mp3player.databinding.ActivityMainBinding
import kotlin.random.Random

class MainActivity : AppCompatActivity() {

    private lateinit var binding : ActivityMainBinding
    private  var mediaPlayer : MediaPlayer? = null
    private var isPlaying = false
    private var songList = ArrayList<Song>()
    private var position = 0
    private var isShuffle = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        fillArray()


        binding.apply {
            btnPlay.setOnClickListener {
                when(isPlaying){
                    false -> play()
                    true -> pause()
                }

            }

            btnNext.setOnClickListener {
                nextSong()
            }

            btnBack.setOnClickListener {
                backSong()
            }

            btnShuffle.setOnClickListener {
                if(isShuffle){
                    isShuffle = false
                    btnShuffle.setImageResource(R.drawable.ic_shuffle)
                }
                else{
                    isShuffle = true
                    btnShuffle.setImageResource(R.drawable.ic_shuffle_green)
                }

            }
        }

    }


    fun play(){
        if(mediaPlayer == null){
            mediaPlayer = MediaPlayer.create(this,songList[position].songInt)
            binding.txtSongName.text = songList[position].name
            binding.txtSingerName.text = songList[position].singerName

            mediaPlayer?.let{
                it.setOnCompletionListener {
                    nextSong()
                }
            }

        }

        mediaPlayer!!.start()
        binding.btnPlay.setImageResource(R.drawable.ic_pause)
        isPlaying=true


    }

    fun pause(){

        if(mediaPlayer != null){
            isPlaying = false
            binding.btnPlay.setImageResource(R.drawable.ic_play)
            mediaPlayer!!.pause()
        }

    }

    fun stopPlayer(){

        if(mediaPlayer != null){
            mediaPlayer!!.release()
            mediaPlayer = null
        }
    }

    fun nextSong(){

        if(isShuffle){
            var temp = position
            do{
                position = (0..7).random()

            }while (position == temp)
        }
        else{
            if(position == 7){
                position = 0
            }
            else{
                position++
            }
        }
        println(position)

        if(isPlaying == true){
            stopPlayer()
            play()
        }
        else{
            stopPlayer()
            binding.txtSongName.text = songList[position].name
            binding.txtSingerName.text = songList[position].singerName
        }

    }

    fun backSong(){

        if (position == 0) {
            position = 7
        } else {
            position--
        }
        if(isPlaying == true){
            stopPlayer()
            play()
        }
        else{
            stopPlayer()
            binding.txtSongName.text = songList[position].name
            binding.txtSingerName.text = songList[position].singerName
        }


    }

    fun stop(){
        stopPlayer()
    }

    override fun onStop() {
        super.onStop()
        stopPlayer()
    }

    fun fillArray(){


        var song = Song("Nabız","Hidra",R.raw.hidra_nabiz)
        songList.add(song)

        song = Song("Göçmen","Hidra",R.raw.hidra_gocmen)
        songList.add(song)

        song = Song("Otuz","Beta Berk Bayındır",R.raw.beta_otuz)
        songList.add(song)

        song = Song("Komşular","Allame",R.raw.allame_komsular)
        songList.add(song)

        song = Song("Caterpillar","Eminem",R.raw.eminem_caterpillar)
        songList.add(song)

        song = Song("City of Sin","Adam Jensen",R.raw.adam_jensen_city_of_sin)
        songList.add(song)

        song = Song("Godzilla","Eminem",R.raw.eminem_godzilla)
        songList.add(song)

        song = Song("Benden Bu Kadar","Patron",R.raw.patron_benden_bukadar)
        songList.add(song)

        song = Song("Mor Yazma","Umut Kaya",R.raw.umut_kaya_mor_yazma)
        songList.add(song)

        song = Song("Harikalar Diyarında 2","No1",R.raw.no1_harikalar_diyarinda)
        songList.add(song)

    }

}