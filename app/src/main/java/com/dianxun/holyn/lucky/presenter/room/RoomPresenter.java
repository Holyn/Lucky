package com.dianxun.holyn.lucky.presenter.room;

import android.os.Handler;
import android.widget.Toast;

import com.dianxun.holyn.lucky.model.http.HttpURL;
import com.dianxun.holyn.lucky.model.parcelable.FoodPar;
import com.dianxun.holyn.lucky.model.parcelable.OrderPar;
import com.dianxun.holyn.lucky.model.parcelable.RoomPar;
import com.dianxun.holyn.lucky.presenter.Presenter;
import com.dianxun.holyn.lucky.view.BaseViewInferface;
import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.google.gson.reflect.TypeToken;

import org.xutils.common.Callback;
import org.xutils.http.RequestParams;
import org.xutils.x;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

/**
 * Created by holyn on 2015/12/10.
 */

public class RoomPresenter extends Presenter {

    private BaseViewInferface baseViewInferface;
    private UniqueViewInterface uniqueViewInterface;

    @Inject
    public RoomPresenter() { }

    @Override
    public void initialize() {

    }

    @Override
    public void resume() {

    }

    @Override
    public void pause() {

    }

    public void setBaseViewInferface(BaseViewInferface baseViewInferface) {
        this.baseViewInferface = baseViewInferface;
    }

    public void setUniqueViewInterface(UniqueViewInterface uniqueViewInterface) {
        this.uniqueViewInterface = uniqueViewInterface;
    }

    public void getRoomParList(int page){

        RequestParams params = new RequestParams(HttpURL.FOOD_ROOM_LIST + String.valueOf(page));
        x.http().get(params, new Callback.CommonCallback<String>() {
            @Override
            public void onSuccess(String result) {
                List<RoomPar> roomParList = new ArrayList<RoomPar>();
                try {
                    JsonParser parser = new JsonParser();
                    JsonObject jsonObject = parser.parse(result).getAsJsonObject();
                    JsonElement listEle = jsonObject.get("list");
                    if (listEle.isJsonArray()){
                        JsonArray jsonArrayList = listEle.getAsJsonArray();
                        for (int i = 0; i < jsonArrayList.size(); i++) {
                            RoomPar roomPar = new RoomPar();
                            JsonObject roomParObj = (JsonObject) jsonArrayList.get(i);
                            roomPar.setName(roomParObj.get("name").toString());

                            JsonElement foodEle = roomParObj.get("food");
                            if (foodEle != null && foodEle.isJsonObject()){
                                RoomPar.FoodEntity foodEntity  = new Gson().fromJson(foodEle, RoomPar.FoodEntity.class);
                                roomPar.setFood(foodEntity);
                            }
                            JsonElement authorEle = roomParObj.get("author");
                            if (authorEle != null && authorEle.isJsonObject()){
                                RoomPar.AuthorEntity authorEntity  = new Gson().fromJson(authorEle, RoomPar.AuthorEntity.class);
                                roomPar.setAuthor(authorEntity);
                            }
                            roomParList.add(roomPar);
                        }
//                        java.lang.reflect.Type type = new TypeToken<List<RoomPar>>() {}.getType();
//                        roomParList = new Gson().fromJson(jsonArray.toString(), type);
                        uniqueViewInterface.successGetRoomParList(roomParList);
                    } else {

                    }
                }catch (Exception e){
                    System.out.println(e);
                    uniqueViewInterface.errorGetRoomParList();
                }
                baseViewInferface.loadingSuccess();
            }

            @Override
            public void onFinished() {
                baseViewInferface.loadingSuccess();
            }

            @Override
            public void onCancelled(CancelledException cex) {
                Toast.makeText(x.app(), "cancelled", Toast.LENGTH_LONG).show();
            }

            @Override
            public void onError(Throwable ex, boolean isOnCallback) {
                Toast.makeText(x.app(), ex.getMessage(), Toast.LENGTH_LONG).show();
                baseViewInferface.loadingError();
                uniqueViewInterface.errorGetRoomParList();
            }
        });
    }

    /**
     * View interface created to abstract the view implementation used in this presenter.
     */
    public interface UniqueViewInterface {

        void successGetRoomParList(List<RoomPar> roomParList);

        void errorGetRoomParList();
    }
}
