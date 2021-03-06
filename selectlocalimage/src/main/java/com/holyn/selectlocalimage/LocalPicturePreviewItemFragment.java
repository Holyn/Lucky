package com.holyn.selectlocalimage;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.holyn.selectlocalimage.R;
import com.squareup.picasso.Picasso;

import uk.co.senab.photoview.PhotoViewAttacher;
import uk.co.senab.photoview.PhotoViewAttacher.OnPhotoTapListener;

/**
 * 控制图片的异步加载，放大缩小操作等
 * 
 * @author Holyn
 * @create 2015-3-15
 * @modified
 */
public class LocalPicturePreviewItemFragment extends Fragment {
	public static final String EXTRA_PICTURE_PATH = "extra_picture_path";// 预览图片开销的位置
	private String picturePath;
	private ImageView ivPhoto;
	private ProgressBar pbLoading;

	private PhotoViewAttacher mAttacher;

	public static LocalPicturePreviewItemFragment newInstance() {
		return new LocalPicturePreviewItemFragment();
	}

	public LocalPicturePreviewItemFragment() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public void onCreate(Bundle savedInstanceState) {
//		System.out.println("====> onCreate...."+picturePath);
		super.onCreate(savedInstanceState);
		Bundle args = getArguments();
		picturePath = args.getString(EXTRA_PICTURE_PATH);

	}

	@Override
	public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
//		System.out.println("====> onCreateView...."+picturePath);
		View rootView = inflater.inflate(R.layout.fragment_local_picture_preview_item, container, false);
		ivPhoto = (ImageView) rootView.findViewById(R.id.iv_photo);
//		System.out.println("====> ivPhoto = "+ivPhoto);
		mAttacher = new PhotoViewAttacher(ivPhoto);

		mAttacher.setOnPhotoTapListener(new OnPhotoTapListener() {// 图片的点击事件

					@Override
					public void onPhotoTap(View arg0, float arg1, float arg2) {

					}
				});

		pbLoading = (ProgressBar) rootView.findViewById(R.id.pb_loading);

		return rootView;
	}

	@Override
	public void onActivityCreated(@Nullable Bundle savedInstanceState) {
//		System.out.println("====> onActivityCreated...."+picturePath);
		super.onActivityCreated(savedInstanceState);
		pbLoading.setVisibility(View.VISIBLE);
		Picasso.with(getActivity())
				.load("file://" + picturePath)
				.error(R.drawable.ic_picture_empty)
				.into(ivPhoto, new com.squareup.picasso.Callback() {
			@Override
			public void onSuccess() {
				pbLoading.setVisibility(View.GONE);
				if (mAttacher != null) {
					mAttacher.update();
				}
			}

			@Override
			public void onError() {
				pbLoading.setVisibility(View.GONE);
				Toast.makeText(getActivity(), "图片无法显示", Toast.LENGTH_SHORT).show();
			}
		});

	}

	@Override
	public void onDestroyView() {
//		System.out.println("====> onDestroyView...."+picturePath);
		mAttacher.cleanup();
		mAttacher = null;
		ivPhoto = null;
//		ImageLoader.getInstance().clearMemoryCache();
		System.gc();
		super.onDestroyView();
	}
	
}
