package zr.com.constraintdemo.normal;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import zr.com.constraintdemo.R;

public class VisibilityActivity extends AppCompatActivity implements View.OnClickListener {

    private Button mButtonA, mButtonB;
    private Button mButton1, mButton2;

    public static void launch(Context context) {
        Intent intent = new Intent();
        intent.setClass(context, VisibilityActivity.class);
        context.startActivity(intent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_visibility);

        initView();
        setListener();
    }

    private void initView() {
        mButtonA = (Button) findViewById(R.id.btn_A);
        mButtonB = (Button) findViewById(R.id.btn_B);
        mButton1 = (Button) findViewById(R.id.btn_1);
        mButton2 = (Button) findViewById(R.id.btn_2);
    }

    private void setListener() {
        mButtonA.setOnClickListener(this);
        mButtonB.setOnClickListener(this);
        mButton1.setOnClickListener(this);
        mButton2.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_A:
            case R.id.btn_B:
                mButtonA.setVisibility(View.GONE);
                break;

            case R.id.btn_1:
            case R.id.btn_2:
                mButton2.setVisibility(View.GONE);
                break;
        }
    }
}
