@echo off



:start
cls
wmic /node:10.0.138.172 /user:administrator /password:zhbit123456!!! process call create "D:\server\bin\shutdown.bat"

rem �˴�����дcopy����
xcopy /s /Y "D:\49968\Workspaces\InttlliJ IDEA\RESTfulRefactorCMS\out\artifacts\RESTfulRefactorCMS_war_exploded\WEB-INF\classes"  "Z:\webapps\ROOT\WEB-INF\classes"

xcopy /s /Y "D:\49968\Workspaces\InttlliJ IDEA\RESTfulRefactorCMS\out\artifacts\RESTfulRefactorCMS_war_exploded\WEB-INF\config"  "Z:\webapps\ROOT\WEB-INF\config"

xcopy /s /Y "D:\49968\Workspaces\InttlliJ IDEA\RESTfulRefactorCMS\out\artifacts\RESTfulRefactorCMS_war_exploded\WEB-INF\web.xml"  "Z:\webapps\ROOT\WEB-INF\web.xml"


wmic /node:10.0.138.172 /user:administrator /password:zhbit123456!!! process call create "D:\server\bin\startup.bat"

echo.�����������ɹ�,��������ٴ�����������
pause
goto :start