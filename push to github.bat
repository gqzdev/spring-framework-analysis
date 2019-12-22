:: show git pull
git pull github master --allow-unrelated-histories

:: show git status
git status

:: add all changing
git add -A

:: input commit message
set /p commit_msg=Please input commit message --push to github--:

:: local commit
git commit -m "%commit_msg%"
:: push to remote repository

git push github master
:: make a pause 
pause