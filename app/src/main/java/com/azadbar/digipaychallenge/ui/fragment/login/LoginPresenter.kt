package com.azadbar.digipaychallenge.ui.fragment.login

import com.azadbar.digipaychallenge.utility.AppSharedPreferences
import com.spotify.sdk.android.authentication.AuthenticationResponse
import javax.inject.Inject

class LoginPresenter constructor(
    private var view: LoginContract.View?,
    private var appSharedPreferences: AppSharedPreferences
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
                appSharedPreferences.setAuthToken(token)
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