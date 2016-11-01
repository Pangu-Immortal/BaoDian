package com.qi.zhongyi.shuangchen.baodian.adapters;

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
		image.setScaleType(ImageView.ScaleType.FIT_XY);
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
		String imgUrl = "";// 地址需要改。。
		// 加载网络图片
		Log.e("imgUrl", imgUrl);
		if (imgUrl != null && !imgUrl.equals("")) {
			((NetworkImageView) holder.imageView).setImageUrl(imgUrl, imageLoader);//volley的NetworkImageView请求
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
