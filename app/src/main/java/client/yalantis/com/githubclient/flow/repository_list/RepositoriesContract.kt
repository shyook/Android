package client.yalantis.com.githubclient.flow.repository_list

import client.yalantis.com.githubclient.model.Repository
import client.yalantis.com.githubclient.mvp.BaseMvpPresenter
import client.yalantis.com.githubclient.mvp.BaseMvpView

/**
 * Created by andrewkhristyan on 10/2/16.
 */
object RepositoriesContract {

    interface View : BaseMvpView {
        fun showOrganizations(repositories: MutableList<Repository>)

    }

    interface Presenter : BaseMvpPresenter<View> {
        fun loadRepositories()

    }


}
