package com.example.ayofutsaladmin;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.TextView;

import java.util.List;

public class PesananAdapter extends ArrayAdapter<Pesanan> {
    private List<Pesanan> pesanans;
    private Context context;
    PesananAdapter.customButtonListener buttonListener;


    public interface customButtonListener {
        public void onButtonClickListener(int position, int id);
    }
    public void setCustomButtonListener(PesananAdapter.customButtonListener listener){
        this.buttonListener = listener;
    }
    public PesananAdapter(Context context, int resource, List<Pesanan> pesanans){
        super(context,resource,pesanans);
        this.context = context;
        this.pesanans = pesanans;
    }
    @Override
    public View getView(final int position, View convertView, ViewGroup parent){
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        View listViewItem = inflater.inflate(R.layout.pesanan,null,true);
        TextView id_pesanan = (TextView) listViewItem.findViewById(R.id.id_pesanan);
        TextView nama = (TextView) listViewItem.findViewById(R.id.nama);
        TextView lapangan = (TextView) listViewItem.findViewById(R.id.lapangan);
        TextView mulai_jam = (TextView) listViewItem.findViewById(R.id.mulai_jam);
        TextView selesai_jam = (TextView) listViewItem.findViewById(R.id.selesai_jam);
        TextView tanggal = (TextView) listViewItem.findViewById(R.id.tanggal);
        TextView catatan = (TextView) listViewItem.findViewById(R.id.catatan);
        TextView status_bayar = (TextView) listViewItem.findViewById(R.id.status_bayar);
        Button btnAction = (Button) listViewItem.findViewById(R.id.btnAction);

        final Pesanan pesanan = pesanans.get(position);

        id_pesanan.setText("ID : " + Integer.toString(pesanan.getId_pesanan()));
        nama.setText("Nama User :\n- " + pesanan.getNama());
        lapangan.setText("Nama Lapangan :\n- " + pesanan.getLapangan());
        mulai_jam.setText("Mulai Jam :\n- " + pesanan.getMulai_jam());
        selesai_jam.setText("Selesai Jam :\n- " + pesanan.getSelesai_jam());
        tanggal.setText("Tanggal :\n- " + pesanan.getTanggal());
        catatan.setText("Catatan :\n- " + pesanan.getCatatan());
        status_bayar.setText("Status Pembayaran :\n- " + pesanan.getStatus_bayar());

        btnAction.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (buttonListener != null) {
                    buttonListener.onButtonClickListener(position,pesanan.getId_pesanan());
                }
            }
        });

        return listViewItem;

    }
}
