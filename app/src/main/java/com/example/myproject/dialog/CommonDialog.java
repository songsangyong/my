package com.example.myproject.dialog;

import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.text.Html;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.Button;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import com.example.myproject.R;

public class CommonDialog extends Dialog {
    public static float DIALOG_WINDOW_DIM_ACOUNT = 0.0f;
    public CommonDialog(@NonNull Context context) {
        super(context);
        init(context);
    }

    public CommonDialog(@NonNull Context context, int themeResId) {
        super(context, themeResId);
        init(context);
    }

    protected CommonDialog(@NonNull Context context, boolean cancelable, @Nullable OnCancelListener cancelListener) {
        super(context, cancelable, cancelListener);
        init(context);
    }

    private void init(Context context){
//        getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
//
//        if(DIALOG_WINDOW_DIM_ACOUNT > 0) {
//            getWindow().setDimAmount(DIALOG_WINDOW_DIM_ACOUNT);
//        }
    }

    public static class Builder{
        //  default는 같은 패키지내에서만 사용 가능
        Context context;
        String title;
        String message;
        View contentView;
        boolean messageHasHtml = false;
        String positiveButtonText;
        String neutralButtonText;
        String negativeButtonText;
        OnClickListener positiveButtonClickListener;
        OnClickListener neutralButtonClickListener;
        OnClickListener negativeButtonClickListener;
        OnDismissListener dismissListener;

        boolean cancelable = false;

        public Builder(Context context){
            this.context = context;
        }

        public Builder setTitle(String title){
            this.title = title;
            return this;
        }

        public Builder setMessage(String message){
            this.message = message;
            return this;
        }

        public Builder setPositiveButton(String positiveButton, OnClickListener listener){
            this.positiveButtonText = positiveButton;
            this.positiveButtonClickListener = listener;
            return this;
        }

        public Builder setNeutralButton(String neutralButtonText, OnClickListener listener) {
            this.neutralButtonText = neutralButtonText;
            this.neutralButtonClickListener = listener;

            return this;
        }

        public Builder setNegativeButton(String negativeButtonText, OnClickListener listener){
            this.negativeButtonText = negativeButtonText;
            this.negativeButtonClickListener = listener;
            return this;
        }

        public Builder setDismissListener(OnDismissListener listener){
            dismissListener = listener;
            return this;
        }

        public void setContentView(View contentView){
            this.contentView = contentView;
        }

