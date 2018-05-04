package com.xiangli.coretraining;

import android.content.Intent;
import android.speech.tts.TextToSpeech;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.Locale;

public class TrainingProgram extends AppCompatActivity {

    private final int[] itemId = {R.id.item1, R.id.item2, R.id.item3, R.id.item4, R.id.item5, R.id.item6, R.id.item7, R.id.item8,
            R.id.item9, R.id.item10, R.id.item11, R.id.item12, R.id.item13, R.id.item14, R.id.item15};
    private TextToSpeech tts;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_training_program);

        Intent intent = getIntent();
        final int[] itemNum = intent.getIntArrayExtra("itemNum");
        Button start = findViewById(R.id.start);

        tts = new TextToSpeech(getApplicationContext(), new TextToSpeech.OnInitListener() {
            @Override
            public void onInit(int status) {
                if (status != TextToSpeech.ERROR) {
                    tts.setLanguage(Locale.TAIWAN);
                }
            }
        });

        setItem(itemNum);
        start.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                gotoTraining(view, itemNum);
            }
        });
    }

    public void gotoTraining(View view, int[] itemNum) {
        tts.speak(Item.item[itemNum[0] - 1][0] + Item.item[itemNum[0] - 1][1] + Item.item[itemNum[0] - 1][2], TextToSpeech.QUEUE_FLUSH, null);
        Intent intent = new Intent(this, Training.class);
        intent.putExtra("itemNum", itemNum);
        startActivity(intent);
    }

    public void setItem(int[] itemNum) {
        Intent intent = getIntent();
        final int pictureRes = intent.getIntExtra("pictureRes", 0);
        final String note = intent.getStringExtra("note");
        TextView[] itemName = new TextView[itemId.length];
        TextView noteTV = findViewById(R.id.item17);
        ImageView picture = findViewById(R.id.imageView);

        picture.setImageResource(pictureRes);
        noteTV.setText(note);

        for (int i = 0; i < itemName.length; i++) {
            itemName[i] = findViewById(itemId[i]);
            String content;
            if (itemNum[i] != 0) {
                if (MainActivity.grade == 1 || itemNum[i] <= 10) {
                    content = Item.item[itemNum[i] - 1][0] + "     " + Item.item[itemNum[i] - 1][2];
                    itemName[i].setText(content);
                } else if (MainActivity.grade == 2) {
                    content = Item.item[itemNum[i] - 1][0] + "     " + Item.item[itemNum[i] - 1][3];
                    itemName[i].setText(content);
                } else {
                    content = Item.item[itemNum[i] - 1][0] + "     " + Item.item[itemNum[i] - 1][4];
                    itemName[i].setText(content);
                }

            }
        }
    }

}
