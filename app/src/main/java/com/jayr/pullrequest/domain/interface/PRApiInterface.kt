package com.jayr.pullrequest.domain.`interface`

import com.jayr.pullrequest.domain.models.Project
import retrofit2.http.GET

interface PRApiInterface {
    @GET("/projects.json")
    suspend fun getProjects(): List<Project>

}