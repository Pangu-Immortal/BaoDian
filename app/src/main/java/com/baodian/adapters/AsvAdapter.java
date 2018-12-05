package com.baodian.adapters;

import android.content.Context;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.android.volley.RequestQueue;
import com.android.volley.toolbox.ImageLoader;
import com.android.volley.toolbox.NetworkImageView;
import com.android.volley.toolbox.Volley;

import java.util.ArrayList;
import java.util.List;

public class AsvAdapter extends RecyclingPagerAdapter {
	private Context contexts;
	private NetworkImageView[] views = null;
	private int position;
	private RequestQueue queue;
	private ImageLoader imageLoader;

	public AsvAdapter( Context contexts, NetworkImageView[] advPics) {
		this.views = advPics;
		this.contexts = contexts;
		queue = Volley.newRequestQueue(contexts);
		imageLoader = new ImageLoader(queue, new BitmapCache());
	}

	/**
	 * 该方法将返回所包含的 Item总个数。为了实现一种循环滚动的效果，返回了基本整型的最大值，这样就会创建很多的Item,
	 * 其实这并非是真正的无限循环。
	 */
	@Override
	public int getCount() {
		if (views.length > 1) {
			return Integer.MAX_VALUE;
		}
		return views.length;
	}
	/**
	 * 判断出去的view是否等于进来的view 如果为true直接复用
	 */
	@Override
	public boolean isViewFromObject(View arg0, Object arg1) {
		// TODO Auto-generated method stub
		return arg0 == arg1;
	}

	private static class ViewHolder {

		ImageView imageView;
	}

	@Override
	public View getView(int id, View convertView, ViewGroup container) {
		// TODO Auto-generated method stub
		/**
		 * 简单的求模会出现问题：考虑用户向左滑的情形，则position可能会出现负值。 所以我们需要对负值再处理一次，使其落在正确的区间内。
		 */
		position = id % views.length;
		NetworkImageView image =new NetworkImageView(contexts);
		image.setScaleType(ImageView.ScaleType.CENTER_CROP);
		if (position < 0) {
			position = views.length + position;
		}
		ViewHolder holder;
        if (convertView == null) {
            holder = new ViewHolder();
            convertView = holder.imageView = image;
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder)convertView.getTag();
        }
        // 假数据
		List<String> list=new ArrayList<String>();
        list.add("http://m.qpic.cn/psb?/V12YixZQ18dqA2/bu3v92s9zA93AU1GdiGn.CrSEMFl*CooY0khetT5k3w!/b/dMkXfG8CPAAA&bo=gAJVAwAAAAAFF.A!&rf=viewer_4");
        list.add("https://b221.photo.store.qq.com/psb?/V12YixZQ18dqA2/41Z0nDRIo4WdlIAzXO7sXFTaR*mgEKWPd4Ycy183ILI!/b/dN0AAAAAAAAA&bo=LAQgAwAAAAAFBy8!&rf=viewer_4");
        list.add("http://m.qpic.cn/psb?/V12YixZQ18dqA2/Gr3JDrdAcvJKQ7bAhkvMKZHugZGeTNMmbUGpvxvpUbU!/b/dAkBAAAAAAAA&bo=LAQgAwAAAAAFFz8!&rf=viewer_4");
        list.add("http://m.qpic.cn/psb?/V12YixZQ18dqA2/qGmW*jssWUWb.SsTaA*KTphQJJeBHl2BXYFk8qLVGsA!/b/dN0AAAAAAAAA&bo=LAQgAwAAAAAFFz8!&rf=viewer_4");
        list.add("http://m.qpic.cn/psb?/V12YixZQ18dqA2/Pabv68*SN1rEjxC9qAETM.9RM87GrYa.zWtU7NYQK20!/b/dN4AAAAAAAAA&bo=LAQgAwAAAAAFFz8!&rf=viewer_4");
        list.add("http://m.qpic.cn/psb?/V12YixZQ18dqA2/lHEv5okYhBk8vfkqkeRwGyYTH7IqCpLmhZFAgLvRJd8!/b/dNoAAAAAAAAA&bo=VgOAAgAAAAAFF.M!&rf=viewer_4");
        list.add("http://m.qpic.cn/psb?/V12YixZQ18dqA2/mh7Oiyyz0n4B3dRXXEP8sRpBhx8LZ9DVLI3Sst5CTa4!/b/dHIBAAAAAAAA&bo=VgOAAgAAAAAFF.M!&rf=viewer_4");
        list.add("http://m.qpic.cn/psb?/V12YixZQ18dqA2/b*ApQ3BN1QPBXvclvK.5KzwfivflQrAMUG9AQVui9hE!/b/dHMBAAAAAAAA&bo=VgOAAgAAAAAFF.M!&rf=viewer_4");
		// 加载网络图片
		Log.e("imgUrl", list.size()+".");
		if (list != null && list.size()>0) {
			((NetworkImageView) holder.imageView).setImageUrl(list.get(position), imageLoader);//volley的NetworkImageView请求
		}
		// 如果View已经在之前添加到了一个父组件，则必须先remove，否则会抛出IllegalStateException。
		position--;
		if (position < 0) {
			position = views.length + position;
		}
		holder.imageView.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
			}
		});
		// 如果需要可以在这个位置增加监听
		return convertView;
	}
}
