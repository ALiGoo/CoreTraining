package com.xiangli.coretraining;

import android.content.Intent;
import android.os.Handler;
import android.os.Message;
import android.speech.tts.TextToSpeech;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

import java.util.Locale;
import java.util.Timer;
import java.util.TimerTask;

import pl.droidsonroids.gif.GifImageView;

public class Training extends AppCompatActivity {

    private TextToSpeech tts;
    private ImageButton next;
    private GifImageView gif;
    private TextView name;
    private TextView gradeNum;
    private int number = 1;
    private int tsec = 0, csec = 0, cmin = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().getDecorView().setSystemUiVisibility(
                View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                        | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                        | View.SYSTEM_UI_FLAG_FULLSCREEN
                        | View.SYSTEM_UI_FLAG_IMMERSIVE);
        setContentView(R.layout.activity_training);

        final Intent intent = getIntent();
        final int[] itemNum = intent.getIntArrayExtra("itemNum");

        gif = findViewById(R.id.gif);
        gif.setImageResource(Item.gif[itemNum[0] - 1]);
        next = findViewById(R.id.next);
        next.setOnClickListener(setListener(itemNum));
        name = findViewById(R.id.name);
        name.setText(Item.item[itemNum[0] - 1][0]);
        gradeNum = findViewById(R.id.gradeNum);
        gradeNum.setText(Item.item[itemNum[0] - 1][2]);
        timer();

        tts = new TextToSpeech(getApplicationContext(), new TextToSpeech.OnInitListener() {
            @Override
            public void onInit(int status) {
                if (status != TextToSpeech.ERROR) {
                    tts.setLanguage(Locale.TAIWAN);
                }
            }
        });

    }

    public void onPause() {
        super.onPause();
        if (tts != null) {
            tts.stop();
            tts.shutdown();
        }
    }

    public void onStop() {
        super.onStop();

        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }

    public View.OnClickListener setListener(final int[] itemNum) {

        final View.OnClickListener listener = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (number < itemNum.length) {
                    if (itemNum[number] == 0) {
                        number++;
                    }
                    gif.setImageResource(Item.gif[itemNum[number] - 1]);
                    name.setText(Item.item[itemNum[number] - 1][0]);
                    tts.stop();
                    if (MainActivity.grade == 1 || itemNum[number] <= 10) {
                        tts.speak(Item.item[itemNum[number] - 1][0] + Item.item[itemNum[number] - 1][1]
                                + Item.item[itemNum[number] - 1][2], TextToSpeech.QUEUE_FLUSH, null);
                        gradeNum.setText(Item.item[itemNum[number] - 1][2]);
                    } else if (MainActivity.grade == 2) {
                        tts.speak(Item.item[itemNum[number] - 1][0] + Item.item[itemNum[number] - 1][1] +
                                Item.item[itemNum[number] - 1][3], TextToSpeech.QUEUE_FLUSH, null);
                        gradeNum.setText(Item.item[itemNum[number] - 1][3]);
                    } else {
                        tts.speak(Item.item[itemNum[number] - 1][0] + Item.item[itemNum[number] - 1][1] +
                                Item.item[itemNum[number] - 1][4], TextToSpeech.QUEUE_FLUSH, null);
                        gradeNum.setText(Item.item[itemNum[number] - 1][4]);
                    }
                    number++;
                } else {
                    onStop();
                }
            }
        };
        return listener;
    }

    public void timer() {
        final TextView timer;

        timer = findViewById(R.id.timer);

        Timer timer01 = new Timer();

        final Handler handler = new Handler() {
            public void handleMessage(Message msg) {
                super.handleMessage(msg);
                switch (msg.what) {
                    case 1:
                        csec = tsec % 60;
                        cmin = tsec / 60;
                        String s = "";
                        if (cmin < 10) {
                            s = "0" + cmin;
                        } else {
                            s = "" + cmin;
                        }
                        if (csec < 10) {
                            s = s + ":0" + csec;
                        } else {
                            s = s + ":" + csec;
                        }
                        timer.setText(s);
                        break;
                }
            }
        };

        TimerTask task = new TimerTask() {
            @Override
            public void run() {
                tsec++;
                Message message = new Message();

                message.what = 1;
                handler.sendMessage(message);
            }
        };
        timer01.schedule(task, 0, 1000);
    }
}
