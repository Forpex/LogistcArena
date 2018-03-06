@ECHO OFF
SETLOCAL ENABLEDELAYEDEXPANSION

echo binsystem for LogisticArena - Windows
echo.

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

java LogisticArena

echo.
pause
