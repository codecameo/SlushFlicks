package com.example.slushflicks.repository.resource

class ResourceFactory {

    companion object {
        /*fun <Resource : NetworkBoundResource<>> getResource(resourceType: ResourceType): Resource {
            when (resourceType) {
                ResourceType.NETWORK_FIRST_SILENT_CACHE -> {

                }
            }
        }*/
    }


    enum class ResourceType {
        NETWORK_FIRST_SILENT_CACHE,
        CACHE_FIRST_SILENT_UPDATE,
        CACHE_ONLY,
        NETWORK_ONLY
    }
}