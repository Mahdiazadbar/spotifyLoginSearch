package com.azadbar.digipaychallenge

import android.content.Context
import android.content.Intent
import com.azadbar.digipaychallenge.ui.fragment.login.LoginContract
import com.azadbar.digipaychallenge.ui.fragment.login.LoginPresenter
import com.azadbar.digipaychallenge.utility.Storage
import com.spotify.sdk.android.authentication.AuthenticationClient
import com.spotify.sdk.android.authentication.AuthenticationResponse
import org.junit.Assert
import org.junit.Before
import org.junit.Test
import org.mockito.Mock
import org.mockito.Mockito



class ExampleUnitTest {



    @Mock
    lateinit var view: LoginContract.View

    @Mock
    lateinit var storage: Storage

    @Mock
    lateinit var intent: Intent

    @Mock
    lateinit var context: Context


    @Mock
    lateinit var authenticationClient: AuthenticationClient



    private lateinit var presenter:LoginPresenter




    @Before
    fun setUp() {
        view = Mockito.mock(LoginContract.View::class.java)
        intent = Mockito.mock(Intent::class.java)
        storage = Mockito.mock(Storage::class.java)
        context = Mockito.mock(Context::class.java)
        authenticationClient = Mockito.mock(AuthenticationClient::class.java)
    }


    @Test
    fun testHandelLogin() {

        presenter=LoginPresenter(view,storage)

        val type=AuthenticationResponse.Type.ERROR
        presenter.checkType(type)

        Assert.assertEquals( presenter.checkType(type),"FAIL")
    }
}
