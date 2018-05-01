package com.xiangli.coretraining;

import android.content.Intent;
import android.speech.tts.TextToSpeech;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;

import java.util.Locale;

public class TrainingProgram extends AppCompatActivity {

    private final int[] itemId = {R.id.item1, R.id.item2, R.id.item3, R.id.item4, R.id.item5, R.id.item6, R.id.item7, R.id.item8,
            R.id.item9, R.id.item10, R.id.item11, R.id.item12, R.id.item13, R.id.item14, R.id.item15};
    private TextToSpeech tts;
    public static int grade;

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

        setGrade();
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
            if (itemNum[i] != 0) {
                itemName[i].setText(Item.item[itemNum[i] - 1][0] + "     " + Item.item[itemNum[i] - 1][2]);
            }
        }
    }

    public void setGrade() {
        Spinner spinner = findViewById(R.id.spinner);

        ArrayAdapter adapter = ArrayAdapter.createFromResource(this, R.array.grade, R.layout.support_simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                if (parent.getSelectedItem().toString() == "初級") {
                    grade = 1;
                } else if (parent.getSelectedItem().toString() == "中級") {
                    grade = 2;
                } else {
                    grade = 3;
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                grade = 1;
            }
        });
    }
}
