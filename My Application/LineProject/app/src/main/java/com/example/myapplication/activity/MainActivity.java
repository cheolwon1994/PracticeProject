package com.example.myapplication.activity;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.res.Resources;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.os.Environment;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myapplication.BasicInfo;
import com.example.myapplication.MemoDatabase;
import com.example.myapplication.MemoWriteActivity;
import com.example.myapplication.R;
import com.example.myapplication.adapter.viewholder.MemoAdapter;
import com.example.myapplication.item.MemoData;

import java.io.File;

public class MainActivity extends AppCompatActivity {
    public static final String TAG = "MultiMemoActivity";
    ListView mMemoListView;// 메모 리스트뷰
    int mMemoCount = 0; // 메모갯수
    public static MemoDatabase mDatabase = null; // 데이터베이스 인스턴스

    private final int REQUEST_WRITE = 1000;
    private MemoAdapter mMemoListAdapter; // 메모 리스트 어댑터;
    private ImageButton mBtnWrite;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //SD card를 통해 작업
        // SD Card checking
        if (!Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED)) {
            Toast.makeText(this, "SD 카드가 없습니다. SD 카드를 넣은 후 다시 실행하십시오.", Toast.LENGTH_LONG).show();
            return;
        } else {
            String externalPath = Environment.getExternalStorageDirectory().getAbsolutePath();
            if (!BasicInfo.ExternalChecked && externalPath != null) {
                BasicInfo.ExternalPath = externalPath + File.separator;
                Log.d(TAG, "ExternalPath : " + BasicInfo.ExternalPath);

                BasicInfo.FOLDER_PHOTO = BasicInfo.ExternalPath + BasicInfo.FOLDER_PHOTO;
                BasicInfo.DATABASE_NAME = BasicInfo.ExternalPath + BasicInfo.DATABASE_NAME;

                BasicInfo.ExternalChecked = true;
            }
        }

        // 메모 리스트
        mMemoListView = (ListView)findViewById(R.id.listMemoView);
        mMemoListAdapter = new MemoAdapter(this);
        mMemoListView.setAdapter(mMemoListAdapter);
        mMemoListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView<?> arg0, View arg1, int position, long arg3) {
                viewMemo(position);
            }
        });

        mBtnWrite = findViewById(R.id.write_Button);
        mBtnWrite.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, MemoWriteActivity.class);
//                intent.putExtra("u_id", userId);
//                intent.putExtra("u_name",userName);
//                startActivityForResult(intent,REQUEST_TEST);
                startActivityForResult(intent,REQUEST_WRITE);
            }
        });
        checkDangerousPermissions();
    }

    private void checkDangerousPermissions() {
        String[] permissions = {
                Manifest.permission.READ_EXTERNAL_STORAGE,
                Manifest.permission.WRITE_EXTERNAL_STORAGE,
                Manifest.permission.CAMERA
        };

        int permissionCheck = PackageManager.PERMISSION_GRANTED;
        for (int i = 0; i < permissions.length; i++) {
            permissionCheck = ContextCompat.checkSelfPermission(this, permissions[i]);
            if (permissionCheck == PackageManager.PERMISSION_DENIED) {
                break;
            }
        }

        if (permissionCheck == PackageManager.PERMISSION_GRANTED) {
            Toast.makeText(this, "권한 있음", Toast.LENGTH_LONG).show();
        } else {
            Toast.makeText(this, "권한 없음", Toast.LENGTH_LONG).show();

            if (ActivityCompat.shouldShowRequestPermissionRationale(this, permissions[0])) {
                Toast.makeText(this, "권한 설명 필요함.", Toast.LENGTH_LONG).show();
            } else {
                ActivityCompat.requestPermissions(this, permissions, 1);
            }
        }
    }

    protected void onStart() {
        // 데이터베이스 열기
        openDatabase();
        // 메모 데이터 로딩
        loadMemoListData();
        super.onStart();
    }
    public void openDatabase() {
        Log.i("abcdabcd","before open");
        // open database
        if (mDatabase != null) {
            mDatabase.close();
            mDatabase = null;
        }
        mDatabase = MemoDatabase.getInstance(this);
        boolean isOpen = mDatabase.open();
        if (isOpen) {
            Log.d(TAG, "Memo database is open.");
        } else {
            Log.d(TAG, "Memo database is not open.");
        }
    }

    /**
     * 메모 리스트 데이터 로딩(리스트 뷰에 기록된 메모정보를 뿌려줌)
     */
    public int loadMemoListData() {
        String SQL = "select _id, INPUT_DATE,TITLE_TEXT, CONTENT_TEXT, ID_PHOTO from MEMO order by INPUT_DATE desc";
        int recordCount = -1;
        if (MainActivity.mDatabase != null) {
            Log.d("abcdabcd main", MainActivity.mDatabase.toString());
            Cursor outCursor = MainActivity.mDatabase.rawQuery(SQL);
            recordCount = outCursor.getCount();
            Log.d("abcdabcd main", recordCount+"");

            Log.d(TAG, "cursor count : " + recordCount + "\n");
            mMemoListAdapter.clear();
            Resources res = getResources();
            for (int i = 0; i < recordCount; i++) {
                outCursor.moveToNext();
                String memoId = outCursor.getString(0);
                String dateStr = outCursor.getString(1);
                if (dateStr.length() > 10) {
                    dateStr = dateStr.substring(0, 10);
                }
                String memoTit = outCursor.getString(2);
                String memoStr = outCursor.getString(3);
                String photoId = outCursor.getString(4);
                String photoUriStr = getPhotoUriStr(photoId);
                mMemoListAdapter.addItem(new MemoData(memoId, dateStr, memoTit,memoStr, photoId, photoUriStr));
            }
            outCursor.close();
            mMemoListAdapter.notifyDataSetChanged();
        }
        return recordCount;
    }

    /**
     * 사진 데이터 URI 가져오기
     */
    public String getPhotoUriStr(String id_photo) {
        String photoUriStr = null;
        if (id_photo != null && !id_photo.equals("-1")) {
            String SQL = "select URI from " + MemoDatabase.TABLE_PHOTO + " where _ID = " + id_photo + "";
            Cursor photoCursor = MainActivity.mDatabase.rawQuery(SQL);
            if (photoCursor.moveToNext()) {
                photoUriStr = photoCursor.getString(0);
            }
            photoCursor.close();
        } else if(id_photo == null || id_photo.equals("-1")) {
            photoUriStr = "";
        }
        return photoUriStr;
    }

    private void viewMemo(int position) {
        MemoData item = (MemoData)mMemoListAdapter.getItem(position);
        // 메모 보기 액티비티 띄우기
        Intent intent = new Intent(getApplicationContext(), MemoWriteActivity.class);
        intent.putExtra(BasicInfo.KEY_MEMO_MODE, BasicInfo.MODE_VIEW);
        intent.putExtra(BasicInfo.KEY_MEMO_ID, item.getId());
        intent.putExtra(BasicInfo.KEY_MEMO_DATE, item.getData(0));
        intent.putExtra(BasicInfo.KEY_MEMO_TITLE, item.getData(1));
        intent.putExtra(BasicInfo.KEY_MEMO_TEXT, item.getData(2));
        intent.putExtra(BasicInfo.KEY_ID_PHOTO, item.getData(3));
        intent.putExtra(BasicInfo.KEY_URI_PHOTO, item.getData(4));
        startActivityForResult(intent, BasicInfo.REQ_VIEW_ACTIVITY);
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == REQUEST_WRITE) {
            if(resultCode==RESULT_OK){
                loadMemoListData();
            }
        }
    }
}
