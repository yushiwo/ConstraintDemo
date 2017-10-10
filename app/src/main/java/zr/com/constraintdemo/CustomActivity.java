package zr.com.constraintdemo;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class CustomActivity extends AppCompatActivity {


    public static void launch(Context context) {
        Intent intent = new Intent();
        intent.setClass(context, CustomActivity.class);
        context.startActivity(intent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_custom);
    }
}
