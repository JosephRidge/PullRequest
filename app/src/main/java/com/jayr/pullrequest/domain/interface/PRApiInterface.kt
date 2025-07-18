package com.jayr.pullrequest.domain.`interface`

import com.jayr.pullrequest.domain.models.Organization
import com.jayr.pullrequest.domain.models.Project
import com.jayr.pullrequest.domain.models.User
import retrofit2.http.GET

interface PRApiInterface {
    @GET("/projects.json")
    suspend fun getProjects(): List<Project>

    @GET("/organisations.json")
    suspend fun getOrganizations(): List<Organization>

    @GET("/users.json?page=2")
    suspend fun getUsers(): List<User>

}