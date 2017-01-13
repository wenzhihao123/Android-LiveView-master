package cm.wzh.live.adapter;

import android.content.Context;
import android.text.TextUtils;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import cm.wzh.live.R;
import cm.wzh.live.entity.Gift;

/**
 * Created by WZH on 2016/12/25.
 */

public class GiftAdapter extends BaseListAdapter<Gift> {
    public GiftAdapter(Context ctx) {
        super(ctx);
    }
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder ;
        if (convertView == null) {
            viewHolder = new ViewHolder();
            convertView = inflater.inflate(R.layout.item_gift_message, null, false);
            viewHolder.avatar = (ImageView) convertView.findViewById(R.id.avatar);
            viewHolder.name = (TextView) convertView.findViewById(R.id.name);
            viewHolder.giftName = (TextView) convertView.findViewById(R.id.gift_name);
            viewHolder.giftType = (ImageView) convertView.findViewById(R.id.gift_type);
            viewHolder.giftNum = (TextView) convertView.findViewById(R.id.gift_num);
            convertView.setTag(viewHolder);
        }else {
            viewHolder = (ViewHolder) convertView.getTag();
        }
        Gift gift = datas.get(position);
        viewHolder.name.setText(gift.name);
        viewHolder.giftName.setText(gift.giftName);
        viewHolder.giftNum.setText("X"+gift.num+"");
        viewHolder.giftType.setImageResource(R.drawable.yipitiezhi001);
        if (!TextUtils.isEmpty(gift.img)) {
            Glide.with(ctx).load(gift.img).placeholder(R.drawable.default_head).into(viewHolder.avatar);
        }else {
            viewHolder.avatar.setImageResource(R.drawable.default_head);
        }
        return  convertView ;
    }
    public class ViewHolder{
        TextView name ;
        TextView giftName  ;
        ImageView giftType ;
        ImageView avatar ;
        TextView giftNum ;
    }
}
