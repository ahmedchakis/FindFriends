package com.example.findfriends.ui.home;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.telephony.SmsManager;
import android.telephony.SmsMessage;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.example.findfriends.databinding.FragmentHomeBinding;

public class HomeFragment extends Fragment {

    private FragmentHomeBinding binding;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        binding = FragmentHomeBinding.inflate(inflater, container, false);
        View root = binding.getRoot();
        binding.button.setOnClickListener(v->{
            String numero=binding.positionED.getText().toString().trim();
            /*Intent i=new Intent(Intent.ACTION_CALL);
            i.setData(Uri.parse("tel:"+numero));
            startActivity(i);*/
            SmsManager manager=SmsManager.getDefault();
            manager.sendTextMessage(numero,null,"#FindFriends - envoyer",null,null);
        });

        return root;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}