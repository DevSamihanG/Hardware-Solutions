package com.example.project;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import android.widget.ListView;
import java.util.ArrayList;

public class Product extends AppCompatActivity {

    public static class Item{

        String prodname;
        String desc;
        int image;

        public Item(String prodname, String desc, int image) {
            this.prodname = prodname;
            this.desc = desc;
            this.image = image;
        }

        public String getProdname() {
            return prodname;
        }

        public void setProdname(String prodname) {
            this.prodname = prodname;
        }

        public String getDesc() {
            return desc;
        }

        public void setDesc(String desc) {
            this.desc = desc;
        }

        public int getImage() {
            return image;
        }

        public void setImage(int image) {
            this.image = image;
        }
    }
}
