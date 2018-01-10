package com.ravikoradiya.library

import android.databinding.BindingAdapter
import android.support.v7.widget.Toolbar
import android.view.View
import android.view.ViewTreeObserver
import android.widget.ImageButton
import android.widget.TextView


object CenterTitle {

    @BindingAdapter("centerTitle")
    @JvmStatic
    fun centerTitle(toolbar: Toolbar, textAlignment: Boolean) {

        // title
        val fieldTitle = Toolbar::class.java.getDeclaredField("mTitleTextView")
        fieldTitle.isAccessible = true
        val mTitleTextView: TextView? = fieldTitle.get(toolbar) as TextView?

        // sub title
        val fieldSubTitle = Toolbar::class.java.getDeclaredField("mSubtitleTextView")
        fieldSubTitle.isAccessible = true
        val mSubtitleTextView: TextView? = fieldSubTitle.get(toolbar) as TextView?

        // nav drawer
        val fieldNav = Toolbar::class.java.getDeclaredField("mNavButtonView")
        fieldNav.isAccessible = true
        val mNavButtonView: ImageButton? = fieldNav.get(toolbar) as ImageButton?

        mTitleTextView?.let {

            //
            mTitleTextView.measure(0, 0)
            mNavButtonView?.measure(0, 0)
            mSubtitleTextView?.measure(0, 0)

            // TODO: 12/18/2017 RTL layout support
            // get the right side margin from the menu items
            var menuIconsMargin = Int.MAX_VALUE
            for (i in 0 until toolbar.menu?.size().orZero()) {
                if (toolbar.menu?.getItem(i)?.isVisible.orFalse()) {
                    val menuView = toolbar.menu?.getItem(i)?.itemId?.let { toolbar.findViewById<View>(it) }
                    val size = IntArray(2)
                    menuView?.getLocationOnScreen(size)

                    menuIconsMargin = Math.min(when (size[0]) {
                        0 -> menuIconsMargin
                        else -> size[0]
                    }, menuIconsMargin)
                }

            }

            val leftSideMarginTitle = Math.max(
                    (toolbar.measuredWidth - mTitleTextView.measuredWidth) / 2, // get the left side margin by dividing total available space by 2
                    mNavButtonView?.measuredWidth.orZero()) // also consider nav button margin

            val leftSideMarginSubTitle = Math.max(
                    (toolbar.measuredWidth - mSubtitleTextView?.measuredWidth.orZero()) / 2, // get the left side margin by dividing total available space by 2
                    mNavButtonView?.measuredWidth.orZero()) // also consider nav button margin

            // position title
            mTitleTextView.run {
                if (textAlignment) left = leftSideMarginTitle
                else left = mNavButtonView?.measuredWidth.orZero()
                right = Math.min(toolbar.measuredWidth - leftSideMarginTitle, menuIconsMargin)
                layoutParams.width = Math.min(toolbar.measuredWidth - leftSideMarginTitle, menuIconsMargin) - leftSideMarginTitle
                text = text
            }

            // position sub title
            mSubtitleTextView?.run {
                if (textAlignment) left = leftSideMarginSubTitle
                else left = mNavButtonView?.measuredWidth.orZero()
                right = Math.min(toolbar.measuredWidth - leftSideMarginSubTitle, menuIconsMargin)
                layoutParams.width = Math.min(toolbar.measuredWidth - leftSideMarginSubTitle, menuIconsMargin) - leftSideMarginSubTitle
                text = text
            }

            val vto = mTitleTextView.getViewTreeObserver()
            // add a view tree observer so that we can center the title every time view tree is updated
            vto.addOnGlobalLayoutListener(object : ViewTreeObserver.OnGlobalLayoutListener {
                override fun onGlobalLayout() {
                    // remove listener to prevent recursive calls
                    mTitleTextView.getViewTreeObserver().removeOnGlobalLayoutListener(this)
                    centerTitle(toolbar, textAlignment)
                }
            })

        }
    }
}

fun Int?.orZero(): Int = this ?: 0
fun Boolean?.orFalse(): Boolean = this ?: false