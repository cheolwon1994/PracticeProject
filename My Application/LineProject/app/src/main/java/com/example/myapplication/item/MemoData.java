package com.example.myapplication.item;

public class MemoData {
    private String memo_Id;
    private String title;
    private String content;
    private String Date;
    private String imagePath;

    public MemoData(String memo_Id, String title, String content, String date, String imagePath) {
        this.memo_Id = memo_Id;
        this.title = title;
        this.content = content;
        Date = date;
        this.imagePath = imagePath;
    }

    public String getTitle() {
        return title;
    }
    public void setTitle(String title) {
        this.title = title;
    }
    public String getContent() {
        return content;
    }
    public void setContent(String content) {
        this.content = content;
    }
    public String getDate() {
        return Date;
    }
    public void setDate(String date) {
        Date = date;
    }
    public String getImagePath() {
        return imagePath;
    }
    public void setImagePath(String imagePath) {
        this.imagePath = imagePath;
    }
    public String getMemo_Id() {
        return memo_Id;
    }
    public void setMemo_Id(String memo_Id) {
        this.memo_Id = memo_Id;
    }
}
