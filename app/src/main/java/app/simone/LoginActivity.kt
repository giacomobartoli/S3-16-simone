package app.simone


import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.TableRow
import io.realm.Realm
import io.realm.RealmConfiguration
import io.realm.RealmList
import java.util.*
import org.jetbrains.anko.toast
import app.simone.Exporter


class LoginActivity : AppCompatActivity() {

    private var realm: Realm? = null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
        Realm.init(this)
        val config = RealmConfiguration.Builder().name("players.realm").deleteRealmIfMigrationNeeded().schemaVersion(3).build()
        Log.d("REALMMMM","path:"+realm?.path.toString())
        Realm.setDefaultConfiguration(config)
        realm = Realm.getDefaultInstance()
        fillDB()
    }

    fun fillDB() {
        realm!!.executeTransaction { realm ->
            val playerName = "ciccio"
            if (realm.where(Player::class.java).equalTo("name", playerName).findAll().isEmpty()) {
                val player = realm.createObject(Player::class.java, playerName)

                val match1 = realm.createObject(Match::class.java)
                val match2 = realm.createObject(Match::class.java)
                val match3 = realm.createObject(Match::class.java)
                val match4 = realm.createObject(Match::class.java)

                match1.score = Random().nextInt(20)
                match2.score = Random().nextInt(20)
                match3.score = Random().nextInt(20)
                match4.score = Random().nextInt(20)

                player.matches = RealmList(match1, match2, match3, match4)
                //Log.d("REALMMMM", "_TRANSAZIONE FINITAA")
                //Log.d("REALMMMM", "path:" + realm?.path.toString())
                toast("DB POPOLATO")
                //textView?.text = player.matches.first().score.toString()
            } else {
                //textView?.text = "error: name already chosen!"
                //Log.d("REALMMMM", "_TRANSAZIONE GIA' FATTA")
                //Log.d("REALMMMM", "path:" + realm?.path.toString())
                toast("DB GiA' PRESENTE")
                var bt1 = findViewById(R.id.btn1)
                bt1?.setOnClickListener {
                    // Handler code here.
                    openDB()


                }
            }
        }
    }


    private fun openDB() {
        toast("APRO DB")
        var exp = Exporter()
    }

    override fun onDestroy() {
        super.onDestroy()
        realm!!.close()
    }
    fun checkPlayerInfo(view: View){
        //val intent = Intent(this, ::class.java)
        //startActivity(intent)

    }


}



