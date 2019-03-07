echo "MavenRunner stars running....."
set myPath=%cd%
echo %myPath%
cd %myPath%
mvn -Dsurefire.suiteXmlFiles=%myPath%\Suite\testng.xml test