package com.example.webtooninfo.ui.detail

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.RatingBar
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.navArgs
import com.bumptech.glide.Glide
import com.example.webtooninfo.R
import com.example.webtooninfo.ui.WebtoonViewModel

class DetailFragment : Fragment() {

    private val viewModel: WebtoonViewModel by viewModels()
    private val args: DetailFragmentArgs by navArgs()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val root = inflater.inflate(R.layout.fragment_detail, container, false)

        val imageView: ImageView = root.findViewById(R.id.webtoon_image)
        val titleView: TextView = root.findViewById(R.id.webtoon_title)
        val descriptionView: TextView = root.findViewById(R.id.webtoon_description)
        val favoriteButton: Button = root.findViewById(R.id.add_to_favorites)
        val ratingBar: RatingBar = root.findViewById(R.id.rating_bar)

        viewModel.allWebtoons.observe(viewLifecycleOwner) { webtoons ->
            val webtoon = webtoons.find { it.id == args.webtoonId }
            webtoon?.let {
                Glide.with(this).load(it.imageUrl).into(imageView)
                titleView.text = it.title
                descriptionView.text = it.description
                favoriteButton.text = if (it.isFavorite) "Remove from Favorites" else "Add to Favorites"
                ratingBar.rating = it.rating

                favoriteButton.setOnClickListener { _ ->
                    viewModel.toggleFavorite(it)
                }

                ratingBar.setOnRatingBarChangeListener { _, rating, _ ->
                    viewModel.updateRating(it, rating)
                }
            }
        }

        return root
    }
}