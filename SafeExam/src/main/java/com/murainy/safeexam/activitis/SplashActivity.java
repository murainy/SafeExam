package com.murainy.safeexam.activitis;

import android.support.v4.app.Fragment;

import com.murainy.safeexam.R;
import com.murainy.safeexam.view.CherryFragment;
import com.murainy.safeexam.view.DoneFragment;
import com.stephentuso.welcome.BasicPage;
import com.stephentuso.welcome.FragmentWelcomePage;
import com.stephentuso.welcome.ParallaxPage;
import com.stephentuso.welcome.TitlePage;
import com.stephentuso.welcome.WelcomeActivity;
import com.stephentuso.welcome.WelcomeConfiguration;


public class SplashActivity extends WelcomeActivity {

	@Override
	protected WelcomeConfiguration configuration() {
		return new WelcomeConfiguration.Builder(this)
				.defaultTitleTypefacePath("Montserrat-Bold.ttf")
				.defaultHeaderTypefacePath("Montserrat-Bold.ttf")
				.defaultBackgroundColor(R.color.air_speed_label)
				.page(new TitlePage(R.drawable.bluebg, "专注·无我·做自己")
						.background(R.color.air_speed_label)

				)
				.page(new BasicPage(R.drawable.cloud, "心无杂念·敏事慎言", "一旦开始工作，请暂且忘记身外的世界。")
						.background(R.color.air_speed_label)
				)
				.page(new ParallaxPage(R.layout.parallax_welcome, "正念•观照•觉察", "记住，你是为自己而生的—一个人生来，也将一个人死去。即使没有你， 地球依旧转动。")
						.background(R.color.air_speed_label)
				)
				.page(new FragmentWelcomePage() {
					@Override
					protected Fragment fragment() {
						return new CherryFragment();
					}
				})
				.page(new FragmentWelcomePage() {
					@Override
					protected Fragment fragment() {
						return new DoneFragment();
					}
				})
				.page(new BasicPage(R.drawable.fish, "玩物养志·淡泊宁静", "花朵再美也不过是一朵花而已，并无特别之处。")
						.background(R.color.air_speed_label)
				)

				.swipeToDismiss(true)
				.exitAnimation(android.R.anim.fade_out)
				.build();
	}

	public static String welcomeKey() {
		return "WelcomeScreen";
	}

}
