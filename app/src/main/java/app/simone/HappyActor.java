package RemoteActors2;
import android.widget.Toast;

import akka.actor.UntypedActor;
import app.simone.TestingRemote;

public class HappyActor extends UntypedActor {
	public void onReceive(Object msg) {
    if (msg instanceof HelloMsg) {
    	HelloMsg hello = (HelloMsg) msg;
        TestingRemote tr =((HelloMsg) msg).getTR();
    	//System.out.println("Hello "+hello.getContent());
        Toast.makeText(tr, "Msg ricevuto", Toast.LENGTH_LONG).show();
    } else
      unhandled(msg);
  }
}
