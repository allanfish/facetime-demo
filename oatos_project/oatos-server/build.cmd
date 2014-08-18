@echo off

set today=%date:~0,4%-%date:~5,2%-%date:~8,2%
set file=log/build-%today%.log

@echo on

svn cleanup
svn update

call mvn clean 

echo.
echo ========== Package start ==========
echo package is running.......

call mvn package >%file% 2>&1

echo.
echo ========== Package end ==========

