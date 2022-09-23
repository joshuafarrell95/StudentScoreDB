@echo off
mkdir app
jar --create --file=app/StudentScoreDB.jar --main-class=studentscoredb.StudentScoreDB -m Manifest.txt -C classes .
mkdir app\lib
copy lib\mysql-connector-java.jar app\lib
REM xcopy .\lib\ .\app\lib /E /l
pause
