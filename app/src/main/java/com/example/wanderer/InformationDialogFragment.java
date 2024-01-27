package com.example.wanderer;

import static android.content.Context.MODE_PRIVATE;

import android.app.Dialog;
import android.content.SharedPreferences;
import android.os.Bundle;
import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.DialogFragment;

import java.util.List;

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
        String text = "";
        //SharedPreferences sharedPreferences = getSharedPreferences("AppSettingsPref", MODE_PRIVATE);
        switch (category) {
            case "Policija":
                text += "Broj centralne policije je " + SplashScreen.mojGrad.broj_centralne_policije + "\n";
                text += "Broj policije " + SplashScreen.mojaOpcina.ime_opcine + " je " + SplashScreen.mojaOpcina.broj_policije + "";
                return text;
            case "Vatrogasci":
                text += "Broj vatrogasaca je "+SplashScreen.mojGrad.broj_vatrogasaca;
                return text;
            case "Hitna":
                text += "Broj hitne pomoći je "+SplashScreen.mojGrad.broj_hitne;
                return text;
            // igraj se jos ako hoces
            case "Taxi":
                text += "Brojevi taksija su:\n\n";
                for(Taksi taksi : MainActivity.taksijiUGradu) {
                    text += taksi.ime_taksija + ": " + taksi.broj_telefona + "\n";
                }
                return text;
            default:
                return "bilo mi mrsko dalje";
        }
    }
}
