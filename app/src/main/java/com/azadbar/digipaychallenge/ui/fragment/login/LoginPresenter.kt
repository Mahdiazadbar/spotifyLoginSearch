package com.azadbar.digipaychallenge.ui.fragment.login

import android.content.Intent
import com.azadbar.digipaychallenge.di.DependencyInjector
import com.azadbar.digipaychallenge.utility.PrefStore
import com.spotify.sdk.android.authentication.AuthenticationClient
import com.spotify.sdk.android.authentication.AuthenticationResponse

class LoginPresenter(
    view: LoginContract.View,
    prefStore: PrefStore,
    dependencyInjector: DependencyInjector
) : LoginContract.Presenter {



    private var view: LoginContract.View? = view
    private var storage= prefStore

    override fun onDestroy() {
        this.view = null
    }


    override fun startLogin() {
        view?.startForLoginSpotify()
    }

    override fun handelLoginResponse(requestCode: Int, resultCode: Int, data: Intent?) {

        val response = AuthenticationClient.getResponse(resultCode, data)

        when (response.type) {
            // Response was successful and contains auth token
            AuthenticationResponse.Type.TOKEN -> {
                storage.setAuthToken(response.accessToken)
                view?.onLoginSuccess()
            }
            AuthenticationResponse.Type.ERROR -> {
                view?.onLoginFail()
            }
            else -> {
                view?.onLoginFail()
            }
        }
    }


}