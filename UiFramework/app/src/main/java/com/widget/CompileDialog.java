package com.widget;

import android.app.Dialog;
import android.content.Context;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.Interface.View.ConfirmCallback;
import com.Interface.View.ReplayCallback;
import com.uidemo.R;
import com.utils.ToastUtils;

import java.util.ArrayList;
import java.util.List;



    /**
     * Created by Eren on 2017/6/27.
     * 自定义带多内容的对话框
     */
    public class CompileDialog extends Dialog implements View.OnClickListener {

        ConfirmCallback confirmCallback;
        Context context;
        private ImageView ivCancel;
        private EditText etKey;
        private EditText etContent;
        private Button butContent;

        public CompileDialog(Context context, ConfirmCallback callback) {
            super(context, R.style.ContentDialog);
            this.context = context;
            this.confirmCallback = callback;
            setContentDialog();
        }

        private void setContentDialog() {
            View mView = LayoutInflater.from(getContext()).inflate(R.layout.editdialog_content, null);
            ivCancel = (ImageView) mView.findViewById(R.id.et_cancel);
            etKey = (EditText) mView.findViewById(R.id.et_key);
            etContent = (EditText) mView.findViewById(R.id.et_content);
            butContent = (Button) mView.findViewById(R.id.but_content);

            ivCancel.setOnClickListener(this);
            butContent.setOnClickListener(this);
            super.setContentView(mView);
        }

        public CompileDialog setKeyWord(String key) {
            etKey.setText(key);
            return this;
        }

        public CompileDialog setCon(String content) {
            etContent.setText(content);
            return this;
        }

        @Override
        public void onClick(View v) {
            switch (v.getId()) {
                case R.id.et_cancel:
                    confirmCallback.negativeCallback();
                    this.cancel();
                    break;

                case R.id.but_content:
                    String keyword = etKey.getText().toString().trim();
                    String content = etContent.getText().toString().trim();
                    if (keyword.length() > 10) {
                        ToastUtils.showShort(context, "关键词不能超过10个字");
                        return;
                    }
                    if (TextUtils.isEmpty(keyword)) {
                        ToastUtils.showShort(context, "关键词不能为空");
                        return;
                    }
                    if (TextUtils.isEmpty(content)) {
                        ToastUtils.showShort(context, "回复内容不能为空");
                        return;
                    }
                    confirmCallback.positiveCallback(keyword, content);
                    this.cancel();
                    break;
            }
        }
    }