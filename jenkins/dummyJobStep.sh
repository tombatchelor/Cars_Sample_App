export GIT_COMMIT=`cat /dev/urandom | tr -dc 'a-zA-Z0-9' | fold -w 32 | head -n 1`
export GIT_BRANCH='origin/master'
export RAND=$(($RANDOM % 4))
if [ $RAND -eq 0 ] ; then export GIT_COMMIT_NAME="Martin Brundell"; export GIT_COMMIT_EMAIL="martin.brundell@observeinc.com"; fi
if [ $RAND -eq 1 ] ; then export GIT_COMMIT_NAME="Damon Hill"; export GIT_COMMIT_EMAIL="damon.hill@observeinc.com"; fi
if [ $RAND -eq 2 ] ; then export GIT_COMMIT_NAME="Nigel Mansell"; export GIT_COMMIT_EMAIL="nigel.mansell@observeinc.com"; fi
if [ $RAND -eq 3 ] ; then export GIT_COMMIT_NAME="Paul di Resta"; export GIT_COMMIT_EMAIL="paul.diresta@observeinc.com"; fi
export GIT_COMMIT_SUBJECT='Removing sone dead code'
export BUILD_STATUS='SUCCESS'
export RAND=$(($RANDOM % 2))
if [ $RAND -eq 3 ] ; then export BUILD_STATUS='FAILED'; fi
curl  -X POST https://collect.observe-staging.com/v1/observations/jenkins  -H 'Content-Type: application/json' -H 'Authorization: Bearer 126338107931 xVA7nFdY64s1u2NIs4Y1criedrXx2Wo_'  -d '{"BUILD_NUMBER":"'$BUILD_NUMBER'","BUILD_ID":"'$BUILD_ID'","JOB_NAME":"'$JOB_NAME'","GIT_COMMIT":"'$GIT_COMMIT'","GIT_BRANCH":"'$GIT_BRANCH'","GIT_COMMITTER_NAME":"'"$GIT_COMMIT_NAME"'","GIT_COMMITTER_EMAIL":"'"$GIT_COMMIT_EMAIL"'","GIT_COMMIT_SUBJECT":"'"$GIT_COMMIT_SUBJECT"'","IMAGE":"'tombatchelor/insurance:$BUILD_NUMBER'","BUILD_STATUS":"'$BUILD_STATUS'"}'
