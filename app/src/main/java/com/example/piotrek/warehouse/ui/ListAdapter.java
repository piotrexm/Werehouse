package com.example.piotrek.warehouse.ui;

import android.content.Context;
import android.support.annotation.NonNull;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.example.piotrek.warehouse.R;
import com.example.piotrek.warehouse.data.IParsable;

/**
 * Created by Piotrek on 2017-06-10.
 */

public class ListAdapter extends ArrayAdapter<IParsable> {

    public ListAdapter(@NonNull Context context) {
        super(context, android.R.layout.simple_list_item_1);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        IParsable element = getItem(position);

        LayoutInflater layoutInflater = LayoutInflater.from(getContext());
        View listElements = layoutInflater.inflate(R.layout.list, null);

        TextView name = (TextView) listElements.findViewById(R.id.list_element);
        Log.d("List Adapter", element.parseName());
        name.setText(element.parseName());

        return listElements;
    }
}