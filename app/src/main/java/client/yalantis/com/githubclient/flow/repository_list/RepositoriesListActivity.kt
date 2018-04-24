package client.yalantis.com.githubclient.flow.repository_list

import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.view.View
import client.yalantis.com.githubclient.R
import client.yalantis.com.githubclient.flow.repository.RepositoryDetailActivity
import client.yalantis.com.githubclient.model.Repository
import client.yalantis.com.githubclient.mvp.BaseMvpActivity
import kotlinx.android.synthetic.main.activity_organization.*
import java.util.*


class RepositoriesListActivity : BaseMvpActivity<RepositoriesContract.View,
        RepositoriesPresenter>(),
        RepositoriesContract.View {

    private var mAdapter: RepositoriesAdapter? = null
    override var mPresenter: RepositoriesPresenter = RepositoriesPresenter()

    override fun showOrganizations(repositories: MutableList<Repository>) {
        mAdapter?.addRepositories(repositories)
        mAdapter?.notifyDataSetChanged()
        hideProgress()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_organization)
        setUpRecyclerView()
        mPresenter.loadRepositories()

        toolbar.title = getString(R.string.title_activity_repositories)
        showProgress()
        fab.setOnClickListener {
            showProgress()
            mPresenter.loadRepositories()
        }
    }

    private fun setUpRecyclerView() {
        mAdapter = RepositoriesAdapter(ArrayList<Repository>(), {
            startActivity(RepositoryDetailActivity.newIntent(this, it.name))
        })
        recyclerViewRepositories.layoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
        recyclerViewRepositories.adapter = mAdapter
    }

    private fun showProgress() {
        recyclerViewRepositories.visibility = View.GONE
        progress_bar.visibility = View.VISIBLE
        fab.isEnabled = false
    }

    private fun hideProgress() {
        recyclerViewRepositories.visibility = View.VISIBLE
        progress_bar.visibility = View.GONE
        fab.isEnabled = true
    }

    override fun showError(error: String?) {
        super.showError(error)
        progress_bar.visibility = View.GONE
        fab.isEnabled = true
    }
}

