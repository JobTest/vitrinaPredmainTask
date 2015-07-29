package com.vitrina.dao;

import com.vitrina.domain.Issue;

import java.util.List;

/**
 * Created by alexandr on 24.07.15.
 */
public interface IssueDao {

    List<Issue> getAll(List<Issue> select) throws Exception;
    void add(List<Issue> insert) throws Exception;
    void delete(int id) throws Exception;
    void update(Issue issue) throws Exception;

}
