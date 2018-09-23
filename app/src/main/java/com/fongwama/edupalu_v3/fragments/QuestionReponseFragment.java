package com.fongwama.edupalu_v3.fragments;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.fongwama.edupalu_v3.R;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link QuestionReponseFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class QuestionReponseFragment extends Fragment {


    public QuestionReponseFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setRetainInstance(true);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_question_reponse, container, false);



        return v;
    }

}
