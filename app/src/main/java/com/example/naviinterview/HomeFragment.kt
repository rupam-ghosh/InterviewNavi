package com.example.naviinterview

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.Switch
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.example.Naviinterview.MainActivity
import com.example.Naviinterview.PullRequestListFragment
import com.google.android.material.textfield.TextInputEditText
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class HomeFragment : Fragment() {
    private lateinit var organisationField: TextInputEditText
    private lateinit var repoField: TextInputEditText
    private lateinit var pullRequestTypeField: Switch
    private lateinit var fetchDataButton: Button

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.home_fragment, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        organisationField = view.findViewById(R.id.organisationName)
        repoField = view.findViewById(R.id.repoName)
        pullRequestTypeField = view.findViewById(R.id.pullRequestType)
        fetchDataButton = view.findViewById(R.id.fetchDataButton)
        fetchDataButton.setOnClickListener { view ->
            if(organisationField.text.toString().isEmpty() || repoField.text.toString().isEmpty()) {
                Toast.makeText(view.context, "Please enter correct information", Toast.LENGTH_SHORT).show()
            } else {
                val newFragment = PullRequestListFragment.newInstance(organisationField.text.toString(), repoField.text.toString(), pullRequestTypeField.isChecked)
                activity?.supportFragmentManager?.beginTransaction()?.apply {
                    replace(R.id.mainContainer, newFragment).addToBackStack(PullRequestListFragment::class.simpleName).commit()
                }
            }
        }

        val mainActivity = activity as MainActivity
        mainActivity.toolbar.setNavigationIcon(null)
    }

    companion object {
        @JvmStatic
        fun newInstance(): HomeFragment {
            return HomeFragment()
        }
    }
}