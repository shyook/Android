package client.yalantis.com.githubclient.flow.repository_list

import client.yalantis.com.githubclient.api.GeneralErrorHandler
import client.yalantis.com.githubclient.manager.ApiManager
import client.yalantis.com.githubclient.mvp.BaseMvpPresenterImpl
import rx.functions.Action1

/**
 * Created by andrewkhristyan on 10/2/16.
 */
class RepositoriesPresenter : BaseMvpPresenterImpl<RepositoriesContract.View>(), RepositoriesContract.Presenter {

    companion object {
        private val ORGANIZATION_NAME = "Yalantis"
        private val REPOS_TYPE = "public"
    }

    override fun loadRepositories() {
        ApiManager.loadOrganizationRepos(ORGANIZATION_NAME, REPOS_TYPE)
                .doOnError { mView?.showMessage(it.toString()) }
                .subscribe(Action1 { mView?.showOrganizations(it) },
                        GeneralErrorHandler(mView, true, { throwable, errorBody, isNetwork -> mView?.showError(throwable.message) }))
    }
}