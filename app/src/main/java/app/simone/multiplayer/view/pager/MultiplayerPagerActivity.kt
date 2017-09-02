package app.simone.multiplayer.view.pager

import android.os.Bundle
import android.support.design.widget.TabLayout
import android.support.v4.app.Fragment
import android.support.v4.view.ViewPager
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.Toolbar
import android.view.Menu
import android.view.MenuItem
import android.view.View
import app.simone.R
import app.simone.multiplayer.messages.FbOnActivityResultMsg
import app.simone.multiplayer.model.MultiplayerType
import app.simone.multiplayer.view.invites.InvitesFragment
import app.simone.multiplayer.view.invites.NearbyInvitesFragment
import app.simone.multiplayer.view.newmatch.FriendsListFragment
import app.simone.shared.application.App
import app.simone.shared.utils.Constants
import app.simone.shared.utils.Utilities
import com.facebook.Profile


open class MultiplayerPagerActivity : AppCompatActivity() {

    var type : MultiplayerType? = null
    var friendsList = FriendsListFragment()
    var invites = InvitesFragment()
    var nearbyInvites = NearbyInvitesFragment()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_multiplayer_pager)

        type = MultiplayerType.valueOf(intent.getStringExtra("source"))

        val fragment : Fragment?

        if(type == MultiplayerType.NEARBY) {
            fragment = nearbyInvites
        } else {
            fragment = invites
        }

        // Get the ViewPager and set it's PagerAdapter so that it can display items
        val viewPager = findViewById(R.id.viewpager) as ViewPager
        viewPager.adapter = MultiplayerPagerAdapter(supportFragmentManager,
                arrayListOf(
                        FragmentContainer(friendsList, "New match"),
                        FragmentContainer(fragment, "Invites")
                ))

        // Give the TabLayout the ViewPager
        val tabLayout = findViewById(R.id.sliding_tabs) as TabLayout
        tabLayout.setupWithViewPager(viewPager)

        val toolbar = findViewById(R.id.multiplayer_toolbar) as Toolbar
        setSupportActionBar(toolbar)

        setFacebookViewVisible(Profile.getCurrentProfile() == null)

    }

    fun setFacebookViewVisible(visible : Boolean) {

        this.runOnUiThread {
            val fbLayout = findViewById(R.id.layout_facebook)
            val pagerLayout = findViewById(R.id.layout_pager)

            if(visible) {
                pagerLayout.visibility = View.INVISIBLE
                fbLayout.visibility = View.VISIBLE
            } else {
                pagerLayout.visibility = View.VISIBLE
                fbLayout.visibility = View.INVISIBLE
            }
        }
    }


    override fun onActivityResult(requestCode: Int, resultCode: Int, data: android.content.Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        val actor = Utilities.getActor(Constants.FBVIEW_ACTOR_NAME, App.getInstance().actorSystem);
        actor.tell(FbOnActivityResultMsg(requestCode, resultCode, data), akka.actor.ActorRef.noSender())
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_multiplayer_pager, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        if(item?.itemId == R.id.action_user){
            setFacebookViewVisible(true)
            return true
        } else {
            return super.onOptionsItemSelected(item)
        }
    }


}
