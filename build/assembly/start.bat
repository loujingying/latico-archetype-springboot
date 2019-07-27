set /p programname=<./programname.cfg
set /p javapath=<./javapath.cfg
title %programname%

set logdir="logs"
IF NOT EXIST %logdir% mkdir %logdir%

%javapath% -Dprogramname=%programname% -Dloader.path=lib -jar lib\%programname%.jar 2>logs/err.log
pause