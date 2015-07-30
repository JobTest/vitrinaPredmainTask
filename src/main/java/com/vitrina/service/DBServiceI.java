package com.vitrina.service;

import com.vitrina.domain.Issue;

import java.io.File;
import java.util.List;
import java.util.Map;

/**
 * Created by alexandr on 30.07.15.
 */
public interface DBServiceI {

    /**
     * #1 Подгружаем (старые/новые) данные из источников
     *
     * @param oldData
     * @param newData
     * @return
     */
    Map loadData(final String oldData, final Map<String,File> newData);

    /**
     * #2 Разбираем (старые/новые) данные и определяем список данных для обновления
     *
     * @param oldData
     * @param newData
     * @param oldDates
     * @return
     */
    Map parseData(final List<Issue> oldData, final List<Issue> newData, final String[] oldDates);

    /**
     * #3 Выполняем обновления данных
     *
     * @param delete
     * @param update
     * @param add
     */
    void updateData(final List<Issue> delete, final List<Issue> update, final List<Issue> add);

}
