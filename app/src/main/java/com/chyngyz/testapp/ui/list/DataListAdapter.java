package com.chyngyz.testapp.ui.list;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.chyngyz.testapp.R;
import com.chyngyz.testapp.data.entity.Car;
import com.chyngyz.testapp.data.entity.User;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

public class DataListAdapter extends BaseAdapter {

    private ArrayList<User.Data> mUserList;
    private ArrayList<Car.Data> mCarList;

    DataListAdapter(ArrayList<User.Data> userList, ArrayList<Car.Data> carList) {
        this.mUserList = userList;
        this.mCarList = carList;
    }

    @Override
    public int getCount() {
        return mUserList.size();
    }

    @Override
    public Object getItem(int position) {
        return mUserList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder;
        if (convertView == null) {
            convertView = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_user_list, parent, false);
            holder = new ViewHolder(convertView);

            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }

        User.Data item = mUserList.get(position);

        if (item != null) {
            holder.mName.setText(item.getName());
            holder.mModel.setText(getCarById(item.getCarId()));
        }

        return convertView;
    }

    private String getCarById(int id) {
        for (Car.Data car : mCarList) {
            if (car.getId() == id) {
                return car.getName();
            }
        }
        return "";
    }

    class ViewHolder {
        @BindView(R.id.name)
        TextView mName;
        @BindView(R.id.model)
        TextView mModel;

        ViewHolder(View view) {
            ButterKnife.bind(this, view);
        }
    }
}
