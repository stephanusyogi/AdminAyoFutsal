package com.example.ayofutsaladmin;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.TextView;

import java.util.List;

public class UserAdapter extends ArrayAdapter<User> {

    private List<User> users;
    private Context context;
    customButtonListener buttonListener;


    public interface customButtonListener {
        public void onButtonClickListener(int position, int id);
    }
    public void setCustomButtonListener(customButtonListener listener){
        this.buttonListener = listener;
    }
    public UserAdapter(Context context, int resource, List<User> users){
        super(context,resource,users);
        this.context = context;
        this.users = users;
    }
    @Override
    public View getView(final int position, View convertView, ViewGroup parent){
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        View listViewItem = inflater.inflate(R.layout.user,null,true);
        TextView id_user = (TextView) listViewItem.findViewById(R.id.id_user);
        TextView name = (TextView) listViewItem.findViewById(R.id.name);
        TextView status = (TextView) listViewItem.findViewById(R.id.point);
        TextView nohp = (TextView) listViewItem.findViewById(R.id.nohp);
        TextView username = (TextView) listViewItem.findViewById(R.id.username);
        TextView password = (TextView) listViewItem.findViewById(R.id.password);
        Button btnAction = (Button) listViewItem.findViewById(R.id.btnAction);

        final User user = users.get(position);

        id_user.setText("ID : " + Integer.toString(user.getId_user()));
        name.setText("Nama User :\n- " + user.getName());
        status.setText("Point User :\n- " + user.getPoint());
        nohp.setText("Nomor Handphone :\n- " + user.getNohp());
        username.setText("Username :\n- " + user.getUsername());
        password.setText("Password :\n- " + user.getPassword());

        btnAction.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (buttonListener != null) {
                    buttonListener.onButtonClickListener(position,user.getId_user());
                }
            }
        });

        return listViewItem;

    }
}
