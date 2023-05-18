package com.example.save_data_in_db_show_in_recyclerview_retrofit;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Data {



        @SerializedName("id")
        @Expose
        private Integer id;
        @SerializedName("name")
        @Expose
        private String name;
        @SerializedName("year")
        @Expose
        private Integer year;
        @SerializedName("color")
        @Expose
        private String color;
        @SerializedName("pantone_value")
        @Expose
        private String pantoneValue;

    public Data(Integer id, String name, Integer year, String color, String pantoneValue) {
        this.id = id;
        this.name = name;
        this.year = year;
        this.color = color;
        this.pantoneValue = pantoneValue;
    }

    public Integer getId() {
            return id;
        }

        public void setId(Integer id) {
            this.id = id;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public Integer getYear() {
            return year;
        }

        public void setYear(Integer year) {
            this.year = year;
        }

        public String getColor() {
            return color;
        }

        public void setColor(String color) {
            this.color = color;
        }

        public String getPantoneValue() {
            return pantoneValue;
        }

        public void setPantoneValue(String pantoneValue) {
            this.pantoneValue = pantoneValue;
        }


}
