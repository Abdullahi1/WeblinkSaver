package com.example.abdullahi.weblinksaver;

import android.annotation.SuppressLint;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatDialogFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import java.io.IOException;

public class DialogBox extends AppCompatDialogFragment {

    public static final String WEB_TITLE = "com.example.abdullahi.weblinksaver.WEB_TITLE";
    public static final String WEB_URL = "com.example.abdullahi.weblinksaver.WEB_URL";

    private DialogListener mListener;

    private String webTitle;
    private String webUrl;

    public static DialogBox newInstance(String webTitle, String webLink){
        DialogBox box = new DialogBox();
        final Bundle args = new Bundle();
        args.putString(WEB_TITLE,webTitle);
        args.putString(WEB_URL,webLink);
        box.setArguments(args);
        return box;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (getArguments() != null){
            webTitle = getArguments().getString(WEB_TITLE);
            webUrl = getArguments().getString(WEB_URL);
        }
    }

    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        //return super.onCreateDialog(savedInstanceState);
        LayoutInflater inflater = getActivity().getLayoutInflater();
        final View view = inflater.inflate(R.layout.fragment_weblink_list_dialog_item, null);

        final EditText ed_webLinkUrl = view.findViewById(R.id.et_webUrl_link);
        final EditText ed_webLinkTitle = view.findViewById(R.id.et_webTitle);
        ed_webLinkTitle.setText(webTitle);
        ed_webLinkUrl.setText(webUrl);

        AlertDialog.Builder dialog = new AlertDialog.Builder(getActivity());
        dialog.setView(view)
                .setPositiveButton("Save", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                    }
                })
                .setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                    }
                });
        return dialog.create();
    }

    public interface DialogListener {
        void applyText (String webLink, String webTitle);
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);

        try {
            mListener = (DialogListener) context;
        }catch (ClassCastException e){
            throw new ClassCastException(context.toString() +" must implement the Listener(DialogListener)");
        }
    }
}
