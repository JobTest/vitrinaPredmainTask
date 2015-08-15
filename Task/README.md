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

Spring | Hibernate | cache
---
* `orm hibernate ehcache проверка производительности`:
* `Hibernate cache`: [http://habrahabr.ru/post/135176/](http://habrahabr.ru/post/135176/) | [http://habrahabr.ru/post/249073/](http://habrahabr.ru/post/249073/)
* `Как подружить Hibernate со Spring и обеспечить управление транзакциями через @ннотации`: [http://samolisov.blogspot.com/2009/06/hibernate-spring.html](http://samolisov.blogspot.com/2009/06/hibernate-spring.html)
* `Hibernate кэширование на практике и заключение`: [http://dr-magic.blogspot.com/2010/01/hibernate-5.html](http://dr-magic.blogspot.com/2010/01/hibernate-5.html)
* `Глава 5 Spring in Action 2th edition`: [http://rastafara.nt.net.ua/index.php/%D0%93%D0%BB%D0%B0%D0%B2%D0%B0_5_Spring_in_Action_2th_edition](http://rastafara.nt.net.ua/index.php/%D0%93%D0%BB%D0%B0%D0%B2%D0%B0_5_Spring_in_Action_2th_edition)
* `HIBERNATE`: [HIBERNATE](http://www.google.com.ua/url?sa=t&rct=j&q=&esrc=s&frm=1&source=web&cd=10&cad=rja&uact=8&ved=0CF4QFjAJahUKEwiV9LWd5KvHAhUkpnIKHeiIA_c&url=http%3A%2F%2Fwww.bsu.by%2Fsm.aspx%3Fguid%3D88083&ei=r43PVdXgB6TMygPokY64Dw&usg=AFQjCNEV0OENpiVX9Hhm5KIGqmwuBSRJOw&sig2=xpM-uTZ6EzH7VmbyS-uVvA&bvm=bv.99804247,d.bGQ) | [HIBERNATE](http://www.studfiles.ru/preview/1182402/)
* `Доступ к данным используя Spring Framework: Hibernate template`: [Доступ к данным используя Spring Framework: Hibernate template](http://www.spring-source.ru/articles.php?type=manual&theme=articles&docs=article_08)
* `Вопросы на интервью по технологиям Java Spring и Hibernate`: [Вопросы на интервью по технологиям Java Spring и Hibernate](http://j4sq.blogspot.com/2012/01/java-spring-hibernate.html)

> Прежде всего Hibernate cache — это 3 уровня кеширования:
* Кеш первого уровня (First-level cache);
* Кеш второго уровня (Second-level cache);
* Кеш запросов (Query cache);
При использовании методов save(), update(), saveOrUpdate(), load(), get(), list(), iterate(), scroll() всегда будет задействован кеш первого уровня.
> На самом деле, хибернейт сам не реализует кеширование как таковое. А лишь предоставляет структуру для его реализации, поэтому подключить можно любую реализацию, которая соответствует спецификации нашего ORM фреймворка:
* EHCache
* OSCache
* SwarmCache
* JBoss TreeCache
Помимо всего этого, вероятней всего, Вам также понадобится отдельно настроить и саму реализацию кеша. В случае с EHCache это нужно сделать в файле ehcache.xml.
Ну и в завершение еще нужно указать самому хибернейту, что именно кешировать. К счастью, это очень легко можно сделать с помощью аннотаций.
> Стратегии кеширования определяют поведения кеша в определенных ситуациях. Выделяют четыре группы:
* Read-only
* Read-write
* Nonstrict-read-write
* Transactional
> Регион или область — это логический разделитель памяти вашего кеша. Для каждого региона можна настроить свою политику кеширования (для EhCache в том же ehcache.xml). Если регион не указан, то используется регион по умолчанию, который имеет полное имя вашего класса для которого применяется кеширование.
> Во время разработки приложения, особенно сначала, очень удобно видеть действительно ли кешируются те или иные запросы, для этого нужно указать фабрике сессий следующие свойства...
В дополнение фабрика сессий также может генерировать и сохранять статистику использования всех объектов, регионов, зависимостей в кеше...
Для этого есть объекты Statistics для фабрики и SessionStatistics для сессии.
> Методы сессии:
* flush() — синхронизирует объекты сессии с БД и в то же время обновляет сам кеш сессии.
* evict() — нужен для удаления объекта из кеша cессии.
* contains() — определяет находится ли объект в кеше сессии или нет.
* clear() — очищает весь кеш.

> Напомню, что аннотации @Entity, @Id и т.д. относятся к JPA и заменяют собой Hibernate-mapping.
> Для этого существует аннотация @Transactional, которой можно аннотировать методы или целый класс. Параметрами данной аннотации задается поведение транзакции. Основными параметрами являются readOnly, который указывает на возможность или невозможность менять состояние БД и propagation, который задает стратегию создания транзакции (не создавать транзакцию, создать новую, присоединиться к существующей и т.д.). Помимо этих параметров можно указывать таймаут, уровень изоляции, классы и типы исключений для которых надо и ненадо делать rollback.
> Работа с шаблонами JDBC:
* JdbcTemplate - Самый основной шаблон JDBC в Spring, этот класс предоставляет простой доступ к базе данных через JDBC и простые индексно-параметризированные запросы.
* NamedParameterJdbcTemplate - Этот класс JDBC шаблона позволяет выполнять запросы, где значения параметров должны быть связаны с именоваными параметрами в SQL, вместо индексированных параметров.
* SimpleJdbcTemplate - Эта версия шаблона JDBC использует, такие новые возможности Java 5 как autoboxing, generics и varargs для упрощения работы с шаблоном.
Session – однопоточный, короткоживущий объект, являющийся посредником между приложением и хранилищем долгоживущих объектов (используется для навигации по объектному графу или для поиска объектов по идентификатору).

> Каким образом Spring поддерживает DAO?
* Предоставляет подход основанный на шаблонах...
* HibernateTemplate, так же как и JdbcTemplate, предоставляет шаблонный подход для доступа к данным...




java как найти утечку памяти
---
* `Типичные случаи утечки памяти в Java`: [http://habrahabr.ru/post/132500/](http://habrahabr.ru/post/132500/)
* `Анализ утечек PermGen памяти в Java`: [http://habrahabr.ru/post/222443/](http://habrahabr.ru/post/222443/)
