package umn.ac.id;

import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import java.util.LinkedList;


public class DaftarKataAdapter extends RecyclerView.Adapter<DaftarKataAdapter.KataViewHolder>{

    private final LinkedList<String> mDaftarKata;
    private LayoutInflater mInflater;

    DaftarKataAdapter(Context context, LinkedList<String> daftarKata)
    {
        mInflater = LayoutInflater.from(context);
        mDaftarKata = daftarKata;
    }

    @NonNull
    @Override
    public DaftarKataAdapter.KataViewHolder onCreateViewHolder (@NonNull ViewGroup parent, int viewType){
        View mItemView = mInflater.inflate(R.layout.daftarkata_item, parent, false);
        return new KataViewHolder(mItemView, this);
    }
    @Override
    public void onBindViewHolder(@NonNull DaftarKataAdapter.KataViewHolder holder, int position){
        String mCurrent = mDaftarKata.get(position);
        holder.kataItemView.setText(mCurrent);
    }
    @Override
    public int getItemCount(){

        return mDaftarKata.size();
    }

    class KataViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        public final TextView kataItemView;
        final DaftarKataAdapter mAdapter;

        public KataViewHolder (@NonNull View itemView, DaftarKataAdapter adapter)
        {
            super(itemView);
            kataItemView = itemView.findViewById(R.id.kata);
            this.mAdapter = adapter;
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v)
        {
            int mPosition = getLayoutPosition();
            String element = mDaftarKata.get(mPosition);
            mDaftarKata.set(mPosition, element + "Pernah diklik");
            mAdapter.notifyDataSetChanged();
            Toast.makeText(v.getContext(), element + "dipilih", Toast.LENGTH_LONG).show();
        }
    }
}
