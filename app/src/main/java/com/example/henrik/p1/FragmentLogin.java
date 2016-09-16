package com.example.henrik.p1;


import android.app.Fragment;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;


/**
 * A simple {@link Fragment} subclass.
 */
public class FragmentLogin extends Fragment {
        private EditText etName;
        private EditText etLastname;
        private Button btnLogin;
        private SharedPreferences sharedPreferences;

    public FragmentLogin() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_fragment_login, container, false);
        initComponents(view);
        initListeners();
        return view;
    }

    private void initComponents(View view){
        etName = (EditText)view.findViewById(R.id.etName);
        etLastname = (EditText)view.findViewById(R.id.etLastname);
        btnLogin = (Button)view.findViewById(R.id.btnLogin);

    }


    public void initListeners(){
        btnLogin.setOnClickListener(new ButtonLoginListener());
    }

    private class ButtonLoginListener implements View.OnClickListener {
        @Override
        public void onClick(View v) {
            sharedPreferences = getActivity().getSharedPreferences("Inlogg", Context.MODE_PRIVATE);
            SharedPreferences.Editor editor = sharedPreferences.edit();
            editor.putString("Firstname", etName.getText().toString());
            editor.putString("Lastname", etLastname.getText().toString());
            editor.apply();
            ((MainActivity)getActivity()).swapFrag(new MainFragment());

        }
    }
}
