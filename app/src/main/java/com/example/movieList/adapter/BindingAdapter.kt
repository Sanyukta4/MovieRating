package com.example.movieList.adapter

import android.view.View
import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions

@BindingAdapter("imageFromUrl")
fun bindImageFromUrl(view: ImageView, imageUrl: String?) {
    if(imageUrl.equals(null)){
        view.visibility = View.GONE
    }else
    {
        val url ="https://www.themoviedb.org/t/p/w220_and_h330_face$imageUrl"
            Glide.with(view.context)
                    .load(url)
                    .transition(DrawableTransitionOptions.withCrossFade())
                    .into(view)
    }
}