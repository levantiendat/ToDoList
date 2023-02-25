package com.example.todolist;

import androidx.appcompat.app.AppCompatActivity;


import android.app.Dialog;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    ListView listview1;
    ArrayList<ToDo> arrayToDo;
    ToDoAdapter adapter;
    Button btnadd,btnedit,btndone;
    CSDL database;
    int index=-1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        set1();
        data1();
        xuli();

    }
    private void set1(){
        database=new CSDL(this, "ghichu.sqlite",null,1);
        listview1 = (ListView) findViewById(R.id.lv1);
        arrayToDo =new ArrayList<>();
        adapter =new ToDoAdapter(this, R.layout.todo_layout, arrayToDo);

        listview1.setAdapter(adapter);

        btnadd= findViewById(R.id.add);
        btnedit=findViewById(R.id.edit);
        btndone=findViewById(R.id.done);

        database.QueryData("CREATE TABLE IF NOT EXISTS ToDoData(id INTEGER PRIMARY KEY AUTOINCREMENT, name NVARCHAR(40),detail NVARCHAR(200) )");
        ArrayAdapter adapter1 = new ArrayAdapter(MainActivity.this, android.R.layout.simple_list_item_1,arrayToDo);




    }
    private void xuli(){
        btnadd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Dialogtheme();



            }
        });
        btndone.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(index>=0){
                    int pp=arrayToDo.get(index).getId();
                    database.QueryData("DELETE FROM ToDoData WHERE id ='"+pp+"'");
                    data1();
                    index=-1;
                }
            }
        });
        btnedit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(index>=0){
                    Dialogtheme();

                }
            }
        });
        listview1.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                index=position;
            }
        });

    }
    private void data1(){
        Cursor data=database.GetData("SELECT * FROM ToDoData");
        arrayToDo.clear();
        while(data.moveToNext()){
            String ten=data.getString(1);
            String nv=data.getString(2);
            int id=data.getInt(0);
            arrayToDo.add(new ToDo(id,ten,nv));

        }
        adapter.notifyDataSetChanged();
    }
    private void Dialogtheme(){
        Dialog dialog=new Dialog(this);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setContentView(R.layout.add_edit_layout);

        EditText edtname=dialog.findViewById(R.id.text1);
        EditText edtdetail =dialog.findViewById(R.id.text2);
        Button btnok=dialog.findViewById(R.id.ok);
        Button btnback =dialog.findViewById(R.id.back);
        if(index>=0){
            edtname.setText(arrayToDo.get(index).getName());
            edtdetail.setText(arrayToDo.get(index).getDetail());
        }
        btnback.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
                index=-1;
            }
        });
        btnok.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                    String name1 = edtname.getText().toString();
                    String detail1 = edtdetail.getText().toString();

                    if(index==-1) {

                        if (name1.equals("")) {
                            Toast.makeText(MainActivity.this, "Vui lòng nhập tên", Toast.LENGTH_SHORT).show();
                        } else {
                            database.QueryData("INSERT INTO ToDoData VALUES(null, '" + name1 + "','" + detail1 + "')");
                            dialog.dismiss();
                            data1();
                        }
                    }
                    else{
                        int pp=arrayToDo.get(index).getId();
                        if (name1.equals("")) {
                            Toast.makeText(MainActivity.this, "Vui lòng nhập tên", Toast.LENGTH_SHORT).show();
                        } else {
                            database.QueryData("UPDATE ToDoData SET name ='"+name1+"'WHERE id='"+ pp +"'");
                            database.QueryData("UPDATE ToDoData SET detail ='"+detail1+"'WHERE id='"+ pp +"'");
                            dialog.dismiss();
                            data1();
                        }
                        index=-1;
                    }
            }
        });
        dialog.show();
    }

}