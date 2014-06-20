package com.example.iching.app.activity;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.ListView;

import com.example.iching.app.R;
import com.example.iching.app.db.DatabaseHelper;
import com.example.iching.app.model.Hex;

import java.util.ArrayList;
import java.util.List;

public class HexagramsActivity extends IChingBaseActivity {
    private GridView gridView;
    private HexagramAdapter adapter;
    private DatabaseHelper helper;
    private Animation anim;
    private ListView hexagramListView;
    private List<Hex> hexes = new ArrayList<Hex>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.hexagrams);
        helper = new DatabaseHelper(getApplicationContext());
        setTitle("64 Hexagrams");
        hexes = helper.getPostDAO().queryForAll();

        final AlertDialog dialog = new AlertDialog.Builder(this).create();
        final View dialogView = getLayoutInflater().inflate(R.layout.switch_gridview_listview_dialog, null);
        dialog.setView(dialogView);
        dialog.setTitle("change display");
        hexagramListView = (ListView) findViewById(R.id.hexagramsDisplayListView);
        gridView = (GridView) findViewById(R.id.hexagramsDisplay);
        anim = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.drop_down);
        adapter = new HexagramAdapter(this, hexes);
        gridView.setAdapter(adapter);
        anim.start();
        gridView.setOnItemClickListener(hexagramClick());
        gridView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                dialog.show();
                dialogView.findViewById(R.id.listViewDisplay).setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        gridView.setVisibility(View.GONE);
                        hexagramListView.setAdapter(adapter);
                        hexagramListView.setVisibility(View.VISIBLE);
                        dialog.dismiss();
                    }
                });
                dialogView.findViewById(R.id.gridViewDisplay).setOnClickListener(noChangeDisplay(dialog));
                return true;
            }
        });
        hexagramListView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                dialog.show();
                dialogView.findViewById(R.id.gridViewDisplay).setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        gridView.setVisibility(View.VISIBLE);
                        hexagramListView.setVisibility(View.GONE);
                        dialog.dismiss();
                    }
                });
                dialogView.findViewById(R.id.listViewDisplay).setOnClickListener(noChangeDisplay(dialog));
                return false;
            }
        });
        hexagramListView.setOnItemClickListener(hexagramClick());
    }

    private AdapterView.OnItemClickListener hexagramClick() {
        return new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(HexagramsActivity.this, HexagramsDisplayActivity.class);
                intent.putExtra(HexagramsDisplayActivity.ID_IN, position);
                startActivity(intent);
            }
        };
    }

    private View.OnClickListener noChangeDisplay(final AlertDialog dialog) {
        return new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        };
    }

    class HexagramAdapter extends BaseAdapter {
        private Context mContext;
        private List<Hex> list;

        public HexagramAdapter(Context c, List<Hex> List) {
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

        public View getView(int position, View convertView, ViewGroup parent) {
            ImageView imageView;
            if (convertView == null) {  // if it's not recycled, initialize some attributes
                imageView = new ImageView(mContext);
                imageView.setPadding(10, 10, 10, 10);
                imageView.setAnimation(anim);
            } else {
                imageView = (ImageView) convertView;
            }
            imageView.setImageResource(list.get(position).getImageSource());
            return imageView;
        }
    }


}