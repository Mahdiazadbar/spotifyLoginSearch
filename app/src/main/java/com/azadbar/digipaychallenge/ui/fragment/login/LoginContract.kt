package com.azadbar.digipaychallenge.ui.fragment.login

import android.content.Intent
import com.azadbar.digipaychallenge.base.BasePresenter
import com.azadbar.digipaychallenge.base.BaseView

interface LoginContract {
    interface Presenter : BasePresenter {

        fun startLogin()
        fun handelLoginResponse(requestCode: Int, resultCode: Int, data: Intent?)
    }

    interface View : BaseView<Presenter>{
        fun startForLoginSpotify()
        fun onLoginSuccess()
        fun onLoginFail()
    }
}
