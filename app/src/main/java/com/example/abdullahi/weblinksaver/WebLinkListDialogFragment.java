package com.example.abdullahi.weblinksaver;

import android.annotation.SuppressLint;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.webkit.WebView;
import android.widget.EditText;
import android.widget.Toast;

import com.example.abdullahi.weblinksaver.model.Web;
import com.example.abdullahi.weblinksaver.model.WebLink;
import com.example.abdullahi.weblinksaver.service.MyClient;
import com.example.abdullahi.weblinksaver.service.ServiceBuilder;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;

import java.io.IOException;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import pl.droidsonroids.retrofit2.JspoonConverterFactory;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;

/**
 * <p>A fragment that shows a list of items as a modal bottom sheet.</p>
 * <p>You can show this modal bottom sheet from your activity like this:</p>
 * <pre>
 *     WebLinkListDialogFragment.newInstance(30).show(getSupportFragmentManager(), "dialog");
 * </pre>
 * <p>You activity (or fragment) needs to implement {@link WebLinkListDialogFragment.Listener}.</p>
 */
public class WebLinkListDialogFragment extends DialogFragment {

    // TODO: Customize parameter argument names
    private static final String ARG_ITEM_COUNT = "item_count";
    String webLinkTitle;
    private Listener mListener;

    // TODO: Customize parameters
    public static WebLinkListDialogFragment newInstance() {
        final WebLinkListDialogFragment fragment = new WebLinkListDialogFragment();
        return fragment;
    }



    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
//        return super.onCreateDialog(savedInstanceState);

        LayoutInflater inflater = getActivity().getLayoutInflater();
        final View view = inflater.inflate(R.layout.fragment_weblink_list_dialog_item, null);

        final EditText ed_webLinkTitle = view.findViewById(R.id.et_webTitle);
        ed_webLinkTitle.setVisibility(View.GONE);


        AlertDialog.Builder dialog = new AlertDialog.Builder(getActivity());

        dialog.setView(view)
                .setTitle("Create / Save new link")
                .setNeutralButton("Generate", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                        EditText webLinkUrl = (EditText) view.findViewById(R.id.et_webUrl_link);
                        final String webLinkValue = webLinkUrl.getText().toString();
                        // String webTitleValue = ed_webLinkTitle.getText().toString();
                        final String[] resultGenerated = new String[1];
                        @SuppressLint("StaticFieldLeak")
                        AsyncTask<String, Void, String > task = new AsyncTask<String, Void, String> () {
                            Listener listener = mListener;
                            @Override
                            protected String doInBackground(String... strings) {

                                try {
                                    String url = strings[0];
                                    if (url != null) {
                                        Document mDocument = Jsoup.connect(url).get();

                                        listener.onWebLinkClicked(webLinkValue,mDocument.title());

                                    }
                                }catch (IOException e){

                                }

                                return null;
                            }

                        };

                        task.execute(webLinkValue);

                    }
                })
                .setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        if (dialog != null) {
                            dialog.dismiss();
                        }
                    }
                });
        return dialog.create();
    }


    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        final Fragment parent = getParentFragment();
        if (parent != null) {
            mListener = (Listener) parent;
        } else {
            mListener = (Listener) context;
        }
    }

    @Override
    public void onDetach() {
        mListener = null;
        super.onDetach();
    }

    public interface Listener {
        void onWebLinkClicked(String webLink, String webTitle);
    }


}
