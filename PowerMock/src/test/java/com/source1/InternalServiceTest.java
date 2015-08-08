package com.source1;

import com.source1.ExternalService;
import com.source1.InternalService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.powermock.api.mockito.PowerMockito;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;

/**
 * Created by Саша on 09.08.2015.
 * {@link http://habrahabr.ru/post/172239/}
 * ****************************************
 */

@RunWith(PowerMockRunner.class)
@PrepareForTest({ ExternalService.class })
public class InternalServiceTest {

    @Before
    public void before() {
        Mockito.reset(externalService);
    }

    @Test
    public void doWorkTest() {
        internalService.doWork();
        Mockito.verify(externalService).doMegaWork();
    }

    private final ExternalService externalService = PowerMockito.mock(ExternalService.class);
    private final InternalService internalService = new InternalService(externalService);
}