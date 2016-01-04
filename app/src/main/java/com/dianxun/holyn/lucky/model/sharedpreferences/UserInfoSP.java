package com.dianxun.holyn.lucky.model.sharedpreferences;

import android.content.Context;
import android.content.SharedPreferences;

import com.dianxun.holyn.lucky.model.Constants;
import com.dianxun.holyn.lucky.model.parcelable.UserPar;
import com.dianxun.holyn.lucky.utils.AESUtil;

/**
 * Created by holyn on 2016/1/4.
 */
public class UserInfoSP {
    public static UserInfoSP singleInstance = null;
    private SharedPreferences sp;
    private SharedPreferences.Editor editor;
    private UserPar userPar;

    public UserInfoSP(Context context) {
        sp = context.getSharedPreferences(Constants.SEED_AES, Context.MODE_PRIVATE);
        editor = sp.edit();
    }

    public static synchronized UserInfoSP getSingleInstance(Context context) {
        if (singleInstance == null) {
            singleInstance = new UserInfoSP(context);
        }
        return singleInstance;
    }

    // 用户的账号(电话号码)
    public void setTel(String tel)
    {
        editor.putString("tel", tel);
        editor.commit();
    }
    public String getTel()
    {
        return sp.getString("tel", "");
    }

    // 用户的密码
    public void setPassword(String password) {
        if (password.equals("")){
            editor.putString("password", "");
            editor.commit();
        }else{
            String encryptingCode = "";
            try {
                encryptingCode = AESUtil.encrypt(Constants.SEED_AES, password);
            } catch (Exception e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
//		System.out.println("-------->password加密: = "+encryptingCode);
            editor.putString("password", encryptingCode);
            editor.commit();
        }
    }

    public String getPassword() {
        String encryptingCode = sp.getString("password", "");
        if (encryptingCode.equalsIgnoreCase("")) {
            return "";
        }else {
            String decryptingCode = "";
            try {
                decryptingCode = AESUtil.decrypt(Constants.SEED_AES, encryptingCode);
            } catch (Exception e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
//			System.out.println("-------->password解密: = "+decryptingCode);
            return decryptingCode;
        }
    }

    public void setUserPar(UserPar userPar){
        if (userPar != null){
            setId(userPar.getId());
            setName(userPar.getName());
            setPic(userPar.getPic());
            setPassword(userPar.getPassword());
            setTel(userPar.getTel());
            setAddress(userPar.getAddress());
            setDemo(userPar.getDemo());
            setUserName(userPar.getUserName());
            setCompanyIds(userPar.getCompanyIds());
            setFoodIds(userPar.getFoodIds());
            setCar(userPar.getCar());
            setCompanyId(userPar.getCompanyId());
            setBuildId(userPar.getBuildId());
            setBuildareaId(userPar.getBuildareaId());
            setBuildType(userPar.getBuildType());
            setBuildPass(userPar.getBuildPass());
            setBuildduty(userPar.getBuildduty());
            setBuildDutyTime(userPar.getBuildDutyTime());
            setMoney(userPar.getMoney());
            setVote(userPar.getVote());
            setCredit(userPar.getCredit());
            setCreatetime(userPar.getCreatetime());
            setAdmin(userPar.getAdmin());
            setLocation(userPar.getLocation());
        }
    }

    public UserPar getUserPar(){
        UserPar userPar = new UserPar();
        userPar.setId(getId());
        userPar.setName(getName());
        userPar.setPic(getPic());
        userPar.setPassword(getPassword());
        userPar.setTel(getTel());
        userPar.setAddress(getAddress());
        userPar.setDemo(getDemo());
        userPar.setUserName(getUserName());
        userPar.setCompanyIds(getCompanyIds());
        userPar.setFoodIds(getFoodIds());
        userPar.setCar(getCar());
        userPar.setCompanyId(getCompanyId());
        userPar.setBuildId(getBuildId());
        userPar.setBuildareaId(getBuildareaId());
        userPar.setBuildType(getBuildType());
        userPar.setBuildPass(getBuildPass());
        userPar.setBuildduty(getBuildduty());
        userPar.setBuildDutyTime(getBuildDutyTime());
        userPar.setMoney(getMoney());
        userPar.setVote(getVote());
        userPar.setCredit(getCredit());
        userPar.setCreatetime(getCreatetime());
        userPar.setAdmin(getAdmin());
        userPar.setLocation(getLocation());
        return  userPar;
    }

    //用户 id
    public void setId(String id) {
        editor.putString("id", id);
        editor.commit();
    }
    public String getId() {
        return sp.getString("id", "");
    }

    //用户 name
    public void setName(String name) {
        editor.putString("name", name);
        editor.commit();
    }
    public String getName() {
        return sp.getString("name", "");
    }

    //用户 pic
    public void setPic(String pic) {
        editor.putString("pic", pic);
        editor.commit();
    }
    public String getPic() {
        return sp.getString("pic", "");
    }

    //用户 address
    public void setAddress(String address) {
        editor.putString("address", address);
        editor.commit();
    }
    public String getAddress() {
        return sp.getString("address", "");
    }

    //用户 demo
    public void setDemo(String demo) {
        editor.putString("demo", demo);
        editor.commit();
    }
    public String getDemo() {
        return sp.getString("demo", "");
    }

    //用户 userName
    public void setUserName(String userName) {
        editor.putString("userName", userName);
        editor.commit();
    }
    public String getUserName() {
        return sp.getString("userName", "");
    }

    //用户 companyIds
    public void setCompanyIds(String companyIds) {
        editor.putString("companyIds", companyIds);
        editor.commit();
    }
    public String getCompanyIds() {
        return sp.getString("companyIds", "");
    }

    //用户 foodIds
    public void setFoodIds(String foodIds) {
        editor.putString("foodIds", foodIds);
        editor.commit();
    }
    public String getFoodIds() {
        return sp.getString("foodIds", "");
    }

    //用户 car
    public void setCar(String car) {
        editor.putString("car", car);
        editor.commit();
    }
    public String getCar() {
        return sp.getString("car", "");
    }

    //用户 companyId
    public void setCompanyId(String companyId) {
        editor.putString("companyId", companyId);
        editor.commit();
    }
    public String getCompanyId() {
        return sp.getString("companyId", "");
    }

    //用户 buildId
    public void setBuildId(String buildId) {
        editor.putString("buildId", buildId);
        editor.commit();
    }
    public String getBuildId() {
        return sp.getString("buildId", "");
    }

    //用户 buildareaId
    public void setBuildareaId(String buildareaId) {
        editor.putString("buildareaId", buildareaId);
        editor.commit();
    }
    public String getBuildareaId() {
        return sp.getString("buildareaId", "");
    }

    //用户 buildType
    public void setBuildType(String buildType) {
        editor.putString("buildType", buildType);
        editor.commit();
    }
    public String getBuildType() {
        return sp.getString("buildType", "");
    }

    //用户 buildPass
    public void setBuildPass(String buildPass) {
        editor.putString("buildPass", buildPass);
        editor.commit();
    }
    public String getBuildPass() {
        return sp.getString("buildPass", "");
    }

    //用户 buildduty
    public void setBuildduty(String buildduty) {
        editor.putString("buildduty", buildduty);
        editor.commit();
    }
    public String getBuildduty() {
        return sp.getString("buildduty", "");
    }

    //用户 buildDutyTime
    public void setBuildDutyTime(String buildDutyTime) {
        editor.putString("buildDutyTime", buildDutyTime);
        editor.commit();
    }
    public String getBuildDutyTime() {
        return sp.getString("buildDutyTime", "");
    }

    //用户 money
    public void setMoney(String money) {
        editor.putString("money", money);
        editor.commit();
    }
    public String getMoney() {
        return sp.getString("money", "");
    }

    //用户 vote
    public void setVote(String vote) {
        editor.putString("vote", vote);
        editor.commit();
    }
    public String getVote() {
        return sp.getString("vote", "");
    }

    //用户 credit
    public void setCredit(String credit) {
        editor.putString("credit", credit);
        editor.commit();
    }
    public String getCredit() {
        return sp.getString("credit", "");
    }

    //用户 createtime
    public void setCreatetime(String createtime) {
        editor.putString("createtime", createtime);
        editor.commit();
    }
    public String getCreatetime() {
        return sp.getString("createtime", "");
    }

    //用户 admin
    public void setAdmin(String admin) {
        editor.putString("admin", admin);
        editor.commit();
    }
    public String getAdmin() {
        return sp.getString("admin", "");
    }

    //用户 location
    public void setLocation(String location) {
        editor.putString("location", location);
        editor.commit();
    }
    public String getLocation() {
        return sp.getString("location", "");
    }
}
