package com.gnz.koinghub.data


sealed class ResourceState

object Loading : ResourceState()
object EmptyState : ResourceState()
object PopulateState : ResourceState()
data class ErrorState(val throwable: Throwable) : ResourceState()