package com.example.testlayoutapp

import android.content.Intent
import android.content.pm.PackageManager
import android.net.Uri
import android.os.Build
import android.os.Bundle
import android.speech.tts.TextToSpeech
import android.speech.tts.Voice
import android.util.Log
import android.widget.Button
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.AppCompatEditText
import java.util.*


class TTSActivity : AppCompatActivity() {

    private val TAG = "TTSActivity"

    private lateinit var textToSpeechEngine: TextToSpeech

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_tts_layout)

        val et_text_input = findViewById<AppCompatEditText>(R.id.et_text_input)


        try {
            if (isGoogleTTSInstalled())
                createGoogleTTS()
            else
                installGoogleTTS()

        } catch (nle: NullPointerException) {
            Log.d(TAG, nle.toString())
        }



        findViewById<Button>(R.id.btn_tts).setOnClickListener {
            // Get the text to be converted to speech from our EditText.
            val text = et_text_input.text.toString().trim()
            // Check if user hasn't input any text.
            if (text.isNotEmpty()) {
                // Lollipop and above requires an additional ID to be passed.
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                    // Call Lollipop+ function
                    if (::textToSpeechEngine.isInitialized) textToSpeechEngine.speak(text,
                        TextToSpeech.QUEUE_FLUSH,
                        null,
                        "tts1"
                    )
                } else {
                    // Call Legacy function
                    if (::textToSpeechEngine.isInitialized) textToSpeechEngine.speak(
                        text,
                        TextToSpeech.QUEUE_FLUSH,
                        null
                    )
                }
            } else {
                Toast.makeText(this, "Text cannot be empty", Toast.LENGTH_LONG).show()
            }
        }
    }

    private fun createGoogleTTS() {
        // Pass in context and the listener.
        textToSpeechEngine = TextToSpeech(
            this,
            TextToSpeech.OnInitListener { status ->
                // set our locale only if init was success.
                if (status == TextToSpeech.SUCCESS) {
                    // textToSpeechEngine.language = Locale.UK
                    onTTSInitialized()
                } else {
                    openTTSSettingsToInstallUnsupportedLanguage()
                }
            }, "com.google.android.tts"
        )
    }

    private fun onTTSInitialized() {
        try {
            if (isGoogleTTSInstalled()) {
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                    val voiceList = textToSpeechEngine.voices
                    Log.d(TAG, voiceList.toString())
                    voiceList.forEach {
                        Log.d(TAG, it.toString())
                        Log.d("TTSActivity1", it.name)
                    }
                    setVoiceSelection()
                }
            } else
                installGoogleTTS()

        } catch (nle: NullPointerException) {

        }
    }

    private fun isGoogleTTSInstalled(): Boolean {
        val ttsIntent = Intent()
        ttsIntent.action = TextToSpeech.Engine.ACTION_CHECK_TTS_DATA
        val pm = this.packageManager
        val listOfInstalledTTSInfo =
            pm.queryIntentActivities(ttsIntent, PackageManager.GET_META_DATA)
        for (r in listOfInstalledTTSInfo) {
            val engineName = r.activityInfo.applicationInfo.packageName
            if (engineName == "com.google.android.tts") {
                return true
            }
        }
        return false
    }

    private fun installGoogleTTS() {
        val goToMarket = Intent(Intent.ACTION_VIEW)
            .setData(Uri.parse("market://details?id=com.google.android.tts"))
        startActivity(goToMarket)
    }

    // use this if attempting to speak in custom locale results in onError()
    // being called by your UtteranceProgressListener.
    private fun openTTSSettingsToInstallUnsupportedLanguage() {
        val intent = Intent()
        intent.action = "com.android.settings.TTS_SETTINGS"
        intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK
        startActivity(intent)
    }

    private fun setVoiceSelection() {
        val a: MutableSet<String> = HashSet()
        a.add("male") //here you can give male if you want to select male voice.

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            //val v = Voice("en-us-x-sfg#male_2-local", Locale("en", "US"), 400, 200, true, a)
          //  val v = Voice("hi-in-x-hie-local", Locale("hi", "IN"), 400, 200, false, a) // male hindi voice
          //  val v = Voice("hi-in-x-hic-local", Locale("hi", "IN"), 500, 200, false, a) // female hindi voice

              val v = Voice("gu-in-x-gum-local", Locale("gu", "IN"), 500, 200, false, a) // male gujarati voice



            textToSpeechEngine.voice = v
            textToSpeechEngine.setSpeechRate(0.8f)

            // int result = T2S.setLanguage(Locale.US);
            val result: Int = textToSpeechEngine.setVoice(v)

            if (result == TextToSpeech.LANG_MISSING_DATA
                || result == TextToSpeech.LANG_NOT_SUPPORTED
            ) {
                Log.e(TAG, "This Language is not supported")
            }/* else {
                // btnSpeak.setEnabled(true);
                speakOut("Data")
            }*/
        }
    }

    @RequiresApi(Build.VERSION_CODES.LOLLIPOP)
    private fun speakOut(message : String){
        if (::textToSpeechEngine.isInitialized) textToSpeechEngine.speak(message, TextToSpeech.QUEUE_FLUSH, null, "tts1")
    }

    override fun onPause() {
        textToSpeechEngine.stop()
        super.onPause()
    }

    override fun onDestroy() {
        textToSpeechEngine.shutdown()
        super.onDestroy()
    }

}