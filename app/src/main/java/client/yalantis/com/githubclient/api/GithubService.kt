package client.yalantis.com.githubclient.api

import client.yalantis.com.githubclient.model.Repository
import client.yalantis.com.githubclient.model.RepositoryDetail
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query
import rx.Observable

/**
 * Created by andrewkhristyan on 10/6/16.
 */
interface GithubService {

    @GET(ApiSettings.ORGANIZATION_REPOS)
    fun getOrganizationRepos(@Path(ApiSettings.PATH_ORGANIZATION) organizationName: String,
                             @Query(ApiSettings.PARAM_REPOS_TYPE) reposType: String): Observable<MutableList<Repository>>

    @GET(ApiSettings.REPOSITORY)
    fun getRepository(@Path(ApiSettings.PATH_OWNER) owner: String,
                      @Path(ApiSettings.PATH_REPO) name: String): Observable<RepositoryDetail>

}