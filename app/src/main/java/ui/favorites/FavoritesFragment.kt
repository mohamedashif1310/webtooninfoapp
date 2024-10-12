package com.example.webtooninfo.ui.favorites

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

class FavoritesFragment : Fragment() {

    private val viewModel: WebtoonViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val root = inflater.inflate(R.layout.fragment_favorites, container, false)
        val recyclerView: RecyclerView = root.findViewById(R.id.favorites_list)

        val adapter = WebtoonAdapter(emptyList()) { webtoon ->
            val action = FavoritesFragmentDirections.actionFavoritesToDetail(webtoon.id)
            findNavController().navigate(action)
        }
        recyclerView.adapter = adapter
        recyclerView.layoutManager = LinearLayoutManager(context)

        viewModel.favoriteWebtoons.observe(viewLifecycleOwner) { webtoons ->
            adapter.updateWebtoons(webtoons)
        }

        return root
    }
}