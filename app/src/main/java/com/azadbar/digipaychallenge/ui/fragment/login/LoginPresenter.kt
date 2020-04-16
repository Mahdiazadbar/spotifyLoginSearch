package com.azadbar.digipaychallenge.ui.fragment.login

import com.azadbar.digipaychallenge.utility.Storage
import com.spotify.sdk.android.authentication.AuthenticationResponse

class LoginPresenter(
    private var view: LoginContract.View?,
    private var storage: Storage
) : LoginContract.Presenter {


    override fun onDestroy() {
        this.view = null
    }


    override fun startLogin() {
        view?.startForLoginSpotify()
    }

    override fun handelLoginResponse(type: AuthenticationResponse.Type, token: String) {

        when (checkType(type)) {
            "SUCCESS" -> {
                storage.setAuthToken(token)
                view?.onLoginSuccess()
            }
            "FAIL" -> {
                view?.onLoginFail()
            }
        }
    }

    fun checkType(type: AuthenticationResponse.Type): String {
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