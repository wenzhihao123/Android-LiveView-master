package cm.wzh.live.ui;

import android.content.Context;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.VideoView;

import java.util.ArrayList;
import java.util.Random;
import java.util.Timer;

import cm.wzh.live.R;
import cm.wzh.live.adapter.MemberAdapter;
import cm.wzh.live.adapter.MessageAdapter;
import cm.wzh.live.entity.Gift;
import cm.wzh.live.entity.Member;
import cm.wzh.live.entity.Message;
import cm.wzh.live.utils.CharUtils;
import cm.wzh.live.view.FragmentDialog;
import cm.wzh.live.view.FragmentGiftDialog;
import cm.wzh.live.view.GiftItemView;
import cm.wzh.live.view.HorizontialListView;
import cm.wzh.live.view.MyVideoView;
import tyrantgit.widget.HeartLayout;

/**
 * author：Administrator on 2016/12/26 09:35
 * description:文件说明
 * version:版本
 */
public class ChatFragment extends Fragment  implements View.OnClickListener, View.OnLayoutChangeListener {
    private HorizontialListView listview ;
    private ListView messageList ;
    private GiftItemView giftView ;
    private MemberAdapter mAdapter ;
    private MessageAdapter messageAdapter ;
    private ArrayList<Member> members ;
    private ArrayList<Message> messages ;
    private ArrayList<Gift> gifts ;
    private HeartLayout heartLayout ;
    private Random mRandom ;
    private Timer mTimer = new Timer();
    private View sendView,menuView ,topView;
    private EditText sendEditText ;
    //屏幕高度
    private int screenHeight = 0;
    //软件盘弹起后所占高度阀值
    private int keyHeight = 0;
    private View rootView ;


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        View view = LayoutInflater.from(getActivity()).inflate(R.layout.fragment_chat,null,false);
        initView(view);
        initData();
        return view;
    }

    private void initView(View view) {
        mRandom = new Random();
        listview = (HorizontialListView) view.findViewById(R.id.list);
        mAdapter = new MemberAdapter(getActivity());
        listview.setAdapter(mAdapter);
        listview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                 showDialog(mAdapter.datas.get(i));
            }
        });
        messageList = (ListView) view.findViewById(R.id.list_message);
        messageAdapter = new MessageAdapter(getActivity());
        messageList.setAdapter(messageAdapter);
        giftView = (GiftItemView) view.findViewById(R.id.gift_item_first);
        heartLayout = (HeartLayout)view.findViewById(R.id.heart_layout);
        handler.postDelayed(runnable, 2000);//每5秒执行一次runnable.
        view.findViewById(R.id.send_message).setOnClickListener(this);
        view.findViewById(R.id.gift).setOnClickListener(this);
        sendView = view.findViewById(R.id.layout_send_message);
        menuView = view.findViewById(R.id.layout_bottom_menu);
        topView = view.findViewById(R.id.layout_top);
        sendEditText = (EditText) view.findViewById(R.id.send_edit);
        //获取屏幕高度
        screenHeight = getActivity().getWindowManager().getDefaultDisplay().getHeight();
        //阀值设置为屏幕高度的1/3
        keyHeight = screenHeight/3;
        rootView = view.findViewById(R.id.activity_main);
        rootView.addOnLayoutChangeListener(this);
    }

    private void showDialog(Member m) {
        FragmentDialog.newInstance(m.name, m.sig, "确定", "取消",-1,false, new FragmentDialog.OnClickBottomListener() {
            @Override
            public void onPositiveClick() {

            }

            @Override
            public void onNegtiveClick() {

            }
        }).show(getChildFragmentManager(),"dialog");

    }

    Handler handler=new Handler();
    Runnable runnable=new Runnable() {
        @Override
        public void run() {
            if (messages!=null){
                Message m = new Message();
                m.img = "http://v1.qzone.cc/avatar/201503/06/18/27/54f981200879b698.jpg%21200x200.jpg" ;
                m.name=CharUtils.getRandomString(8) ;
                m.level = (int)(Math.random()*100+1) ;
                m.message= CharUtils.getRandomString(20);
                messages.add(m);
                messageAdapter.notifyDataSetChanged();
                messageList.setSelection(messageAdapter.getCount()-1);
            }
            handler.postDelayed(this, 1000);
        }
    };
    Handler heartHandler=new Handler();
    Runnable heartRunnable=new Runnable() {
        @Override
        public void run() {
            heartLayout.post(new Runnable() {
                @Override
                public void run() {
                    heartLayout.addHeart(randomColor());
                }
            });
            heartHandler.postDelayed(this, 1000);
        }
    };

    @Override
    public void onPause() {
        super.onPause();
        heartHandler.removeCallbacks(heartRunnable);
    }

    @Override
    public void onResume() {
        super.onResume();
        heartHandler.postDelayed(heartRunnable, 2000);
    }

    private int randomColor() {
        return Color.rgb(mRandom.nextInt(255), mRandom.nextInt(255), mRandom.nextInt(255));
    }
    /**
     * 添加一些数据
     */
    private void initData() {
        members = new ArrayList<>();
        for (int i=0;i<18;i++){
            Member m = new Member();
            m.img = "http://v1.qzone.cc/avatar/201503/06/18/27/54f981200879b698.jpg%21200x200.jpg" ;
            m.name="Baby" ;
            m.sig = "这个家伙很懒，什么都没留下！";
            members.add(m);
        }
        mAdapter.setDatas(members);


        messages = new ArrayList<>();
        for (int i=0;i<18;i++){
            Message m = new Message();
            m.img = "http://www.ld12.com/upimg358/allimg/c150808/143Y5Q9254240-11513_lit.png" ;
            m.name="Baby" ;
            m.level = i ;
            m.message="掘金是中国质量最高的技术分享社区,邀请稀土用户作为 Co-Editor 来分享优质的技术干货" ;
            messages.add(m);
        }
        messageAdapter.setDatas(messages);

        gifts = new ArrayList<>();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        mTimer.cancel();
        handler.removeCallbacks(runnable);
    }

    @Override
    public void onClick(View v) {
        int id = v.getId() ;
        if (id==R.id.send_message){
            sendView.setVisibility(View.VISIBLE);
            menuView.setVisibility(View.GONE);
            topView.setVisibility(View.GONE);
            sendEditText.requestFocus();
            InputMethodManager inputManager =
                    (InputMethodManager)sendEditText.getContext().getSystemService(Context.INPUT_METHOD_SERVICE);
            inputManager.showSoftInput(sendEditText, 0);
        }else if (id==R.id.gift){
            FragmentGiftDialog.newInstance().setOnGridViewClickListener(new FragmentGiftDialog.OnGridViewClickListener() {
                @Override
                public void click(Gift gift) {
                    gift.name="文人骚客";
                    gift.giftName = "送你一个小礼物" ;
                    if (!gifts.contains(gift)){
                        gifts.add(gift);
                        giftView.setGift(gift);
                    }
                    giftView.addNum(1);
                }
            }).show(getChildFragmentManager(),"dialog");
        }
    }

    @Override
    public void onLayoutChange(View v, int left, int top, int right, int bottom, int oldLeft, int oldTop, int oldRight, int oldBottom) {
        //现在认为只要控件将Activity向上推的高度超过了1/3屏幕高，就认为软键盘弹起
        if(oldBottom != 0 && bottom != 0 &&(oldBottom - bottom > keyHeight)){
            sendView.setVisibility(View.VISIBLE);
            menuView.setVisibility(View.GONE);
            topView.setVisibility(View.GONE);
//            Toast.makeText(MainActivity.getActivity(), "监听到软键盘弹起...", Toast.LENGTH_SHORT).show();
        }else if(oldBottom != 0 && bottom != 0 &&(bottom - oldBottom > keyHeight)){
            sendView.setVisibility(View.GONE);
            menuView.setVisibility(View.VISIBLE);
            topView.setVisibility(View.VISIBLE);
//            Toast.makeText(MainActivity.getActivity(), "监听到软件盘关闭...", Toast.LENGTH_SHORT).show();
        }
    }
}
