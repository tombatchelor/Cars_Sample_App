# Add SSH key from Volume Mount
cp /keys/id_rsa /id_rsa
sed -i -e '$a\' /id_rsa
chmod 600 /id_rsa
eval "$(ssh-agent -s)"
ssh-add /id_rsa

# Send in ref data
cd /
curl -s -v -H 'Content-Type: application/json' -X POST $PROXY_ENDPOINT/v1/observations/httpscodes -d @HTTPCodes.json
curl -s -v -H 'Content-Type: text/csv' -X POST $PROXY_ENDPOINT/v1/observations/users -d @users.csv
curl -s -v -H 'Content-Type: text/csv' -X POST $PROXY_ENDPOINT/v1/observations/companies -d companies.csv

# Clone and make Git Commit
cd /tmp
git config --global user.email tom.batchelor@me.com
git config --global user.name tombatchelor
git clone git@github.com:$GIT_ROOT/Cars_Sample_App.git
cd Cars_Sample_App/app_server
if  `grep -q 'TRUE' Dockerfile`; then
  sed -i 's/TRUE/FALSE/g' Dockerfile
  git add Dockerfile
  git commit -m "Resolving mem leak issue"
  IS_FAILING=FALSE
else
  sed -i 's/FALSE/TRUE/g' Dockerfile
  git add Dockerfile
  git commit -m "Small change to cache code"
  IS_FAILING=TRUE
fi
git push
