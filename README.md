# jsonplaseholder
This project is for testing the following flow on https://jsonplaceholder.typicode.com/:
Check if comments to posts have correct formatting and the flow itself.

This project uses Yandex Allure (https://docs.qameta.io/allure/) for reporting. To see the report you need to do the following:

1. install allure (you can find instructions here: https://docs.qameta.io/allure/#_installing_a_commandline)
2. run tests (run 'mvn clean test' command) in the project folder
3. run 'allure serve allure-results' command in the same folder
The report should be opened in a new browser windod.
