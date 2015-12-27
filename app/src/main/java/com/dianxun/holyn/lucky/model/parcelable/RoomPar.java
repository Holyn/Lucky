package com.dianxun.holyn.lucky.model.parcelable;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by holyn on 2015/12/27.
 */
public class RoomPar implements Parcelable {


    /**
     * id : 10
     * name : Apple Watch Sport MLC62CH/A 42?? ????
     * password : qqq
     * foodId : 6
     * userId : 40
     * time : 2015-11-21 17:35:45
     * status : 0
     * food : {"id":"6","code":"230","pic":"1449896043.jpg","pics":"","name":"Apple Watch Sport MLC72CH/A 42毫米 ","price":"3688","price2":"0","num":"3688","stock":"0","unit":"个","demo":"<p style=\"text-align: center\">\r\n\t<img alt=\"\" height=\"5558\" src=\"/wenhua/Upload/Demo/1447143305.jpg\" width=\"750\" /><\/p>\r\n","companyId":"1","authorId":"1","groupId":"1","type":null,"typeId":"11","isonsell":"1","istop":"0","listorder":"0","sales":"0","pay":"","issend":"","isbill":"","sendtime":"","last":"0","admin":"1","hit":"0","finishTime":"0000-00-00","upTime":"0000-00-00","downTime":"0000-00-00"}
     * author : {"id":"40","name":"rrr","pic":"0","password":"96e79218965eb72c92a549dd5a330112","tel":"13670617582","address":"fd","demo":"","userName":"","companyIds":"","foodIds":"","car":"","companyId":"0","buildId":"0","buildareaId":"0","buildType":"0","buildPass":"0","buildduty":"0","buildDutyTime":"0000-00-00 00:00:00","money":"0","vote":"0","credit":"0","createtime":"2015-11-15 05:08:12","admin":"0","location":""}
     */

    private String id;
    private String name;
    private String password;
    private String foodId;
    private String userId;
    private String time;
    private String status;
    /**
     * id : 6
     * code : 230
     * pic : 1449896043.jpg
     * pics :
     * name : Apple Watch Sport MLC72CH/A 42毫米
     * price : 3688
     * price2 : 0
     * num : 3688
     * stock : 0
     * unit : 个
     * demo : <p style="text-align: center">
     <img alt="" height="5558" src="/wenhua/Upload/Demo/1447143305.jpg" width="750" /></p>

     * companyId : 1
     * authorId : 1
     * groupId : 1
     * type : null
     * typeId : 11
     * isonsell : 1
     * istop : 0
     * listorder : 0
     * sales : 0
     * pay :
     * issend :
     * isbill :
     * sendtime :
     * last : 0
     * admin : 1
     * hit : 0
     * finishTime : 0000-00-00
     * upTime : 0000-00-00
     * downTime : 0000-00-00
     */

    private FoodEntity food;
    /**
     * id : 40
     * name : rrr
     * pic : 0
     * password : 96e79218965eb72c92a549dd5a330112
     * tel : 13670617582
     * address : fd
     * demo :
     * userName :
     * companyIds :
     * foodIds :
     * car :
     * companyId : 0
     * buildId : 0
     * buildareaId : 0
     * buildType : 0
     * buildPass : 0
     * buildduty : 0
     * buildDutyTime : 0000-00-00 00:00:00
     * money : 0
     * vote : 0
     * credit : 0
     * createtime : 2015-11-15 05:08:12
     * admin : 0
     * location :
     */

    private AuthorEntity author;

