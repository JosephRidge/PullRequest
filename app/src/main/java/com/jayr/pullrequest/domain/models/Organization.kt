package com.jayr.pullrequest.domain.models

data class Organization(
    val avatar_url: String,
    val link: String,
    val login: String,
    val users: List<User>
)

data class User(
    val contributions_count: Int,
    val github_profile: String,
    val gravatar_id: String,
    val id: Int,
    val link: String,
    val nickname: String,
    val organisations: List<Organisation>,
    val pull_requests: List<Contribution>? = emptyList<Contribution>()
)

data class Contribution(
    val body: String?,
    val created_at: String?,
    val issue_url: String?,
    val repo_name: String?,
    val title: String?
)

data class Organisation(
    val avatar_url: String,
    val link: String,
    val login: String
)