package app.simone;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.typesafe.config.Config;
import com.typesafe.config.ConfigFactory;

import java.io.File;

import akka.actor.ActorRef;
import akka.actor.ActorSelection;
import akka.actor.ActorSystem;
import akka.actor.Props;

public class TestingRemote extends AppCompatActivity {

    private Button btn;
    private ActorRef act;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_testing_remote);
        btn = (Button)findViewById(R.id.btn);
        init();
    }

    private void init(){
        Config config = ConfigFactory.parseFile(new File("app1.conf"));
        ActorSystem system = ActorSystem.create("MySystem",config);
        act = system.actorOf(Props.create(HappyActor.class),"myActor");
        System.out.println(act.path());
        Toast.makeText(this, "the happy actor is running!", Toast.LENGTH_LONG).show();

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    sendMsgRemote();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                    System.out.println("errore");
                }
            }
        });

    }

    private void sendMsgRemote() throws InterruptedException {
        act.tell(new HelloMsg("World",this), ActorRef.noSender());
        Thread.sleep(10000);
    }
}
