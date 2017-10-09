package zr.com.constraintdemo;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import zr.com.constraintdemo.normal.BiasActivity;
import zr.com.constraintdemo.normal.CenterPositionActivity;
import zr.com.constraintdemo.normal.ChainActivity;
import zr.com.constraintdemo.normal.DimenActivity;
import zr.com.constraintdemo.normal.MarginActivity;
import zr.com.constraintdemo.normal.RatioActivity;
import zr.com.constraintdemo.normal.RelativePositionActivity;
import zr.com.constraintdemo.normal.VisibilityActivity;

public class NormalActivity extends AppCompatActivity implements View.OnClickListener{

    private Button mRelativePositionButton;
    private Button mMarginButton;
    private Button mCenterPositionButton;
    private Button mBiasButton;
    private Button mVisibilityButton;
    private Button mDimenButton;
    private Button mRatioButton;
    private Button mChainButton;

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
        mVisibilityButton = (Button) findViewById(R.id.btn_visibility);
        mDimenButton = (Button) findViewById(R.id.btn_dimen);
        mRatioButton = (Button) findViewById(R.id.btn_ratio);
        mChainButton = (Button) findViewById(R.id.btn_chain);
    }

    private void setListener() {
        mRelativePositionButton.setOnClickListener(this);
        mMarginButton.setOnClickListener(this);
        mCenterPositionButton.setOnClickListener(this);
        mBiasButton.setOnClickListener(this);
        mVisibilityButton.setOnClickListener(this);
        mDimenButton.setOnClickListener(this);
        mRatioButton.setOnClickListener(this);
        mChainButton.setOnClickListener(this);
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

            case R.id.btn_visibility:
                VisibilityActivity.launch(this);
                break;

            case R.id.btn_dimen:
                DimenActivity.launch(this);
                break;

            case R.id.btn_ratio:
                RatioActivity.launch(this);
                break;

            case R.id.btn_chain:
                ChainActivity.launch(this);
                break;
        }
    }
}
