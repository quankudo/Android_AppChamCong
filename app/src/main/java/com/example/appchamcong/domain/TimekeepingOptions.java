package com.example.appchamcong.domain;

import com.example.appchamcong.R;

import java.util.ArrayList;
import java.util.List;

public class TimekeepingOptions {
    int img;
    private String name;
    private String describe;

    public TimekeepingOptions(int img, String name, String describe) {
        this.img = img;
        this.name = name;
        this.describe = describe;
    }

    public TimekeepingOptions() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescribe() {
        return describe;
    }

    public void setDescribe(String describe) {
        this.describe = describe;
    }

    public int getImg() {
        return img;
    }

    public void setImg(int img) {
        this.img = img;
    }
    public List<TimekeepingOptions> addData(){
        List<TimekeepingOptions> list = new ArrayList<>();
        TimekeepingOptions to1 = new TimekeepingOptions(R.drawable.user_timepicking_form, "Chấm cho cá nhân","Bạn sẽ tự chấm công cho riêng cá nhân bạn");
        list.add(to1);

        TimekeepingOptions to2 = new TimekeepingOptions(R.drawable.people, "Chấm cho nhân viên","Với tư cách là chủ, bạn sẽ tự chấm công cho nhân viên của mình");
        list.add(to2);

        TimekeepingOptions to3 = new TimekeepingOptions(R.drawable.connect, "Nhân viên tự chấm","Bạn sẽ theo dõi toàn bộ quá trình chấm công của tất cả nhân viên");
        list.add(to3);

        TimekeepingOptions to4 = new TimekeepingOptions(R.drawable.scan, "Tham gia nhóm bằng QR code","Bạn sẽ tham gia vào một công việc được tạo từ người chủ của mình");
        list.add(to4);
        return list;
    }
}
