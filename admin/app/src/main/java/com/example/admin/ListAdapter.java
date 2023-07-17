package com.example.admin;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;
import androidx.annotation.NonNull;
import java.util.ArrayList;
public class ListAdapter extends ArrayAdapter<Users> {
    public ListAdapter(Context context,ArrayList<Users> usersArrayList)
    {
        super(context,R.layout.list_item,usersArrayList);
    }
    @NonNull
    @Override
    public View getView(int position, View convertView, ViewGroup parent)
    {
        Users user=getItem(position);
        if(convertView==null)
        {
            convertView= LayoutInflater.from(getContext()).inflate(R.layout.list_item,parent,false);
        }
        TextView regno=convertView.findViewById(R.id.personame);
        TextView fess=convertView.findViewById(R.id.fess);
        TextView time=convertView.findViewById(R.id.msgtime);
        regno.setText(user.regno);
        fess.setText(user.name);
        time.setText(user.fess);
                return convertView;
    }
}
