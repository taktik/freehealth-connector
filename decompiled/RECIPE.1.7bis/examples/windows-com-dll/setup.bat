: to be executed as an administrator!!!
cd "%~dp0"
copy ..\..\lib\dotNet\*.dll RecipePrescriberCom\bin\Debug\
cd RecipePrescriberCom/bin/Debug/
C:\Windows\Microsoft.NET\Framework\v4.0.30319\RegAsm.exe /unregister RecipePrescriberCom.dll 
C:\Windows\Microsoft.NET\Framework\v4.0.30319\RegAsm.exe RecipePrescriberCom.dll /verbose /tlb /codebase
pause