        public CommonDialog create(){
            LayoutInflater inflater = (LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

            final CommonDialog dialog = new CommonDialog(context);
            dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);

            if(contentView == null){
                contentView = inflater.inflate(R.layout.layout_common_dialog, null);

                TextView tvTitle = (TextView) contentView.findViewById(R.id.tv_dialog_title);
                if(TextUtils.isEmpty(title)){
                    tvTitle.setVisibility(View.GONE);
                }else{
                    tvTitle.setVisibility(View.VISIBLE);
                    tvTitle.setText(title);
                }

                TextView tvMsg = (TextView) contentView.findViewById(R.id.tv_dialog_msg);
                if(TextUtils.isEmpty(message)){
                    tvMsg.setVisibility(View.GONE);
                }else{
                    if(messageHasHtml){
                        tvMsg.setText(Html.fromHtml(message));
                    }else{
                        tvMsg.setText(message);
                    }
                    tvMsg.setVisibility(View.VISIBLE);
                }
            }

            View leftMargin = (View)contentView.findViewById(R.id.left_margin);
            View rightMargin = (View)contentView.findViewById(R.id.right_margin);

            //  취소
            Button btnNegative = (Button)contentView.findViewById(R.id.btn_dialog_negative);
            if(TextUtils.isEmpty(negativeButtonText)){
                btnNegative.setVisibility(View.GONE);
            }else{
                btnNegative.setVisibility(View.VISIBLE);
                btnNegative.setText(negativeButtonText);

                if(negativeButtonClickListener == null){
                    btnNegative.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            negativeButtonClickListener = new OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialogInterface, int i) {
                                    dialogInterface.dismiss();
                                }
                            };

                            negativeButtonClickListener.onClick(dialog, BUTTON_NEGATIVE);
                        }
                    });
                }else{
                    btnNegative.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            negativeButtonClickListener.onClick(dialog, BUTTON_NEGATIVE);
                            dialog.dismiss();
                        }
                    });
                }
            }

            //  버튼 한개
            Button btnNeutral = (Button) contentView.findViewById(R.id.btn_dialog_neutral);

            if (TextUtils.isEmpty(neutralButtonText)) {
                //  버튼간격조정
                leftMargin.setVisibility(View.VISIBLE);
                rightMargin.setVisibility(View.VISIBLE);
                btnNeutral.setVisibility(View.GONE);
            } else {
                leftMargin.setVisibility(View.GONE);
                rightMargin.setVisibility(View.GONE);

                btnNeutral.setVisibility(View.VISIBLE);
                btnNeutral.setText(neutralButtonText);
                //btnNeutral.setTypeface(SmartApplication.getInstance().getBoldFont());

                if (neutralButtonClickListener == null) {
                    btnNeutral.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            neutralButtonClickListener = new OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialogInterface, int i) {
                                    dialogInterface.dismiss();
                                }
                            };
                            neutralButtonClickListener.onClick(dialog, DialogInterface.BUTTON_NEUTRAL);
                        }
                    });
                } else {
                    btnNeutral.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            neutralButtonClickListener.onClick(dialog, DialogInterface.BUTTON_NEUTRAL);
                            dialog.dismiss();
                        }
                    });
                }
            }

            //  확인
            Button btnPositive = (Button) contentView.findViewById(R.id.btn_dialog_positive);
            if(TextUtils.isEmpty(positiveButtonText)){
                btnPositive.setVisibility(View.GONE);
            } else {
                btnPositive.setVisibility(View.VISIBLE);
                btnPositive.setText(positiveButtonText);
                //btnPositive.setTypeface(SmartApplication.getInstance().getBoldFont());

                if (positiveButtonClickListener == null) {
                    btnPositive.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            positiveButtonClickListener = new OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialogInterface, int i) {
                                    dialogInterface.dismiss();
                                }
                            };
                            positiveButtonClickListener.onClick(dialog, DialogInterface.BUTTON_POSITIVE);
                        }
                    });
                } else {
                    btnPositive.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            positiveButtonClickListener.onClick(dialog, DialogInterface.BUTTON_POSITIVE);
                            dialog.dismiss();
                        }
                    });
                }
            }

            dialog.setContentView(contentView, new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));
            dialog.setCancelable(cancelable);
            dialog.setOnDismissListener(new OnDismissListener() {
                @Override
                public void onDismiss(DialogInterface dialogInterface) {
                    if(dismissListener != null){
                        dismissListener.onDismiss(dialogInterface);
                    }
                }
            });

            return dialog;
        }

        public void setMessageHasHtml(boolean messageHasHtml) {
            this.messageHasHtml = messageHasHtml;
        }

        public void setCancelable(boolean cancelable) {
            this.cancelable = cancelable;
        }
    }

    public static void show(Context context, View customView, String positiveBtnText, OnClickListener positiveListener, String negativeBtnText, OnClickListener negativeListener){
        Builder builder = new Builder(context);
        builder.setContentView(customView);
        builder.setPositiveButton(positiveBtnText, positiveListener);
        builder.setNegativeButton(negativeBtnText, negativeListener);
        builder.setCancelable(false);
        builder.create().show();
    }


   public static void show(Context context, String title, String message, String positiveBtnTxt, OnClickListener positiveListener, String negativeBtnTxt, OnClickListener negativeListener, String neuralBtnTxt, OnClickListener neuralListener, OnDismissListener onDismissListener, boolean cancelable, boolean hasHtml){
        Builder builder = new CommonDialog.Builder(context);
        builder.setTitle(title);
        builder.setMessage(message);
        builder.setNegativeButton(negativeBtnTxt, negativeListener);
        builder.setNeutralButton(neuralBtnTxt, neuralListener);
        builder.setPositiveButton(positiveBtnTxt, positiveListener);
        builder.setDismissListener(onDismissListener);
        builder.setCancelable(cancelable);
        builder.setMessageHasHtml(hasHtml);
        CommonDialog dialog = builder.create();
        dialog.show();
   }

   public static void show(Context context, String msg){
        show(context, null, msg, null, null, null, null, context.getString(R.string.confirm), null, null, false, false);
   }

    public static void show(Context context, String msg, OnDismissListener dismissListener){
        show(context, null, msg, null, null, null, null, context.getString(R.string.confirm), null, dismissListener, false, false);
    }

    public static void show(Context context, String msg, String neuralBtnTxt, OnClickListener neuralListener) {
        show(context, null, msg, null, null, null, null, neuralBtnTxt, neuralListener, null, false, false);
    }

    public static void show(Context context, String msg, String positiveBtnTxt, OnClickListener positiveListener, String negativeBtnTxt, OnClickListener negativeListener) {
        show(context, null, msg, positiveBtnTxt, positiveListener, negativeBtnTxt, negativeListener, null, null, null, false, false);
    }


}
