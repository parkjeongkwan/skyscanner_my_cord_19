package mydom.com.alpha;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.ViewGroup;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.LinearLayout;
import android.widget.Toast;

public class Main extends AppCompatActivity {
    String temp;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        final Context context=Main.this;

        LinearLayout frame=new LinearLayout(context);
        LinearLayout.LayoutParams mm=new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.MATCH_PARENT);
        frame.setLayoutParams(mm);
        WebView wv=new WebView(context);
        wv.setLayoutParams(mm);
        WebSettings settings=wv.getSettings();
        settings.setUseWideViewPort(true);
        settings.setJavaScriptEnabled(true);
        wv.setWebViewClient(new WebViewClient());

        wv.addJavascriptInterface(new JavascriptInterface() {
            @Override @android.webkit.JavascriptInterface
            public void showToast(String message) {
                Toast.makeText(context,temp,Toast.LENGTH_LONG).show();
            }

            @Override @android.webkit.JavascriptInterface
            public void sendMessage(String message) {
                temp=message;
            }
        }, "Hybrid");
        wv.loadUrl("file:///android_asset/www/index.html");
        frame.addView(wv);
        setContentView(frame);

    }
    public interface JavascriptInterface{
        public void showToast(String message);
        public void sendMessage(String message);

    }
}
