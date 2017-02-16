package com.tigerspike.business;


import com.tigerspike.business.logic.MainViewPresenter;

import org.junit.Test;

;import java.security.InvalidParameterException;

import static org.junit.Assert.assertEquals;


/**
 * Copyright (c) 2017.
 * All rights reserved.
 *
 * @author enricodelzotto
 * @since 10/04/16
 */
public class ExampleFuntionalTestMainLogic {

    //With this design-pattern we can test logic in a separate context from android,
    //for absurd the same code can run in a different os.
    //We for example we can write the same code in different languages (c++ for example)
    //compiling as .so and running through jni but the final result should be the same.
    //With this approch, we can write logic in complex app once a time, (as in c++ core case)
    //and running in different os (iOS,etc).
    //These are simple example on how to tests this layer.

    //In the data layer / network/ also unit tests are expected.

    @Test
    public void testIfConstructorParametersAreValid() {
        int exceptions = 0;
        try {
            MainViewPresenter mainLogic = new MainViewPresenter(
                    new NetworkControllerMock(),
                    new LoggerControllerMock(),
                    new DataControllerMock(),
                    new ShareControllerMock(),
                    new MockView()
            );
        } catch (InvalidParameterException e) {
            exceptions++;
        }
        assertEquals(exceptions, 0);

    }

    //Have we the right error handling?
    @Test
    public void testIfConstructorParametersAreInvalid() {
        int exceptions = 0;
        try {
            MainViewPresenter mainLogic = new MainViewPresenter(
                    new NetworkControllerMock(),
                    new LoggerControllerMock(),
                    new DataControllerMock(),
                    new ShareControllerMock(),
                    null
            );
        } catch (InvalidParameterException e) {
            exceptions++;
        }
        assertEquals(exceptions, 1);

    }

}
