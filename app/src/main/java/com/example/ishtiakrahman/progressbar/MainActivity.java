package com.example.ishtiakrahman.progressbar;

        import android.net.ConnectivityManager;
        import android.net.NetworkInfo;
        import android.os.Build;
        import android.support.annotation.RequiresApi;
        import android.support.v7.app.AppCompatActivity;
        import android.os.Bundle;
        import android.view.KeyEvent;
        import android.view.View;
        import android.webkit.WebChromeClient;
        import android.webkit.WebResourceRequest;
        import android.webkit.WebView;
        import android.webkit.WebViewClient;
        import android.widget.FrameLayout;
        import android.widget.ProgressBar;
        import android.widget.Toast;

        import static android.R.id.progress;

public class MainActivity extends AppCompatActivity {
    WebView webView;
    ProgressBar progressBar;
    FrameLayout frameLayout;


    @Override
    public void onBackPressed() {
        if (webView.canGoBack()) {
            webView.goBack();

        }

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        frameLayout = (FrameLayout) findViewById(R.id.frameLayout);
        webView = (WebView) findViewById(R.id.webView);
        progressBar = (ProgressBar) findViewById(R.id.progressBar);
        progressBar.setMax(100);

        webView.getSettings().setJavaScriptEnabled(true);
        webView.setVerticalScrollBarEnabled(true);
        webView.loadUrl("http://www.google.com");
        webView.setWebViewClient(new WebViewClient(){
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                   view.loadUrl(url);
                   frameLayout.setVisibility(View.VISIBLE);
                return true;
            }
        });

        webView.setWebChromeClient(new WebChromeClient(){

            @Override
            public void onProgressChanged(WebView view, int newProgress) {
                frameLayout.setVisibility(View.VISIBLE);
                progressBar.setProgress(newProgress);
                setTitle("Loading......");

                if (newProgress==100){
                    frameLayout.setVisibility(View.GONE);
                    setTitle(view.getTitle());

                }
                super.onProgressChanged(view, newProgress);

            }
        });

        progressBar.setProgress(0);



    }
}
