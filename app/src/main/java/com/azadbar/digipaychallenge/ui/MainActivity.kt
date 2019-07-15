package com.azadbar.digipaychallenge.ui

import android.content.Intent
import android.os.Bundle
import com.azadbar.digipaychallenge.R
import com.azadbar.digipaychallenge.ui.fragment.login.LoginFragment
import dagger.android.support.DaggerAppCompatActivity

class MainActivity : DaggerAppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        supportFragmentManager.beginTransaction().replace(R.id.root, LoginFragment.newInstance()).commit()


    }

    public override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        for (fragment in supportFragmentManager.fragments) {
            fragment.onActivityResult(requestCode, resultCode, data)
        }
    }







}
