package com.example.abdullahi.weblinksaver;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.abdullahi.weblinksaver.model.WebLink;

import java.util.List;

public class WebLinkRecyclerAdapter extends RecyclerView.Adapter<WebLinkRecyclerAdapter.ViewHolders> {

    private Context mContext;
    private List<WebLink> mLinks;

    public WebLinkRecyclerAdapter(Context context, List<WebLink> links){
        mContext = context;
        mLinks = links;
    }

    @NonNull
    @Override
    public ViewHolders onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(mContext).inflate(R.layout.weblink_item,parent,false);

        return new ViewHolders(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolders holder, int position) {
        WebLink link = mLinks.get(position);

        holder.mWebLink.setText(link.getWebLink());
        holder.mWebTitle.setText(link.getWebTitle().getTitle());
    }

    @Override
    public int getItemCount() {
        return mLinks.size();
    }

    public class ViewHolders extends RecyclerView.ViewHolder{

        TextView mWebTitle;
        TextView mWebLink;
        View mView;
        public ViewHolders(View itemView) {
            super(itemView);
            mView = itemView;
            mWebTitle = itemView.findViewById(R.id.webTitle);
            mWebLink = itemView.findViewById(R.id.webLink);
        }
    }
}
