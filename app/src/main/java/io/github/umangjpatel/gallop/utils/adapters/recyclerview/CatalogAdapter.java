package io.github.umangjpatel.gallop.utils.adapters.recyclerview;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import io.github.umangjpatel.gallop.databinding.CatalogCourseListItemBinding;
import io.github.umangjpatel.gallop.utils.viewholders.CatalogViewHolder;

public class CatalogAdapter extends RecyclerView.Adapter<CatalogViewHolder> {

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
        //To be implemented with dummy data
        holder.bind();
    }

    @Override
    public int getItemCount() {
        return 100;
    }
}
