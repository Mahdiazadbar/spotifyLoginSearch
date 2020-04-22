package com.azadbar.digipaychallenge.ui.fragment.login

import android.content.Intent
import com.azadbar.digipaychallenge.base.BasePresenter
import com.azadbar.digipaychallenge.base.BaseView
import com.spotify.sdk.android.authentication.AuthenticationResponse
import javax.inject.Inject

interface LoginContract {
    interface Presenter : BasePresenter {
        fun startLogin()
        fun handelLoginResponse(type:AuthenticationResponse.Type,token: String)
    }

    interface View {
        fun startForLoginSpotify()
        fun onLoginSuccess()
        fun onLoginFail()
    }
}
