package io.github.umangjpatel.gallop.utils.adapters.recyclerview;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import java.util.List;

import io.github.umangjpatel.gallop.databinding.CatalogCourseListItemBinding;
import io.github.umangjpatel.gallop.models.course.CourseInfo;
import io.github.umangjpatel.gallop.utils.viewholders.CatalogViewHolder;

public class CatalogAdapter extends RecyclerView.Adapter<CatalogViewHolder> {

    private List<CourseInfo> mCourseInfoList;

    public CatalogAdapter(List<CourseInfo> courseInfoList) {
        mCourseInfoList = courseInfoList;
    }

    public void setCourseInfoList(List<CourseInfo> courseInfoList) {
        mCourseInfoList = courseInfoList;
    }

    @NonNull
    @Override
    public CatalogViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        CatalogCourseListItemBinding itemBinding = CatalogCourseListItemBinding
                .inflate(inflater, parent, false);
        return new CatalogViewHolder(itemBinding);
    }

    @Override
    public void onBindViewHolder(@NonNull CatalogViewHolder holder, int position) {
        CourseInfo courseInfo = mCourseInfoList.get(position);
        holder.bind(courseInfo);
    }

    @Override
    public int getItemCount() {
        return mCourseInfoList.size();
    }
}
