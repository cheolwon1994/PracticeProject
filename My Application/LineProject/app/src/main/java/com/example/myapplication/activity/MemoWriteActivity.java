package com.example.myapplication.activity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.example.myapplication.R;

import java.util.ArrayList;
import java.util.List;

public class MemoWriteActivity extends AppCompatActivity {
    public static final int PICK_FOR_MAIN_IMAGE = 1000;
    public static final int PICK_FOR_SUB_IMAGE = 2000;
    public static final int NEW_RECIPE = 3000;
    public static final int MODIFY_MY_RECIPE = 4000;

    List<Uri> fileUris = new ArrayList<>();
    private EditText mWriteContent;
    private Intent intent;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_write_memo);

//        Uri dummyForCheck = null;
//        fileUris.add(dummyForCheck);
        Toolbar toolbar = findViewById(R.id.toolbar_memo_form);
        setSupportActionBar(toolbar);
        if (getSupportActionBar() != null) getSupportActionBar().setDisplayShowTitleEnabled(false);

        ImageButton mBtnBack = findViewById(R.id.arrow_back2_ImageButton);
        mBtnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        ImageButton mBtnWriteRecipe = findViewById(R.id.done_ImageButton);
        mBtnWriteRecipe.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                if(checkInput()) {
//                    category.add(mSpinnerCountry.getSelectedItem().toString());
//                    category.add(mSpinnerCookingType.getSelectedItem().toString());
//                    List<MultipartBody.Part> parts = new ArrayList<>();
//                    for (int i = 0; i < fileUris.size(); i++)
//                        parts.add(prepareFileParts("image", fileUris.get(i)));
//
//                    if (typeOfWrite == NEW_RECIPE) {
//                        cooking_descriptions.add(new Cooking_description(
//                                mEtCookingDescription.getText().toString(), "TEMP"));
//                        for (int i = 0; i < mRecipeSequenceFormDataList.size(); i++) {
//                            cooking_descriptions.add(new Cooking_description(
//                                    mRecipeSequenceFormDataList.get(i).getDescription(),
//                                    "TEMP"));
//                        }
//                    }
//                    else if(typeOfWrite == MODIFY_MY_RECIPE){
//
//                    }
//                }
            }
        });
    }
}
