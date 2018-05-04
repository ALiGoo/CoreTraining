package com.xiangli.coretraining;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.Spinner;

public class MainActivity extends AppCompatActivity {

    ImageButton lossWeight;
    ImageButton abdomen;
    ImageButton body;
    ImageButton kick;
    ImageButton throwBall;
    ImageButton humpBack;
    ImageButton waist;
    ImageButton tired;
    Button music;
    Spinner spinner;
    public static int grade;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        lossWeight = findViewById(R.id.lossWeight);
        abdomen = findViewById(R.id.abdomen);
        body = findViewById(R.id.body);
        kick = findViewById(R.id.kick);
        throwBall = findViewById(R.id.throwball);
        humpBack = findViewById(R.id.humpback);
        waist = findViewById(R.id.waist);
        tired = findViewById(R.id.tiredd);
        music = findViewById(R.id.music);
        music.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                gotoMusic();
            }
        });


        setGrade();

        lossWeight.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                gotoLossWeight(view);
            }
        });
        abdomen.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                gotoAbdomen(view);
            }
        });
        body.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                gotoBody(view);
            }
        });
        kick.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                gotoKick(view);
            }
        });
        throwBall.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                gotoThrowBall(view);
            }
        });
        humpBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                gotoHumpBack(view);
            }
        });
        waist.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                gotoWaist(view);
            }
        });
        tired.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                gotoTired(view);
            }
        });
    }

    public void gotoLossWeight(View view) {
        Intent intent = new Intent(this, TrainingProgram.class);
        int pictureRes = R.drawable.run;
        int[] itemNum = {3, 8, 9, 11, 13, 0, 17, 19, 0, 23, 25, 0, 3, 8, 9};
        String note = "[備註]這些是一個以提高新陳代謝為目標的練習。";
        intent.putExtra("pictureRes", pictureRes);
        intent.putExtra("itemNum", itemNum);
        intent.putExtra("note", note);
        startActivity(intent);
    }

    public void gotoAbdomen(View view) {
        Intent intent = new Intent(this, TrainingProgram.class);
        int pictureRes = R.drawable.abdomen;
        int[] itemNum = {2, 8, 10, 11, 12, 0, 19, 21, 0, 26, 28, 0, 2, 8, 10};
        String note = "[備註]要瘦小腹，需要均勻地鍛鍊腹部周圍才行。以腹直肌為主來鍛鍊側腹部。";
        intent.putExtra("pictureRes", pictureRes);
        intent.putExtra("itemNum", itemNum);
        intent.putExtra("note", note);
        startActivity(intent);
    }

    public void gotoBody(View view) {
        Intent intent = new Intent(this, TrainingProgram.class);
        int pictureRes = R.drawable.body;
        int[] itemNum = {2, 6, 7, 11, 13, 14, 18, 20, 22, 24, 27, 30, 2, 6, 7};
        String note = "[備註]重點在安定骨盆。以腰部周圍為主，來鍛鍊深層肌肉吧。";
        intent.putExtra("pictureRes", pictureRes);
        intent.putExtra("itemNum", itemNum);
        intent.putExtra("note", note);
        startActivity(intent);
    }

    public void gotoKick(View view) {
        Intent intent = new Intent(this, TrainingProgram.class);
        int pictureRes = R.drawable.kick;
        int[] itemNum = {2, 3, 5, 13, 15, 16, 17, 19, 21, 23, 25, 29, 2, 3, 5};
        String note = "[備註]首先強化身體的軸心，鍛鍊背部的肌肉。再去做連動就可以增加踢力和腳力。";
        intent.putExtra("pictureRes", pictureRes);
        intent.putExtra("itemNum", itemNum);
        intent.putExtra("note", note);
        startActivity(intent);
    }

    public void gotoThrowBall(View view) {
        Intent intent = new Intent(this, TrainingProgram.class);
        int pictureRes = R.drawable.throwball;
        int[] itemNum = {3, 4, 8, 14, 15, 16, 18, 19, 21, 24, 27, 29, 3, 4, 8};
        String note = "[備註]首先強化身體的軸心，鍛鍊背部的肌肉。再去做連動就可以增加踢力和腳力。";
        intent.putExtra("pictureRes", pictureRes);
        intent.putExtra("itemNum", itemNum);
        intent.putExtra("note", note);
        startActivity(intent);
    }

    public void gotoHumpBack(View view) {
        Intent intent = new Intent(this, TrainingProgram.class);
        int pictureRes = R.drawable.humpback;
        int[] itemNum = {1, 6, 7, 11, 14, 0, 19, 20, 0, 25, 27, 0, 1, 6, 7};
        String note = "[備註]要改善姿勢，背部、骨盆和腿都是重點。目的在鍛鍊從頭部往下一直線的軸心。";
        intent.putExtra("pictureRes", pictureRes);
        intent.putExtra("itemNum", itemNum);
        intent.putExtra("note", note);
        startActivity(intent);
    }

    public void gotoWaist(View view) {
        Intent intent = new Intent(this, TrainingProgram.class);
        int pictureRes = R.drawable.waist;
        int[] itemNum = {1, 3, 6, 12, 14, 0, 17
                , 22, 0, 23, 25, 0, 1, 3, 6};
        String note = "[備註]提高腹部和腰部周圍的柔軟性，一邊鬆弛一邊鍛鍊。對練臀部也很重要。";
        intent.putExtra("pictureRes", pictureRes);
        intent.putExtra("itemNum", itemNum);
        intent.putExtra("note", note);
        startActivity(intent);
    }

    public void gotoTired(View view) {
        Intent intent = new Intent(this, TrainingProgram.class);
        int pictureRes = R.drawable.tired;
        int[] itemNum = {1, 4, 8, 12, 15, 0, 20, 22, 0, 28, 30, 0, 1, 4, 8};
        String note = "[備註]為了促進血液循環，提高恢復力，群身運動和伸展是很重要的。這練習可以鍛鍊到你的全身。";
        intent.putExtra("pictureRes", pictureRes);
        intent.putExtra("itemNum", itemNum);
        intent.putExtra("note", note);
        startActivity(intent);
    }

    public void setGrade() {
        Spinner spinner = findViewById(R.id.spinner);

        ArrayAdapter adapter = ArrayAdapter.createFromResource(this, R.array.grade, R.layout.support_simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                switch (parent.getSelectedItem().toString()) {
                    case "初級":
                        grade = 1;
                        break;
                    case "中級":
                        grade = 2;
                        break;
                    case "高級":
                        grade = 3;
                        break;
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                grade = 1;
            }
        });
    }

    public void gotoMusic() {
        Intent intent = new Intent(this, Music.class);
        startActivity(intent);
    }
}
