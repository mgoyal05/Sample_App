### Branching Model
We currently follow mainline git model ([See here](https://gitversion.readthedocs.io/en/latest/input/docs/reference/versioning-modes/mainline-development/)) including the use of feature / epic branches, a master (mainline) branch, and release branches (for publishing/production).

### Branch Descriptions and Naming

#### Production and UAT Branch: 
`release-v<version>`

#### Mainline/Staging Branch: 
`master`

#### Epic Branch: 
Should start with your initials (example, vb_, av_, ph_, etc.), followed by _epic_ and the epic id (example, CB-425, etc.) in small alphabets and hyphen ( - ) replaced with underscore ( _ ). Finally, it should look like `vb_epic_cb_425`.

#### Feature Branch: 
Should start with your initials (see above). It should be followed by the issue id (example, CB-476, etc.) in small alphabets and hyphen ( - ) replaced with underscore ( _ ). Finally, it should look like `vb_cb_476`.

#### Hotfix Branch:
Should start with your initials (see above). It should be followed by _hotfix_ and the issue id. Finally, it should look like `vb_hotfix_cb_446`.

### Feature Development

#### Feature Branch Creation
Once a user decides they want to start development on a user story, they must run the following commands to create a feature branch. Only one developer must follow this process, if other developers want to access this branch, they can follow Other Team Member Setup, below:
- Start Feature
`git checkout -b {feature_branch_name}`
- Make it available for everyone by publishing it to git
`git push origin {feature_branch_name}`
{feature_branch_name} is derived from the first and last initial of the developer and the User Story number they are developing on inside of JIRA, for example:
Varun Bargali starting development on Story CB-476 would result in the feature branch name: `vb_cb_476`

#### Other Team Members Participating on Feature Branch
The following commands are to be run by all of the other members on the team that want to develop or view an existing feature branch.
- Start Feature
`git fetch`
`git checkout {feature_branch_name}`
`git pull origin {feature_branch_name}`
- Make Changes and Push To Feature Branch
`git push origin {feature_branch_name}`

#### When a Feature is complete
When the feature has been finished, all of the changes will be merged into the parent / epic branch.
- Pull requests need to be used in projects.
- This serves as a single diff for a branch/issue to be reviewed and merged.
Moreover, pull requests are always merged using `--no-ff`.
- The title of a pull request must contain the story id. This should be followed by a short summary. For, e.g., “CB-476: created api for profile page”. This needs to be adhered with, without failure.
- We need to use the template defined in Github for the description.
- A pull request should only be merged if it has been approved.
- Pull requests need to be merged using the Github GUI (avoid using CLI). All commits should be squashed while merging, and the branch should be deleted after merging.

Using Pull Request: [About pull requests](https://docs.github.com/en/github/collaborating-with-issues-and-pull-requests/about-pull-requests)

### Release Management
We are using jenkins to manage deployments

#### Staging Release
Staging release pipeline is triggered from the master branch.

#### UAT and Production Release
- We are using uat as a pre-production server, i.e., anything that needs to be deployed to production, has to be deployed and tested on uat first.
- When a uat release is planned, we cut off a release branch from master (see Branch Description and Naming above).
- To trigger the deployment pipeline, we create a tag from the release branch. If our current release branch is named release-v1.0, the tag will be named v1.0.0
- Only after the release has been deployed to uat and verified, should we deploy it to production.

### Hotfix Management

#### Hotfix Branch Creation
When a critical bug fix (or a change) is immediately required on production, a hotfix branch should be branched off from the deployed release branch.

- **Start Hotfix Branch**
`git checkout -b {hotfix_branch_name}`
- **Make Changes To Hotfix Branch**
`git push origin {hotfix_branch_name}`
- **Hotfix Branch Merge**
When finished, the bugfix needs to be merged back into master, but also needs to be cherry-picked to the release branch. (NOTE: Avoid merging master and release branches, if any change that is on master needs to be on release branch, or vice versa, please use cherry-pick)

**NOTE:** In some rare cases, we might be doing some release specific hotfixes. In such cases, the hotfix branch will be cut off from the release branch, and will be merged back to it. No cherry-picks will be required in this case.

#### Hotfix Deployment
A new tag will be cut off from the release branch after the fix has been done. If current release branch was named release-v1.0 and the current deployed tag was v1.0.0, we will now cut off v1.0.1 from the release branch, deploy and test it directly on uat (testing on staging is not necessary in cases of hotfix), and deploy to production after verification.

### Push/Pull on master
- Push to master is not allowed. It has been restricted in the repositories, and should be avoided (even by the members who have the access to do so).
- Please do not (simply) pull the master branch when git does not allow you to push to master or git is creating a merge commit for it. We should use `git pull --rebase origin master`. This keeps the master branch clean. Moreover, this process shouldn’t be encountered, since we never make direct commits to master.

### Commenting
- Every commit should be commented properly in a certain way which should be strictly followed.
Every comment should include the story id. This should be followed by the comment text. For, e.g., “CB-476: created api for profile page”. This needs to be adhered with, without failure.
- Merge commits should not be custom commented. Default merge comment generated by GIT should be used.

