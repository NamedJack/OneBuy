package wang.wongxd.wquicklib.base.retrofit;

import android.content.Intent;
import android.support.annotation.NonNull;

import com.alibaba.android.arouter.launcher.ARouter;
import com.google.gson.Gson;
import com.orhanobut.logger.Logger;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;
import java.util.concurrent.TimeUnit;

import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Action;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;
import okhttp3.Cookie;
import okhttp3.CookieJar;
import okhttp3.HttpUrl;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.RequestBody;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.converter.scalars.ScalarsConverterFactory;
import wang.wongxd.wquicklib.base.retrofit.consts.Consts;
import wang.wongxd.wquicklib.base.retrofit.model.LuckyBean;
import wang.wongxd.wquicklib.base.rx.RxBus;
import wang.wongxd.wquicklib.base.rx.RxEventCodeType;
import wang.wongxd.wquicklib.utils.TU;


/**
 * Created by wongxd on 2017/8/2.
 * <p>
 * 获取需要的 Retrofit 客户端
 */
public class RetrofitUtil {

    public static ConcurrentMap<Object, Observer<String>> tagMap = new ConcurrentHashMap<>();

    public static final int DEFAULT_TIMEOUT = 0;


    public static void toCancelAll() {
        for (Observer<String> ob : tagMap.values()) {
            ob.onError(new Exception("用户 主动取消"));
        }
        tagMap.clear();
    }

    private static <T> void toObserver(Observable<T> observable, Observer<T> observer) {
        observable.subscribeOn(Schedulers.io())
                .unsubscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(observer);
    }


    public static void toBaseIntercept(final Object tag, Observable<String> observable, Observer<String> observer) {
        if (tagMap.get(tag) != null) {
            Logger.e("网络请求中，请稍后再试");
            return;
        } else {
            tagMap.put(tag, observer);
        }
        observable.subscribeOn(Schedulers.io())
                .unsubscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .doOnDispose(new Action() {
                    @Override
                    public void run() throws Exception {
                        if (tagMap.get(tag) != null) {
                            tagMap.remove(tag);
                        }
                    }
                })
                .doOnNext(new Consumer<String>() {
                    @Override
                    public void accept(@io.reactivex.annotations.NonNull String s) throws Exception {
                        try {
                            Gson gson = new Gson();
                            JSONObject jsonObject = new JSONObject(s);
                            JSONObject data = jsonObject.optJSONObject("data");
                            if (data == null) return;
                            if (data.optInt("code") == 888) {
                                String list = data.optString("productList");
                                LuckyBean luckyBean = gson.fromJson("{ \"productList\": " + list + "}", LuckyBean.class);
                                RxBus.getDefault().post(RxEventCodeType.BE_LUCKY, luckyBean);
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }

                    }
                })
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(observer);
    }

    public static void toBaseIntercept(Observable<String> observable, Observer<String> observer) {
        observable.subscribeOn(Schedulers.io())
                .unsubscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .doOnNext(new Consumer<String>() {
                    @Override
                    public void accept(@io.reactivex.annotations.NonNull String s) throws Exception {
                        try {
                            Gson gson = new Gson();
                            JSONObject jsonObject = new JSONObject(s);
                            JSONObject data = jsonObject.optJSONObject("data");
                            if (jsonObject.optInt("code") == 300) {
                                TU.cT(jsonObject.optString("msg"));
                                ARouter.getInstance().build("/base/login").withFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK)
                                        .navigation();
                                return;
                            }
                            if (data == null) return;
                            if (data.optInt("code") == 888) {
                                String list = data.optString("productList");
                                Logger.e("中奖列表  "+list);
                                LuckyBean luckyBean = gson.fromJson("{ \"productList\": " + list + "}", LuckyBean.class);
                                RxBus.getDefault().post(RxEventCodeType.BE_LUCKY, luckyBean);
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }

                    }
                })
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(observer);
    }


    private static final class Holder {
        private static class LocalCookieJar implements CookieJar {
            List<Cookie> cookies;

            @Override
            public List<Cookie> loadForRequest(@NonNull HttpUrl arg0) {
                if (cookies != null)
                    return cookies;
                return new ArrayList<>();
            }

            @Override
            public void saveFromResponse(@NonNull HttpUrl arg0, @NonNull List<Cookie> cookies) {
                this.cookies = cookies;
            }

        }

        private static final OkHttpClient sOkHttpClient = new OkHttpClient.Builder()
                .readTimeout(DEFAULT_TIMEOUT, TimeUnit.SECONDS)
                .writeTimeout(DEFAULT_TIMEOUT, TimeUnit.SECONDS)
                .connectTimeout(DEFAULT_TIMEOUT, TimeUnit.SECONDS)
                .addInterceptor(getInterceptor())
                .cookieJar(new LocalCookieJar())
                .build();
        private static final Retrofit INSTANCE_GSON = new Retrofit.Builder()
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .client(sOkHttpClient)
                .baseUrl(Consts.APP_HOST)
                .build();
        private static final Retrofit INSTANCE_STRING = new Retrofit.Builder()
                .addConverterFactory(ScalarsConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .client(sOkHttpClient)
                .baseUrl(Consts.APP_HOST)
                .build();

        private static final Retrofit INSTANCE_DEFAULT = new Retrofit.Builder()
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .client(sOkHttpClient)
                .baseUrl(Consts.APP_HOST)
                .build();

        private static HttpLoggingInterceptor getInterceptor() {
            HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
            interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
            return interceptor;
        }
    }

    public static Retrofit getGsonInstance() {
        return Holder.INSTANCE_GSON;
    }

    public static Retrofit getStringInstance() {
        return Holder.INSTANCE_STRING;
    }

    public static Retrofit getDefaultInstance() {
        return Holder.INSTANCE_DEFAULT;
    }


    /**
     * 打包file 以供上传
     *
     * @param files  要上出传的文件队列
     * @param upName 对应服务端需要的name
     */
    public static Map<String, RequestBody> packingFile(File[] files, String upName) {
        if (files == null) return null;
        Map<String, RequestBody> map = new HashMap<>();
        for (File file : files) {
            // 创建 RequestBody，用于封装 请求RequestBody
            RequestBody requestFile =
                    RequestBody.create(MediaType.parse("multipart/form-data"), file);

            // MultipartBody.Part is used to send also the actual file name
//            MultipartBody.Part body =
//                    MultipartBody.Part.createFormData(upName, files[i].getName(), requestFile);

            map.put(upName, requestFile);
        }
        return map;
    }
}