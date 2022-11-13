Dependencies overview:
- Network: Retrofit
- JSON parsing: Moshi
- Versions control: refreshVersions
- Pictures loading: Coil
- DI: Koin
- Navigation, ViewModel: Jetpack
- Permissions: PermissionX
- Testing: Mockito, Junit, Turbine

Architecture:MVVM

Used modularisation following clean architecture principles
network <- data -> domain <- (over UseCase) feature

App made by a single activity approach

Remarks:
- Codestyling might not be perfect in some places due to no automatic lint check added
- App may still crash on parsing(some types are not Long but actually Double). Most been updated already fixed
- App handles units system according to your devie locale (US, UK, Other)
- Location is required to work, if last location will be null, app wont work
- UI tests are not added as it was not asked about that in the requirements
- No proguard rules or other usual manipulation are done for the release app version to save on development time 
- API source: https://www.visualcrossing.com/weather-api

Further development:
- Display upcoming days weather details

 
