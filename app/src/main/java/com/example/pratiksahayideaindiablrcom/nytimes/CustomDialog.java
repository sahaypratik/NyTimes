package com.example.pratiksahayideaindiablrcom.nytimes;

import android.app.Dialog;
import android.content.Context;
import android.view.View;

public class CustomDialog extends Dialog {
    Context mContext;
    View view;



    public CustomDialog(Context context) {
        super(context);

        this.mContext = context;
        requestWindowFeature(1);
        setCancelable(true);
        setCanceledOnTouchOutside(true);
        setContentView(R.layout.dialogcustom);

        view = getWindow().getDecorView();
        /*TextView loading = (TextView) view.findViewById(R.id.loader);*/
        //loading.setTypeface(faces);
        view.setBackgroundColor(mContext.getResources().getColor(android.R.color.transparent));

//

    }
}
