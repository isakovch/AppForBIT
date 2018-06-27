package com.chyngyz.testapp.data.entity;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class User {
    @SerializedName("OK")
    private int ok;
    @SerializedName("data")
    private ArrayList<Data> dataList;

    public int getOk() {
        return ok;
    }

    public void setOk(int ok) {
        this.ok = ok;
    }

    public ArrayList<Data> getDataList() {
        return dataList;
    }

    public void setDataList(ArrayList<Data> dataList) {
        this.dataList = dataList;
    }

    public class Data {
        private int id;
        private String name;
        private int carId;

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public int getCarId() {
            return carId;
        }

        public void setCarId(int carId) {
            this.carId = carId;
        }
    }

}
