package com.example.zxingdemo;

import com.example.libzxing.activity.CaptureActivity;
import com.example.libzxing.encoding.EncodingUtils;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends Activity {

	private TextView tv_resault;
	private EditText et_input;
	private Button bt_make;
	private ImageView im_image;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.activity_main);
		initView();
	}

	private void initView() {
		Button bt_button = (Button) findViewById(R.id.bt_button);
		tv_resault = (TextView) findViewById(R.id.tv_resault);
		et_input = (EditText) findViewById(R.id.et_input);
		bt_make = (Button) findViewById(R.id.bt_make);
		im_image = (ImageView) findViewById(R.id.im_image);

		bt_button.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				startActivityForResult(new Intent(MainActivity.this,
						CaptureActivity.class), 0);
			}
		});

		bt_make.setOnClickListener(new OnClickListener()
		{
			@Override
			public void onClick(View v) 
			{
				String input = et_input.getText().toString().trim();
				if (input.equals(""))
				{
					Toast.makeText(MainActivity.this, "输入不能为空",Toast.LENGTH_SHORT).show();
				} 
				else 
				{
					//Bitmap bitmap = EncodingUtils.createQRCode(input, 500, 500,null);
					Bitmap bitmap = EncodingUtils.createQRCode(input, 400, 400,BitmapFactory.decodeResource(getResources(), R.drawable.icon));
					im_image.setImageBitmap(bitmap);
				}
			}
		});
	}

	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		super.onActivityResult(requestCode, resultCode, data);

		if (resultCode == RESULT_OK) {
			switch (requestCode) {

			case 0:
				Bundle bundle = data.getExtras();
				String resault = bundle.getString("result");
				tv_resault.setText("扫描结果:" + resault);
				break;
			}
		}
	}
}
