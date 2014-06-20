package com.example.iching.app.activity;

import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.example.iching.app.R;
import com.example.iching.app.db.DivinationDatabaseHelper;
import com.example.iching.app.model.DivinationObject;

import java.util.ArrayList;
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

        divinationListView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                divinationDatabaseHelper.getPostDAO().deleteById(id);
                divinationObjectList = new ArrayList<DivinationObject>();
                adapter.notifyDataSetChanged();
                return true;
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
            TextView textView;
            if (convertView == null) {
                textView = new TextView(mContext);
            } else {
                textView = (TextView) convertView;
            }
            textView.setText(list.get(position).getQuestion());
            textView.setGravity(Gravity.CENTER_VERTICAL);
            textView.setPadding(10, 0, 0, 0);
            textView.setTextColor(Color.BLACK);
            Drawable drawable = getResources().getDrawable(list.get(position).getImageSource());
            textView.setCompoundDrawablesWithIntrinsicBounds(drawable, null, null, null);

            return textView;
        }
    }
}
