package com.example.zfilm;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Parcelable;
import android.view.Menu;
import android.view.MenuItem;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private AppDatabase database;
    private MainAdapter adapter;
    private RecyclerView filmRecyclerView;
    private RecyclerView serialRecyclerView;
    private RecyclerView animationRecyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        filmRecyclerView = findViewById(R.id.recyclerView_main_film);
        serialRecyclerView = findViewById(R.id.recyclerView_main_serial);
        animationRecyclerView = findViewById(R.id.recyclerView_main_animation);
        prepareDatabase();
        insertSampleData();
        prepareAllRecyclerViews();
    }

    private void prepareAllRecyclerViews() {
        AppExecutors.getsInstance().diskIO().execute(() -> {
            prepareFilmRecyclerView();
            prepareSerialRecyclerView();
            prepareAnimationRecyclerView();
        });
    }

    private void prepareSerialRecyclerView() {
        List<MovieEntry> movieList = database.movieDao().getSerials();
        adapter = new MainAdapter(movieList);
        serialRecyclerView.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false));
        serialRecyclerView.setAdapter(adapter);
    }

    private void prepareAnimationRecyclerView() {
        List<MovieEntry> movieList = database.movieDao().getAnimations();
        adapter = new MainAdapter(movieList);
        animationRecyclerView.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false));
        animationRecyclerView.setAdapter(adapter);
    }

    private void prepareFilmRecyclerView() {
        List<MovieEntry> movieList = database.movieDao().getFilms();
        adapter = new MainAdapter(movieList);
        filmRecyclerView.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false));
        filmRecyclerView.setAdapter(adapter);
    }

    private void insertSampleData() {

        AppExecutors.getsInstance().diskIO().execute(() -> {
            int checkDatabase = database.movieDao().getAllMovies().size();
            if (checkDatabase == 0)
                database.movieDao().insertAllMovies(getSampleData());
        });
    }

    private List<MovieEntry> getSampleData() {
        List<MovieEntry> movieList = new ArrayList<>();

        movieList.add(new MovieEntry("کنستانتین", "اکشن", 2005, "خلاصه داستان : جان کنستانتین به خاطر ارتباطش با موجودات ماورای طبیعی شهرت دارد. او به خاطر خودکشی یک بار به جهنم رفته است، اما دوباره به زندگی بازگشته تا شاید با انجام اعمال خوب، جایی در بهشت برایش پیدا شود. بعد از داخل شدن در یک سری از ماجراها، او در میابد که شیاطین قصد دارند...", R.drawable.action1));
        movieList.add(new MovieEntry("راکنرولا", "اکشن", 2008, "خلاصه داستان : داستان این فیلم از این قرار است که «لنی کول» فرد بانفوذ لندنی است که در عملیات ساخت و ساز به صورت غیرقانونی هم شرکت می کند؛ یک مافیای روس برای گرفتن مجوز ساخت و ساز به او مراجعه می کند؛ معامله ای چند میلیون دلاری در کار است. اما…", R.drawable.action2));
        movieList.add(new MovieEntry("وثیقه", "اکشن", 2004, "خلاصه داستان : در مورد یک قاتل حرفه ای به نام وینست (با بازی تام کروز) است که برای کشتن عده ای که قصد شهادت علیه یک تبهکار در دادگاه را دارند، استخدام می شود. او که قصد دارد کارش را در یک شب، بدون ایجاد اثری از خود به پایان برساند، یک راننده تاکسی به نام مکس (با بازی جیمی فاکس) را با دستمزد بالا در اختیار می گیرد. اما زمانی که مکس متوجه می شود مسافرش، یک قاتل است همه چیز تغییر کرده و…", R.drawable.action3));
        movieList.add(new MovieEntry("آخرین سامورایی", "اکشن", 2003, "خلاصه داستان : یک کاپیتان آمریکایی به نام نیتن آلگرن ماموریت می یابد که به ارتش ژاپن علیه سامورایی ها کمک کند. در این میان او به اسارت جنگجویان ژاپنی در می آید و با آداب و فرهنگ آنها آشنا می شود.", R.drawable.action4));
        movieList.add(new MovieEntry("من ربات هستم", "اکشن", 2004, "خلاصه داستان : شیکاگو، سال ۲۰۳۵ روبات ها در همه جا به عنوان کارگر یا پیشخدمت به کار گرفته شده اند. در حالی که به سه قانون روباتی پای بند هستند که ظاهرا باعث می شود به آدم ها لطمه ای نزنند. تا این که کارآگاه ویژه ی جنایی به نام «دل اسپونز» (اسمیت) به شدت به روبات ها شک می کند و سرانجام رودرروی روباتی به نام «سانی» می شود که تکامل پیدا کرده و به ضوابط «سه قانون روباتی» پای بند نیست ...", R.drawable.action5));

        movieList.add(new MovieEntry("چهارمین همسفر", "کمدی", 2022, "خلاصه داستان : جولیان پنجاه ساله که از همسرش جدا شده و درگیر مشکلات مالی است، با استفاده از یک اپلیکیشن برای سفر به مادرید برای خود همسفر پیدا می کند که یکی از آن ها زن موردعلاقه اوست. دردسر از جایی شروع می شود که دو همسفر دیگر به آن ها اضافه می شود.", R.drawable.comedy1));
        movieList.add(new MovieEntry("پینه دوز", "کمدی", 2014, "خلاصه داستان : فیلم پینه دوز 2014 در مورد یک پینه دوز به نام مکس سیمکین (با بازی آدام سندلر) میباشد که از زندگی روزمره خود خسته شده است. تا اینکه یک روز او به صورت اتفاقی با میراث جادویی خانواده اش روبرو شده که به وی اجازه می دهد وارد زندگی مشتریانش شده تا دنیا را از نگاه آن ها ببیند. گاهی راه رفتن با کفش های دیگران تنها راهی است که می توان احساسات واقعی آن ها را کشف کرد…", R.drawable.comedy2));
        movieList.add(new MovieEntry("جک کانگورو", "کمدی", 2003, "خلاصه داستان : فیلم جک کانگورو 2003 در مورد دو دوست اهل بروکلین است که باید محموله پول یک گانگستر را به استرالیا برسانند اما ماجراجویی های آن ها زمانی شروع می شود که یکی از آن ها ژاکت قرمزش را تن یک کانگورو می کند تا از او عکس بگیرد. ولی وقتی کانگورو فرار می کند، متوجه می شوند که پول اوباش در داخل این ژاکت بوده است. حال آن ها مجبور می شوند کانگورو را تعقیب کرده و…", R.drawable.comedy3));
        movieList.add(new MovieEntry("کنت", "کمدی", 2023, "خلاصه داستان : آگوستو پینوشه خون آشامی است که در عمارتی ویران شده و سرد زندگی می کند. حال او پس از گذشت 250 سال تصمیم می گیرد از نوشیدن خون دست کشیده و امتیاز زندگی ابدی را رها کند، زیرا دیگر نمی تواند تحمل کند که دنیا از او به عنوان یک دزد یاد می کند. اما در این میان علیرغم بدنام شدن چهره اش و همچنین وضعیت پیچیده خانوادگی، او از طریق یک رابطه غیرمنتظره، انگیزه ای جدید برای ادامه زندگی پیدا می کند و…", R.drawable.comedy4));
        movieList.add(new MovieEntry("پنگوئن های آقای پاپر", "کمدی", 2011, "خلاصه داستان : «تام پاپرز» در طول عمرش رابطه اندکی با پدرش که یک جهانگرد بوده داشته است. خودش هم وقت کمی را با بچه هایش می گذراند و اکثر اوقات مشغول کارش است. یک روز پدر او برای هدیه ای غیر منتظره می فرستد: شش پنگوئن. پاپرز که از هدف پدرش از فرستادن این پنگوئن ها سر در نمی آورد، تصمیم میگیرد از شر آن ها خلاص شود. اما...", R.drawable.comedy5));

        movieList.add(new MovieEntry("ربوده شده", "جنایی", 2008, "خلاصه داستان : فیلم ربوده شده 1 داستان درباره دختر 17 ساله یکی از مامورین سابق CIA، در سفری که به پاریس داشته، توسط عده ای دزدیده می شود و حال او با تکیه بر استعداد ها و مهارت هایی که دارد در اروپا به دنبال دخترش می گردد.", R.drawable.crime1));
        movieList.add(new MovieEntry("سوداگران درد", "جنایی", 2023, "خلاصه داستان : فیلم سوداگران درد 2023 براساس داستانی واقعی روایت شده و در مورد مادری تنها به نام لیزا (با بازی امیلی بلانت) است که وقتی شغل خود را از دست می دهد برای تامین مخارج دخترش، درگیر یک طرح پرسود اما خطرناک فروش دارو می شود و…", R.drawable.crime2));
        movieList.add(new MovieEntry("بلیتز", "جنایی", 2011, "خلاصه داستان : افسر پلیس برانت، سعی دارد تا یک قاتل زنجیره ای که تعداد زیادی از ماموران پلیس را به قتل رسانده پیدا کند.", R.drawable.crime3));
        movieList.add(new MovieEntry("بازی مالی", "جنایی", 2017, "خلاصه داستان : فیلم بازی مالی 2017 بر اساس داستان واقعی مالی بلوم است که گرداننده بازی پوکر پرریسکی ویژه سلبریتی ها و اعضای مافیا بود و درنهایت به دام اف بی آی افتاد. فیلمنامه این فیلم نامزد جایزه بهترین فیلمنامه اقتباسی از آکادمی اسکار، جوایز بفتا و گلدن گلوبز 2017 شد.", R.drawable.crime4));
        movieList.add(new MovieEntry("رکن پنجم", "جنایی", 2013, "خلاصه داستان : فیلم رکن پنجم 2013 براساس داستانی واقعی روایت شده و در مورد دو موسس وبسایت ویکی لیکس است که با همکاری یکدیگر به ارسال و افشای اسناد محرمانه از سوی منابع ناشناس می پردازند. اما زمانی که به اسنادی محرمانه مربوط به سازمان اطلاعاتی آمریکا دسترسی پیدا می کنند، با مشکلاتی جدی روبرو شده و…", R.drawable.crime5));

        movieList.add(new MovieEntry("کینه", "ترسناک", 2004, "خلاصه داستان : کینه شرح نفرینی است که به وجود آمدن آن به دو صورت اتفاق می افتد: هنگامی که فردی با خشم شدید یا با غصهٔ فراوان می میرد. کسانی که با این قدرت ماوراالطبیعهٔ کُشنده مواجه میشوند می میرند و خود به شکل همین نفرین دوباره متولد می شوند، از یک قربانی به قربانی دیگر و این زنجیرهٔ وحشت بی انتها ادامه می یابد.", R.drawable.horror1));
        movieList.add(new MovieEntry("راهبه", "ترسناک", 2018, "خلاصه داستان : فیلم راهبه 2018 کشیشی که گذشته تاریکی دارد، همراه با یک راهبه تازه کار، به کشور رومانی اعزام می شوند تا در مورد علت مرگ یک راهبه  جوان تحقیق کنند. در ادامه، آنها با نیروی اهریمنی مخوفی روبرو می شوند که...", R.drawable.horror2));
        movieList.add(new MovieEntry("تسخیر", "ترسناک", 2019, "خلاصه داستان : فیلم تسخیر 2019 ، داستان این فیلم در شب هالووین در جریان می باشد و درباره ی گروهی از دوستان جوان است که وارد یک خانه ی خالی از سکنه می شوند تا کمی ماجراجویی کنند. اما آنها خبر ندارند که در این خانه رازهای تاریکی وجود دارد و به زودی اتفاقات ترسناکی رخ می دهد و…", R.drawable.horror3));
        movieList.add(new MovieEntry("مگالومانیک", "ترسناک", 2019, "خلاصه داستان : گالومانیک مرز نامرئی بین قربانی و جلاد و چگونگی عبور از آن را زیر سوال می برد...", R.drawable.horror4));
        movieList.add(new MovieEntry("هر طور خدایان بخواهند", "ترسناک", 2014, "خلاصه داستان : گروهی از دانش آموزان دبیرستانی مجبور به انجام یک بازی مرگبار می شوند، بی آنکه بدانند چه کسی، با چه هدفی و چگونه این بازی را طراحی کرده است و...", R.drawable.horror5));

        movieList.add(new MovieEntry("بالا در آسمان", "عاشقانه", 2009, "خلاصه داستان : فیلم بالا در آسمان 2009 داستان مردی به نام رایان بینگام است که چمدان به دست در سرتاسر کشور می چرخد و به نیابت از کارفرماها، کارمندان را اخراج می کند. درست وقتی که رایان با زن رویاهایش آشنا می شود و چیزی نمانده که به رکورد ده میلیون مایل سفر برسد، خوشبختی اش در معرض خطر قرار می گیرد.", R.drawable.romance1));
        movieList.add(new MovieEntry("سلسله جید", "عاشقانه", 2019, "خلاصه داستان : فیلم سلسله جید 2019 داستان ژانگ شیائوفان، پسر بچه روستایی، مهربان و ساده را نشان می دهد که پس از قتل عام مردم روستایش، خشمگین و ناامید به فرقه چینگ یون وارد می شود. ژانگ شیائوفان هنرهای رزمی را از سه استاد یاد می گیرد و استاد قلمرو اسطوره ای می شود. حالا او می خواهد ...", R.drawable.romance2));
        movieList.add(new MovieEntry("عشق فراموش شده", "عاشقانه", 2023, "خلاصه داستان : فیلم عشق فراموش شده 2023 در مورد یک پروفسور جراح به نام رافال ویلکزور است که زمانی مورد احترام همه بود، تا اینکه همسرش او را ترک کرده و رافال قربانی یک سرقت می شود و به دلیل جراحاتش حافظه خود را از دست می دهد. سال ها بعد، در حالی که رافال با فقر و فراموشی دست و پنجه نرم می کند با شخصی از گذشته فراموش شده اش ارتباط برقرار کرده و فرصتی برای رستگاری پیدا می کند…", R.drawable.romance3));
        movieList.add(new MovieEntry("بلوار لندن", "عاشقانه", 2010, "خلاصه داستان : فیلم بلوار لندن 2010، یک خلافکار سابق که به تازگی از زندان آزاد شده، بعنوان محافظ یک بازیگر زن استخدام می شود. طولی نمی کشد که او در میابد عاشق این زن شده و این مساله او را در موقعیت خطرناکی قرار می دهد...", R.drawable.romance4));
        movieList.add(new MovieEntry("نجات بهشت", "عاشقانه", 2021, "خلاصه داستان : فیلم نجات بهشت درباره یک شخص بی رحم می باشد که پس از مدت ها مجبور می شود به شهری باز گردد که تمام ریشه خانوادگی اش در آنجا می باشد در همین حین کارخانه مداد ورشکسته شده پدرش را به ارث می برد. حال وی در یک دوراهی بزرگ قرار دارد همه چیز را رها کند یا آن که در یک جامعه بی رحم با کلی رقیب قوی بجنگند تا نجات پیدا کند....", R.drawable.romance5));

        movieList.add(new MovieEntry("بهشت", "علمی تخیلی", 2023, "خلاصه داستان : داستان فیلم «بهشت» در آینده ای خیالی می گذرد که با تکنولوژی پیشرفته می توان عمر انسانی را به انسان دیگر انتقال داد. وقتی مکس، همسر النا، بدهی سنگینی به بیمه بالا می آورد، النا چهل سال از عمرش را به کمپانی ایان می فروشد و مکس خودش را به آب و آتش می زند تا سال های از دست رفته عمر همسرش را برگرداند.", R.drawable.scifi1));
        movieList.add(new MovieEntry("سوسک آبی", "علمی تخیلی", 2023, "خلاصه داستان : فیلم سوسک آبی 2023 در مورد یک نوجوان مکزیکی-آمریکایی به نام جیمی ریس است که یک سوسک بیگانه مرموز به نام اِسکرب را پیدا می کند. اما زمانی که این سوسک به بدن جیمی چسبیده و لباس زره پوش فرازمینی قدرتمندی به او می دهد، جیمی قدرت های فوق العاده ای به دست آورده و…", R.drawable.scifi2));
        movieList.add(new MovieEntry("کشتی جنگی", "علمی تخیلی", 2012, "خلاصه داستان : فیلم کشتی جنگی داستان در مورد ناوگان کشتی های جنگی با ارتشی از موجودات بیگانه مواجه می شوند. نبردی سهمگین با این موجودات در دریا، هوا و زمین آغاز می شود و...", R.drawable.scifi3));
        movieList.add(new MovieEntry("تبدیل شوندگان", "علمی تخیلی", 2007, "خلاصه داستان : فیلم تبدیل شوندگان2007 داستان در مورد اتوبات ها و دیسپتیکان ها ربات های بین کهکشانی هستند که دشمنی دیرینه ای با یکدیگر دارند. آنها برای به دست آوردن منبع قدرت بی نهایت به زمین می آیند. جای این منبع قدرت را نوجوانی به نام سم می داند و...", R.drawable.scifi4));
        movieList.add(new MovieEntry("آخرین کلمات", "علمی تخیلی", 2020, "خلاصه داستان : فیلم آخرین کلمات 2020 در زمان آینده و در سال 2085 میلادی روایت شده و در مورد مرد جوانی است که یکی از معدود بازماندگان جامعه بشری محسوب می شود. اکنون وی سفری طولانی و خطرناک را برای یافتن سایر افراد آغاز می کند و…", R.drawable.scifi5));

        movieList.add(new MovieEntry("جهنم بدون خشم", "جنگی", 2021, "خلاصه داستان : فیلم در مورد ماری دوجاردین، یک شهروند فرانسوی که به خیانت متهم شده است، توسط سربازان آمریکایی نجات می یابد. اما نجات او مشروط است: او باید آنها را به مخفیگاه طلایی ببرد که هم نازی ها، هم مقاومت فرانسه و هم آمریکایی ها به دنبال آن هستند.", R.drawable.war1));
        movieList.add(new MovieEntry("درگیری", "جنگی", 2016, "خلاصه داستان : فیلم درگیری 2016 گروهی از افراد بازداشت شده با پیشینه های سیاسی و اجتماعی مختلف، توسط کامیون پلیس در حال انتقال هستند، اما در این میان در جریان آشوبی که پس از برکناری رئیس جمهور از قدرت، رخ می دهد، سرنوشت آن ها را گرد هم آورده و…", R.drawable.war2));
        movieList.add(new MovieEntry("پایان سفر", "جنگی", 2006, "خلاصه داستان : داستان فیلم Flags of Our Fathers در فوریه ی سال 1945 یکی از وحشتناک ترین نبردهای جنگ جهانی دوم، در اقیانوس آرام و در جزیره ی کوچک ایووجیما رخ داد. در همان اوایل نبرد، پرچم امریکا در بالای تپه ی سوریباچی برافراشته شد و عکسی از این برافراشتن پرچم گرفته شد که به یکی از شمایل های تاریخ امریکا تبدیل شد.", R.drawable.war3));
        movieList.add(new MovieEntry("پرچم های پدران ما", "جنگی", 2006, "خلاصه داستان : داستان فیلم Flags of Our Fathers در فوریه ی سال 1945 یکی از وحشتناک ترین نبردهای جنگ جهانی دوم، در اقیانوس آرام و در جزیره ی کوچک ایووجیما رخ داد. در همان اوایل نبرد، پرچم امریکا در بالای تپه ی سوریباچی برافراشته شد و عکسی از این برافراشتن پرچم گرفته شد که به یکی از شمایل های تاریخ امریکا تبدیل شد.", R.drawable.war4));
        movieList.add(new MovieEntry("سرزمین مین", "جنگی", 2015, "خلاصه داستان : فیلم سرزمین مین 2015 داستان پس از پایان یافتن جنگ جهانی دوم، گروهی از سربازان جوان آلمانی که به اسارت نیروهای ارتش دانمارک درآمده اند، مجبور می شوند که تحت نظارت یک گروهبان دانمارکی، منطقه ساحلی این کشور را از مین های انفجاری پاکسازی کنند و...", R.drawable.war5));

        movieList.add(new MovieEntry("بریکینگ بد", "سریال", 2013, "خلاصه داستان : برایان کرنستون نقش والتر وایت دبیر شیمی را ایفا می کند که دوران سختی را می گذراند و به سختی از پس مخارج همسرش و پسرش که مشکل جسمانی دارد بر می آید . زمانیکه والتر متوجه می شود که به بیماری کشنده سرطان ریه دچار است ، همه چیز به یکباره برای او تغییر میکند . با دانستن اینکه چند سالی بیشتر زنده نیست و چیزی برای از دست دادن ندارد ، به همراه یکی از شاگردان قدیمی اش و با بهره گیری از آموخته هایش در زمینه علم شیمی شروع به ساخت و فروش ماده مخدر شیشه می کند", R.drawable.serial1));
        movieList.add(new MovieEntry("دکستر", "سریال", 2006, "خلاصه داستان : دکستر ، داستان سریال درباره مردی عجیب به نام «دکستر» است که برخلاف ظاهر مهربان و آرامش، باطنی ترسناک و وحشی دارد که همه را به لرزه در می اندازد. او در اداره پلیس مشغول به کار است و در کنار آن نیز، شب ها به قتل های زنجیره ای می پردازد و با این کار، آرامش خاطر بدست می آورد. این سریال داستان زندگی این قاتل حرفه ای را روایت میکند که تماشایی است…", R.drawable.serial2));
        movieList.add(new MovieEntry("کاراگاه حقیقی", "سریال", 2014, "خلاصه داستان : کارآگاه حقیقی سریالی جنایی، درام و رازآلود محصول شبکه HBO، که پخش آن از سال ۲۰۱۴ آغاز شده است و بازیگرانی چون «متیو مک کانهی»، «وودی هارلسون»، «میشل موناهان»، «الکساندرا ددریو»، «توری کیتلز» و «کری فوکوناگا» در آن به ایفای نقش پرداخته اند. کارآگاه حقیقی مورد توجه بسیاری از منتقدین قرار گرفت و جوایز بسیار را دریافت کرد این سریال در سایت IMDB در رتبه برترین آثار تلویزیونی قرار دارد.", R.drawable.serial3));
        movieList.add(new MovieEntry("آخرین بازمانده از ما", "سریال", 2023, "خلاصه داستان : در سریال آخرین بازمانده از ما 2023 بیست سال پس از نابودی تمدن مدرن، جوئل، یک بازمانده سرسخت، استخدام می شود تا دختری 14 ساله به نام الی را از یک منطقه قرنطینه شده، خارج کند. اما کاری که به عنوان یک ماموریت کوچک شروع می شود به زودی به یک سفر دلخراش وحشتناک تبدیل شده و جوئل و الی باید ایالات متحده را طی کرده و برای بقا به یکدیگر تکیه کنند اما…", R.drawable.serial4));
        movieList.add(new MovieEntry("آینه سیاه", "سریال", 2011, "خلاصه داستان : سریال آینه سیاه (بلک میرور) ، داستان سریال جهان را در چند بعد با تکنولوژی های مختلفی که دارد نشان می دهد و تاثیر هریک از آن هارا برروی زندگی بشریت به تصویر می کشاند برخی از آن ها مخرب هستند و موجب شده اند که مردم دیگر زندگی عادی خود را فراموش کنند و برخی دیگر انقدر پیش پا افتاده هستند که باز هم ممکن است تاثیرات منفی ای در زندگی بشریت داشته باشند ....", R.drawable.serial5));

        movieList.add(new MovieEntry("سربازان امپراطور", "انیمیشن", 2010, "خلاصه داستان : انیمیشن سربازان امپراطور 2010 گروهی از تفنگداران حرفه ای پس از دریافت یک تماس اضطراری، به سیاره ای دور افتاده سفر می کنند تا بفهمند چه اتفاقی در آنجا رخ داده است اما…", R.drawable.animation1));
        movieList.add(new MovieEntry("لئو", "انیمیشن", 2023, "خلاصه داستان : انیمیشن لئو 2023 در مورد یک مارمولک 74 ساله به نام لئو و دوست لاک پشتش است که دهه ها در یک محفظه شیشه ای در یک کلاس درس در فلوریدا زندگی می کنند. در این میان زمانی که لئو متوجه می شود تنها یک سال از زندگی اش باقی مانده است، تصمیم می گیرد فرار کرده تا زندگی بیرون از کلاس درس را نیز تجربه کند اما…", R.drawable.animation2));
        movieList.add(new MovieEntry("دزدان دریایی بی خاصیت", "انیمیشن", 2008, "خلاصه داستان : سه کارگر رستوران که از چالاکی و اعتماد به نفس لازم برای کار برخوردار نیستند و رویای بازی در نقش دزدان دریایی را در سر می پرورانند، در پی رویدادهایی غیرمنتظره، به گذشته های دور سفر می کنند تا رویای همیشگی خود را محقق سازند و...", R.drawable.animation3));
        movieList.add(new MovieEntry("پاپی", "انیمیشن", 2017, "خلاصه داستان : در این انیمیشن که در واقع قسمتی جدید از انیمیشن هتل ترانسیلوانیا است، دنیس پسر جاناتان و میویس پدر و مادرش را برای خرید سگ مجاب میکند. آنها یک سگ عجیب و غریب خریداری میکنند که…", R.drawable.animation4));
        movieList.add(new MovieEntry("میمون شاه", "انیمیشن", 2023, "خلاصه داستان : یک میمون چوب دستی با یک دختر جوان در تلاشی حماسی برای جاودانگی، مبارزه با شیاطین، اژدها، خدایان - و منیت خود - در طول راه تیم می شود.", R.drawable.animation5));

        return movieList;
    }

    private void prepareDatabase() {
        database = AppDatabase.getInstance(getApplicationContext());
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main_menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        AppExecutors.getsInstance().diskIO().execute(() -> {
            Intent intent = new Intent(this, MovieListActivity.class);

            if (item.getItemId() == R.id.item_allMovies) {
                List<MovieEntry> movieList = database.movieDao().getAllMovies();
                intent.putParcelableArrayListExtra("EXTRA", (ArrayList<? extends Parcelable>) movieList);
                intent.putExtra("EXTRA2", "همه فیلم ها");
                startActivity(intent);
            }

            if (item.getItemId() == R.id.item_sort_group_year) {
                List<MovieEntry> movieList = database.movieDao().getAllMoviesOrderByYear();
                intent.putParcelableArrayListExtra("EXTRA", (ArrayList<? extends Parcelable>) movieList);
                intent.putExtra("EXTRA2", "سال انتشار");
                startActivity(intent);
            }

            if (item.getItemId() == R.id.item_sort_group_action) {
                List<MovieEntry> movieList = database.movieDao().getActions();
                intent.putParcelableArrayListExtra("EXTRA", (ArrayList<? extends Parcelable>) movieList);
                startActivity(intent);
            }

            if (item.getItemId() == R.id.item_sort_group_comedy) {
                List<MovieEntry> movieList = database.movieDao().getComedies();
                intent.putParcelableArrayListExtra("EXTRA", (ArrayList<? extends Parcelable>) movieList);
                startActivity(intent);
            }

            if (item.getItemId() == R.id.item_sort_group_crime) {
                List<MovieEntry> movieList = database.movieDao().getCrimes();
                intent.putParcelableArrayListExtra("EXTRA", (ArrayList<? extends Parcelable>) movieList);
                startActivity(intent);
            }

            if (item.getItemId() == R.id.item_sort_group_horror) {
                List<MovieEntry> movieList = database.movieDao().getHorrors();
                intent.putParcelableArrayListExtra("EXTRA", (ArrayList<? extends Parcelable>) movieList);
                startActivity(intent);
            }

            if (item.getItemId() == R.id.item_sort_group_romance) {
                List<MovieEntry> movieList = database.movieDao().getRomances();
                intent.putParcelableArrayListExtra("EXTRA", (ArrayList<? extends Parcelable>) movieList);
                startActivity(intent);
            }

            if (item.getItemId() == R.id.item_sort_group_scifi) {
                List<MovieEntry> movieList = database.movieDao().getSciFis();
                intent.putParcelableArrayListExtra("EXTRA", (ArrayList<? extends Parcelable>) movieList);
                startActivity(intent);
            }

            if (item.getItemId() == R.id.item_sort_group_war) {
                List<MovieEntry> movieList = database.movieDao().getWars();
                intent.putParcelableArrayListExtra("EXTRA", (ArrayList<? extends Parcelable>) movieList);
                startActivity(intent);
            }

            if (item.getItemId() == R.id.item_sort_group_serial) {
                List<MovieEntry> movieList = database.movieDao().getSerials();
                intent.putParcelableArrayListExtra("EXTRA", (ArrayList<? extends Parcelable>) movieList);
                startActivity(intent);
            }

            if (item.getItemId() == R.id.item_sort_group_animation) {
                List<MovieEntry> movieList = database.movieDao().getAnimations();
                intent.putParcelableArrayListExtra("EXTRA", (ArrayList<? extends Parcelable>) movieList);
                startActivity(intent);
            }

            if (item.getItemId() == R.id.item_user) {
                //reset user state
                SharedPreferences sharedPreferences =
                        getApplicationContext().getSharedPreferences("USER_DATA", MODE_PRIVATE);
                SharedPreferences.Editor editor = sharedPreferences.edit();
                editor.putBoolean("USER_STATE", false);
                editor.apply();
                //return to login activity
                Intent signOutIntent = new Intent(this, LoginActivity.class);
                startActivity(signOutIntent);
                finish();
            }
        });

        return super.onOptionsItemSelected(item);
    }
}