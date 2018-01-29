package wang.wongxd.wquicklib.utils;

import android.webkit.WebView;

/**
 * Created by wongxd on 2017/8/14.
 */

public class WebViewUtil {

    /**
     * webView 适配图片
     *
     * @param bodyHTML
     * @return
     */
    public static String getHtmlData(String bodyHTML) {
        String head = "<head><style>img{max-width: 100%; width:auto; height: auto;}</style></head>";
        return "<html>" + head + "<body>" + bodyHTML + "</body></html>";
    }

    /**
     * 加载data 不乱码
     *
     * @param webView
     * @param data
     */
    public static void loadCNData(WebView webView, String data) {
//        webView.loadData(data, "text/html; charset=UTF-8", null);//这种写法可以正确解码  ;
        webView.loadDataWithBaseURL(null, getHtmlData(data), "text/html", "utf-8", null);
    }
}
