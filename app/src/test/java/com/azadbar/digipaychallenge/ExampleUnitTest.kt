package com.azadbar.digipaychallenge

import com.azadbar.digipaychallenge.ui.fragment.login.LoginContract
import com.azadbar.digipaychallenge.ui.fragment.login.LoginPresenter
import com.spotify.sdk.android.authentication.AuthenticationResponse
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.runners.MockitoJUnitRunner


@RunWith(MockitoJUnitRunner::class)
class ExampleUnitTest  {

    @Mock
    private lateinit var view: LoginContract.View

    @Mock
    private lateinit var appSharedPreferences: com.azadbar.digipaychallenge.utility.AppSharedPreferences


    lateinit var presenter: LoginPresenter


    @Before
    fun setUp() {
        presenter = LoginPresenter(view, appSharedPreferences)
    }

    @Test
    fun testHandelLogin() {


        presenter.handelLoginResponse(AuthenticationResponse.Type.TOKEN,"" )

        Mockito.verify(view).onLoginSuccess()

    }

    @Test
    fun testHandelFailLogin() {


        presenter.handelLoginResponse(AuthenticationResponse.Type.ERROR,"" )

        Mockito.verify(view).onLoginFail()

    }
}
