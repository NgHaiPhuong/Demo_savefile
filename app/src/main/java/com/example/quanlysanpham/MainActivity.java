package com.example.quanlysanpham;

import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.arch.core.internal.SafeIterableMap;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class MainActivity extends AppCompatActivity {
    EditText etDM, etMa, etTen, etSL;
    ListView lstView;
    List<SanPham> dsSP = new ArrayList<>();
    SanPham spSelected = new SanPham();
    ArrayAdapter<SanPham> adapter = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Toolbar toolbar = this.findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        getWidget();
    }

    private void getWidget() {
        etMa = findViewById(R.id.etMaSP);
        etTen = findViewById(R.id.etTenSP);
        etDM = findViewById(R.id.etDanhMuc);
        etSL = findViewById(R.id.etSoLuong);

        dsSP.add(new SanPham("SP01", "Pencil", "Education", 20));
        dsSP.add(new SanPham("SP02", "Ruler", "Education", 100));

        lstView = findViewById(R.id.listview);
        adapter = new ArrayAdapter<>(MainActivity.this,
                R.layout.item_listview, dsSP);
        lstView.setAdapter(adapter);

        lstView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                spSelected = dsSP.get(position);
                etMa.setText(spSelected.getMaSP());
                etTen.setText(spSelected.getTenSP());
                etDM.setText(spSelected.getDanhMuc());
                etSL.setText(String.valueOf(spSelected.getSoLuong()));
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        int id1 = item.getItemId();
        if (R.id.them == id1) themHang();
        else if(R.id.sua == id1) suaHang();
        else if(R.id.xoa == id1) xoaHang();
        else if(R.id.luu == id1) luuHang();
        else if(R.id.doc == id1) docHang();
        else if(R.id.dong == id1) dong();
        return false;
    }

    private void dong() {

    }

    private void docHang() {
        try {
            dsSP.clear();
            FileInputStream in = openFileInput("qlsp.txt");
            BufferedReader reader = new BufferedReader(new InputStreamReader(in));
            String line;
            while ((line = reader.readLine()) != null){
                dsSP.add(SanPham.fromString(line));
            }
            adapter.notifyDataSetChanged();
            in.close();
        }catch (IOException e){
            Log.e("nghp", e.getMessage().toString());
        }
    }

    private void luuHang() {
        try {
            FileOutputStream out = openFileOutput("qlsp.txt",MODE_APPEND);
            BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(out));
            for(SanPham sp : dsSP){
                writer.write(sp.toString());
                writer.newLine();
            }
            writer.close();
        }catch (IOException e){
            Log.e("nghp", e.getMessage().toString() );
        }
    }

    private void xoaHang() {
        SanPham sp = spSelected;
        dsSP.remove(sp);
        adapter.notifyDataSetChanged();
    }

    private void suaHang() {
        SanPham sp = spSelected;
        sp.setMaSP(etMa.getText().toString());
        sp.setTenSP(etTen.getText().toString());
        sp.setDanhMuc(etDM.getText().toString());
        sp.setSoLuong(Integer.parseInt(etSL.getText().toString()));
        adapter.notifyDataSetChanged();
    }

    private void themHang() {
        String ma = etMa.getText().toString();
        String ten = etTen.getText().toString();
        String dm = etDM.getText().toString();
        int sl = Integer.parseInt(etSL.getText().toString());

        dsSP.add(new SanPham(ma, ten, dm, sl));
        adapter.notifyDataSetChanged();
    }
}