@echo off

call setenv.bat

if not "%J2EE_HOME%" == "" goto CONT0
echo ERROR: Set J2EE_HOME before running this script.
goto END
:CONT0

if EXIST "%J2EE_HOME%\bin\setenv.bat" goto CONT1
echo ERROR: Set J2EE_HOME to the path of a valid j2sdkee.
goto END
:CONT1

call %J2EE_HOME%\bin\setenv.bat

if not "%JAVA_HOME%" == "" goto CONT2
echo ERROR: Set JAVA_HOME before running this script.
goto END
:CONT2

if EXIST "%JAVA_HOME%\bin\java.exe" goto CONT3
echo ERROR: Set JAVA_HOME to the path of a valid jdk.
goto END
:CONT3

set DH_JAR=%LIBDIR%\..\help\DeployTool\deployhelp.jar

set JAVA_HELP=%LIBDIR%\jh.jar

set CPATH_CH=%JAVA_HELP%;%DH_JAR%;%CPATH%

rem @echo on
%JAVACMD% -Dcom.sun.enterprise.home=%J2EE_HOME% -classpath "%CPATH_CH%" com.sun.enterprise.tools.deployment.main.Main %1 %2 %3 %4 %5 %6

:END
