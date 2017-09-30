package zr.com.constraintdemo.normal;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import zr.com.constraintdemo.R;

public class RelativePositionActivity extends AppCompatActivity {


    public static void launch(Context context) {
        Intent intent = new Intent();
        intent.setClass(context, RelativePositionActivity.class);
        context.startActivity(intent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_relative_position);
    }
}
