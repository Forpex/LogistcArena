@ECHO OFF
SETLOCAL ENABLEDELAYEDEXPANSION

echo Buildsystem for LogisticArena - Windows
echo @author Garbiel Denner
echo.

if exist .\bin (
    cd bin
    
    if exist *.class (
        echo Cleaning up ...
        del *.class
    ) else (echo Nothing to clean)
    
    cd ..
) else (
    echo Error: No bin directory found.
    echo Closing application ...
    echo.
    pause
    exit /b
)

echo Searching for javac in path ...
where /q javac.exe
if ERRORLEVEL 1 (
    echo Error: Javac seems to be missing. Ensure it is installed and placed in your PATH.
    echo Closing application ...
    echo.
    pause
    exit /b
)

echo Searching for java-files ...
for /f %%i in ('dir /b/s *.java') do (
    set temp=%%i
    set filenames=!filenames! !temp!
)

echo Compiling sources ...
javac -d bin -cp "." %filenames%

if exist .\bin (
    cd bin
) else (
    echo Error: No bin directory found.
    echo Closing application ...
    echo.
    pause
    exit /b
)

echo Searching for java in path ...
where /q java.exe
if ERRORLEVEL 1 (
    echo Error: Java seems to be missing. Ensure it is installed and placed in your PATH.
    echo Closing application ...
    echo.
    pause
    exit /b
)

java java/main/LogisticArena

echo.
pause
