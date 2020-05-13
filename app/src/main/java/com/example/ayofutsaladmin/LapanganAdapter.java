package com.example.ayofutsaladmin;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.TextView;

import java.util.List;

public class LapanganAdapter extends ArrayAdapter<Lapangan> {
    private List<Lapangan> lapangans;
    private Context context;
    LapanganAdapter.customButtonListener buttonListener;

    public interface customButtonListener {
        public void onButtonClickListener(int position, int id);
    }
    public void setCustomButtonListener(LapanganAdapter.customButtonListener listener){
        this.buttonListener = listener;
    }
    public LapanganAdapter(Context context, int resource, List<Lapangan> lapangans){
        super(context,resource,lapangans);
        this.context = context;
        this.lapangans = lapangans;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent){
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        View listViewItem = inflater.inflate(R.layout.lapangan,null,true);
        TextView id_lap = (TextView) listViewItem.findViewById(R.id.id_lap);
        TextView name = (TextView) listViewItem.findViewById(R.id.name);
        TextView status = (TextView) listViewItem.findViewById(R.id.status);
        Button btnAction = (Button) listViewItem.findViewById(R.id.btnAction);

        final Lapangan lapangan = lapangans.get(position);

        id_lap.setText("ID : " + Integer.toString(lapangan.getId_lapangan()));
        name.setText("Nama Lapangan :\n- " + lapangan.getName());
        status.setText("Status Lapangan :\n- " + lapangan.getStatus());


        btnAction.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (buttonListener != null) {
                    buttonListener.onButtonClickListener(position,lapangan.getId_lapangan());
                }
            }
        });

        return listViewItem;

    }
}
