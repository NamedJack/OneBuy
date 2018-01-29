package wang.wongxd.wquicklib.base.retrofit;

import java.util.List;
import java.util.Map;

import io.reactivex.Observable;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;
import retrofit2.http.PartMap;
import retrofit2.http.Streaming;
import retrofit2.http.Url;

/**
 * @GET 表明这是get请求
 * @POST 表明这是post请求
 * @PUT 表明这是put请求
 * @DELETE 表明这是delete请求
 * @PATCH 表明这是一个patch请求，该请求是对put请求的补充，用于更新局部资源
 * @HEAD 表明这是一个head请求
 * @OPTIONS 表明这是一个option请求
 * @HTTP 通用注解, 可以替换以上所有的注解，其拥有三个属性：method，path，hasBody
 * @Headers 用于添加固定请求头，可以同时添加多个。通过该注解添加的请求头不会相互覆盖，而是共同存在
 * @Header 作为方法的参数传入，用于添加不固定值的Header，该注解会更新已有的请求头
 * @Body 多用于post请求发送非表单数据, 比如想要以post方式传递json格式数据
 * @Filed 多用于post请求中表单字段, Filed和FieldMap需要FormUrlEncoded结合使用
 * @FiledMap 和@Filed作用一致，用于不确定表单参数
 * @Part 用于表单字段, Part和PartMap与Multipart注解结合使用, 适合文件上传的情况
 * @PartMap 用于表单字段, 默认接受的类型是Map<String,REquestBody>，可用于实现多文件上传
 * <p>
 * Part标志上文的内容可以是富媒体形势，比如上传一张图片，上传一段音乐，即它多用于字节流传输。
 * 而Filed则相对简单些，通常是字符串键值对。
 * </p>
 * Part标志上文的内容可以是富媒体形势，比如上传一张图片，上传一段音乐，即它多用于字节流传输。
 * 而Filed则相对简单些，通常是字符串键值对。
 * @Path 用于url中的占位符,{占位符}和PATH只用在URL的path部分，url中的参数使用Query和QueryMap代替，保证接口定义的简洁
 * @Query 用于Get中指定参数
 * @QueryMap 和Query使用类似
 * @Url 指定请求路径
 */

public interface BaseApiService {

//    /**
//     * 使用普通的retrofit方式获取数据
//     * @return
//     */
//    @GET("ezSQL/get_user.php")
//    Call<BaseResponse<List<UserModel>>> getUsers();
//
//
//    /**
//     * 使用rx+retrofit的方式获取数据
//     */
//    @GET("ezSQL/get_user.php")
//    Observable<BaseResponse<List<UserModel>>> getUsersByRx();
//
//
//    @GET("api/cook/list")
//    <T>Observable<BaseResponse<T>> getCookList(@Query("page") int page, @Query("rows")int rows);


//    @Multipart
//    @POST("{url}")
//    Observable<ResponseBody> uploadFiles(
//            @Path(value = "url", encoded = true) String url,
//            @Part("filename") String description,
//            @PartMap() Map<String, RequestBody> maps);
//
//
//
//    //使用@Headers添加多个请求头
//    @Headers({
//            "User-Agent:android",
//            "apikey:123456789",
//    })
//    @POST()
//    Call<HttpResult<News>> post(@Url String url, @QueryMap Map<String, String> map);
//
//    @GET("mobile/active")
//    Call<HttpResult<News>> get(@Header("token") String token, @Query("id") int activeId);
//
//    @GET("active/list")
//    Call<HttpResult<News>> ActiveList();
//
//    @POST("api/news/newsList")
//    Call<HttpResult<News>> post2(@QueryMap Map<String, String> map);
//
//    /**
//     * 很多情况下，我们需要上传json格式的数据。比如当我们注册新用户的时候，因为用户注册时的数据相对较多，
//     * 并可能以后会变化，这时候，服务端可能要求我们上传json格式的数据。此时就要@Body注解来实现。
//     * 直接传入实体,它会自行转化成Json
//     *
//     * @param url
//     * @param post
//     * @return
//     */
//    @POST("api/{url}/newsList")
//    Call<HttpResult<News>> login(@Path("url") String url, @Body News post);
//
//    /**
//     * 单张图片上传
//     * retrofit 2.0的上传和以前略有不同，需要借助@Multipart注解、@Part和MultipartBody实现。
//     *
//     * @param url
//     * @param file
//     * @return
//     */
//    @Multipart
//    @POST("{url}")
//    Call<HttpResult<News>> upload(@Path("url") String url, @Part MultipartBody.Part file);
//
//    /**
//     * 多张图片上传
//     *
//     * @param map
//     * @return
//     */
//    @Multipart
//    @POST("upload/upload")
//    Call<HttpResult<News>> upload(@PartMap Map<String, MultipartBody.Part> map);
//
//    /**
//     * 图文混传
//     *
//     * @param post
//     * @param map
//     * @return
//     */
//    @Multipart
//    @POST("")
//    Call<HttpResult<News>> register(@Body News post, @PartMap Map<String, MultipartBody.Part> map);
//
//    /**
//     * 文件下载
//     *
//     * @param fileUrl
//     * @return
//     */
//    @Streaming
//    @GET
//    Call<HttpResult<News>> downloadPicture(@Url String fileUrl);
//
//    /**
//     * 这里需要注意的是如果下载的文件较大，比如在10m以上，那么强烈建议你使用@Streaming进行注解，否则将会出现IO异常.
//     *
//     * @param fileUrl
//     * @return
//     */
//    @Streaming
//    @GET
//    Observable<HttpResult<News>> downloadPicture2(@Url String fileUrl);
//
//    @POST()
//    @FormUrlEncoded
//    Observable<HttpResult<News>> executePost(@FieldMap Map<String, Object> maps);
//


//    链接：http://www.jianshu.com/p/73216939806a


