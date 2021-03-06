package com.example.iching.app.fragment;

import android.app.Fragment;
import android.os.Bundle;
import android.os.Parcelable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.iching.app.R;
import com.example.iching.app.model.Hex;

public class HexagramFragment  extends Fragment{
    public static final String HEX_IN="hex";
    public static final String STATE="state";
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.hexagram_fragment,container, false);

        Bundle bundle = getArguments();
        Parcelable parcelable=bundle.getParcelable(HEX_IN);
        Hex hex = (Hex) parcelable;
        Boolean indicate = bundle.getBoolean(STATE);
        ImageView hexImageView = (ImageView) root.findViewById(R.id.image_source);
        hexImageView.setImageResource(hex.getImageSource());
        TextView description = (TextView) root.findViewById(R.id.description);
        if(indicate){
            description.setText(hex.getDescriptionInEnglish());
        }else{
            description.setText(hex.getDescriptionInMandarin());
        }
        return root;
    }
}
