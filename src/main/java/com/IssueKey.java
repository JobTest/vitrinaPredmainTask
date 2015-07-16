package com;

/**
 * Created by alexandr on 16.07.15.
 */
public class IssueKey {

    private Integer key;

    public Integer getKey() {
        return key;
    }

    public void setKey(Integer key) {
        this.key = key;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;

        if (!(obj instanceof IssueKey))
            return false;

        IssueKey issueKey = (IssueKey) obj;
        if (!key.equals(issueKey.key))
            return false;

        return true;
    }

    @Override
    public int hashCode() {
        return key.hashCode();
    }

    @Override
    public String toString() {
        return "IssueKey{" +
                "key=" + key +
                '}';
    }
}
