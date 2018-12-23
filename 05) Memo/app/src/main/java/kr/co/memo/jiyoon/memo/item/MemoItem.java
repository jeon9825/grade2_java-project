package kr.co.memo.jiyoon.memo.item;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class MemoItem {
    public String category;
    public String memo;
    public String date;

    public MemoItem(String category, String memo) {
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss",Locale.KOREA);
        Date date = new Date();
        this.category=category;
        this.memo=memo;
        this.date=formatter.format(date);
    }

    public String getCategory() {
        return category;
    }

    public String getMemo() {
        return memo;
    }

    public String getDate() {
        return date;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public void setMemo(String memo) {
        this.memo = memo;
    }

    public void setDate(String date) {
        this.date = date;
    }
}
