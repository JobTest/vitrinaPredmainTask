vitrinaPredmainTask
===================

'Сервис' (бизнес логика) - принимает на вход какие-то данные и возвращает определенный результат. На стадии написания сервиса требуются данные чтобы его тестировать, поэтому здесь используются специальные заглушки для базы данных
'Бин' (это может быть любая реализация чего угодно, главное чтобы там присутствовали Гетеры/Сетеры) -
'ДАО' - это интерфейс между 'сервисом' и 'базой' - по сути нет смысла его вообще тестировать. ДАО имеет стандартные методы которые строят транзакции к базе. Обычно ДАО применяют для выбора реализации работы с разными версиями драйверов
'Сущность' - это эмитация копии базы в языке программирования
Все это вместе называется - API


Разработчик не должен тратить свое время на работу с базой или для разработки каких-то интерфейсов...
Потому-что:
1.0 разработкой интерфейсов занимается бизнес - бизнес диктует правила какая логика нужна сервису
2.1 установкой / настройкой доступа / и подключениями к базе занимаются администраторы - администраторы выдают конфигурационные данные для подключения и доступа к базе
2.2 конфигурирование структуры базы и написанием процедур для нее делают базисты - базисты дают разработчику реализованую процедуру которая позволяет получить требуемые данные из базы
3.0 сущность представляет собой эмитацию определенного типа данных (разработчик просто описывает эти данные согласно требованиям бизнесу, но времени на нее тоже много нетратит)

Разработчик может заниматься:
- покрытием какого-нибудь сервиса (интерфейса) тестами
- реализацией какого-нибудь класса/метода для обработки/работы с конкретным типом данных



(spring несколько баз данных) Распределённая система кеша ehcache для приложений любого уровня
---
http://acestime.com/2012/01/11/spring-framework-mongodb/#.VbeSkmD7t0w
https://gkruglov.wordpress.com/2012/08/25/пример-использования-dbunit-для-наполнени/
http://atamanenko.blogspot.com/2012/02/jpa-spring-data-jpa.html
http://zwnotes.blogspot.com/2014/08/blog-post.html
http://vresheno.ru/909145-how-to-use-several-databases-with-spring-mvc-and-hibernate
https://github.com/wizardjedi/my-spring-learning/wiki/Работа-с-базами-данных-на-основе-jpa
http://devcolibri.com/3966
http://devcolibri.com/4149
* http://articles.javatalks.ru/articles/24
http://habrahabr.ru/post/255829/
http://it.vaclav.kiev.ua/2012/02/14/hibernate-as-jpa-provider-with-spring-3/
http://www.seostella.com/ru/article/2012/04/26/sessionattributes-sessiya-v-spring-mvc.html
http://www.sql.ru/forum/1006877/spring-hibernate-vopros-o-rabote-s-sessiyami-v-dao-obektah
http://rsdn.ru/forum/java/3716694.flat
* https://github.com/Cache-Spring/Spring31/blob/master/src/com/finecosoft/spring31/dao/cacheipml/CountryDaoImpl.java
https://github.com/Cache-Spring/HibernateEHCacheExample/blob/master/src/main/java/com/journaldev/hibernate/main/HibernateEHCacheMain.java
(hibernate limit cache size config)
* http://ehcache.org/documentation/2.8/configuration/cache-size
* https://github.com/Cache-Spring/docs/blob/master/spring.txt
http://apmblog.dynatrace.com/2009/03/24/understanding-caching-in-hibernate-part-three-the-second-level-cache/
https://docs.jboss.org/hibernate/orm/3.3/reference/en-US/html/session-configuration.html
* https://github.com/Cache-Spring/docs/blob/master/spring.txt
* http://ehcache.org/
* http://habrahabr.ru/post/25140/
* http://spring-projects.ru/guides/caching/
http://habrahabr.ru/post/98972/

(hibernate limit cache size config)
-----
http://ehcache.org/documentation/2.8/configuration/cache-size
http://apmblog.dynatrace.com/2009/03/24/understanding-caching-in-hibernate-part-three-the-second-level-cache/
https://docs.jboss.org/hibernate/orm/3.3/reference/en-US/html/session-configuration.html


http://habrahabr.ru/sandbox/41444/
http://www.mkyong.com/jdbc/jdbc-preparedstatement-example-batch-update/
http://docs.oracle.com/javase/7/docs/api/javax/sql/rowset/Predicate.html

(spring data)
** (Работа с запросами, сортировкой, порционной загрузкой) http://habrahabr.ru/post/139421/
http://jeeconf.com/archive/jeeconf-2013/materials/spring-data/
* http://devcolibri.com/4149
https://msdn.microsoft.com/ru-ru/library/ms181299%28v=sql.120%29.aspx
http://examples.javacodegeeks.com/core-java/sql/commit-rollback-transaction-example/



java json xml
---

* `JSON и XML. Что лучше?`: [http://habrahabr.ru/post/31225/](http://habrahabr.ru/post/31225/)
* `Converting JSON to XML to Java Objects using XStream`: [http://www.javacodegeeks.com/2014/07/converting-json-to-xml-to-java-objects-using-xstream.html](http://www.javacodegeeks.com/2014/07/converting-json-to-xml-to-java-objects-using-xstream.html)

