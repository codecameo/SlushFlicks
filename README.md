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
**MVI** architecture has been followed in this project where the *View* is producing *Event*s (Intent) based on user interaction. When *ViewModel* receives these event it updates the *ViewState* to show loading state if necessary and observe for data from the Repository. Repository responds to the request with either *Success* or *Error* and upon receiving the response *ViewModel* update the *ViewState* accordingly. As the View is observing for the *ViewState*, it eventually gets notified about the response and show relevant information to the user.

+ **ViewState:** ViewState can be considered as the snapshot of the current state of the screen. 
+ **Event:**  Events are being fired from the screen based on user interaction.
+ **ViewEvent:** When *ViewState* changes it fires the *ViewEvent*s which eventually reflects the changes to the user on the screen.
<br><br>
<img height="60%" width="60%" src=https://github.com/codecameo/SlushFlicks/blob/documentation/screenshots/mvi.png >

# Resource
Resources can be stated as a set of strategies that have been used by the Repository to provide data to the ViewModel. The strategies followed in this project are mentioned below:

+ **CacheFirstNetworkUpdateResource:** Provide the data from the cache immediately. Then look for any update in the network. As soon as the updated data is being retrieved, update the cache. In case of using *Room* database, if the *Query* was being observed, the updated data will be reflected in the UI immediately.

+ **NetworkFirstCacheUpdateResource:** This resource will first try to provide data from the network and update the cache, if fails to do so(ex: no internet connection), will provide the data from cache(local database)

+ **CacheOnlyResource:** A the name suggests, this resource will provide. data only from the cache.

+ **NetworkOnlyResource:** In contrast to *CacheOnlyResource*, this resource will provide data only from the network.

+ **CacheUpdateResource:** This resource is used to keep the cached data update.

# DataManager
As the **Facade** design pattern suggests, to create an abstraction layer on top to hide the internal complex implementation. DataManager works as an abstraction layer on top of the local data storage system. As local data can be saved in *Database*, *SharedPreference* or even in the *asset* folder in different files, *DataManager* provides a simple interface, hiding the information about the actual storage location of data. It also enhances the scalability, as new Local Storage can be added in the future with minimal change.
+ **DatabaseManager:** Provides data from local database
+ **LocalDataManager:** Provides non-persistent data. Data that is essential to the different states of the application can be kept here to ensure faster access.
+ **SharedPrefManager:** Provides data from *SharedPreference*
+ **FireStoreManager:** Provides data from firestore. As firestore manages a persistent database for itself and data can be accessed locally from firestore, it has been kept here in the *DataManager*.

# ErrorParser
ErrorParser comes into the picture to parse the error responses from the server separately. An *ApiTag* has been associated with each endpoint. With the help of this API tag and status code from the response, *ErrorParser* parse error effectively from the error body. As different API endpoints can provide different error responses with the same status code, *ApiTag* plays a vital role to add more flexibility on error parsing.

# ErrorMapper (Future Work)
Different error messages need a different way to be handled in the UI. For example, some messages can be shown as *Toast* and *Snackbar* whereas, some needed to be shown as *AlertDialog* or *BottomSheetDialog*. With the help of *ErrorMapper* ViewModel can map these actions to a specific error response.

# Optimization
+ **Memory leak:** Leak canary has been used to identify and resolve most of the memory leaks.
+ **GPU Overdraw optimization:** GPU Overdrawn has been reduced by using Development Tools (like Layout Inspector, Debug GPU overdraw).