    /**
     * 图文混传
     * <p>
     * <p>
     * // create a map of data to pass along
     * RequestBody tokenBody =
     * RequestBody.create(MediaType.parse("multipart/form-data"), App.token);
     * <p>
     * <p>
     * // 创建 RequestBody，用于封装构建RequestBody
     * RequestBody requestFile =
     * RequestBody.create(MediaType.parse("multipart/form-data"), file);
     * <p>
     * // MultipartBody.Part  和后端约定好Key，这里的partName是用 headImg
     * MultipartBody.Part body =
     * MultipartBody.Part.createFormData("file", file.getName(), requestFile);
     * <p>
     * <p>
     * Map<String, RequestBody> map = new HashMap<>();
     * map.put("token", tokenBody);
     * <p>
     * List<MultipartBody.Part> files = new ArrayList<>();
     * files.add(body);
     *
     * @param url
     * @param map   文字
     * @param files 文件数组
     * @return
     */
    @POST()
    @Multipart
    Observable<String> updateUserHeader(@Url() String url,
                                        @PartMap() Map<String, RequestBody> map,
                                        @Part() List<MultipartBody.Part> files);

    /**
     * 图文混传
     * <p>
     * <p>
     * 构建body
     * RequestBody requestBody = new MultipartBody.Builder().setType(MultipartBody.FORM)
     * .addFormDataPart("name", name)
     * .addFormDataPart("psd", psd)
     * .addFormDataPart("file", file.getName(), RequestBody.create(MediaType.parse("image/*"), file))
     * .build();
     *
     * @param url
     * @param Body
     * @return
     */

    @POST()
    Observable<String> upLoad(@Url() String url, @Body RequestBody Body);


    /**
     * 上传json
     * <p>
     * upLoadJson 也可以具体指明Content-Type 为 “application/json”格式的
     * <p>
     * 具体组装我们的RequestBody则可以这样:
     * <p>
     * RequestBody body=
     * RequestBody.create(okhttp3.MediaType.parse("application/json; charset=utf-8"), jsonString);
     *
     * @param url
     * @param jsonBody
     * @return
     */
    @POST()
    Observable<String> uploadjson(
            @Url() String url,
            @Body RequestBody jsonBody);


    /**
     * 下载文件
     *
     * @param fileUrl
     * @return
     */
    @Streaming
    @GET
    Observable<ResponseBody> downloadFile(@Url String fileUrl);


    /**
     * 简单的get请求
     *
     * @param url
     * @return
     */
//    @GET
//    Observable<String> baseGet(@Url String url);


    /**
     * 简单的get请求
     *
     * @param url
     * @return
     */
    @GET
    Call<String> baseGet(@Url String url);


}