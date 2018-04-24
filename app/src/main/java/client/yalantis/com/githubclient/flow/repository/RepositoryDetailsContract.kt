package client.yalantis.com.githubclient.flow.repository

import client.yalantis.com.githubclient.model.RepositoryDetail
import client.yalantis.com.githubclient.mvp.BaseMvpPresenter
import client.yalantis.com.githubclient.mvp.BaseMvpView

/**
 * Created by Alexey on 07.10.2016.
 */
object RepositoryDetailsContract {

    interface View : BaseMvpView {
        fun showRepository(repositoryDetail: RepositoryDetail)

        fun showReloadButton()
    }

    interface Presenter : BaseMvpPresenter<View> {
        fun loadRepository(name: String)
    }


}