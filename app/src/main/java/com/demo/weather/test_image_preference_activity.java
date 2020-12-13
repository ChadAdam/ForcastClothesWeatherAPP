package com.demo.weather;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.demo.weather.utils.PreferenceUtils;

import org.w3c.dom.Text;

public class test_image_preference_activity extends AppCompatActivity {
    ImageView testImage;
    Button enter;
    Button reset;
    Button b1x1;
    Button b1x2;
    Button b1x3;
    Button b1x4;
    Button b2x1;
    Button b2x2;
    Button b2x3;
    Button b2x4;
    TextView tv1;
    int head=1;
    int arms=1;
    int body=1;
    int legs=1;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.image_preference_layout);
        testImage = findViewById(R.id.image_preference);
        enter = findViewById(R.id.btnEnter);
        enter.setVisibility(View.VISIBLE);

        reset = findViewById(R.id.btnReset);
        reset.setVisibility(View.VISIBLE);

        enter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                head = preCheck(head);
                body= preCheck(body);
                arms = preCheck(arms);
                legs = preCheck(legs);
                //------------
                String results = String.valueOf(head)+String.valueOf(body)+String.valueOf(arms)+String.valueOf(legs);
                Toast.makeText(getApplicationContext() , results , Toast.LENGTH_SHORT).show();
                PreferenceUtils.setDefClothes(getApplicationContext(),head,body,arms,legs);
                //test_image_preference_activity.super.onBackPressed();
                //test_image_preference_activity.super.finish();
                Intent i = new Intent(getApplicationContext() , ClothesActivity.class);
                startActivity(i);
            }
        });
        reset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                    b1x1.setAlpha(0);
                    b1x2.setAlpha(0);
                    b1x3.setAlpha(0);
                    b1x4.setAlpha(0);
                    b2x1.setAlpha(0);
                    b2x2.setAlpha(0);
                    b2x3.setAlpha(0);
                    b2x4.setAlpha(0);
                    head =1;
                    body=1;
                    arms=1;
                    legs=1;
                    Toast.makeText(getApplicationContext(), "Reset", Toast.LENGTH_SHORT).show();
            }
        });
        //testImage.setImageDrawable(getResources().getDrawable(R.drawable.clothes_list));
        b1x1 =  findViewById(R.id.g1x1);
        b1x2 =  findViewById(R.id.g1x2);
        b1x3 = findViewById(R.id.g1x3);
        b1x4 = findViewById(R.id.g1x4);
        b2x1 =  findViewById(R.id.g2x1);
        b2x2 =  findViewById(R.id.g2x2);
        b2x3 = findViewById(R.id.g2x3);
        b2x4 = findViewById(R.id.g2x4);


        b1x1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                b1x1.setAlpha((float) 0.5);
                head=head++;
                //b1x1.setBackgroundColor(getResources().getColor(R.color.colorPrimaryDark));

            }
        });
        b1x2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                b1x2.setAlpha((float) 0.5);
                arms=arms++;
                body=body++;
                legs=legs++;
                //b1x1.setBackgroundColor(getResources().getColor(R.color.colorPrimaryDark));

            }
        });
        b1x3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                b1x3.setAlpha((float) 0.5);
                arms=arms+2;
                body=body+2;
                //b1x1.setBackgroundColor(getResources().getColor(R.color.colorPrimaryDark));

            }
        });
        b1x4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                b1x4.setAlpha((float) 0.5);
                legs=legs+3;
                //b1x1.setBackgroundColor(getResources().getColor(R.color.colorPrimaryDark));

            }
        });
        // Second row
        b2x1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                b2x1.setAlpha((float) 0.5);
                head++;
                //b1x1.setBackgroundColor(getResources().getColor(R.color.colorPrimaryDark));

            }
        });
        b2x2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                b2x2.setAlpha((float) 0.5);

                body=body+1;

                //b1x1.setBackgroundColor(getResources().getColor(R.color.colorPrimaryDark));

            }
        });
        b2x3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                b2x3.setAlpha((float) 0.5);
               arms= arms+1;
                body=body+1;

                //b1x1.setBackgroundColor(getResources().getColor(R.color.colorPrimaryDark));

            }
        });
        b2x4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                b2x4.setAlpha((float) 0.5);
                arms= arms+1;
                body=body+1;

                //b1x1.setBackgroundColor(getResources().getColor(R.color.colorPrimaryDark));

            }
        });
    }
    private static int preCheck(int num){
        int ret_val;
        if(num>3){
            ret_val = 3;
            return ret_val;
        }
        else {return num;}
    }
}
