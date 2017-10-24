package com.solly74.spongemytext;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.*;

@RunWith(MockitoJUnitRunner.class)
public class UnitTests {

    @Mock
    emailValidator myEmailer = new emailValidator();

    @Test
    public void emailValidatorTest() throws Exception {



        assertThat(myEmailer.validateEmail("solly74@gmail.com"),is(true));


    }
}