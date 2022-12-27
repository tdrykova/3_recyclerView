package com.example.a3_recyclerview

import android.annotation.SuppressLint
import android.graphics.BlendMode
import android.graphics.BlendModeColorFilter
import android.graphics.Color
import android.graphics.PorterDuff
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.widget.SearchView
import androidx.appcompat.widget.Toolbar
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.a3_recyclerview.databinding.ActivityMainBinding
import com.example.a3_recyclerview.model.User
import com.google.android.material.snackbar.Snackbar
import java.util.*
import kotlin.collections.ArrayList

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var adapter: UsersAdapter
    var tempList = ArrayList<User>()
    val userList = ArrayList<User>()

    @SuppressLint("RestrictedApi")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        adapter = UsersAdapter(
            object : UserActionListener {

                override fun onUserLike(user: User) {
                    Snackbar.make(
                        findViewById(R.id.main),
                        "Нажат лайк: ${user.fio}",
                        Snackbar.LENGTH_LONG
                    ).show();
                }

            },
        this)

        val layoutManager = LinearLayoutManager(this)
        binding.recyclerView.layoutManager = layoutManager
        binding.recyclerView.adapter = adapter
        adapter.setList(myUser())

        val toolbar: Toolbar = binding.toolbar
        setSupportActionBar(toolbar)
        title = "Notifications"

        supportActionBar?.apply {
            setDisplayHomeAsUpEnabled(true)
            setDisplayShowHomeEnabled(true)
        }

        toolbar.navigationIcon?.apply {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) {
                colorFilter = BlendModeColorFilter(Color.BLACK, BlendMode.SRC_IN)
            } else {
                setColorFilter(Color.BLACK, PorterDuff.Mode.SRC_IN)
            }
        }

    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.main_menu, menu)
        val item = menu?.findItem(R.id.search)
        if (item != null) {
            val searchView = item.actionView as SearchView
            searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener{
                override fun onQueryTextSubmit(query: String?): Boolean {
                    return true
                }

                @SuppressLint("NotifyDataSetChanged")
                override fun onQueryTextChange(newText: String?): Boolean {
                    if (newText!!.isNotEmpty()) {
                        tempList.clear()
                        val search = newText.toLowerCase(Locale.getDefault())
                        userList.forEach{
                            if (it.fio.toLowerCase(Locale.getDefault()).contains(search)) {
                                tempList.add(it)
                            }
                        }
                        binding.recyclerView.adapter!!.notifyDataSetChanged()
                    }
                    else {
                        tempList.clear()
                        tempList.addAll(userList)
                        binding.recyclerView.adapter!!.notifyDataSetChanged()
                    }
                    return true
                }

            })
        }
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            android.R.id.home -> finish()
        }
        return true
    }

    fun myUser(): ArrayList<User> {

        val images: Array<Int> = arrayOf(
            resources.getIdentifier("person_1", "drawable", packageName),
            resources.getIdentifier("person_2", "drawable", packageName),
            resources.getIdentifier("person_3", "drawable", packageName),
            resources.getIdentifier("person_4", "drawable", packageName),
            resources.getIdentifier("person_5", "drawable", packageName),
            resources.getIdentifier("person_6", "drawable", packageName),
            resources.getIdentifier("person_7", "drawable", packageName),
            resources.getIdentifier("person_8", "drawable", packageName),
            resources.getIdentifier("person_9", "drawable", packageName),
            resources.getIdentifier("person_10", "drawable", packageName)
        )

        val user = User(
            images[0],
            "Стив Джобс",
            "1955 - 2011",
            "Сти́вен Пол (Стив) Джобс (англ. Steven Paul (Steve) Jobs; 24 февраля 1955, Сан-Франциско, Калифорния, США — 5 октября 2011, Пало-Алто, Санта-Клара, Калифорния, США) — американский предприниматель, изобретатель и промышленный дизайнер, получивший широкое признание в качестве пионера эры информационных технологий[6][7]. Один из основателей, председатель совета директоров и CEO корпорации Apple. Один из основателей и CEO киностудии Pixar.",
            "male"
        )
        userList.add(user)

        val user2 = User(
            images[1],
            "Билл Гейтс",
            "1955",
            "Уи́льям Ге́нри Гейтс III (англ. William Henry Gates III; 28 октября 1955, Сиэтл[5], Вашингтон), более известный как Билл Гейтс (англ. Bill Gates)[6][7] — американский предприниматель и общественный деятель, филантроп, один из создателей (совместно с Полом Алленом) и бывший крупнейший акционер компании Microsoft. До июня 2008 года являлся руководителем компании; после ухода с поста остался в должности её неисполнительного председателя совета директоров. Также является сопредседателем благотворительного Фонда Билла и Мелинды Гейтс, членом совета директоров Berkshire Hathaway, генеральным директором Cascade Investment.",
            "male"
        )
        userList.add(user2)

        val user3 = User(
            images[2],
            "Томас Эдисон",
            "1847 - 1931",
            "То́мас А́лва Э́дисон[5] (англ. Thomas Alva Edison; 11 февраля 1847, Майлен, штат Огайо — 18 октября 1931, Уэст-Ориндж, штат Нью-Джерси) — американский изобретатель и предприниматель, получивший в США 1093 патента[6] и около 3 тысяч — в других странах мира[7]; создатель фонографа; усовершенствовал телеграф, телефон, киноаппаратуру, разработал один из первых коммерчески успешных вариантов электрической лампы накаливания[8], которая была доработкой других вариантов. Именно он предложил использовать в начале телефонного разговора слово «алло».",
            "male"
        )
        userList.add(user3)

        val user4 = User(
            images[3],
            "Гагарин Юрий Алексеевич",
            "1934 - 1968",
            "Ю́рий Алексе́евич Гага́рин (9 марта 1934, Клушино, Гжатский (ныне Гагаринский) район, Западная область (ныне — Смоленская область) — 27 марта 1968, возле села Новосёлово, Киржачский район, Владимирская область) — лётчик-космонавт СССР, Герой Советского Союза, кавалер высших знаков отличия ряда государств, почётный гражданин многих российских и зарубежных городовПерейти к разделу «#Почётные звания и награды».\n" +
                    "\n" +
                    "Полковник ВВС СССР (1963), военный лётчик 1-го класса, заслуженный мастер спорта СССР (1961), член ЦК ВЛКСМ, депутат Верховного Совета СССР 7-го и 8-го созывов.",
            "male"
        )
        userList.add(user4)

        val user5 = User(
            images[4],
            "Мария Склодовская-Кюри",
            "1867 - 1934",
            "Мари́я Склодо́вская-Кюри́ (фр. Marie Curie, польск. Maria Skłodowska-Curie; урождённая Мария Саломея Склодовская, польск. Maria Salomea Skłodowska[1]; 7 ноября 1867 года, Варшава, Российская империя — 4 июля 1934 года, санаторий Санселльмоз[fr], Пасси[fr], Франция) — польская и французская учёная-экспериментатор (физик, химик), педагог, общественная деятельница. Первая женщина — преподавательница Сорбонны. Удостоена Нобелевских премий по физике (1903) и по химии (1911)[2], является первой женщиной — нобелевским лауреатом в истории[3][4] и первым дважды нобелевским лауреатом в истории[5][6][2][7]. Первая женщина — член Парижской медицинской академии[8]. ",
            "female"
        )
        userList.add(user5)

        val user6 = User(
            images[5],
            "Валентина Владимировна Терешкова",
            "1937",
            "Валенти́на Влади́мировна Терешко́ва (род. 6 марта 1937, деревня Большое Масленниково, Тутаевский район, Ярославская область) — лётчик-космонавт СССР, первая в мире женщина-космонавт (1963), Герой Советского Союза (1963), генерал-майор авиации (1995)[2]. Полный кавалер ордена «За заслуги перед Отечеством».",
            "female"
        )
        userList.add(user6)

        val user7 = User(
            images[6],
            "Пушкин Александр Сергеевич",
            "1799 - 1837",
            "Алекса́ндр Серге́евич Пу́шкин (26 мая [6 июня] 1799, Москва — 29 января [10 февраля] 1837, Санкт-Петербург) — русский поэт, драматург и прозаик, заложивший основы русского реалистического направления[2], литературный критик[3] и теоретик литературы, историк[3], публицист, журналист[3].",
            "male"
        )
        userList.add(user7)

        val user8 = User(
            images[7],
            "Альберт Эйнштейн",
            "1879 - 1955",
            "Альбе́рт Эйнште́йн (нем. Albert Einstein, МФА [ˈalbɐt ˈaɪ̯nʃtaɪ̯n] Информация о файле слушать[C 1]; 14 марта 1879, Ульм, Королевство Вюртемберг, Германия — 18 апреля 1955, Принстон, Нью-Джерси, США) — физик-теоретик, один из основателей современной теоретической физики, лауреат Нобелевской премии по физике 1921 года, общественный деятель-гуманист.",
            "male"
        )
        userList.add(user8)

        val user9 = User(
            images[8],
            "Джеки Чан",
            "1954",
            "Дже́ки Чан (англ. Jackie Chan; иер. трад. 陳港生, кант.-рус.: Чхань Конса́н, палл.: Чэнь Ганшэ́н, буквально: «Чхань, рождённый в Гонконге»; в Китае наиболее известен под сценическим псевдонимом Син Лун или Чэн Лун, кит. 成龍, иногда упоминается как Фон Силун[5]; род. 7 апреля 1954[1][2][3][…], Пик Виктория, Британский Гонконг[4])[6] — гонконгский актёр, каскадёр, кинорежиссёр, кинопродюсер, сценарист, постановщик трюков и боевых сцен, певец, филантроп, мастер боевых искусств. Посол доброй воли ЮНИСЕФ. Кавалер ордена Британской империи[7], главный режиссёр Чанчуньской киностудии — старейшей киностудии в КНР.",
            "male"
        )
        userList.add(user9)

        val user10 = User(
            images[9],
            "Исаак Ньютон",
            "1643 - 1668",
            "Сэр Исаа́к Нью́то́н[K 1] (англ. Isaac Newton, английское произношение: [ˌaɪzək ˈnjuːtən]; 25 декабря 1642 года — 20 марта 1727 года по юлианскому календарю, действовавшему в Англии до 1752 года; или 4 января 1643 года — 31 марта 1727 года по григорианскому календарю) — английский физик, математик, механик и астроном, один из создателей классической физики и математического анализа.",
            "male"
        )
        userList.add(user10)

        tempList.addAll(userList)

        return tempList
    }
}