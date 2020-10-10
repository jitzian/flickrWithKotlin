package org.com.raian.flickrcodechallenge.showPhotos.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.lifecycle.LiveData
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso
import org.com.raian.flickrcodechallenge.R
import org.com.raian.flickrcodechallenge.database.model.FlickerDataClass
import org.com.raian.flickrcodechallenge.util.prepareUrl

class RVCustomAdapter(
    private val context: Context,
    var lstRes: LiveData<List<FlickerDataClass>>
) : RecyclerView.Adapter<RVCustomAdapter.ViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(LayoutInflater.from(context).inflate(R.layout.photo_card_view, parent, false))
    }

    override fun getItemCount(): Int {
        return lstRes.value?.size ?: 0
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        lstRes.value?.get(position)?.let { holder.bindData(it) }
    }

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val mImageViewPhoto: ImageView

        init {
            itemView.let {
                mImageViewPhoto = it.findViewById(R.id.mImageViewPhoto)
            }
        }

        fun bindData(flickerDataClass: FlickerDataClass) {
            Picasso.get()
                .load(prepareUrl(flickerDataClass))
                .resize(600, 400)
                .centerInside()
                .into(mImageViewPhoto)
        }

    }

}