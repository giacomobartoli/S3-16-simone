package pcd.lab11.actors_remote.pingpong;

import java.io.File;

import com.typesafe.config.Config;
import com.typesafe.config.ConfigFactory;

import akka.actor.ActorRef;
import akka.actor.ActorSystem;
import akka.actor.Props;

public class MainPonger {

  public static void main(String[] args) {
	Config config = ConfigFactory.parseFile(new File("src/main/java/pcd/lab11/actors_remote/pingpong/ponger.conf"));
	ActorSystem system = ActorSystem.create("MySystem",config);
	system.actorOf(Props.create(Ponger.class),"ponger");
  }
}
