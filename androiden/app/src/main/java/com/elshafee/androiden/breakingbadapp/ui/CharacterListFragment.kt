package com.elshafee.androiden.breakingbadapp.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.RecyclerView
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import com.elshafee.androiden.R
import com.elshafee.androiden.breakingbadapp.BreakingBadApllication
import com.elshafee.androiden.breakingbadapp.ui.utils.CharacterListAdapter

class CharacterListFragment : Fragment() {
    private val charcterListViewModel: CharcterListViewModel by viewModels {
        CharcterListViewModelFactory((requireActivity().application as BreakingBadApllication).characterRepository)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.activity_character_list_fragment, container, false)

    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        val rvCharacterList = requireActivity().findViewById<RecyclerView>(R.id.rvCharacterList)
        val adapter = CharacterListAdapter { bbCharater ->
            if (bbCharater.img != null) {
                findNavController().navigate(
                    CharacterListFragmentDirections.showCharcterImageFragment(
                        bbCharater.img
                    )
                )
            }

        }
        rvCharacterList.adapter = adapter
        charcterListViewModel.characteList.observe(viewLifecycleOwner, { breakingBadCharacter ->
            adapter.submitList(breakingBadCharacter)
        })

        val refreshLayout = requireActivity().findViewById<SwipeRefreshLayout>(R.id.refreshlayout)
        refreshLayout.setOnRefreshListener {
            charcterListViewModel.refreshDataFromRepository()
            Toast.makeText(requireContext(), "Data Updated", Toast.LENGTH_LONG).show()
            refreshLayout.isRefreshing = false
        }


    }
}