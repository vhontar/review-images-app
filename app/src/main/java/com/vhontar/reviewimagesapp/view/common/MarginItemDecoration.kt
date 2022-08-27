package com.vhontar.reviewimagesapp.view.common

import android.graphics.Rect
import android.view.View
import androidx.recyclerview.widget.RecyclerView

data class MarginItemDecoration(
    val topSpace: Int = 0,
    val topSpaceOnlyFirst: Boolean = false,
    val topSpaceWithoutLast: Boolean = false,
    val rightSpace: Int = 0,
    val rightSpaceOnlyFirst: Boolean = false,
    val rightSpaceWithoutLast: Boolean = false,
    val bottomSpace: Int = 0,
    val bottomSpaceOnlyFirst: Boolean = false,
    val bottomSpaceWithoutLast: Boolean = false,
    val leftSpace: Int = 0,
    val leftSpaceOnlyFirst: Boolean = false,
    val leftSpaceWithoutLast: Boolean = false
) : RecyclerView.ItemDecoration() {

    override fun getItemOffsets(
        outRect: Rect,
        view: View,
        parent: RecyclerView,
        state: RecyclerView.State
    ) {
        val position = parent.getChildAdapterPosition(view)
        val itemCount = state.itemCount - 1

        with(outRect) {
            val canAddTopSpace = topSpaceOnlyFirst && position == 0 ||
                    topSpaceWithoutLast && position < itemCount ||
                    !topSpaceOnlyFirst && !topSpaceWithoutLast
            if (canAddTopSpace) top = topSpace

            val canAddRightSpace = rightSpaceOnlyFirst && position == 0 ||
                    rightSpaceWithoutLast && position < itemCount ||
                    !rightSpaceOnlyFirst && !rightSpaceWithoutLast
            if (canAddRightSpace) right = rightSpace

            val canAddBottomSpace = bottomSpaceOnlyFirst && position == 0 ||
                    bottomSpaceWithoutLast && position < itemCount ||
                    !bottomSpaceOnlyFirst && !bottomSpaceWithoutLast
            if (canAddBottomSpace) bottom = bottomSpace

            val canAddLeftSpace = leftSpaceOnlyFirst && position == 0 ||
                    leftSpaceWithoutLast && position < itemCount ||
                    !leftSpaceOnlyFirst && !leftSpaceWithoutLast
            if (canAddLeftSpace) left = leftSpace
        }
    }
}
