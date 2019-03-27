package tech.xiaosuo.com.remoteviewsimple;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class TestActivity1 extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test1);
    }


    @Override
    public void finish() {
        super.finish();
       // overridePendingTransition(0,R.anim.exit_activity);
    }
}
