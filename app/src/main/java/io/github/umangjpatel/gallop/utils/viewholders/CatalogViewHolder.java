package io.github.umangjpatel.gallop.utils.viewholders;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;

import io.github.umangjpatel.gallop.R;
import io.github.umangjpatel.gallop.databinding.CatalogCourseListItemBinding;

public class CatalogViewHolder extends RecyclerView.ViewHolder {

    private CatalogCourseListItemBinding mItemBinding;

    public CatalogViewHolder(@NonNull CatalogCourseListItemBinding itemBinding) {
        super(itemBinding.getRoot());
        mItemBinding = itemBinding;
    }

    public void bind() {
        mItemBinding.courseCodeTextView.setText(R.string.course_code_placeholder);
        mItemBinding.courseTitleTextView.setText(R.string.course_title_placeholder);
        mItemBinding.executePendingBindings();
    }
}
