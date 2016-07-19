package com.murainy.safeexam.activitis;
import com.stephentuso.welcome.WelcomeScreenBuilder;
import com.stephentuso.welcome.ui.WelcomeActivity;
import com.stephentuso.welcome.util.WelcomeScreenConfiguration;
import com.murainy.safeexam.R;

/**
 * Created by stephentuso on 11/15/15.
 */
public class SplashActivity extends WelcomeActivity {

    @Override
    protected WelcomeScreenConfiguration configuration() {
        return new WelcomeScreenBuilder(this)
                .theme(R.style.WelcomeScreenTheme)
                .defaultTitleTypefacePath("Montserrat-Bold.ttf")
                .defaultHeaderTypefacePath("Montserrat-Bold.ttf")
                .titlePage(R.drawable.bluebg, "专注·无我·做自己", R.color.background_color)
                .basicPage(R.drawable.cloud, "心无杂念·敏事慎言", "一旦开始工作，请暂且忘记身外的世界。", R.color.background_color)
                .parallaxPage(R.layout.parallax_welcome, "正念•观照•觉察", "记住，你是为自己而生的—一个人生来，也将一个人死去。即使没有你， 地球依旧转动。", R.color.background_color, 0.2f, 2f)
                .basicPage(R.drawable.fish, "玩物养志·淡泊宁静", "花朵再美也不过是一朵花而已，并无特别之处。", R.color.background_color)
                .swipeToDismiss(true)
                .exitAnimation(android.R.anim.fade_out)
                .build();
    }
    public static String welcomeKey() {
        return "WelcomeScreen";
    }

}
