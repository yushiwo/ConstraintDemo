package zr.com.constraintdemo;

import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.support.constraint.ConstraintLayout;
import android.support.constraint.ConstraintSet;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.transition.TransitionManager;
import android.view.View;
import android.widget.Button;

public class ConstraintSetActivity extends AppCompatActivity implements View.OnClickListener {

    private ConstraintLayout constraintLayout;
    private ConstraintSet applyConstraintSet = new ConstraintSet();
    private ConstraintSet resetConstraintSet = new ConstraintSet();

    private Button mApplyButton, mResetButton;


    public static void launch(Context context) {
        Intent intent = new Intent();
        intent.setClass(context, ConstraintSetActivity.class);
        context.startActivity(intent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_constraint_set);

        initView();
        setListener();
    }

    private void initView() {
        constraintLayout = (ConstraintLayout) findViewById(R.id.activity_constraint_set);
        resetConstraintSet.clone(constraintLayout);
        applyConstraintSet.clone(constraintLayout);
        mApplyButton = (Button) findViewById(R.id.btn_4);
        mResetButton = (Button) findViewById(R.id.btn_5);
    }

    private void setListener() {
        mApplyButton.setOnClickListener(this);
        mResetButton.setOnClickListener(this);
    }

    public void onApplyClick() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            TransitionManager.beginDelayedTransition(constraintLayout);
        }

        applyConstraintSet.setMargin(R.id.btn_1, ConstraintSet.START, 0);
        applyConstraintSet.setMargin(R.id.btn_1, ConstraintSet.END, 0);
        applyConstraintSet.setMargin(R.id.btn_2, ConstraintSet.START, 0);
        applyConstraintSet.setMargin(R.id.btn_2, ConstraintSet.END, 0);
        applyConstraintSet.setMargin(R.id.btn_3, ConstraintSet.START, 0);
        applyConstraintSet.setMargin(R.id.btn_3, ConstraintSet.END, 0);


        applyConstraintSet.centerHorizontally(R.id.btn_1, R.id.activity_constraint_set);
        applyConstraintSet.centerHorizontally(R.id.btn_2, R.id.activity_constraint_set);
        applyConstraintSet.centerHorizontally(R.id.btn_3, R.id.activity_constraint_set);
        applyConstraintSet.applyTo(constraintLayout);
    }


    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    public void onResetClick() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            TransitionManager.beginDelayedTransition(constraintLayout);
        }
        resetConstraintSet.applyTo(constraintLayout);

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_4:
                onApplyClick();
                break;

            case R.id.btn_5:
                onResetClick();
                break;
        }
    }
}
