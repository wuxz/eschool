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


rem @echo on
%JAVACMD% -Djava.security.policy==%J2EE_HOME%\lib\security\server.policy -Dcom.sun.enterprise.home=%J2EE_HOME% -classpath "%CPATH%" com.sun.enterprise.server.J2EEServer %1 %2

:END
