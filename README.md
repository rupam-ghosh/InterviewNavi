How to run the application ?
1. Open folder in Android Studio
2. Run app module in run configuration
3. Code is divided in 4 modules ( app or the presentation, domain, repository and entity)
4. presentation contains UI logic
5. domain contains business logic
6. repository contains network or database logic
7. entity contains data models / pojos / database entities
8. Used Hilt library for dependancy injection
9. Used retrofit library for making network requests
10. Used glide library for image loading


Things to improve in app:
1. retry logic in case of api failure / network failure
2. cache api results in db for cache first approach to handle no network situations
3. make api calls via coroutines
4. make navigation using navigation graph library
5. introduce integration and unit tests