package com.example.andriodconcept.Fragments;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.andriodconcept.R;

public class HomeFragment extends Fragment {

// recieving data from activity to home fragment.
    private static final String ARG_TEXT = "argText";
    private static final String ARG_NUMBER = "argNumber";
    private String text;
    private int number;
    private FragmentAListener fragmentAListener;
    public static HomeFragment newInstance(String text, int number) {
        HomeFragment fragment = new HomeFragment();
        Bundle args = new Bundle();
        args.putString(ARG_TEXT, text);
        args.putInt(ARG_NUMBER, number);
        fragment.setArguments(args);
        return fragment;
    }

    //////////////////////////////////////////
    // send data between two /fragment to activity.
    // for sendind data betwwen two fragments same code should be used in fagment b also.
    public interface FragmentAListener {
        void onInputASent(CharSequence input);
    }
    EditText ed;
    Button data;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View v=inflater.inflate(R.layout.homefragment,container,false);
        Button btn=(Button) v.findViewById(R.id.btn);
        ed=v.findViewById(R.id.sendtext);
        data=v.findViewById(R.id.senddata);

        if (getArguments() != null) {
            text = getArguments().getString(ARG_TEXT);
            number = getArguments().getInt(ARG_NUMBER);
            Toast.makeText(getContext(),text+"  "+number,Toast.LENGTH_LONG).show();
        }
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getActivity(),"HOME FRAGMENT",Toast.LENGTH_LONG).show();
            }
        });
        data.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                CharSequence input = ed.getText();
                fragmentAListener.onInputASent(input);
            }
        });
        return v;
    }
    public void updateEditText(CharSequence newText) {
        ed.setText(newText);
    }
    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof FragmentAListener) {
            fragmentAListener = (FragmentAListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement FragmentAListener");
        }
    }
    @Override
    public void onDetach() {
        super.onDetach();
        fragmentAListener = null;
    }
}