    public void setId(String id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setFoodId(String foodId) {
        this.foodId = foodId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public void setFood(FoodEntity food) {
        this.food = food;
    }

    public void setAuthor(AuthorEntity author) {
        this.author = author;
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getPassword() {
        return password;
    }

    public String getFoodId() {
        return foodId;
    }

    public String getUserId() {
        return userId;
    }

    public String getTime() {
        return time;
    }

    public String getStatus() {
        return status;
    }

    public FoodEntity getFood() {
        return food;
    }

    public AuthorEntity getAuthor() {
        return author;
    }

    public static class FoodEntity implements Parcelable {
        private String id;
        private String code;
        private String pic;
        private String pics;
        private String name;
        private String price;
        private String price2;
        private String num;
        private String stock;
        private String unit;
        private String demo;
        private String companyId;
        private String authorId;
        private String groupId;
        private Object type;
        private String typeId;
        private String isonsell;
        private String istop;
        private String listorder;
        private String sales;
        private String pay;
        private String issend;
        private String isbill;
        private String sendtime;
        private String last;
        private String admin;
        private String hit;
        private String finishTime;
        private String upTime;
        private String downTime;

        public void setId(String id) {
            this.id = id;
        }

        public void setCode(String code) {
            this.code = code;
        }

        public void setPic(String pic) {
            this.pic = pic;
        }

        public void setPics(String pics) {
            this.pics = pics;
        }

        public void setName(String name) {
            this.name = name;
        }

        public void setPrice(String price) {
            this.price = price;
        }

        public void setPrice2(String price2) {
            this.price2 = price2;
        }

        public void setNum(String num) {
            this.num = num;
        }

        public void setStock(String stock) {
            this.stock = stock;
        }

        public void setUnit(String unit) {
            this.unit = unit;
        }

        public void setDemo(String demo) {
            this.demo = demo;
        }

        public void setCompanyId(String companyId) {
            this.companyId = companyId;
        }

        public void setAuthorId(String authorId) {
            this.authorId = authorId;
        }

        public void setGroupId(String groupId) {
            this.groupId = groupId;
        }

        public void setType(Object type) {
            this.type = type;
        }

        public void setTypeId(String typeId) {
            this.typeId = typeId;
        }

        public void setIsonsell(String isonsell) {
            this.isonsell = isonsell;
        }

        public void setIstop(String istop) {
            this.istop = istop;
        }

        public void setListorder(String listorder) {
            this.listorder = listorder;
        }

        public void setSales(String sales) {
            this.sales = sales;
        }

        public void setPay(String pay) {
            this.pay = pay;
        }

        public void setIssend(String issend) {
            this.issend = issend;
        }

        public void setIsbill(String isbill) {
            this.isbill = isbill;
        }

        public void setSendtime(String sendtime) {
            this.sendtime = sendtime;
        }

        public void setLast(String last) {
            this.last = last;
        }

        public void setAdmin(String admin) {
            this.admin = admin;
        }

        public void setHit(String hit) {
            this.hit = hit;
        }

        public void setFinishTime(String finishTime) {
            this.finishTime = finishTime;
        }

        public void setUpTime(String upTime) {
            this.upTime = upTime;
        }

        public void setDownTime(String downTime) {
            this.downTime = downTime;
        }

        public String getId() {
            return id;
        }

        public String getCode() {
            return code;
        }

        public String getPic() {
            return pic;
        }

        public String getPics() {
            return pics;
        }

        public String getName() {
            return name;
        }

        public String getPrice() {
            return price;
        }

        public String getPrice2() {
            return price2;
        }

        public String getNum() {
            return num;
        }

        public String getStock() {
            return stock;
        }

        public String getUnit() {
            return unit;
        }

        public String getDemo() {
            return demo;
        }

        public String getCompanyId() {
            return companyId;
        }

        public String getAuthorId() {
            return authorId;
        }

        public String getGroupId() {
            return groupId;
        }

        public Object getType() {
            return type;
        }

        public String getTypeId() {
            return typeId;
        }

        public String getIsonsell() {
            return isonsell;
        }

        public String getIstop() {
            return istop;
        }

        public String getListorder() {
            return listorder;
        }

        public String getSales() {
            return sales;
        }

        public String getPay() {
            return pay;
        }

        public String getIssend() {
            return issend;
        }

        public String getIsbill() {
            return isbill;
        }

        public String getSendtime() {
            return sendtime;
        }

        public String getLast() {
            return last;
        }

        public String getAdmin() {
            return admin;
        }

        public String getHit() {
            return hit;
        }

        public String getFinishTime() {
            return finishTime;
        }

        public String getUpTime() {
            return upTime;
        }

        public String getDownTime() {
            return downTime;
        }


        @Override
        public int describeContents() {
            return 0;
        }

        @Override
        public void writeToParcel(Parcel dest, int flags) {
            dest.writeString(this.id);
            dest.writeString(this.code);
            dest.writeString(this.pic);
            dest.writeString(this.pics);
            dest.writeString(this.name);
            dest.writeString(this.price);
            dest.writeString(this.price2);
            dest.writeString(this.num);
            dest.writeString(this.stock);
            dest.writeString(this.unit);
            dest.writeString(this.demo);
            dest.writeString(this.companyId);
            dest.writeString(this.authorId);
            dest.writeString(this.groupId);
            dest.writeParcelable((Parcelable) this.type, flags);
            dest.writeString(this.typeId);
            dest.writeString(this.isonsell);
            dest.writeString(this.istop);
            dest.writeString(this.listorder);
            dest.writeString(this.sales);
            dest.writeString(this.pay);
            dest.writeString(this.issend);
            dest.writeString(this.isbill);
            dest.writeString(this.sendtime);
            dest.writeString(this.last);
            dest.writeString(this.admin);
            dest.writeString(this.hit);
            dest.writeString(this.finishTime);
            dest.writeString(this.upTime);
            dest.writeString(this.downTime);
        }

        public FoodEntity() {
        }

        protected FoodEntity(Parcel in) {
            this.id = in.readString();
            this.code = in.readString();
            this.pic = in.readString();
            this.pics = in.readString();
            this.name = in.readString();
            this.price = in.readString();
            this.price2 = in.readString();
            this.num = in.readString();
            this.stock = in.readString();
            this.unit = in.readString();
            this.demo = in.readString();
            this.companyId = in.readString();
            this.authorId = in.readString();
            this.groupId = in.readString();
            this.type = in.readParcelable(Object.class.getClassLoader());
            this.typeId = in.readString();
            this.isonsell = in.readString();
            this.istop = in.readString();
            this.listorder = in.readString();
            this.sales = in.readString();
            this.pay = in.readString();
            this.issend = in.readString();
            this.isbill = in.readString();
            this.sendtime = in.readString();
            this.last = in.readString();
            this.admin = in.readString();
            this.hit = in.readString();
            this.finishTime = in.readString();
            this.upTime = in.readString();
            this.downTime = in.readString();
        }

        public static final Creator<FoodEntity> CREATOR = new Creator<FoodEntity>() {
            public FoodEntity createFromParcel(Parcel source) {
                return new FoodEntity(source);
            }

            public FoodEntity[] newArray(int size) {
                return new FoodEntity[size];
            }
        };
    }

    public static class AuthorEntity implements Parcelable{
        private String id;
        private String name;
        private String pic;
        private String password;
        private String tel;
        private String address;
        private String demo;
        private String userName;
        private String companyIds;
        private String foodIds;
        private String car;
        private String companyId;
        private String buildId;
        private String buildareaId;
        private String buildType;
        private String buildPass;
        private String buildduty;
        private String buildDutyTime;
        private String money;
        private String vote;
        private String credit;
        private String createtime;
        private String admin;
        private String location;

        public void setId(String id) {
            this.id = id;
        }

        public void setName(String name) {
            this.name = name;
        }

        public void setPic(String pic) {
            this.pic = pic;
        }

        public void setPassword(String password) {
            this.password = password;
        }

        public void setTel(String tel) {
            this.tel = tel;
        }

        public void setAddress(String address) {
            this.address = address;
        }

        public void setDemo(String demo) {
            this.demo = demo;
        }

        public void setUserName(String userName) {
            this.userName = userName;
        }

        public void setCompanyIds(String companyIds) {
            this.companyIds = companyIds;
        }

        public void setFoodIds(String foodIds) {
            this.foodIds = foodIds;
        }

        public void setCar(String car) {
            this.car = car;
        }

        public void setCompanyId(String companyId) {
            this.companyId = companyId;
        }

        public void setBuildId(String buildId) {
            this.buildId = buildId;
        }

        public void setBuildareaId(String buildareaId) {
            this.buildareaId = buildareaId;
        }

        public void setBuildType(String buildType) {
            this.buildType = buildType;
        }

        public void setBuildPass(String buildPass) {
            this.buildPass = buildPass;
        }

        public void setBuildduty(String buildduty) {
            this.buildduty = buildduty;
        }

        public void setBuildDutyTime(String buildDutyTime) {
            this.buildDutyTime = buildDutyTime;
        }

        public void setMoney(String money) {
            this.money = money;
        }

        public void setVote(String vote) {
            this.vote = vote;
        }

        public void setCredit(String credit) {
            this.credit = credit;
        }

        public void setCreatetime(String createtime) {
            this.createtime = createtime;
        }

        public void setAdmin(String admin) {
            this.admin = admin;
        }

        public void setLocation(String location) {
            this.location = location;
        }

        public String getId() {
            return id;
        }

        public String getName() {
            return name;
        }

        public String getPic() {
            return pic;
        }

        public String getPassword() {
            return password;
        }

        public String getTel() {
            return tel;
        }

        public String getAddress() {
            return address;
        }

        public String getDemo() {
            return demo;
        }

        public String getUserName() {
            return userName;
        }

        public String getCompanyIds() {
            return companyIds;
        }

        public String getFoodIds() {
            return foodIds;
        }

        public String getCar() {
            return car;
        }

        public String getCompanyId() {
            return companyId;
        }

        public String getBuildId() {
            return buildId;
        }

        public String getBuildareaId() {
            return buildareaId;
        }

        public String getBuildType() {
            return buildType;
        }

        public String getBuildPass() {
            return buildPass;
        }

        public String getBuildduty() {
            return buildduty;
        }

        public String getBuildDutyTime() {
            return buildDutyTime;
        }

        public String getMoney() {
            return money;
        }

        public String getVote() {
            return vote;
        }

        public String getCredit() {
            return credit;
        }

        public String getCreatetime() {
            return createtime;
        }

        public String getAdmin() {
            return admin;
        }

        public String getLocation() {
            return location;
        }


        @Override
        public int describeContents() {
            return 0;
        }

        @Override
        public void writeToParcel(Parcel dest, int flags) {
            dest.writeString(this.id);
            dest.writeString(this.name);
            dest.writeString(this.pic);
            dest.writeString(this.password);
            dest.writeString(this.tel);
            dest.writeString(this.address);
            dest.writeString(this.demo);
            dest.writeString(this.userName);
            dest.writeString(this.companyIds);
            dest.writeString(this.foodIds);
            dest.writeString(this.car);
            dest.writeString(this.companyId);
            dest.writeString(this.buildId);
            dest.writeString(this.buildareaId);
            dest.writeString(this.buildType);
            dest.writeString(this.buildPass);
            dest.writeString(this.buildduty);
            dest.writeString(this.buildDutyTime);
            dest.writeString(this.money);
            dest.writeString(this.vote);
            dest.writeString(this.credit);
            dest.writeString(this.createtime);
            dest.writeString(this.admin);
            dest.writeString(this.location);
        }

        public AuthorEntity() {
        }

        protected AuthorEntity(Parcel in) {
            this.id = in.readString();
            this.name = in.readString();
            this.pic = in.readString();
            this.password = in.readString();
            this.tel = in.readString();
            this.address = in.readString();
            this.demo = in.readString();
            this.userName = in.readString();
            this.companyIds = in.readString();
            this.foodIds = in.readString();
            this.car = in.readString();
            this.companyId = in.readString();
            this.buildId = in.readString();
            this.buildareaId = in.readString();
            this.buildType = in.readString();
            this.buildPass = in.readString();
            this.buildduty = in.readString();
            this.buildDutyTime = in.readString();
            this.money = in.readString();
            this.vote = in.readString();
            this.credit = in.readString();
            this.createtime = in.readString();
            this.admin = in.readString();
            this.location = in.readString();
        }

        public static final Creator<AuthorEntity> CREATOR = new Creator<AuthorEntity>() {
            public AuthorEntity createFromParcel(Parcel source) {
                return new AuthorEntity(source);
            }

            public AuthorEntity[] newArray(int size) {
                return new AuthorEntity[size];
            }
        };
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.id);
        dest.writeString(this.name);
        dest.writeString(this.password);
        dest.writeString(this.foodId);
        dest.writeString(this.userId);
        dest.writeString(this.time);
        dest.writeString(this.status);
        dest.writeParcelable(this.food, flags);
        dest.writeParcelable(this.author, flags);
    }

    public RoomPar() {
    }

    protected RoomPar(Parcel in) {
        this.id = in.readString();
        this.name = in.readString();
        this.password = in.readString();
        this.foodId = in.readString();
        this.userId = in.readString();
        this.time = in.readString();
        this.status = in.readString();
        this.food = in.readParcelable(FoodEntity.class.getClassLoader());
        this.author = in.readParcelable(AuthorEntity.class.getClassLoader());
    }

    public static final Creator<RoomPar> CREATOR = new Creator<RoomPar>() {
        public RoomPar createFromParcel(Parcel source) {
            return new RoomPar(source);
        }

        public RoomPar[] newArray(int size) {
            return new RoomPar[size];
        }
    };
}
