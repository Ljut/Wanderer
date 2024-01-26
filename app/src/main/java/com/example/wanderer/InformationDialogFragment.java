package com.example.wanderer;

import android.app.Dialog;
import android.os.Bundle;
import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.DialogFragment;

public class InformationDialogFragment extends DialogFragment {

    private static final String ARG_CATEGORY = "category";

    public static InformationDialogFragment newInstance(String category) {
        InformationDialogFragment fragment = new InformationDialogFragment();
        Bundle args = new Bundle();
        args.putString(ARG_CATEGORY, category);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        String category = getArguments().getString(ARG_CATEGORY, "");
        String info = getCategoryInformation(category); // dodao sam samo bezveze switch case, promjeni u nesto ljepse ako ti se dadne
        builder.setMessage(info)
                .setPositiveButton("Return", (dialog, id) -> dismiss());
        return builder.create();
    }

    private String getCategoryInformation(String category) {
        switch (category) {
            case "Policija":
                return "reci dvanaest";
            case "Vatrogasci":
                return "danas je lijep dan";
            // igraj se jos ako hoces
            default:
                return "bilo mi mrsko dalje";
        }
    }
}
