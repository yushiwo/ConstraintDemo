package zr.com.constraintdemo;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import zr.com.constraintdemo.normal.BiasActivity;
import zr.com.constraintdemo.normal.CenterPositionActivity;
import zr.com.constraintdemo.normal.MarginActivity;
import zr.com.constraintdemo.normal.RelativePositionActivity;

public class NormalActivity extends AppCompatActivity implements View.OnClickListener{

    private Button mRelativePositionButton;
    private Button mMarginButton;
    private Button mCenterPositionButton;
    private Button mBiasButton;

    public static void launch(Context context) {
        Intent intent = new Intent();
        intent.setClass(context, NormalActivity.class);
        context.startActivity(intent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_normal);
        initView();
        setListener();
    }

    private void initView() {
        mRelativePositionButton = (Button) findViewById(R.id.btn_relative_position);
        mMarginButton = (Button) findViewById(R.id.btn_margins);
        mCenterPositionButton = (Button) findViewById(R.id.btn_center);
        mBiasButton = (Button) findViewById(R.id.btn_bias);
    }

    private void setListener() {
        mRelativePositionButton.setOnClickListener(this);
        mMarginButton.setOnClickListener(this);
        mCenterPositionButton.setOnClickListener(this);
        mBiasButton.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_relative_position:
                RelativePositionActivity.launch(this);
                break;

            case R.id.btn_margins:
                MarginActivity.launch(this);
                break;

            case R.id.btn_center:
                CenterPositionActivity.launch(this);
                break;

            case R.id.btn_bias:
                BiasActivity.launch(this);
                break;
        }
    }
}
