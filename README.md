# JavaTest
Remote server API autotests
The project is written for the following api: http://85.192.34.140:8080/swagger-ui/index.html#/
4 tests written:
1. checkUserActivities() - Checking possible user activity. Registration, authorization, adding/removing games, deleting an account.
2. checkGetRequestsReturn200() - Checking all kinds of GET requests with the expected code 200.
3. checkGetRequestsReturnNot200() - Checking all kinds of GET requests with any other code.
4. checkFilesRequests() - Checking file interaction.
In the last test, change the files path to the appropriate one
