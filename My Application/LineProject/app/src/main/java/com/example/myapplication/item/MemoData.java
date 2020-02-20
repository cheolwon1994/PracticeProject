package com.example.myapplication.item;

public class MemoData {
    private String[] mData; // Data 배열
    private String mId; // item id
    private boolean mSelectable = true; // 이 아이템이 선택가능할경유 true

    public MemoData(String itemId, String[] obj) {
        mId = itemId;
        mData = obj;
    }
    public MemoData(String memoId, String memoDate, String memoTitle,String memoText,String id_photo, String uri_photo)
    {
        mId = memoId;
        mData = new String[5];
        mData[0] = memoDate;
        mData[1] = memoTitle;
        mData[2] = memoText;
        mData[3] = id_photo; //4
        mData[4] = uri_photo; // 5
    }
    public boolean isSelectable() {
        return mSelectable;
    }
    public void setSelectable(boolean selectable) {
        mSelectable = selectable;
    }
    public String getId() {
        return mId;
    }
    public void setId(String itemId) {
        mId = itemId;
    }
    public String[] getData() {
        return mData;
    }
    public String getData(int index) {
        if (mData == null || index >= mData.length) {
            return null;
        }

        return mData[index];
    }
    public void setData(String[] obj) {
        mData = obj;
    }
    public int compareTo(MemoData other) {
        if (mData != null) {
            Object[] otherData = other.getData();
            if (mData.length == otherData.length) {
                for (int i = 0; i < mData.length; i++) {
                    if (!mData[i].equals(otherData[i])) {
                        return -1;
                    }
                }
            } else {
                return -1;
            }
        } else {
            throw new IllegalArgumentException();
        }
        return 0;
    }
}
