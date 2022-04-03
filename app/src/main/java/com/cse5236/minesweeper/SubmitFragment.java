package com.cse5236.minesweeper;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.fragment.app.DialogFragment;

public class SubmitFragment extends DialogFragment {
    private EditText editName;
    private DialogListener listener;

    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());

        LayoutInflater inflater = getActivity().getLayoutInflater();
        View view = inflater.inflate(R.layout.fragment_game_submit, null);

        builder.setView(view).setTitle("Succeeded!").setNegativeButton("Cancel", (dialogInterface, i) -> {
            //quit
        }).setPositiveButton("Submit", (dialogInterface, i) -> {
            String playerName = editName.getText().toString();
            listener.applyPlayerName(playerName);
        });
        editName = view.findViewById(R.id.name);
        return builder.create();
    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);

        try {
            listener = (DialogListener) context;
        } catch (Exception e) {
            throw new ClassCastException(context.toString() + " no dialog listener!");
        }
    }

    public interface DialogListener {
        void applyPlayerName(String playerName);
    }
}
