package com.source2;

import com.source2.StaticService;
import com.source2.UseStaticService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.powermock.api.mockito.PowerMockito;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;

import static org.junit.Assert.*;

/**
 * Created by Саша on 09.08.2015.
 * {@link http://habrahabr.ru/post/172239/}
 * ****************************************
 * тест нашего сервиса
 */

@RunWith(PowerMockRunner.class)
@PrepareForTest({ StaticService.class })
public class UseStaticServiceTest {

    public UseStaticServiceTest() {
        PowerMockito.mockStatic(StaticService.class);
        PowerMockito.when(StaticService.doStaticWithParams(OBJECT_PARAM)).thenReturn(RETURN_STRING);
    }

    @Test
    public void useStaticTest() {
        String result = useStaticService.useStatic(OBJECT_PARAM);

        PowerMockito.verifyStatic();
        StaticService.doStatic();

        PowerMockito.verifyStatic();
        StaticService.doStaticWithParams(OBJECT_PARAM);

        assertEquals(RETURN_STRING, result);
    }


    private static final Object OBJECT_PARAM = new Object();
    private static final String RETURN_STRING = "result";
    private final UseStaticService useStaticService = new UseStaticService();
}