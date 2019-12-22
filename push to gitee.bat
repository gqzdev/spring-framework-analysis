:: show git pull
git pull 

:: show git status
git status

:: add all changing
git add -A

:: input commit message
set /p commit_msg=Please input commit message:

:: local commit
git commit -m "%commit_msg%"
:: push to remote repository

git push 
:: make a pause 
pause