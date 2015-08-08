package com.miratex.my_immutable;

import java.util.Date;

/**
 * Created by Саша on 06.08.2015.
 * ******************************
 * {@link http://ithq.ru/index.php/article/view/delaem-immutable-ob-ekt-v-java}
 */
public final class ImmutableDateHolder {

    private final Date date;

    public ImmutableDateHolder(Date idate) {
        this.date = new Date(idate.getTime());
    }

    public Date getIdate() {
        return new Date(date.getTime());
    }
}
