package com.chyngyz.testapp.data.entity;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class Car {
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
        private Info info;

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

        public Info getInfo() {
            return info;
        }

        public void setInfo(Info info) {
            this.info = info;
        }

        class Info {
            private int date;
            private int mileage;
            private String color;

            public int getDate() {
                return date;
            }

            public void setDate(int date) {
                this.date = date;
            }

            public int getMileage() {
                return mileage;
            }

            public void setMileage(int mileage) {
                this.mileage = mileage;
            }

            public String getColor() {
                return color;
            }

            public void setColor(String color) {
                this.color = color;
            }
        }
    }

}
