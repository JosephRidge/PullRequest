package com.jayr.pullrequest.domain.`interface`

import com.jayr.pullrequest.domain.models.Project
import retrofit2.http.GET

// place all CRUD operations here
interface ProjectAPIInterface {
    @GET("/projects.json") // defines the method and you pass in as a param the endpoint
    suspend fun getProjects(): List<Project>
}