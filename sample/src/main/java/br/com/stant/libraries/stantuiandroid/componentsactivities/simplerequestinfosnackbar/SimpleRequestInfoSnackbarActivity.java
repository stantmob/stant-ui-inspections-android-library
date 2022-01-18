package br.com.stant.libraries.stantuiandroid.componentsactivities.simplerequestinfosnackbar;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.Toast;

import br.com.stant.libraries.stantuiandroid.R;
import br.com.stant.libraries.stantuiandroid.databinding.ActionButtonViewTestActBinding;
import br.com.stant.libraries.uilibrary.components.actionbuttonview.ActionButtonViewContract.OnClickActionButtonListener;
import br.com.stant.libraries.uilibrary.components.simplerequestinfosnackbar.SimpleRequestInfoSnackbar;
import br.com.stant.libraries.uilibrary.components.simplerequestinfosnackbar.SimpleRequestInfoSnackbarTypeEnum;

public class SimpleRequestInfoSnackbarActivity extends AppCompatActivity {

    private ActionButtonViewTestActBinding mActionButtonViewTestActBinding;
    
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        mActionButtonViewTestActBinding = DataBindingUtil.setContentView(this, R.layout.action_button_view_test_act);

        mActionButtonViewTestActBinding.actionButtonViewHorizontalComponent.setOnClickActionButtonListener(new OnClickActionButtonListener() {
            @Override
            public void onClick() {
                SimpleRequestInfoSnackbar snackbar = new SimpleRequestInfoSnackbar(SimpleRequestInfoSnackbarActivity.this,
                        mActionButtonViewTestActBinding.getRoot(), "Success message", SimpleRequestInfoSnackbarTypeEnum.SUCCESS);
                snackbar.showSnackbar();
            }
        });

        mActionButtonViewTestActBinding.actionButtonViewVerticalComponent.setOnClickActionButtonListener(new OnClickActionButtonListener() {
            @Override
            public void onClick() {
                SimpleRequestInfoSnackbar snackbar = new SimpleRequestInfoSnackbar(SimpleRequestInfoSnackbarActivity.this,
                        mActionButtonViewTestActBinding.getRoot(), "Fail message", SimpleRequestInfoSnackbarTypeEnum.FAILED);
                snackbar.showSnackbar();
            }
        });
    }
}
