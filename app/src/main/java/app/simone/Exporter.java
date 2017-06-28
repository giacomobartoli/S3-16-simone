package app.simone;

import android.os.Environment;
import android.widget.Toast;

import org.jetbrains.annotations.NotNull;

import java.io.File;

import io.realm.Realm;
import io.realm.internal.IOException;

/**
 * Created by Giacomo on 26/06/2017.
 */

public class Exporter {

    @NotNull
    private String path;

    public Exporter(){
        exportDB();
    }

    public void exportDB(){
        final Realm realm = Realm.getDefaultInstance();

        try {
            final File file = new File("/data/data/app.simone/files/players.realm");
            if (file.exists()) {
                //noinspection ResultOfMethodCallIgnored
                file.delete();
            }

            realm.writeCopyTo(file);

        } catch (IOException e) {
            realm.close();
            e.printStackTrace();
        }
    }
}
