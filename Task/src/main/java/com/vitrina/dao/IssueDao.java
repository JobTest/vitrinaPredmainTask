package com.vitrina.dao;

import com.vitrina.domain.Issue;

import java.util.List;

/**
 * Created by alexandr on 24.07.15.
 */
public interface IssueDao {

    /**
     * Вернуть список всех (элементов)данных из базы
     *
     * @param issues
     * @return
     * @throws Exception
     */
    List<Issue> getAll(List<Issue> issues) throws Exception;

    /**
     * Добавление список (элементов)данных в базу
     *
     * @param issues
     * @throws Exception
     */
    void add(List<Issue> issues) throws Exception;

    /**
     * Удаление (элемента)записи из базы
     *
     * @param id
     * @throws Exception
     */
    void delete(int id) throws Exception;

    /**
     * Обновление (элемента)записи в базе
     *
     * @param issue
     * @throws Exception
     */
    void update(Issue issue) throws Exception;

}
