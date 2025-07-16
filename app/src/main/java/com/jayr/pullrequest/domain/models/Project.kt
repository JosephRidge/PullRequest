package com.jayr.pullrequest.domain.models

import kotlinx.serialization.Serializable

data class Project(
    val description: String,
    val github_url: String,
    val main_language: String
)