package com.source3;

import com.source3.ExternalServiceFactory;

/**
 * Created by Саша on 09.08.2015.
 * {@link http://habrahabr.ru/post/172239/}
 * ****************************************
 * наш сервис, который использует фабрику для получения внешнего сервиса
 */

public class InternalService {
    private final ExternalServiceFactory externalServiceFactory;

    public InternalService(final ExternalServiceFactory externalServiceFactory) {
        this.externalServiceFactory = externalServiceFactory;
    }

    public void doWork() {
        externalServiceFactory.createExternalService().doMegaWork(); //externalServiceProvider.createExternalService.doMegaWork();
    }
}