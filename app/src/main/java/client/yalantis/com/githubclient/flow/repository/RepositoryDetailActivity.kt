package client.yalantis.com.githubclient.flow.repository

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.View
import client.yalantis.com.githubclient.R
import client.yalantis.com.githubclient.formatDate
import client.yalantis.com.githubclient.model.RepositoryDetail
import client.yalantis.com.githubclient.mvp.BaseMvpActivity
import kotlinx.android.synthetic.main.activity_repository.*

/**
 * Created by Alexey on 07.10.2016.
 */
class RepositoryDetailActivity : BaseMvpActivity<RepositoryDetailsContract.View,
        RepositoryDetailsContract.Presenter>(),
        RepositoryDetailsContract.View {

    override var mPresenter: RepositoryDetailsContract.Presenter = RepositoryDetailsPresenter()

    companion object {
        private const val NAME = "name"

        fun newIntent(context: Context, name: String): Intent =
                Intent(context, RepositoryDetailActivity::class.java).apply {
                    putExtra(NAME, name)
                }
    }


    override fun showRepository(repositoryDetail: RepositoryDetail) {
        layoutContent.visibility = View.VISIBLE
        progressBar.visibility = View.GONE
        with(repositoryDetail) {
            repository_name.text = name
            textCreatedDate.text = created_at.formatDate()
            textUpdatedDate.text = updated_at.formatDate()
            textDescription.text = description
            textForks.text = forks_count
            textStars.text = stargazers_count
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_repository)

        layoutContent.visibility = View.GONE
        progressBar.visibility = View.VISIBLE

        mPresenter.loadRepository(intent.getStringExtra(NAME))

        btnReload.setOnClickListener {
            progressBar.visibility = View.VISIBLE
            btnReload.visibility = View.GONE
            mPresenter.loadRepository(intent.getStringExtra(NAME))
        }
        toolbar.title = getString(R.string.title_activity_details)
    }

    override fun showReloadButton() {
        progressBar.visibility = View.GONE
        btnReload.visibility = View.VISIBLE
    }

}