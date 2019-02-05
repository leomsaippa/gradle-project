package com.lsaippa.frontapp;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;


public class JokerFragment extends Fragment {


    private TextView textViewJoke;

    public static JokerFragment newInstance(String joke){

        JokerFragment jokeActivityFragment = new JokerFragment();

        Bundle args = new Bundle();
        args.putString(Intent.EXTRA_TEXT, joke);

        jokeActivityFragment.setArguments(args);

        return jokeActivityFragment;
    }

    public JokerFragment() {
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_joker, container, false);

        textViewJoke = view.findViewById(R.id.textView_joke);

        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        Bundle args = getArguments();

        if(args != null) {
            String joke = args.getString(Intent.EXTRA_TEXT);
            if(joke != null) {
                textViewJoke.setText(joke);
            }
        }
    }
}
