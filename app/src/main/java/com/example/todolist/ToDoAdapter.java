package com.example.todolist;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.List;

public class ToDoAdapter  extends BaseAdapter {
    private Context context;
    private int layout;
    private List<ToDo> todolist;

    public ToDoAdapter(Context context, int layout, List<ToDo> todolist) {
        this.context = context;
        this.layout = layout;
        this.todolist = todolist;
    }

    @Override
    public int getCount() {
        return todolist.size();
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    private class ViewHolder{
        TextView txtname;
    }
    @Override
    public View getView(int position, View view, ViewGroup parent) {
        ViewHolder holder;
        if(view==null){
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view = inflater.inflate(layout, null);
            holder=new ViewHolder();
            //ánh xạ view

            holder.txtname=(TextView) view.findViewById(R.id.name);

            view.setTag(holder);
        }
        else{
            holder= (ViewHolder) view.getTag();
        }


        //gán giá trị
        ToDo todo=todolist.get(position);


        holder.txtname.setText(todo.getName());


        return view;
    }
}
