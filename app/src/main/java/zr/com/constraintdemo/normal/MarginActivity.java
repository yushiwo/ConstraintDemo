package zr.com.constraintdemo.normal;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import zr.com.constraintdemo.R;

public class MarginActivity extends AppCompatActivity implements View.OnClickListener {

    private Button mButton1, mButton2;

    public static void launch(Context context) {
        Intent intent = new Intent();
        intent.setClass(context, MarginActivity.class);
        context.startActivity(intent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_margin);

        initView();
        setListener();
    }

    private void initView() {
        mButton1 = (Button) findViewById(R.id.btn_1);
        mButton2 = (Button) findViewById(R.id.btn_2);
    }

    private void setListener() {
        mButton1.setOnClickListener(this);
        mButton2.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_1:
                mButton1.setVisibility(View.GONE);
                break;

            case R.id.btn_2:
                mButton2.setVisibility(View.GONE);
                break;
        }
    }
}
