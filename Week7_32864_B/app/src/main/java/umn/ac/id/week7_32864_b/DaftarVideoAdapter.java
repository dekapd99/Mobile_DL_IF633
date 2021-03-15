package umn.ac.id.week7_32864_b;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.VideoView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.LinkedList;

public class DaftarVideoAdapter extends RecyclerView.Adapter<DaftarVideoAdapter.ItemVideoViewHolder> {
    private LinkedList<SumberVideo> mDaftarVideo;
    private LayoutInflater mInflater;
    private Context mContext;

    public DaftarVideoAdapter(Context context, LinkedList<SumberVideo> daftarVideo){
        this.mContext = context;
        this.mDaftarVideo = daftarVideo;
        this.mInflater = LayoutInflater.from(context);
    }

    @NonNull
    @Override
    public ItemVideoViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType){
        View view = mInflater.inflate(R.layout.video_item_layout, parent, false);
        return new ItemVideoViewHolder(view, this);
    }

    @Override
    public void onBindViewHolder(@NonNull DaftarVideoAdapter.ItemVideoViewHolder holder, int position) {
        SumberVideo mSumberVideo = mDaftarVideo.get(position);
        holder.tvJudul.setText(mSumberVideo.getJudul());
        holder.tvKeterangan.setText(mSumberVideo.getKeterangan());
        holder.tvUri.setText(mSumberVideo.getVideoURI());
        holder.kotakVideo.setVideoURI(Uri.parse(mSumberVideo.getVideoURI()));
        holder.kotakVideo.seekTo(100);
    }

    @Override
    public int getItemCount() {
        return mDaftarVideo.size();
    }

    class ItemVideoViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        private VideoView kotakVideo;
        private TextView tvJudul;
        private TextView tvKeterangan;
        private TextView tvUri;
        private DaftarVideoAdapter mAdapter;
        private int mPosisi;
        private SumberVideo mSumberVideo;

        public ItemVideoViewHolder(@NonNull View itemView, DaftarVideoAdapter adapter){
            super(itemView);
            mAdapter = adapter;
            kotakVideo = (VideoView) itemView.findViewById(R.id.kotakVideo);
            tvJudul = (TextView) itemView.findViewById(R.id.tvJudul);
            tvKeterangan = (TextView) itemView.findViewById(R.id.tvKeterangan);
            tvUri = (TextView) itemView.findViewById(R.id.tvUri);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            mPosisi = getLayoutPosition();
            mSumberVideo = mDaftarVideo.get(mPosisi);
            Intent detilInten = new Intent(mContext,
                    DetilVideoActivity.class);
            Bundle bundle = new Bundle();
            bundle.putSerializable("DetilVideo",mSumberVideo);
            detilInten.putExtras(bundle);
            mContext.startActivity(detilInten);
        }
    }
}