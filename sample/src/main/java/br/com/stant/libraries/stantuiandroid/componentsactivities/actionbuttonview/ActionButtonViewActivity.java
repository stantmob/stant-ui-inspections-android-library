package br.com.stant.libraries.stantuiandroid.componentsactivities.actionbuttonview;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.Toast;


import br.com.stant.libraries.stantuiandroid.R;
import br.com.stant.libraries.stantuiandroid.databinding.ActionButtonViewTestActBinding;
import br.com.stant.libraries.uilibrary.components.actionbuttonview.ActionButtonViewContract.OnClickActionButtonListener;

public class ActionButtonViewActivity extends AppCompatActivity {

    private ActionButtonViewTestActBinding mActionButtonViewTestActBinding;
    
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        mActionButtonViewTestActBinding = DataBindingUtil.setContentView(this, R.layout.action_button_view_test_act);
        mActionButtonViewTestActBinding.actionButtonViewHorizontalComponent.setOnClickActionButtonListener(new OnClickActionButtonListener() {
            @Override
            public void onClick() {
                Toast.makeText(ActionButtonViewActivity.this, "Horizontal Component", Toast.LENGTH_SHORT).show();
            }
        });
        mActionButtonViewTestActBinding.actionButtonViewVerticalComponent.setOnClickActionButtonListener(new OnClickActionButtonListener() {
            @Override
            public void onClick() {
                Toast.makeText(ActionButtonViewActivity.this, "Vertical Component", Toast.LENGTH_SHORT).show();
            }
        });
    }
}
