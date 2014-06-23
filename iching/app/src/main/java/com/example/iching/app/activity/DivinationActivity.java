package com.example.iching.app.activity;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.example.iching.app.R;
import com.example.iching.app.db.DivinationDatabaseHelper;
import com.example.iching.app.model.DivinationObject;

import java.util.List;

public class DivinationActivity extends IChingBaseActivity {
    private DivinationDatabaseHelper divinationDatabaseHelper = new DivinationDatabaseHelper(this);
    private List<DivinationObject> divinationObjectList;
    private ListView divinationListView;
    DivinationAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.divinations_layout);
        divinationObjectList = divinationDatabaseHelper.getPostDAO().queryForAll();
        divinationListView = (ListView) findViewById(R.id.divinationDisplay);
        adapter = new DivinationAdapter(this, divinationObjectList);
        divinationListView.setAdapter(adapter);
        if (adapter.isEmpty()) {
            setContentView(R.layout.divinations_layout_empty);
        }

        divinationListView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, final int position, long id) {
                AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(
                        DivinationActivity.this);
                alertDialogBuilder.setTitle("Your Title");
                alertDialogBuilder
                        .setMessage("Delete the current item?")
                        .setCancelable(false)
                        .setNegativeButton("No", new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                dialog.cancel();
                            }
                        })
                        .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                divinationDatabaseHelper.getPostDAO().deleteById(divinationObjectList.get(position).getId());
                                divinationObjectList.clear();
                                divinationObjectList.addAll(divinationDatabaseHelper.getPostDAO().queryForAll());
                                adapter.notifyDataSetChanged();
                                if (adapter.isEmpty()) {
                                    setContentView(R.layout.divinations_layout_empty);
                                }
                            }
                        });
                AlertDialog alertDialog = alertDialogBuilder.create();
                alertDialog.show();
                return true;
            }
        });
        divinationListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                DivinationObject divinationObject=divinationDatabaseHelper.getPostDAO().queryForId(divinationObjectList.get(position).getId());
                Intent intent = new Intent(DivinationActivity.this,LoadDivinationActivity.class);
                intent.putExtra(LoadDivinationActivity.DIVINATION, divinationObject);
                startActivity(intent);
            }
        });
    }

    class DivinationAdapter extends BaseAdapter {

        private Context mContext;
        private List<DivinationObject> list;

        public DivinationAdapter(Context c, List<DivinationObject> List) {
            mContext = c;
            this.list = List;
        }

        @Override
        public int getCount() {
            return list.size();
        }

        @Override
        public Object getItem(int position) {
            return null;
        }

        @Override
        public long getItemId(int position) {
            return 0;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            if (convertView == null) {
                convertView = LayoutInflater.from(mContext).inflate(R.layout.divination_row, parent, false);
            }
            ImageView iconOne= (ImageView) convertView.findViewById(R.id.icon1);
            ImageView iconTwo= (ImageView) convertView.findViewById(R.id.icon2);
            TextView questionField = (TextView) convertView.findViewById(R.id.questionField);
            questionField.setTextColor(Color.BLACK);
            iconOne.setImageResource(list.get(position).getImageSource());
            iconTwo.setImageResource(list.get(position).getSecondImageSource());
            questionField.setText(list.get(position).getQuestion());
            return convertView;
        }
    }
}
