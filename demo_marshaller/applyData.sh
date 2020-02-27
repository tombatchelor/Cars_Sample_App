# Add SSH key from Volume Mount
cp /keys/id_rsa /id_rsa
sed -i -e '$a\' /id_rsa
chmod 600 /id_rsa
eval "$(ssh-agent -s)"
ssh-add /id_rsa

# Send in ref data
cd /
python sendJSON.py HTTPCodes.json httpcodes
python sendJSON.py users.json users

# Clone and make Git Commit
cd /tmp
git config --global user.email tom.batchelor@me.com
git clone git@github.com:$GIT_ROOT/Cars_Sample_App.git
cd Cars_Sample_App/
echo 'Adding a line to file' >> file.txt
git add file.txt
git commit -m "Small change to cache code"
git push
