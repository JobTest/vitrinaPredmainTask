package com.source1;

/**
 * Created by Саша on 09.08.2015.
 * {@link http://habrahabr.ru/post/172239/}
 * ****************************************
 * наш класс
 */

public class InternalService {

    private final ExternalService externalService;

    public InternalService(final ExternalService externalService) {
        this.externalService = externalService;
    }

    public void doWork() {
        externalService.doMegaWork();
    }

}