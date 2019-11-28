cd /var/www/html/Chqbook-api_push/

version=$1
if [ -z "$version" ]; then
  echo "Please specify a version"
  exit
fi

# Output
echo "Releasing version $version"
echo "-------------------------------------------------------------------------"

# Get current branch and checkout if needed
branch=$(git symbolic-ref --short -q HEAD)
if [ "$branch" != "$version" ]; then
  git reset
  git stash
  git checkout $version
fi

# Ensure working directory in version branch clean
git update-index -q --refresh
if ! git diff-index --quiet HEAD --; then
  echo "Working directory not clean, please commit your changes first"
  exit
fi

# Checkout master branch and merge version branch into master
git checkout master
git pull origin $version --no-ff --no-edit

if ! git diff-index --quiet HEAD --; then
  read -p "Do you want to push changes to master? "  approval
  y=1
  if [ $approval == $y ] 
  then
    git push
    curl https://jenkins.chqbook.com/job/Backend/job/RUN_NON_PROD/build?token=run_staging --user "manish:Chkb00k$"
  fi
fi

# Checkout dev branch and merge master into dev (to ensure we have the version)
git checkout uat
git pull origin $version --no-ff --no-edit

if ! git diff-index --quiet HEAD --; then
  read -p "Do you want to push changes to UAT? "  approval
  y=1
  if [ $approval == $y ] 
  then
    git push
    curl https://jenkins.chqbook.com/job/Backend/job/RUN_NON_PROD/build?token=run_uat --user "manish:Chkb00k$"
  fi
fi

# Success
echo "-------------------------------------------------------------------------"
echo "Release $version complete"
