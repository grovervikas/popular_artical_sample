package com.article.app.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.viewpager.widget.PagerAdapter
import androidx.viewpager.widget.ViewPager
import com.article.app.R
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.slide_bigger_image.view.*

class ImageSliderAdapter(private val context: Context, private val items: List<String>) : PagerAdapter() {

    private var inflater: LayoutInflater? = null


    override fun isViewFromObject(view: View, `object`: Any): Boolean {

        return view === `object`
    }

    override fun getCount(): Int {
        return items.size
    }

    override fun instantiateItem(container: ViewGroup, position: Int): Any {

        inflater = context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
        val view = inflater!!.inflate(R.layout.slide_bigger_image, null)
        if(items.isNotEmpty() && items[position].isNotEmpty()){
            Picasso.get().load(items[position])
                .placeholder(R.drawable.ic_place_holder)
                .error(R.drawable.ic_place_holder)
                .into(view.imageView_slide)
        }
        val vp = container as ViewPager
        vp.addView(view, 0)
        return view
    }

    override fun destroyItem(container: ViewGroup, position: Int, `object`: Any) {

        val vp = container as ViewPager
        val view = `object` as View
        vp.removeView(view)
    }

}