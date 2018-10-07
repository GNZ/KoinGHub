package com.gnz.koinghub.service.model

data class Owner(
        val login: String,
        val id: Int,
        val node_id: String,
        val avatar_url: String,
        val gravatar_id: String,
        val url: String,
        val received_events_url: String,
        val type: String
)