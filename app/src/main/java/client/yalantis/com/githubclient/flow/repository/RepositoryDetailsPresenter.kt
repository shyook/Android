package client.yalantis.com.githubclient.flow.repository

import client.yalantis.com.githubclient.api.GeneralErrorHandler
import client.yalantis.com.githubclient.manager.ApiManager
import client.yalantis.com.githubclient.mvp.BaseMvpPresenterImpl
import rx.functions.Action1

/**
 * Created by Alexey on 07.10.2016.
 */
class RepositoryDetailsPresenter : BaseMvpPresenterImpl<RepositoryDetailsContract.View>(),
        RepositoryDetailsContract.Presenter {

    companion object {
        private const val ORGANIZATION_NAME = "Yalantis"
    }

    override fun loadRepository(name: String) {
        ApiManager.loadRepository(ORGANIZATION_NAME, name)
                .subscribe(Action1 { mView?.showRepository(it) },
                        GeneralErrorHandler(mView, true,
                                { throwable, errorBody, isNetwork -> mView?.showReloadButton() }))
    }
}