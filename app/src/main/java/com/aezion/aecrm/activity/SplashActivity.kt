package com.aezion.aecrm.activity

import android.content.Intent
import android.os.Bundle
import android.preference.PreferenceManager
import androidx.appcompat.app.AppCompatActivity
import com.aezion.aecrm.R
import kotlinx.android.synthetic.main.activity_splash.*

class SplashActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)
        //  Declare a new thread to do a preference check
        val t = Thread {
            //  Initialize SharedPreferences
            val getPrefs = PreferenceManager
                .getDefaultSharedPreferences(baseContext)
            //  Create a new boolean and preference and set it to true
            val isFirstStart = getPrefs.getBoolean("firstStart", true)
            //  If the activity has never started before...
            if (isFirstStart) {
                //  Launch app intro
                //  boolean firstTime=getPrefs.getBoolean("firstTime",true);
                val i = Intent(this@SplashActivity, IntroActivity::class.java)
                runOnUiThread { startActivity(i) }
                //  Make a new preferences editor
                val e = getPrefs.edit()
                //  Edit preference to make it false because we don't want this to run again
                e.putBoolean("firstStart", false)
                //  Apply changes
                e.apply()
            } else {

                practical_view.startAnim()
                /*practical_view.setOnParticleAnimListener {
                    runOnUiThread {
                        val i = Intent(applicationContext, LoginActivity::class.java)
                        i.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
                        i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK)
                        i.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION)
                        startActivity(i)
                        //finish()
                    }
                }*/

                runOnUiThread {

                    val i = Intent(applicationContext, LoginActivity::class.java)
                    i.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
                    i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK)
                    i.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION)
                    startActivity(i)
                    //finish()
                }
            }

        }
        // Start the thread
        t.start()
    }
}
