package com.aezion.aecrm.activity

import android.content.Intent
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.View
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.aezion.aecrm.R
import com.google.android.material.textfield.TextInputEditText
import com.google.android.material.textfield.TextInputLayout

class LoginActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)


      val userInputLayout=findViewById<View>(R.id.username_inputLayout) as TextInputLayout
      val passInputLayout=findViewById<View>(R.id.password_inputLayout) as TextInputLayout
      val username=findViewById<View>(R.id.username_edittext) as TextInputEditText
      val password=findViewById<View>(R.id.password_edittext) as TextInputEditText
      val signIn=findViewById<View>(R.id.sign_in) as TextView

        /*val resId = R.anim.layout_animation_fall_down
        val animation = AnimationUtils.loadAnimation(this@LoginActivity, resId)
        powered_by_aezion.animation = animation*/

        signingIn(signIn)


        username.addTextChangedListener(object : TextWatcher{
            override fun beforeTextChanged(s: CharSequence, start: Int, count: Int, after: Int) {

            }

            override fun onTextChanged(s: CharSequence, start: Int, before: Int, count: Int) {

            }

            override fun afterTextChanged(s: Editable) {
                userInputLayout.error =
                        if (s.length > userInputLayout.counterMaxLength) {
                            "Max character length is: ${userInputLayout.counterMaxLength}"
                        } else null
            }
        })

        password.addTextChangedListener(object : TextWatcher{
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {

            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {

            }

            override fun afterTextChanged(s: Editable?) {
                if (s != null) {
                    passInputLayout.error=
                            if (s.length > passInputLayout.counterMaxLength) {
                                "Max character length is: ${passInputLayout.counterMaxLength}"
                            }else null
                }

            }
        })


    }

    private fun signingIn(signIn: TextView) {
        signIn.setOnClickListener {
        val intent=Intent(this, MainActivity::class.java)
            startActivity(intent)
        }

    }
}
