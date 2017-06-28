package RemoteActors2;

import android.view.View;

import java.io.Serializable;

import app.simone.TestingRemote;

public final class HelloMsg implements Serializable {
	private final String content;
	private TestingRemote tr;

	public HelloMsg(String content, TestingRemote tr){
		this.content = content;
		this.tr = tr;
	}
	
	public String getContent(){
		return content;
	}

	public TestingRemote getTR() {return tr; }
}
