package com.farhanapps.githubtrending.data.api

import com.farhanapps.githubtrending.data.Constants
import com.farhanapps.githubtrending.data.model.ApiResponseModel
import com.farhanapps.githubtrending.data.model.RepoModel
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {

    // Used third party open source API since official API didn't
    // have official API for trending repos.
    // API - https://trendings.herokuapp.com/repo?since=daily
    // Repo - https://github.com/doforce/github-trending

    @GET(Constants.REPO_ENDPOINT)
    fun getTrendingRepos(
        @Query("since") sinceTime: String // since=daily/weekly
    ): Call<ApiResponseModel<List<RepoModel>>>


    // Alternate API (official)- doesn't give actual trending
    /*
curl --location --request GET 'https://api.github.com/search/repositories?q=created:%3E2022-03-01&sort=stars&order=desc' \
--header 'Accept: application/vnd.github.preview'
    * */
}