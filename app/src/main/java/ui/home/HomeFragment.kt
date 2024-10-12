package com.example.webtooninfo.ui.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.webtooninfo.R
import com.example.webtooninfo.ui.WebtoonAdapter
import com.example.webtooninfo.ui.WebtoonViewModel

class HomeFragment : Fragment() {

    private val viewModel: WebtoonViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val root = inflater.inflate(R.layout.fragment_home, container, false)
        val recyclerView: RecyclerView = root.findViewById(R.id.webtoon_list)

        val adapter = WebtoonAdapter(emptyList()) { webtoon ->
            val action = HomeFragmentDirections.actionHomeToDetail(webtoon.id)
            findNavController().navigate(action)
        }
        recyclerView.adapter = adapter
        recyclerView.layoutManager = LinearLayoutManager(context)

        viewModel.allWebtoons.observe(viewLifecycleOwner) { webtoons ->
            adapter.updateWebtoons(webtoons)
        }

        return root
    }
}