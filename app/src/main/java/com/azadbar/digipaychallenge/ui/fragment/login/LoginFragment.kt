package com.azadbar.digipaychallenge.ui.fragment.login

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.azadbar.digipaychallenge.utility.Storage
import com.azadbar.digipaychallenge.R
import com.azadbar.digipaychallenge.ui.fragment.search.SearchFragment
import com.spotify.sdk.android.authentication.AuthenticationClient
import com.spotify.sdk.android.authentication.AuthenticationRequest
import com.spotify.sdk.android.authentication.AuthenticationResponse
import dagger.android.support.DaggerFragment
import kotlinx.android.synthetic.main.fragmnet_login.*

class LoginFragment : DaggerFragment(),LoginContract.View{


    private lateinit var presenter: LoginContract.Presenter

    lateinit var prefs: Storage

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
            presenter.startLogin()
        }

        prefs = Storage(context!!)

        setPresenter(LoginPresenter(this,prefs))

    }

    override fun setPresenter(presenter: LoginContract.Presenter) {
        this.presenter = presenter
    }


    override fun startForLoginSpotify() {
        val test = AuthenticationRequest.Builder("ba05b9cd59634cefa8493ac961d76ed6",
            AuthenticationResponse.Type.TOKEN, "http://mydigipay.com/")
            .setScopes(arrayOf("user-top-read")).build()
        AuthenticationClient.openLoginActivity(activity, 123, test)
    }


    override fun onLoginSuccess() {
        fragmentManager!!.beginTransaction().replace(
            R.id.root,
            SearchFragment.newInstance()
        ).commit()
    }

    override fun onLoginFail() {
        Toast.makeText(context, getText(R.string.fail_login), Toast.LENGTH_SHORT).show()
    }


    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        val response = AuthenticationClient.getResponse(resultCode, data)

        presenter.handelLoginResponse(response.type,response.accessToken)

    }










}