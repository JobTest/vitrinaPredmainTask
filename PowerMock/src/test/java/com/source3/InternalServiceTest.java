package com.source3;

import com.source1.ExternalService;
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
 * и, собственно, тест
 */

@RunWith(PowerMockRunner.class)
@PrepareForTest({ ExternalServiceFactory.class, ExternalService.class })
public class InternalServiceTest {

    public InternalServiceTest() throws Exception {
        PowerMockito.whenNew(ExternalService.class).withNoArguments().thenReturn(externalService);
        externalServiceFactory = new ExternalServiceFactory();
        internalService        = new InternalService(externalServiceFactory);
    }

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
    private final ExternalServiceFactory externalServiceFactory;
    private final InternalService   internalService;
}