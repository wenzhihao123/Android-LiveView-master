package cm.wzh.live.view;

import android.app.Dialog;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.text.TextUtils;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import cm.wzh.live.R;

/**
 * author：Administrator on 2016/12/19 14:02
 * description:文件说明
 * version:版本
 */
public class FragmentDialog extends DialogFragment {
    /**
     * 显示的图片
     */
    private ImageView imageIv ;

    /**
     * 显示的标题
     */
    private TextView titleTv ;

    /**
     * 显示的消息
     */
    private TextView messageTv ;

    /**
     * 确认和取消按钮
     */
    private Button negtiveBn ,positiveBn;

    /**
     * 按钮之间的分割线
     */
    private View columnLineView ;

    /**
     * 都是内容数据
     */
    private String message;
    private String title;
    private String positive,negtive ;
    private int imageResId = -1 ;

    /**
     * 底部是否只有一个按钮
     */
    private boolean isSingle = false;

    private Dialog dialog ;
    private void initView(View rootView) {
        Bundle args=getArguments();
        if(args==null)
            return;
        title=args.getString("title");
        positive = args.getString("positive");
        negtive = args.getString("negtive");
        message=args.getString("message");
        imageResId = args.getInt("imageResId");
        isSingle = args.getBoolean("isSingle");

        negtiveBn = (Button) rootView.findViewById(R.id.negtive);
        positiveBn = (Button) rootView.findViewById(R.id.positive);
        titleTv = (TextView) rootView.findViewById(R.id.title);
        messageTv = (TextView) rootView.findViewById(R.id.message);
        imageIv = (ImageView) rootView.findViewById(R.id.image);
        columnLineView = rootView.findViewById(R.id.column_line);

    }
    public static final FragmentDialog newInstance(String title, String message, String positive, String negtive, int imageResId ,boolean isSingle ,OnClickBottomListener onClickBottomListener){
        FragmentDialog fragment = new FragmentDialog();
        Bundle bundle = new Bundle();
        bundle.putString("title", title);
        bundle.putString("message", message);
        bundle.putString("positive", positive);
        bundle.putString("negtive", negtive);
        bundle.putInt("imageResId", imageResId);
        bundle.putBoolean("isSingle",isSingle);
        fragment.onClickBottomListener = onClickBottomListener ;
        fragment.setArguments(bundle);
        return fragment;
    }
    /**
     * 初始化界面的确定和取消监听器
     */
    private void initEvent() {
        //设置确定按钮被点击后，向外界提供监听
        positiveBn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dismiss();
                if ( onClickBottomListener!= null) {
                    onClickBottomListener.onPositiveClick();
                }
            }
        });
        //设置取消按钮被点击后，向外界提供监听
        negtiveBn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dismiss();
                if ( onClickBottomListener!= null) {
                    onClickBottomListener.onNegtiveClick();
                }
            }
        });
    }

    /**
     * 初始化界面控件的显示数据
     */
    private void refreshView() {
        //如果用户自定了title和message
        if (!TextUtils.isEmpty(title)) {
            titleTv.setText(title);
            titleTv.setVisibility(View.VISIBLE);
        }else {
            titleTv.setVisibility(View.GONE);
        }
        if (!TextUtils.isEmpty(message)) {
            messageTv.setText(message);
        }
        //如果设置按钮的文字
        if (!TextUtils.isEmpty(positive)) {
            positiveBn.setText(positive);
        }else {
            positiveBn.setText("确定");
        }
        if (!TextUtils.isEmpty(negtive)) {
            negtiveBn.setText(negtive);
        }else {
            negtiveBn.setText("取消");
        }

        if (imageResId!=-1){
            imageIv.setImageResource(imageResId);
            imageIv.setVisibility(View.VISIBLE);
        }else {
            imageIv.setVisibility(View.GONE);
        }
        /**
         * 只显示一个按钮的时候隐藏取消按钮，回掉只执行确定的事件
         */
        if (isSingle){
            columnLineView.setVisibility(View.GONE);
            negtiveBn.setVisibility(View.GONE);
        }else {
            negtiveBn.setVisibility(View.VISIBLE);
            columnLineView.setVisibility(View.VISIBLE);
        }
    }
    /**
     * 设置确定取消按钮的回调
     */
    public OnClickBottomListener onClickBottomListener;
    public FragmentDialog setOnClickBottomListener(OnClickBottomListener onClickBottomListener) {
        this.onClickBottomListener = onClickBottomListener;
        return this;
    }
    public interface OnClickBottomListener{
        /**
         * 点击确定按钮事件
         */
        public void onPositiveClick();
        /**
         * 点击取消按钮事件
         */
        public void onNegtiveClick();
    }
    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        View rootView = LayoutInflater.from(getActivity()).inflate(R.layout.common_dialog_layout, null, false);
        // 使用不带Theme的构造器, 获得的dialog边框距离屏幕仍有几毫米的缝隙。
        initDialogStyle(rootView);
        initView(rootView);
        initEvent();
        return dialog;

    }

    private void initDialogStyle(View view) {
        dialog = new Dialog(getActivity(), R.style.CustomDialog);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE); // 设置Content前设定
        dialog.setContentView(view);
        dialog.setCanceledOnTouchOutside(true); // 外部点击取消
        // 设置宽度为屏宽, 靠近屏幕底部。
        Window window = dialog.getWindow();
        WindowManager.LayoutParams lp = window.getAttributes();
        lp.gravity = Gravity.CENTER; // 紧贴底部
        window.setAttributes(lp);
    }

    @Override
    public void onResume() {
        super.onResume();
        refreshView();
    }

}
