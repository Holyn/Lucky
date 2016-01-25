package com.dianxun.holyn.lucky.presenter.base;

import android.graphics.Bitmap;

import com.dianxun.holyn.lucky.model.http.HttpURL;
import com.dianxun.holyn.lucky.model.parcelable.BookPar;
import com.dianxun.holyn.lucky.model.parcelable.ClassifyFoodPar;
import com.dianxun.holyn.lucky.model.parcelable.CompanyTypePar;
import com.dianxun.holyn.lucky.model.parcelable.UserPar;
import com.dianxun.holyn.lucky.utils.ImageTransFormatUtil;
import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.google.gson.reflect.TypeToken;
import com.holyn.selectlocalimage.utils.PictureCompressUtil;

import org.xutils.common.Callback;
import org.xutils.http.RequestParams;
import org.xutils.x;

import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.lang.reflect.Type;
import java.net.URLEncoder;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.inject.Inject;

/**
 * Created by holyn on 2016/1/7.
 */
public class BasePresenter implements BasePresenterInterface{

    @Inject
    public BasePresenter() { }

    public void setOnGetCompanyTypeParListListener(OnBaseGetNetDataListener onBaseGetNetDataListener){
        String url = HttpURL.COMPANY_CLASSIFY_TYPE_ID_LIST;
        setOnBaseGetNetDataListener(url, new TypeToken<List<CompanyTypePar>>() {}.getType(), onBaseGetNetDataListener);
    }

    public void setOnGetClassifyFoodParListListener(String typeId, int page, OnBaseGetNetDataListener onBaseGetNetDataListener){
        String url = HttpURL.FOOD_CLASSIFY__LIST+String.valueOf(page)+HttpURL.ONE_SPRIT+HttpURL.PARAM_TYPE_ID+typeId;
        setOnBaseGetNetDataListener(url, new TypeToken<List<ClassifyFoodPar>>() {}.getType(), onBaseGetNetDataListener);
    }

    public void setOnUploadHeaderListener(String imagePath, OnBaseUploadListener onBaseUploadListener){
        String url = HttpURL.USER_UPLOAD_IMG;
        RequestParams requestParams = new RequestParams(url);
        requestParams.setMultipart(true);

        // 压缩
        Bitmap bitmap = PictureCompressUtil.getInstance().compress(imagePath, 400, 400, 800);
        InputStream inputStream = ImageTransFormatUtil.getInstance().Bitmap2InputStream(bitmap);
        // 设置文件名
        String timeStr = (new SimpleDateFormat("hhmmss")).format(new Date());
        String imgName = timeStr + ".jpg";

        requestParams.addBodyParameter("imgfile", inputStream, "image/jpeg",imgName);
//        requestParams.addBodyParameter("imgfile", new File(imagePath));

        setOnBaseUploadListener(requestParams, onBaseUploadListener);
    }

