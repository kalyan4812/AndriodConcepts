package com.example.andriodconcept.Basics;

import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.GridView;
import android.widget.VideoView;

import com.example.andriodconcept.Basics.Gallery;
import com.example.andriodconcept.R;

import java.util.ArrayList;
import java.util.Arrays;

public class Mygalleryadapter extends BaseAdapter {
    ArrayList<String> arrayList=new ArrayList<>(Arrays.asList("https://www.youtube.com/watch?v=hSKr72Whook","https://file-examples-com.github.io/uploads/2017/04/file_example_MP4_1280_10MG.mp4",
            "https://cdn.videvo.net/videvo_files/video/premium/video0225/small_watermarked/MR_Stock%20Footage%20MR%20(2338)_preview.webm"));

    Gallery gal;
    GridView gv;

    public Mygalleryadapter(Gallery gallery) {
        this.gal=gallery;
    }


    @Override
    public int getCount() {
        return 3;
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
        LayoutInflater inflater= LayoutInflater.from(parent.getContext());
        View v=inflater.inflate(R.layout.mygalleryadapter,null);
        final VideoView videoView=(VideoView) v.findViewById(R.id.video);
        CheckBox checkBox=(CheckBox) v.findViewById(R.id.cb);
        videoView.setVideoURI(Uri.parse(arrayList.get(position)));
        checkBox.setText(arrayList.get(position));
        checkBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked){
                    videoView.start();
                }
                else {
                    videoView.stopPlayback();
                }
            }
        });
        return v;
    }
}
