package com.elshafee.androiden.unittesting

import com.elshafee.androiden.unittest.RegistrationUtil
import com.google.common.truth.Truth.assertThat
import org.junit.Test

class RegistrationUtilTest {

    @Test
    fun `empty username returns false`(){
        var result = RegistrationUtil.validateRegistrationInput(
            "",
            "123",
            "123"
        )
        assertThat(result).isFalse()
    }
    @Test
    fun `empty password returns false`(){
        var result = RegistrationUtil.validateRegistrationInput(
            "ahmad",
            "",
            ""
        )
        assertThat(result).isFalse()
    }
    @Test
    fun `password and confirmation is not matched returns false`(){
        var result = RegistrationUtil.validateRegistrationInput(
            "ahmad",
            "123",
            "321"
        )
        assertThat(result).isFalse()
    }
        @Test
    fun `already token username returns false`(){
        var result = RegistrationUtil.validateRegistrationInput(
            "Said",
            "123",
            "123"
        )
        assertThat(result).isFalse()
    }
    @Test
    fun `password is less than 2 digits returns false`(){
        var result = RegistrationUtil.validateRegistrationInput(
            "Said",
            "1",
            "1"
        )
        assertThat(result).isFalse()
    }
    @Test
    fun `valid data returns false`(){
        var result = RegistrationUtil.validateRegistrationInput(
            "Amr",
            "123",
            "123"
        )
        assertThat(result).isTrue()
    }






}