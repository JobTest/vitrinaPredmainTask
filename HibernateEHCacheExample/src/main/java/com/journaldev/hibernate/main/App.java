package com.journaldev.hibernate.main;

import org.hibernate.stat.Statistics;
import com.journaldev.hibernate.model.Employee;

/**
 * Created by Саша on 14.08.2015.
 * {@link http://www.journaldev.com/2980/hibernate-ehcache-second-level-caching-example-tutorial}
 * **********************************************************************************************
 * Hibernate EHCache Project
 */
public class App {

	public void printStatistics(Statistics statistics, int count) {
		System.out.println("---------------[ " + count + " ]---------------");
		System.out.println("            Fetch Count = " + statistics.getEntityFetchCount());
		System.out.println(" Second Level Hit Count = " + statistics.getSecondLevelCacheHitCount());
		System.out.println("Second Level Miss Count = " + statistics.getSecondLevelCacheMissCount());
		System.out.println(" Second Level Put Count = " + statistics.getSecondLevelCachePutCount());
	}

    public void printEmployee(Employee employee, Statistics statistics, int count) {
		System.out.println("NAME="+employee.getName()+"; ZIPCODE="+employee.getAddress().getZipcode() + ";"); //System.out.println(count+":: " + employee);
        printStatistics(statistics,count);
	}

}
