
* ``: [JOIN в MySQL...](https://docviewer.yandex.ru/?uid=40270829&url=ya-mail%3A%2F%2F2370000006240542699%2F1.2&name=%D1%81%D1%81%D1%8B%D0%BB%D0%BA%D0%B8-2.txt&page=6&c=55c2b1405cc0)
* ``: [Пример unchecked исключения...](https://docviewer.yandex.ru/?uid=40270829&url=ya-mail%3A%2F%2F2370000006240542699%2F1.2&name=%D1%81%D1%81%D1%8B%D0%BB%D0%BA%D0%B8-2.txt&page=7&c=55c2b1405cc0)
* ``: [Внутри механизм-MySQL](https://docviewer.yandex.ru/?uid=40270829&url=ya-mail%3A%2F%2F2370000006240542699%2F1.2&name=%D1%81%D1%81%D1%8B%D0%BB%D0%BA%D0%B8-2.txt&page=5&c=55c2b2dff4a7)

* `github.com`: [github.com](https://github.com/Home-Java8/Java8/tree/master/src/com/sql)
* `github.com`: [github.com](https://github.com/Home-Java8/MyMap)
* `github.com`: [github.com](https://github.com/Home-Java8/MyMap/blob/master/links.txt)
* `github.com`: [github.com](https://github.com/Home-Java8/Solutions/tree/master/src/com)
* `github.com`: [github.com](https://github.com/Home-Java8/Java8/blob/master/heap.txt)
* `github.com`: [github.com](https://github.com/Home-Java8/Java8/tree/master/src/com/exception)
* `github.com`: [github.com](https://github.com/JobTest/AddressBook/tree/release)
* `github.com`: [github.com](https://github.com/JobTest/AddressBook/blob/release/src/main/java/com/web/ContactController.java)
* `github.com`: [github.com](https://github.com/JobTest/AddressBook/blob/release/src/main/webapp/WEB-INF/spring/data.xml)
* `github.com`: [github.com](https://github.com/JobTest/AddressBookDB)
* `github.com`: [github.com](https://github.com/JobTest/AddressBookDB/blob/master/tomcat_realm.sql)
* `github.com`: [github.com](https://github.com/Home-Java8/MyMap/blob/master/поисковые системы.txt)
* `github.com`: [github.com](https://github.com/Home-Java8/Solutions/blob/master/src/com/protect/p1/ClassA.java)
* `github.com`: [github.com](https://github.com/Home-Java8/MyMap/tree/master/src/com/luxoft)
* `Доступ к данным в Spring`: [JdbcTemplate, NamedParameterJdbcTemplate, SimpleJdbcTemplate, HibernateTemplate, JpaTemplateя](http://www.spring-source.ru/articles.php?type=manual&theme=articles&docs=article_01)
* `spring работа с сессиями`: [spring работа с сессиями](http://javatalks.ru/topics/16834?page=1#80610)
* `spring работа с сессиями`: [spring работа с сессиями](http://rsdn.ru/forum/java/3716694.flat)
* `spring работа с сессиями`: [spring работа с сессиями](http://www.sql.ru/forum/1006877/spring-hibernate-vopros-o-rabote-s-sessiyami-v-dao-obektah)
* `spring работа с сессиями`: [spring работа с сессиями](http://www.seostella.com/ru/article/2012/04/26/sessionattributes-sessiya-v-spring-mvc.html)

* `spring работа с сессиями`: [spring работа с сессиями](http://j4sq.blogspot.com/2012/01/java-spring-hibernate.html)

* `spring работа с сессиями`: [spring работа с сессиями](http://www.springbyexample.com.ua/2012/08/faq-transactional.html)
* `spring работа с сессиями`: [spring работа с сессиями](http://www.ibm.com/developerworks/ru/library/j-ts1/)


===================================================

Есть 3-области:
Стек / Stack
"Куча" / Heap


module: java memory

Подсистемы
- стек / stack
- "куча" / heap
- передача по ссылке / по значению
- permanent generation / PermGen


Память процесса различается на:
• heap (куча);
• и non-heap (стек) память;

И состоит из 5 областей (memory pools, memory spaces):
1. Eden (heap) ....................... в этой области выделятся память под все создаваемые из программы объекты. Большая часть объектов живет недолго (итераторы, временные объекты, используемые внутри методов и т.п.), и удаляются при выполнении сборок мусора это области памяти, не перемещаются в другие области памяти. Когда данная область заполняется (т.е. количество выделенной памяти в этой области превышает некоторый заданный процент), GC выполняет быструю (minor collection) сборку мусора. По сравнению с полной сборкой мусора она занимает мало времени, и затрагивает только эту область памяти — очищает от устаревших объектов Eden Space и перемещает выжившие объекты в следующую область.
2. Survivor (heap) ................... сюда перемещаются объекты из предыдущей, после того, как они пережили хотя бы одну сборку мусора. Время от времени долгоживущие объекты из этой области перемещаются в Tenured Space.
3. Tenured (Old) Generation (heap) ... здесь скапливаются долгоживущие объекты (крупные высокоуровневые объекты, синглтоны, менеджеры ресурсов и проч.). Когда заполняется эта область, выполняется полная сборка мусора (full, major collection), которая обрабатывает все созданные JVM объекты.
4. Permanent Generation (non-heap) ... здесь хранится метаинформация, используемая JVM (используемые классы, методы и т.п.). В частноси
5. Code Cache (non-heap) ............. эта область используется JVM, когда включена JIT-компиляция, в ней кешируется скомпилированный платформенно — зависимый код.

================================================================================================================================================
	Изначально когда мы стартуем JVM (Виртуальную Машину Java) - это есть некая исполнительная (системная) программа - при этом для работы
такой системной программы выделяется определенный-стартовый размер памяти. Важно понимать, что эта выделяемая память для системной программы
JVM является специальной-фиксированой областью памяти которая используется только для обеспечения работы системной программы JVM (но не для
клиентских программ)