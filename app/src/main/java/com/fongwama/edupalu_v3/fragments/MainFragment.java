package com.fongwama.edupalu_v3.fragments;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.widget.ImageButton;

import com.fongwama.edupalu_v3.R;


public class MainFragment extends Fragment {

    ImageButton comprendre_button,proteger_btn, diagnostic_btn, soigner_btn, search_btn,question_btn;

    public MainFragment() {
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
        View v =  inflater.inflate(R.layout.fragment_main, container, false);
        initRes(v);

        //intialization fragments
        ComprendreFragment comprendreFragment = new ComprendreFragment();
        DiagnosticFragment diagnosticFragment = new DiagnosticFragment();
        ProtegerFragment protegerFragment = new ProtegerFragment();
        QuestionReponseFragment questionReponseFragment = new QuestionReponseFragment();
        SoignerFragment soignerFragment = new SoignerFragment();

        //initialization of clickListners
        clickListenerWidgetFragments(comprendre_button,comprendreFragment);
        clickListenerWidgetFragments(proteger_btn,protegerFragment);
        clickListenerWidgetFragments(soigner_btn,soignerFragment);
        clickListenerWidgetFragments(diagnostic_btn,diagnosticFragment);
        clickListenerWidgetFragments(question_btn,questionReponseFragment);


        return v;
    }

    private void initRes(View v){
        comprendre_button = (ImageButton) v.findViewById(R.id.nav_comprendre);
        soigner_btn = (ImageButton) v.findViewById(R.id.nav_soigner);
        diagnostic_btn = (ImageButton) v.findViewById(R.id.nav_diagnostic);
        question_btn = (ImageButton) v.findViewById(R.id.nav_questions);
        proteger_btn = (ImageButton) v.findViewById(R.id.nav_proteger);
    }

    //clickListener to change fragment
    private void clickListenerWidgetFragments(ImageButton widgetButton,final Fragment fragmentDest){
        widgetButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                android.support.v4.app.FragmentManager fm = getFragmentManager();
                FragmentTransaction ft = fm.beginTransaction();
                ft.replace(R.id.main_frame,fragmentDest);
                ft.commit();
            }
        });
    }

}