    public void setOnUpdateUserInfoListener(UserPar userPar, OnBaseGetNetDataListener onBaseGetNetDataListener){
        try {
            String address = URLEncoder.encode(userPar.getAddress(), "UTF-8");

            String url = HttpURL.USER_UPDATE+HttpURL.PARAM_ID+userPar.getId()
                    +HttpURL.ONE_SPRIT+HttpURL.PARAM_NAMW+userPar.getName()
                    +HttpURL.ONE_SPRIT+HttpURL.PARAM_TEL+userPar.getTel()
                    +HttpURL.ONE_SPRIT+HttpURL.PARAM_ADDRESS+address
                    +HttpURL.ONE_SPRIT+HttpURL.PARAM_PIC+userPar.getPic();
            setOnBaseGetNetDataListener(url, UserPar.class, onBaseGetNetDataListener);

        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
    }

    public void setOnGetBookParListListener(String userId, int page, OnBaseGetNetDataListener onBaseGetNetDataListener){
        String url = HttpURL.BOOK_LIST
                +HttpURL.PARAM_USER_ID+userId
                +HttpURL.ONE_SPRIT+HttpURL.PARAM_PAGE+String.valueOf(page);
        setOnBaseGetNetDataListener(url, new TypeToken<List<BookPar>>() {}.getType(), onBaseGetNetDataListener);
    }

    @Override
    public void setOnBaseGetNetDataListener(String url,final Class cl, final OnBaseGetNetDataListener onBaseGetNetDataListener) {
        onBaseGetNetDataListener.onBegin();
        RequestParams params = new RequestParams(url);
        x.http().get(params, new Callback.CommonCallback<String>() {
            @Override
            public void onSuccess(String result) {
                try {
                    JsonParser parser = new JsonParser();
                    JsonObject jsonObject = parser.parse(result).getAsJsonObject();
                    JsonElement msgtEle = jsonObject.get("msg");
                    if (msgtEle == null) {
                        onBaseGetNetDataListener.onError("出现异常");
                    } else {
                        if (msgtEle.isJsonObject()) {
                            onBaseGetNetDataListener.onSuccess(new Gson().fromJson(msgtEle, cl));
                        } else if (msgtEle.isJsonPrimitive()){
                            String msg = msgtEle.getAsString();
                            onBaseGetNetDataListener.onError(msg);
                        }
                    }
                } catch (Exception e) {
                    onBaseGetNetDataListener.onError("出现异常");
                    System.out.println(e);
                }

            }

            @Override
            public void onError(Throwable ex, boolean isOnCallback) {
                onBaseGetNetDataListener.onError("出现异常");
            }

            @Override
            public void onCancelled(CancelledException cex) {

            }

            @Override
            public void onFinished() {

            }
        });
    }

    @Override
    public void setOnBaseGetNetDataListener(String url, final Type type, final OnBaseGetNetDataListener onBaseGetNetDataListener) {
        onBaseGetNetDataListener.onBegin();
        RequestParams params = new RequestParams(url);
        x.http().get(params, new Callback.CommonCallback<String>() {
            @Override
            public void onSuccess(String result) {
                try {
                    JsonParser parser = new JsonParser();
                    JsonObject jsonObject = parser.parse(result).getAsJsonObject();
                    JsonElement listEle = jsonObject.get("list");
                    if (listEle.isJsonArray()){
                        JsonArray jsonArray = listEle.getAsJsonArray();
                        onBaseGetNetDataListener.onSuccess(new Gson().fromJson(jsonArray.toString(), type));
                    } else {
                        onBaseGetNetDataListener.onError("出现异常");
                    }
                } catch (Exception e) {
                    onBaseGetNetDataListener.onError("出现异常");
                    System.out.println(e);
                }

            }

            @Override
            public void onError(Throwable ex, boolean isOnCallback) {
                onBaseGetNetDataListener.onError("出现异常");
            }

            @Override
            public void onCancelled(CancelledException cex) {

            }

            @Override
            public void onFinished() {

            }
        });
    }

    @Override
    public void setOnBaseUploadListener(RequestParams requestParams, final OnBaseUploadListener onBaseUploadListener) {
        x.http().post(requestParams, new Callback.ProgressCallback<String>() {
            @Override
            public void onWaiting() {
                System.out.println("====> onWaiting....");
            }

            @Override
            public void onStarted() {
                System.out.println("====> onStarted....");
                onBaseUploadListener.onBegin();
            }

            @Override
            public void onLoading(long total, long current, boolean isDownloading) {
                System.out.println("====> onLoading....total="+total+";current="+current+";"+isDownloading);
                onBaseUploadListener.onLoading(total, current, isDownloading);
            }

            @Override
            public void onSuccess(String result) {
                System.out.println("====> onSuccess....result = "+result);
                try {
                    JsonParser parser = new JsonParser();
                    JsonObject jsonObject = parser.parse(result).getAsJsonObject();
                    JsonElement msgtEle = jsonObject.get("msg");
                    if (msgtEle == null) {
                        onBaseUploadListener.onError("出现异常");
                    } else {
                        if (msgtEle.isJsonPrimitive()){
                            String msg = msgtEle.getAsString();
                            if (msg.equals(HttpURL.MSG_ERROR)){
                                onBaseUploadListener.onError("出现异常");
                            }else {
                                onBaseUploadListener.onSuccess(msg);
                            }
                        }
                    }
                } catch (Exception e) {
                    onBaseUploadListener.onError("出现异常");
                    System.out.println(e);
                }
            }

            @Override
            public void onError(Throwable ex, boolean isOnCallback) {
                System.out.println("====> onError....ex=" + ex);
                onBaseUploadListener.onError("出现异常");
            }

            @Override
            public void onCancelled(CancelledException cex) {
                System.out.println("====> onCancelled....");
            }

            @Override
            public void onFinished() {
                System.out.println("====> onFinished....");
            }
        });
    }
}
