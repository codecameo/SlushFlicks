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
**MVI** architecture has been followed in this project where the *View* is producing *Event*s (Intent) based on user interaction. When *ViewModel* receives these event it updates the *ViewState* to show loading state if necessary and observe for data from the Repository. Repository responds to the request with either *Success* or *Error* and upon receiving the response *ViewModel* update the *ViewState* accordingly. As the View is obserbing for the *ViewState*, it eventually get notified about the response and show relevent information to the user.

+ **ViewState:** ViewState can be considered as the snapshot of the current state of the screen. 
+ **Event:**  Events are being fired from the screen based on user interaction.
+ **ViewEvent:** When *ViewState* changes it fires the *ViewEvent*s which eventually reflects the changes to the user on the screen.
<br><br>
<img height="45%" width="45%" src=https://github.com/codecameo/SlushFlicks/blob/documentation/screenshots/mvi.png >

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
