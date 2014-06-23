package com.example.iching.app.activity;

import android.app.FragmentTransaction;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.iching.app.fragment.HexagramFragment;
import com.example.iching.app.R;
import com.example.iching.app.db.DatabaseHelper;
import com.example.iching.app.listener.OnSwipeTouchListener;
import com.example.iching.app.model.Hex;

import java.util.List;

public class HexagramsDisplayActivity extends IChingBaseActivity {
    public static final String ID_IN = "id";
    private List<Hex> hexList;
    private DatabaseHelper helper;
    private int itemId;
    private boolean indicate=true;
    LinearLayout mainLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.hexgrams_display);
        setTitle("Hexagram display");

        mainLayout = (LinearLayout) findViewById(R.id.hexagram_display_xml);
        helper = new DatabaseHelper(getApplicationContext());
        hexList = helper.getPostDAO().queryForAll();

        itemId = getIntent().getIntExtra(ID_IN, -1);
        final Bundle bundle = new Bundle();
        View mandarin = findViewById(R.id.mandarinIndicator);
        mandarin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                TextView textView= (TextView) getFragmentManager().findFragmentByTag("currentState").getView().findViewById(R.id.description);
                textView.setText(hexList.get(itemId).getDescriptionInMandarin());
                indicate=false;
            }
        });

        View english = findViewById(R.id.englishIndicator);
        english.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                TextView textView= (TextView) getFragmentManager().findFragmentByTag("currentState").getView().findViewById(R.id.description);
                textView.setText(hexList.get(itemId).getDescriptionInEnglish());
                indicate=true;
            }
        });


        FragmentTransaction fragmentTransaction = getFragmentManager().beginTransaction();
        HexagramFragment hexagramFragment = new HexagramFragment();
        bundle.putParcelable(HexagramFragment.HEX_IN, hexList.get(itemId));
        hexagramFragment.setArguments(bundle);
        fragmentTransaction.replace(R.id.hexagramDisplayContent, hexagramFragment,"currentState");
        fragmentTransaction.commit();

        mainLayout.setOnTouchListener(new OnSwipeTouchListener(this) {
            @Override
            public void onSwipeLeft() {
                if (itemId < 63) {
                    itemId = itemId + 1;
                    change(itemId);
                } else {
                    Toast.makeText(HexagramsDisplayActivity.this, "this is the last Hexagram", Toast.LENGTH_SHORT).show();
                }
            }
            @Override
            public void onSwipeRight() {
                if (itemId > 0) {
                    itemId = itemId - 1;
                    change(itemId);
                } else {
                    Toast.makeText(HexagramsDisplayActivity.this, "this is the first Hexagram", Toast.LENGTH_SHORT).show();
                }
            }

            private void change(int itemId) {
                FragmentTransaction fragmentTransaction = getFragmentManager().beginTransaction();
                HexagramFragment hexagramFragment = new HexagramFragment();
                bundle.putParcelable(HexagramFragment.HEX_IN, hexList.get(itemId));
                bundle.putBoolean(HexagramFragment.STATE,indicate);
                hexagramFragment.setArguments(bundle);
                fragmentTransaction.replace(R.id.hexagramDisplayContent, hexagramFragment,"currentState");
                fragmentTransaction.commit();
            }
        });
    }

}
