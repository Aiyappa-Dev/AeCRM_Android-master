package com.aezion.aecrm.activity

import android.content.Intent
import android.graphics.Color
import android.os.Build
import android.os.Bundle
import androidx.fragment.app.Fragment
import com.aezion.aecrm.R

import com.github.paolorotolo.appintro.AppIntro
import com.github.paolorotolo.appintro.AppIntroFragment
import com.github.paolorotolo.appintro.model.SliderPage

class IntroActivity : AppIntro() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        // Note here that we DO NOT use setContentView();
        // Instead of fragments, you can also use our default slide.
        // Just create a `SliderPage` and provide title, description, background and image.
        // AppIntro will do the rest.
        val sliderPage = SliderPage()
        sliderPage.title = getString(R.string.prospects)
        sliderPage.description = "This is Prospect Page"

        sliderPage.imageDrawable = R.drawable.proposal
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            sliderPage.bgColor = applicationContext.getColor(R.color.colorPrimaryDark)
        }
        addSlide(AppIntroFragment.newInstance(sliderPage))


        sliderPage.title = getString(R.string.contact)
        sliderPage.description = "This is Contact Page"
        sliderPage.imageDrawable = R.drawable.contact
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            sliderPage.bgColor = applicationContext.getColor(R.color.colorPD)
        }
        addSlide(AppIntroFragment.newInstance(sliderPage))


        sliderPage.title = getString(R.string.proposals)
        sliderPage.description = "This is Proposal Page"
        sliderPage.imageDrawable = R.drawable.prospact
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            sliderPage.bgColor = applicationContext.getColor(R.color.proposal_amount)
        }
        addSlide(AppIntroFragment.newInstance(sliderPage))

        // OPTIONAL METHODS
        // Override bar/separator color.
        //setBarColor(Color.parseColor("#3F51B5"));
        setSeparatorColor(Color.parseColor("#2196F3"))

        // Hide Skip/Done button.
        showSkipButton(true)
        isProgressButtonEnabled = true

        // Turn vibration on and set intensity.
        // NOTE: you will probably need to ask VIBRATE permission in Manifest.
        setVibrate(true)
        setVibrateIntensity(30)
    }

    override fun onSkipPressed(currentFragment: Fragment?) {
        super.onSkipPressed(currentFragment)
        // Do something when users tap on Skip button.
        introToLogPage()
    }

    private fun introToLogPage() {
        val i = Intent(applicationContext, SplashActivity::class.java)
        i.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
        i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK)
        //i.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION)
        startActivity(i)
        finish()
    }

    override fun onDonePressed(currentFragment: Fragment?) {
        super.onDonePressed(currentFragment)
        // Do something when users tap on Done button.
        introToLogPage()
    }

    override fun onSlideChanged(oldFragment: Fragment?, newFragment: Fragment?) {
        super.onSlideChanged(oldFragment, newFragment)
        // Do something when the slide changes.
    }
}
