Установка Oracle SQL Developer на линукс (ubuntu) Updated 2015-05-12
----
* `Решить данную проблему можно выполнив следующую команду`:[http://kidun.ru/index.php/component/content/article?id=94](http://kidun.ru/index.php/component/content/article?id=94)

A fatal error has been detected by the Java Runtime Environment:
#
#  SIGSEGV (0xb) at pc=0x00007f7c9a4bbbe0, pid=24208, tid=140173480953600
#
# JRE version: Java(TM) SE Runtime Environment (7.0_67-b01) (build 1.7.0_67-b01)
# Java VM: Java HotSpot(TM) 64-Bit Server VM (24.65-b04 mixed mode linux-amd64 compressed oops)
# Problematic frame:
# C  0x00007f7c9a4bbbe0
#
# Failed to write core dump. Core dumps have been disabled. To enable core dumping, try "ulimit -c unlimited" before starting Java again
#
# An error report file with more information is saved as:
# /tmp/hs_err_pid24208.log
#
# If you would like to submit a bug report, please visit:
#   http://bugreport.sun.com/bugreport/crash.jsp
#
/opt/sqldeveloper/sqldeveloper/bin/../../ide/bin/launcher.sh: line 1193: 24208 Aborted                 ${JAVA} "${APP_VM_OPTS[@]}" ${APP_ENV_VARS} -classpath ${APP_CLASSPATH} ${APP_MAIN_CLASS} "${APP_APP_OPTS[@]}"


Решить данную проблему можно выполнив следующую команду:
---

$ unset -v GNOME_DESKTOP_SESSION_ID
