package com.source3;

import com.source1.ExternalService;

/**
 * Created by Саша on 09.08.2015.
 * {@link http://habrahabr.ru/post/172239/}
 * ****************************************
 * фабрика, создающая внешний сервис
 */

public final class ExternalServiceFactory {

    public ExternalService createExternalService() {
        return new ExternalService();
    }

}