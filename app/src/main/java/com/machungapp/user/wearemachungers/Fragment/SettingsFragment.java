package com.machungapp.user.wearemachungers.Fragment;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;
import com.machungapp.user.wearemachungers.R;
import com.machungapp.user.wearemachungers.Services.SaveSharedPreference;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class SettingsFragment extends Fragment {
    private View view;
    private EditText edtOldPassword, edtNewPassword, edtConfirmPassword;
    private Button btnChangePassword;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.settings, container, false);

        edtOldPassword = view.findViewById(R.id.edtOldPassword);
        edtNewPassword = view.findViewById(R.id.edtNewPassword);
        edtConfirmPassword = view.findViewById(R.id.edtConfirmPassword);
        btnChangePassword = view.findViewById(R.id.btnChangePassword);

        btnChangePassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (TextUtils.isEmpty(edtOldPassword.getText()) || TextUtils.isEmpty(edtNewPassword.getText()) || TextUtils.isEmpty(edtConfirmPassword.getText())) {
                    Toast.makeText(view.getContext(), "All Field Must Be Filled", Toast.LENGTH_SHORT).show();
                } else {
                    if (SaveSharedPreference.getPassword(view.getContext()).equals(md5(edtOldPassword.getText().toString()))) {
                        if (edtNewPassword.getText().toString().equals(edtConfirmPassword.getText().toString())) {
                            Query query = FirebaseDatabase.getInstance().getReference().child("mahasiswa").orderByChild("nim");
                            query.addListenerForSingleValueEvent(new ValueEventListener() {
                                @Override
                                public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                                    for (DataSnapshot data : dataSnapshot.getChildren()) {
                                        if (data.child("nim").getValue().toString().equals(SaveSharedPreference.getNim(view.getContext()))) {
                                            data.getRef().child("password").setValue(md5(edtNewPassword.getText().toString()));
                                            SaveSharedPreference.setPassword(view.getContext(), md5(edtNewPassword.getText().toString()));
                                            Toast.makeText(view.getContext(), "Password Changed", Toast.LENGTH_SHORT).show();
                                            edtOldPassword.setText("");
                                            edtNewPassword.setText("");
                                            edtConfirmPassword.setText("");
                                        }
                                    }
                                }

                                @Override
                                public void onCancelled(@NonNull DatabaseError databaseError) {

                                }
                            });
                        }
                        else {
                            Toast.makeText(view.getContext(), "New Password and Confirm Password doesn't match", Toast.LENGTH_SHORT).show();
                        }
                    } else {
                        Toast.makeText(view.getContext(), "Wrong Old Password", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });


        return view;
    }

    public static final String md5(final String s) {
        final String MD5 = "MD5";
        try {
            // Create MD5 Hash
            MessageDigest digest = java.security.MessageDigest
                    .getInstance(MD5);
            digest.update(s.getBytes());
            byte messageDigest[] = digest.digest();

            // Create Hex String
            StringBuilder hexString = new StringBuilder();
            for (byte aMessageDigest : messageDigest) {
                String h = Integer.toHexString(0xFF & aMessageDigest);
                while (h.length() < 2)
                    h = "0" + h;
                hexString.append(h);
            }
            return hexString.toString();

        } catch (NoSuchAlgorithmException e) {

            e.printStackTrace();
        }
        return "";
    }
}
