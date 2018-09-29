package io.github.umangjpatel.gallop.utils.viewholders;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import io.github.umangjpatel.gallop.databinding.CatalogCourseListItemBinding;
import io.github.umangjpatel.gallop.models.course.CourseInfo;

public class CatalogViewHolder extends RecyclerView.ViewHolder {

    private CatalogCourseListItemBinding mItemBinding;

    public CatalogViewHolder(@NonNull CatalogCourseListItemBinding itemBinding) {
        super(itemBinding.getRoot());
        mItemBinding = itemBinding;
    }

    public void bind(CourseInfo courseInfo) {
        mItemBinding.courseCodeTextView.setText(courseInfo.getCourseCode());
        mItemBinding.courseTitleTextView.setText(courseInfo.getTitle());
        mItemBinding.executePendingBindings();
    }
}
