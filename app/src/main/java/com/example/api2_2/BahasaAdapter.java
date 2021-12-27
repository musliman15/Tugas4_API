package com.example.api2_2;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;

import com.bumptech.glide.Glide;

import java.util.ArrayList;

public class BahasaAdapter extends ArrayAdapter {

    private Context context;
    ArrayList<DataBahasa> datab = new ArrayList();
    LayoutInflater layoutInflater;
    ImageView logo;
    TextView nama,readMore,deskripsi,contohKode;

    public BahasaAdapter(Context context, ArrayList<DataBahasa> data){
        super(context, R.layout.row_bahasa, data);
        this.context = context;
        layoutInflater = LayoutInflater.from(context);
        this.datab = data;
    }

    @NonNull
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        convertView = layoutInflater.inflate(R.layout.row_bahasa, parent, false);
        DataBahasa data = datab.get(position);

        logo = convertView.findViewById(R.id.logoRow);
        nama = convertView.findViewById(R.id.txNama);
        readMore = convertView.findViewById(R.id.txReadMore);
        deskripsi = convertView.findViewById(R.id.txDeskripsi);
        contohKode = convertView.findViewById(R.id.contohKode);

        Glide
                .with(context)
                .load(data.getLogo())
                .into(logo);
        nama.setText(data.getNama());
        deskripsi.setText(data.getDeskripsi());
        contohKode.setText(data.getContohKode());
        readMore.setText(data.getReadMore());


        return convertView;
    }
}
