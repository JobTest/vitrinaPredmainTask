package com.vitrina.util.drivers;

import com.vitrina.util.Driver;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;
import java.util.Vector;

/**
 * Created by alexandr on 17.06.15.
 */
public class PoolDriver implements Driver {
    private Vector<Connection> availableConns = new Vector<Connection>(); /* Первый будет содержать список доступных для использования соединений */
    private Vector<Connection> usedConns = new Vector<Connection>(); /* Второй список используемых в любой момент времени */
    /* Кроме этого определяется переменная url, которая будет хранить строку подключения к бд: */
    private String url;
    private String user;
    private String passwd;
    private Properties properties;
    private static PoolDriver connectionPool = null;

    /* В конструкторе используется функция getConnection, которая просто создает новое подключение: */
    public PoolDriver(){}
    public PoolDriver(String url, String driver, int initConnCnt, String user, String passwd) {
        try {
            Class.forName(driver);
        } catch (Exception e) { e.printStackTrace(); }
        this.url = url;
        this.user = user;
        this.passwd = passwd;
        for (int i=0; i<initConnCnt; i++)
            availableConns.addElement(createConnection());
    }
    public PoolDriver(String url, String driver, int initConnCnt, Properties properties) {
        try {
            Class.forName(driver);
        } catch (Exception e) { e.printStackTrace(); }
        this.url = url;
        this.properties = properties;
        for (int i=0; i<initConnCnt; i++)
            availableConns.addElement(createConnection());
    }

    public static PoolDriver getInstance(String url, String driver, int initConnCnt, String user, String passwd){
        return connectionPool == null ? connectionPool = new PoolDriver(url,driver,initConnCnt,user,passwd) : connectionPool;
    }
    public static PoolDriver getInstance(String url, String driver, int initConnCnt, Properties properties){
        return connectionPool == null ? connectionPool = new PoolDriver(url,driver,initConnCnt,properties) : connectionPool;
    }

    private Connection createConnection() {
        Connection conn = null;
        try {
            conn = DriverManager.getConnection(url, properties);
        } catch (Exception e) { e.printStackTrace(); }
        return conn;
    }

    /* Эта функция забирает из availableConns очередной Connection и добавляет его в usedConns, затем возвращает это соединение, тем самым он становится используемым: */
    public synchronized Connection getConnection() throws SQLException {
        /* Сначала мы проверяем, есть ли свободные соединения, если нет, то мы создаем новое подключение, если есть, то мы извлекаем последний элемент из availableConns и удаляем его из вектора свободных соединений. */
        /* Затем мы только что созданное соединение или извлеченное из списка свободных добавляем в список используемых строкой. */
        Connection newConn = null;
        if (availableConns.size() == 0) {
            newConn = createConnection();
        } else {
            newConn = (Connection) availableConns.lastElement();
            availableConns.removeElement(newConn);
        }
        usedConns.addElement(newConn);
        return newConn; /* И возвращаем это соединение. */
    }

    /* Когда соединение становится не нужным, то мы выполняем обратную операцию (иначе говоря putback): */
    public synchronized void putback(Connection c) throws NullPointerException {
        /* Конечно же без synchronized не обойтись. */
        /* Доступ то многопоточный, вдруг двум потокам выделится одно и то же соединение. */
        if (c != null) {
            if (usedConns.removeElement(c)) {
                availableConns.addElement(c);
            } else {
                throw new NullPointerException("Connection not in the usedConns array");
            }
        }
    }

    /* Дальше можно по необходимости написать кучу всяких дополнительных функций, которые предоставляют доступ к дополнительной информации, например, функция для получения количества свободных соединений: */
    public int getAvailableConnsCnt() {
        return availableConns.size();
    }
}