@echo off
set ANT_HOME=D:\installers\eclipse_3.6_SR2_mini\plugins\org.apache.ant_1.7.1.v20100518-1145
set path=%ANT_HOME%\bin;%path%
ant -buildfile D:\workspace\eclipse\AntDemo\build_ftp.xml
pause