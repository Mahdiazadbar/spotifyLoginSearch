package com.azadbar.digipaychallenge.ui.fragment

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.azadbar.digipaychallenge.utility.PrefStore
import com.azadbar.digipaychallenge.R
import com.spotify.sdk.android.authentication.AuthenticationClient
import com.spotify.sdk.android.authentication.AuthenticationRequest
import com.spotify.sdk.android.authentication.AuthenticationResponse
import dagger.android.support.DaggerFragment
import kotlinx.android.synthetic.main.fragmnet_login.*

class LoginFragment : DaggerFragment(){

    lateinit var prefs: PrefStore

    companion object {

        fun newInstance(): LoginFragment {
            return LoginFragment()
        }
    }

    override fun onCreateView(inflater: LayoutInflater,
                              container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragmnet_login, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        button_login.setOnClickListener {
            startActivityResult()
        }

        createUtils()


    }

    private fun startActivityResult() {

        val test = AuthenticationRequest.Builder("ba05b9cd59634cefa8493ac961d76ed6",
                AuthenticationResponse.Type.TOKEN, "http://mydigipay.com/")
                .setScopes(arrayOf("user-top-read")).build()
        AuthenticationClient.openLoginActivity(activity, 123, test)
    }


    private fun createUtils() {
        prefs = PrefStore(context)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        val response = AuthenticationClient.getResponse(resultCode, data)
        prefs.setAuthToken(response.accessToken)
        showSearch()
    }


    private fun showSearch() {
        fragmentManager!!.beginTransaction().replace(
            R.id.root,
            SearchFragment.newInstance()
        ).commit()

    }





}