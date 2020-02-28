@echo off

echo 校外读者服务程序正在运行（本窗口可关闭）

for %%i in (outschoolreaders*.jar) do javaw -jar %%i

exit