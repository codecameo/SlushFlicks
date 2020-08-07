# SlushFlicks
SlushFlicks has been built upon public APIs from IMDB. This application helps users to view trending, popular, upcoming and top-rated movies and Tv shows and their detailed information. Users can also search for a movie or Tv show. This app also cache data to support offline browsing.
This is a personal project and the core objective of this project was to have hands-on experience on recent Android jetpack library components like navigation, paging and MVI architecture.  In addition to this, Local unit testing is also being implemented in this project. Furthermore, worked on optimization by eliminating memory leaks and reducing gpu-overdraw.<br><br>
[![SlushFlicks](https://img.shields.io/badge/PlayStore-SlushFlicks-blue.svg)](https://play.google.com/store/apps/details?id=com.sifat.slushflicks)

<div>
<img height="25%" width="25%" src=https://github.com/codecameo/SlushFlicks/blob/documentation/screenshots/movielist.png >
<img height="25%" width="25%" src=https://github.com/codecameo/SlushFlicks/blob/documentation/screenshots/tvshowlist.png >
<img height="25%" width="25%" src=https://github.com/codecameo/SlushFlicks/blob/documentation/screenshots/movie_details.png >
<img height="25%" width="25%" src=https://github.com/codecameo/SlushFlicks/blob/documentation/screenshots/tvshow_details.png >
<img height="25%" width="25%" src=https://github.com/codecameo/SlushFlicks/blob/documentation/screenshots/search.png >
</div>

# Architecture
**MVI** 
+ ViewState
+ Event
+ ViewEvent

# Resource

+ CacheFirstNetworkUpdateResource
+ NetworkFirstCacheUpdateResource
+ CacheOnlyResource
+ NetworkOnlyResource
+ CacheUpdateResource

# DataManager

+ DatabaseManager
+ LocalDataManager
+ SharedPrefManager
+ FireStoreManager

# ErrorParser

# ErrorMapper

# Optimization

+ Memory leak
+ GPU Overdraw optimization