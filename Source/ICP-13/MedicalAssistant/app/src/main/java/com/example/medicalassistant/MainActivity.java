package com.example.medicalassistant;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.speech.RecognizerIntent;
import android.speech.tts.TextToSpeech;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

public class MainActivity extends AppCompatActivity {

    private static final int REQ_CODE_SPEECH_INPUT = 100;
    private TextView mVoiceInputTv;
    private ImageButton mSpeakBtn;
    private String textResult = "";
    private TextToSpeech speaker;
    private SharedPreferences preferences;
    private SharedPreferences.Editor editor;
    private static final String NAME = "name";
    private String inputName = "";

    private final int CHECK_CODE = 0x1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mVoiceInputTv = findViewById(R.id.voiceInput);
        mSpeakBtn = findViewById(R.id.btnSpeak);
        mSpeakBtn.setOnClickListener(v -> startVoiceInput());
        speaker = new TextToSpeech(getApplicationContext(), new TextToSpeech.OnInitListener(){
            @Override
            public void onInit(int status) {
                speaker.setLanguage(Locale.US);
            }
        });
    }




    private void startVoiceInput() {
        Intent intent = new Intent(RecognizerIntent.ACTION_RECOGNIZE_SPEECH);
        intent.putExtra(RecognizerIntent.EXTRA_CALLING_PACKAGE, getClass().getPackage().getName());
        intent.putExtra(RecognizerIntent.EXTRA_LANGUAGE_MODEL, RecognizerIntent.LANGUAGE_MODEL_FREE_FORM);
        intent.putExtra(RecognizerIntent.EXTRA_MAX_RESULTS,  5);
        intent.putExtra(RecognizerIntent.EXTRA_LANGUAGE, Locale.getDefault());
        intent.putExtra(RecognizerIntent.EXTRA_PROMPT, "Hello!");

        startActivityForResult(intent, REQ_CODE_SPEECH_INPUT);

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        switch (requestCode) {
            case REQ_CODE_SPEECH_INPUT: {
                if (resultCode == RESULT_OK && null != data) {
                    ArrayList<String> result = data.getStringArrayListExtra(RecognizerIntent.EXTRA_RESULTS);
                    textResult += result.get(0) + "\n";
                    if(result.get(0).equals("hello")){
                        speaker.speak("What is your name?", TextToSpeech.QUEUE_FLUSH, null,null);

                    }else if(result.get(0).contains("my name is")){
                        String Name = result.get(0).toString();
                        inputName = Name.replace("my name is ", "");
                        preferences = getSharedPreferences(result.get(0),0);
                        editor = preferences.edit();
                        editor.putString(NAME, Name).apply();
                        textResult += inputName + "\n";
                        speaker.speak("Hello " + inputName, TextToSpeech.QUEUE_FLUSH, null,null);

                    }else if(result.get(0).contains("I am not feeling well what should I do")){
                        speaker.speak("I can understand. Please tell your symptoms in short.",
                                TextToSpeech.QUEUE_FLUSH, null,null);

                    }else if(result.get(0).contains("thank you my medical assistant")){
                        speaker.speak("Thank you too, " + inputName + "Take care",
                                TextToSpeech.QUEUE_FLUSH, null,null);

                    }else if(result.get(0).contains("what time is it")){
                        SimpleDateFormat sdfDate = new SimpleDateFormat("HH:mm");//dd/MM/yyyy
                        Date now = new Date();
                        String[] strDate = sdfDate.format(now).split(":");
                        if(strDate[1].contains("00"))strDate[1] = "o'clock";
                        speaker.speak("The time is " + sdfDate.format(now),
                                TextToSpeech.QUEUE_FLUSH, null,null);

                    } else if(result.get(0).contains("what medicine should I take")){
                        speaker.speak("I think you have a fever. Please take this medicine.",
                                TextToSpeech.QUEUE_FLUSH, null,null);

                    }
                    mVoiceInputTv.setText(textResult);
                }
                break;
            }
        }
    }
}