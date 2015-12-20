package com.dianxun.holyn.lucky.Utils;

/**
 * byte[]、InputStream、Bitmap、Drawable 直接的转换工具类
 * @author Holyn
 * @create 2014-8-18
 * @modified
 */
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.graphics.PixelFormat;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;

public class ImageTransFormatUtil {
	private static ImageTransFormatUtil tools = new ImageTransFormatUtil();

	public static ImageTransFormatUtil getInstance() {
		if (tools == null) {
			tools = new ImageTransFormatUtil();
			return tools;
		}
		return tools;
	}

	// 将byte[]转换成InputStream
	public InputStream Byte2InputStream(byte[] b) {
		ByteArrayInputStream bais = new ByteArrayInputStream(b);
		return bais;
	}

	// 将InputStream转换成byte[]
	public byte[] InputStream2Bytes(InputStream is) {
		String str = "";
		byte[] readByte = new byte[1024];
		int readCount = -1;
		try {
			while ((readCount = is.read(readByte, 0, 1024)) != -1) {
				str += new String(readByte).trim();
			}
			return str.getBytes();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	// 将Bitmap转换成InputStream
	public InputStream Bitmap2InputStream(Bitmap bm) {
		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		bm.compress(Bitmap.CompressFormat.JPEG, 100, baos);
		InputStream is = new ByteArrayInputStream(baos.toByteArray());
		return is;
	}

	// 将Bitmap转换成InputStream
	public InputStream Bitmap2InputStream(Bitmap bm, int quality) {
		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		bm.compress(Bitmap.CompressFormat.PNG, quality, baos);
		InputStream is = new ByteArrayInputStream(baos.toByteArray());
		return is;
	}

	// 将InputStream转换成Bitmap
	public Bitmap InputStream2Bitmap(InputStream is) {
		ByteArrayOutputStream outStream = new ByteArrayOutputStream();
		byte[] buffer = new byte[1024];
		int byteread = 0;

		try {
			while ((byteread = is.read(buffer)) != -1) {

				outStream.write(buffer, 0, byteread);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (is != null) {
				try {
					is.close();// 关闭输入流
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
			if (outStream != null) {
				try {
					outStream.close();
					;// 关闭输出流
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}

		byte[] data = outStream.toByteArray();
		BitmapFactory.Options opts = new BitmapFactory.Options();
		Bitmap bm = BitmapFactory.decodeByteArray(data, 0, data.length, opts);

		return bm;
	}

	// 将InputStream转换成Bitmap，并根据屏幕大小来压缩，主要用来处理html文本的图片
	public Bitmap InputStream2BitmapAndCompress(Context context, InputStream is) {
		ByteArrayOutputStream outStream = new ByteArrayOutputStream();
		byte[] buffer = new byte[1024];
		int byteread = 0;

		try {
			while ((byteread = is.read(buffer)) != -1) {

				outStream.write(buffer, 0, byteread);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (is != null) {
				try {
					is.close();// 关闭输入流
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
			if (outStream != null) {
				try {
					outStream.close();// 关闭输出流
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}

		byte[] data = outStream.toByteArray();
		BitmapFactory.Options opts = new BitmapFactory.Options();
		opts.inJustDecodeBounds = true;
		BitmapFactory.decodeByteArray(data, 0, data.length, opts);

		float screenWidth = ScreenUtil.getScreenWidth(context);
		float screenHeight = ScreenUtil.getScreenHeight(context);

		if (opts.outWidth > screenWidth || opts.outHeight > screenHeight) {
			int scaleW = opts.outWidth / (int) screenWidth;
			int scaleH = opts.outHeight / (int) screenHeight;
			int scale = scaleW > scaleH ? scaleW : scaleH;
			opts.inSampleSize = scale;
		}
		opts.inJustDecodeBounds = false;

		Bitmap bm = BitmapFactory.decodeByteArray(data, 0, data.length, opts);

		// 获取这个图片的宽和高
		float oldWidth = bm.getWidth();
		float oldHeight = bm.getHeight();

		// 压缩的宽高
		float newWidth = screenWidth - 40;
		float newHeight = (newWidth * oldHeight) / oldWidth;

		
		if (oldWidth > newWidth || oldHeight > newHeight) {
			// 创建操作图片用的matrix对象
			Matrix matrix = new Matrix();
			// 计算宽高缩放率
			float scaleWidth = ((float) newWidth) / oldWidth;
			float scaleHeight = ((float) newHeight) / oldHeight;
			// 缩放图片动作
			matrix.postScale(scaleWidth, scaleHeight);
			Bitmap bmNew = Bitmap.createBitmap(bm, 0, 0, (int) oldWidth, (int) oldHeight, matrix, true);
			
			return bmNew;
		}
		return bm;
	}

	// Drawable转换成InputStream
	public InputStream Drawable2InputStream(Drawable d) {
		Bitmap bitmap = this.drawable2Bitmap(d);
		return this.Bitmap2InputStream(bitmap);
	}

	// InputStream转换成Drawable
	public Drawable InputStream2Drawable(InputStream is) {
		Bitmap bitmap = this.InputStream2Bitmap(is);
		return this.bitmap2Drawable(bitmap);
	}

	// Drawable转换成byte[]
	public byte[] Drawable2Bytes(Drawable d) {
		Bitmap bitmap = this.drawable2Bitmap(d);
		return this.Bitmap2Bytes(bitmap);
	}

	// byte[]转换成Drawable
	public Drawable Bytes2Drawable(byte[] b) {
		Bitmap bitmap = this.Bytes2Bitmap(b);
		return this.bitmap2Drawable(bitmap);
	}

	// Bitmap转换成byte[]
	public byte[] Bitmap2Bytes(Bitmap bm) {
		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		bm.compress(Bitmap.CompressFormat.PNG, 100, baos);
		return baos.toByteArray();
	}

	// byte[]转换成Bitmap
	public Bitmap Bytes2Bitmap(byte[] b) {
		if (b.length != 0) {
			return BitmapFactory.decodeByteArray(b, 0, b.length);
		}
		return null;
	}

	// Drawable转换成Bitmap
	public Bitmap drawable2Bitmap(Drawable drawable) {
		Bitmap bitmap = Bitmap.createBitmap(drawable.getIntrinsicWidth(), drawable
				.getIntrinsicHeight(),
				drawable.getOpacity() != PixelFormat.OPAQUE ? Bitmap.Config.ARGB_8888
						: Bitmap.Config.RGB_565);
		Canvas canvas = new Canvas(bitmap);
		drawable.setBounds(0, 0, drawable.getIntrinsicWidth(), drawable.getIntrinsicHeight());
		drawable.draw(canvas);
		return bitmap;
	}

	// Bitmap转换成Drawable
	public Drawable bitmap2Drawable(Bitmap bitmap) {
		BitmapDrawable bd = new BitmapDrawable(bitmap);
		Drawable d = (Drawable) bd;
		return d;
	}
}
