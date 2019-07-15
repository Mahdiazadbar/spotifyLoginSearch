package com.azadbar.digipaychallenge.ui.fragment.login

import android.content.Intent
import com.azadbar.digipaychallenge.utility.Storage
import com.spotify.sdk.android.authentication.AuthenticationClient
import com.spotify.sdk.android.authentication.AuthenticationResponse

class LoginPresenter(
    view: LoginContract.View,
    storage: Storage
) : LoginContract.Presenter {


    private var view: LoginContract.View? = view
    private var storage = storage

    override fun onDestroy() {
        this.view = null
    }


    override fun startLogin() {
        view?.startForLoginSpotify()
    }

    override fun handelLoginResponse(requestCode: Int, resultCode: Int, data: Intent?) {

        val response = AuthenticationClient.getResponse(resultCode, data)
        when (checkType(response.type)) {
            "SUCCESS" -> {
                storage.setAuthToken(response.accessToken)
                view?.onLoginSuccess()
            }
            "FAIL" -> {
                view?.onLoginFail()
            }
        }
    }

    public fun checkType(type: AuthenticationResponse.Type): String {
        return when (type) {
            // Response was successful and contains auth token
            AuthenticationResponse.Type.TOKEN -> {
                "SUCCESS"
            }
            AuthenticationResponse.Type.ERROR -> {
                "FAIL"
            }
            else -> {
                "FAIL"
            }
        }
    }


}