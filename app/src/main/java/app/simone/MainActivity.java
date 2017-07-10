package app.simone;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.TextView;
import com.crashlytics.android.Crashlytics;

import utils.AudioManager;
import app.simone.Controller.ControllerImplementations.DataManager;
import app.simone.users.FacebookLoginActivity;
import app.simone.users.GoogleGamesActivity;
import io.fabric.sdk.android.Fabric;

/**
 * @author Michele Sapignoli
 */
public class MainActivity extends FullscreenActivity{

    private Button VSCpuButton;
    private Button connectionButton;
    private Button btnMultiplayer;
    private Button multiplayerButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);



        Fabric.with(this, new Crashlytics());

        DataManager.Companion.getInstance().setup(this);

        VSCpuButton = (Button)findViewById(R.id.button_vs_cpu);
        connectionButton = (Button)findViewById(R.id.button4);
        btnMultiplayer = (Button)findViewById(R.id.main_button_multiplayer);

        //Listener on vs CPUActor button
        VSCpuButton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                openActivity(VSCpuActivity.class, R.anim.left_in, R.anim.right_out);
            }
        });

        btnMultiplayer.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                openActivity(FacebookLoginActivity.class, R.anim.left_in, R.anim.right_out);
            }
        });

        connectionButton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                openActivity(SettingsActivity.class, R.anim.left_in, R.anim.right_out);
            }
        });

        // Upon interacting with UI controls, delay any scheduled hide()
        // operations to prevent the jarring behavior of controls going away
        // while interacting with the UI.
        //findViewById(R.id.dummy_button).setOnTouchListener(mDelayHideTouchListener);


        multiplayerButton = (Button) findViewById(R.id.main_button_multiplayer);
        multiplayerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openActivity(FacebookLoginActivity.class, R.anim.left_in, R.anim.right_out);
            }
        });

        FloatingActionButton mainFab = (FloatingActionButton) findViewById(R.id.main_fab);
        TextView simoneTextView = (TextView) findViewById(R.id.main_simone_textview);
        Animation animation = AnimationUtils.loadAnimation(this, R.anim.pulse);
        mainFab.startAnimation(animation);
        simoneTextView.startAnimation(animation);

        mainFab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), GoogleGamesActivity.class);
                startActivity(intent);
            }
        });

        AudioManager.Companion.getInstance().playSimoneMusic();

        Button b = (Button) findViewById(R.id.main_button_highscore);
        b.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               openActivity(ScoreboardActivity.class, R.anim.slide_up, R.anim.slide_up_existing);
            }
        });
    }

    @Override
    protected void onPostCreate(Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
        // Trigger the initial hide() shortly after the activity has been
        // created, to briefly hint to the user that UI controls
        // are available.
        //delayedHide(100);
    }


    @Override
    protected void backTransition() {
        overridePendingTransition(R.anim.right_in, R.anim.left_out);
    }


    protected void setSubclassContentView() {
        setContentView(R.layout.activity_main);
        mContentView = findViewById(R.id.main_fullscreen_content);
        mVisible = true;
    }

    public void playVsCpu(View view) {
        Intent intent = new Intent(this, LoginActivity.class);
        startActivity(intent);

    }
}
