package com.azadbar.digipaychallenge.ui

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.azadbar.digipaychallenge.R
import com.azadbar.digipaychallenge.di.component.DaggerAppComponent
import com.azadbar.digipaychallenge.ui.fragment.login.LoginFragment
import com.azadbar.digipaychallenge.utility.AppSharedPreferences
import dagger.android.AndroidInjector
import dagger.android.DispatchingAndroidInjector
import dagger.android.support.HasSupportFragmentInjector
import javax.inject.Inject

class MainActivity : AppCompatActivity(), HasSupportFragmentInjector {

    @Inject
    lateinit var appSharedPreferences: AppSharedPreferences

    @Inject
    lateinit var dispatchingAndroidInjector: DispatchingAndroidInjector<Fragment>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        appSharedPreferences.setAuthToken("")


        supportFragmentManager.beginTransaction().replace(R.id.root, LoginFragment.newInstance()).commit()


    }

    public override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        for (fragment in supportFragmentManager.fragments) {
            fragment.onActivityResult(requestCode, resultCode, data)
        }
    }

    override fun supportFragmentInjector(): AndroidInjector<Fragment> {
        return dispatchingAndroidInjector;
    }


}
