package net.tvplay;

import android.content.Context;
import android.os.CountDownTimer;
import android.support.v17.leanback.widget.Presenter;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.widget.Button;
import android.widget.FrameLayout;
/**
 * Created by egork on 12.02.2018.
 */

public class ItemPresenter extends Presenter {
    private static final int WIDTH_ITEM = 1000;
    private static final int HEIGHT_ITEM = 500;
    private static final String TAG = "ItemPresenter";

    @Override
    public ViewHolder onCreateViewHolder(final ViewGroup parent) {
        final FrameLayout frameLayout = new FrameLayout(parent.getContext());
        frameLayout.setLayoutParams(new FrameLayout.LayoutParams(WIDTH_ITEM,HEIGHT_ITEM));
        frameLayout.addView(getButton(parent.getContext()));
        frameLayout.addView(getWebView(parent.getContext()));

        Log.d(TAG,"onCreateViewHolder");
        return new ViewHolder(frameLayout);
    }

    @Override
    public void onBindViewHolder(final ViewHolder viewHolder, Object item) {
        final FrameLayout frameLayout = (FrameLayout) viewHolder.view;
       Button button = (Button)frameLayout.getChildAt(0);
       button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                frameLayout.removeAllViews();
                frameLayout.setVisibility(View.GONE);
            }
        });
       setTimer(10000,button);

        Log.d(TAG,"onBindViewHolder");
    }

    @Override
    public void onUnbindViewHolder(ViewHolder viewHolder) {
        Log.d(TAG,"onUnbindViewHolder");
    }

    private Button getButton(Context context){
        FrameLayout.LayoutParams buttonParams = new FrameLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        buttonParams.gravity = Gravity.BOTTOM|Gravity.RIGHT;
        Button button = new Button(context);
        button.setLayoutParams(buttonParams);
        button.setFocusable(true);
        button.setFocusableInTouchMode(true);
        button.setText(context.getText(R.string.skip_text));
        button.setVisibility(View.INVISIBLE);
        return button;
    }

    private WebView getWebView(Context context){
        String html = "<script type='text/javascript' src=\"https://t181137.vj-vid.com/VideoJamAdService/getCreativeJS?CreativeTagId=181137&w=300&h=250&ref=cnn.com\"></script>";
        String mime = "text/html";
        String encoding = "utf-8";
        WebView webView = new WebView(context);
        webView.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));
        webView.setFocusable(true);
        webView.setFocusableInTouchMode(true);
        webView.getSettings().setJavaScriptEnabled(true);
        webView.setInitialScale(90);
        webView.loadDataWithBaseURL(null, html, mime, encoding, null);
        return webView;
    }

    private void setTimer(long backtime, final Button button){
        CountDownTimer countDownTimer = new CountDownTimer(backtime,1000) {
            @Override
            public void onTick(long millisUntilFinished){}
            @Override
            public void onFinish() {
                if(button != null){
                    button.setVisibility(View.VISIBLE);
                }
            }
        }.start();
    }

}