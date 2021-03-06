package com.pixelnos.fire.ui.home;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.pixelnos.fire.MainActivity;
import com.pixelnos.fire.R;
import com.pixelnos.fire.backend.manager.Account;

import java.util.Map;

public class HomeFragment extends Fragment {

    private HomeViewModel homeViewModel;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        homeViewModel = ViewModelProviders.of(this).get(HomeViewModel.class);
        View root = inflater.inflate(R.layout.fragment_home, container, false);
        final TextView textView = root.findViewById(R.id.text_home);
        homeViewModel.getText().observe(this, new Observer<String>() {
            @Override
            public void onChanged(@Nullable String s) {
                textView.setText(s);
            }
        });
        Map<String, Account> accounts = MainActivity.user.getWallet().getAccounts();
     //   for(Map.Entry<String, Account> account : accounts.entrySet()){

     //   }
        MyListData[] myListData = new MyListData[] {
                new MyListData(MainActivity.user.getWallet().getAccountByName("New Account1").getName(), android.R.drawable.ic_dialog_email),
                new MyListData("Map", android.R.drawable.ic_dialog_map),
        };
        RecyclerView recyclerView =  root.findViewById(R.id.recyclerView);
        MyListAdapter adapter = new MyListAdapter(myListData);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        recyclerView.setAdapter(adapter);
        return root;
      }

}