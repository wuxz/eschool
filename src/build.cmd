e:
cd \eschool\classes
jar -cf ..\ejb-jar-ic.jar com META-INF conf
cd ..\defaultroot
jar -cf ..\web-war.war .
cd ..
jar -uf war-ic.war WEB-INF
jar -cf eschool.ear ejb-jar.jar web-war.war library META-INF
