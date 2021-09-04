package com.example.Naviinterview

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ProgressBar
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.naviinterview.PullRequestListAdapter
import com.example.naviinterview.R
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject


private const val ORGANISATION_NAME = "organisationName"
private const val REPO_NAME = "repoName"
private const val PULL_REQUEST_TYPE = "pullRequestType"

@AndroidEntryPoint
class PullRequestListFragment : Fragment() {
    private var organisationName: String? = null
    private var repoName: String? = null
    private var pullRequestType: Boolean = false
    private val viewModel: PullRequestViewModel by viewModels()
    private lateinit var recyclerView: RecyclerView
    private lateinit var progressBar: ProgressBar
    private lateinit var heroMessage: TextView

    @Inject
    lateinit var pullRequestListAdapter: PullRequestListAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            organisationName = it.getString(ORGANISATION_NAME)
            repoName = it.getString(REPO_NAME)
            pullRequestType = it.getBoolean(PULL_REQUEST_TYPE)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.pull_request_list_fragment, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        activity?.actionBar?.setHomeButtonEnabled(true)
        activity?.actionBar?.setDisplayHomeAsUpEnabled(true)

        recyclerView = view.findViewById(R.id.recyclerView)
        recyclerView.adapter = pullRequestListAdapter
        recyclerView.layoutManager = LinearLayoutManager(view.context)
        val divider = DividerItemDecoration(recyclerView.context, DividerItemDecoration.VERTICAL)
        divider.setDrawable(
            ContextCompat.getDrawable(
                view.context,
                R.drawable.custom_divider
            )!!
        )
        recyclerView.addItemDecoration(divider)
        progressBar = view.findViewById(R.id.progressBar)
        heroMessage = view.findViewById(R.id.heroMessage)

        val mainActivity = activity as MainActivity
        mainActivity.toolbar.setNavigationIcon(androidx.appcompat.R.drawable.abc_ic_ab_back_material)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel.pullRequestsLiveData.observe(viewLifecycleOwner, Observer {
            recyclerView.visibility = if (it.isNotEmpty()) View.VISIBLE else View.INVISIBLE
            pullRequestListAdapter.pullRequestList = it
            pullRequestListAdapter.notifyDataSetChanged()
        })
        viewModel.progressBarLiveData.observe(viewLifecycleOwner, Observer {
            progressBar.visibility = if (it) View.VISIBLE else View.INVISIBLE
        })
        viewModel.messageLiveData.observe(viewLifecycleOwner, Observer {
            heroMessage.visibility = if (it.isNotEmpty()) View.VISIBLE else View.INVISIBLE
            heroMessage.text = it
        })
        getPullRequests()
    }

    private fun getPullRequests() {
        organisationName?.let { repoName?.let { it1 -> pullRequestType?.let { it2 -> viewModel.getPullRequests(
            it,
            it1,
            it2
        ) } } }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        viewModel.pullRequestsLiveData.removeObservers(viewLifecycleOwner)
        viewModel.progressBarLiveData.removeObservers(viewLifecycleOwner)
        viewModel.messageLiveData.removeObservers(viewLifecycleOwner)
    }

    companion object {
        @JvmStatic
        fun newInstance(organisationName: String, repoName: String, pullRequestType: Boolean) =
                PullRequestListFragment().apply {
                    arguments = Bundle().apply {
                        putString(ORGANISATION_NAME, organisationName)
                        putString(REPO_NAME, repoName)
                        putBoolean(PULL_REQUEST_TYPE, pullRequestType)
                    }
                }
    }
}