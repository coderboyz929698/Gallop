package io.github.umangjpatel.gallop.utils.viewholders;

import android.view.View;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import io.github.umangjpatel.gallop.databinding.CatalogCourseListItemBinding;
import io.github.umangjpatel.gallop.info.CourseInfoActivity;
import io.github.umangjpatel.gallop.models.course.CourseInfo;

public class CatalogViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

    private CatalogCourseListItemBinding mItemBinding;
    private CourseInfo mCourseInfo;

    public CatalogViewHolder(@NonNull CatalogCourseListItemBinding itemBinding) {
        super(itemBinding.getRoot());
        mItemBinding = itemBinding;
    }

    public void bind(CourseInfo courseInfo) {
        mCourseInfo = courseInfo;
        mItemBinding.courseCodeTextView.setText(courseInfo.getCourseCode());
        mItemBinding.courseTitleTextView.setText(courseInfo.getTitle());
        itemView.setOnClickListener(this);
        mItemBinding.executePendingBindings();
    }

    @Override
    public void onClick(View v) {
        mItemBinding
                .getRoot()
                .getContext()
                .startActivity(CourseInfoActivity.newIntent(mItemBinding.getRoot().getContext(),
                        mCourseInfo.getCourseCode()));
    }
}
