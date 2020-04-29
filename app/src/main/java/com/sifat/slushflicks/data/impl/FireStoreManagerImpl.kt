package com.sifat.slushflicks.data.impl

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.google.firebase.firestore.FirebaseFirestore
import com.google.gson.annotations.SerializedName
import com.sifat.slushflicks.api.StatusCode
import com.sifat.slushflicks.data.FireStoreManager
import com.sifat.slushflicks.di.app.AppScope
import com.sifat.slushflicks.model.CollectionModel
import com.sifat.slushflicks.ui.state.DataErrorResponse
import com.sifat.slushflicks.ui.state.DataState
import com.sifat.slushflicks.ui.state.DataSuccessResponse
import javax.inject.Inject

@AppScope
class FireStoreManagerImpl
@Inject constructor(
    private val fireStore: FirebaseFirestore
) : FireStoreManager {
    private val TAG = "FireStoreManagerImpl"

    override fun getMovieCollections(): LiveData<DataState<List<CollectionModel>>> {
        val result = MutableLiveData<DataState<List<CollectionModel>>>()
        val docRef = fireStore.collection(COLLECTION_NAME).document(MOVIE_COLLECTION_ID)
        docRef.get()
            .addOnSuccessListener { document ->
                if (document != null && document.data != null) {
                    Log.d(TAG, "DocumentSnapshot data: ${document.data}")
                    try {
                        val collection = document.toObject(CollectionFireStoreResponse::class.java)
                        result.postValue(
                            DataState.Success(
                                DataSuccessResponse(
                                    data = collection?.collections
                                )
                            )
                        )
                    } catch (ex: Exception) {
                        result.postValue(
                            DataState.Error(
                                DataErrorResponse(
                                    statusCode = StatusCode.INTERNAL_ERROR
                                )
                            )
                        )
                    }
                } else {
                    result.postValue(
                        DataState.Error(
                            DataErrorResponse(
                                statusCode = StatusCode.EMPTY_RESPONSE
                            )
                        )
                    )
                }
            }
            .addOnFailureListener { exception ->
                result.postValue(
                    DataState.Error(
                        DataErrorResponse(
                            errorMessage = exception.message
                        )
                    )
                )
            }

        return result
    }

    companion object {
        const val COLLECTION_NAME = "Collection"
        const val MOVIE_COLLECTION_ID = "movie"
        const val TV_COLLECTION_ID = "tv_show"
    }

    data class CollectionFireStoreResponse(
        @SerializedName("collections")
        var collections: List<CollectionModel> = emptyList()
    )
}