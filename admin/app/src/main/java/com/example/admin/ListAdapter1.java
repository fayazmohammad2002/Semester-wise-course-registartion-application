package com.example.admin;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;
import androidx.annotation.NonNull;
import java.util.ArrayList;
public class ListAdapter1 extends ArrayAdapter<String> {
    public ListAdapter1(Context context, ArrayList<String> branches) {
        super(context,R.layout.layout,branches);
    }
    @NonNull
    @Override
    public View getView(int position, View convertView, ViewGroup parent)
    {
        String user=getItem(position);
        if(convertView==null)
        {
            convertView= LayoutInflater.from(getContext()).inflate(R.layout.layout,parent,false);
        }
        TextView regno=convertView.findViewById(R.id.branchname);
        regno.setText(user);
        return convertView;
    }
}
