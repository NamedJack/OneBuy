package com.ejar.onebuy.util;

import android.app.Dialog;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;

import com.ejar.onebuy.R;

/**
 * Created by wongxd on 2017/8/10.
 */

public class DialogUtil {

    /**
     * @param aty
     * @param title
     * @param content
     * @param left
     * @param right
     * @param listener
     */
    public static void showDialog(AppCompatActivity aty, String title, String content, String left, String right, final DialogListener listener) {
        final AlertDialog dialog = new AlertDialog.Builder(aty).create();
        View v = View.inflate(aty, R.layout.dialog_layout, null);
        TextView tvTiltle = (TextView) v.findViewById(R.id.tv_title);
        TextView tvTContent = (TextView) v.findViewById(R.id.tv_content);
        TextView tvLeft = (TextView) v.findViewById(R.id.tv_left);
        TextView tvRight = (TextView) v.findViewById(R.id.tv_right);

        tvTiltle.setText(title);
        tvTContent.setText(content);
        tvLeft.setText(left);
        tvRight.setText(right);

        if (listener != null) {
            tvLeft.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    listener.left(dialog);
                    if (dialog != null && dialog.isShowing())
                        dialog.dismiss();
                }
            });


            tvRight.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    listener.right(dialog);
                    if (dialog != null && dialog.isShowing())
                        dialog.dismiss();
                }
            });
        }


        dialog.setView(v);

        dialog.show();

    }

    public interface DialogListener {
        public void left(Dialog dialog);

        public void right(Dialog dialog);
    }
}
