@echo off
set JAVA_HOME=C:\Program Files\Java\jdk-25
set PATH=%JAVA_HOME%\bin;%PATH%
set MODULE_PATH=C:\Users\Estudo\Downloads\javafx-sdk-25.0.1\lib

java --module-path "%MODULE_PATH%" --add-modules javafx.controls,javafx.fxml -jar microTimer-1.0-SNAPSHOT.jar
pause