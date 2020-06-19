package com.yuluyao.frog.repo

import com.yuluyao.frog.R
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import java.util.*
import java.util.concurrent.TimeUnit

/**
 * 描 述：
 * 作 者：Vegeta Yu
 * 时 间：2017/9/19 20:53
 */

data class Character(
  var id: String,
  var iconRes: Int,
  var picRes: Int,
  var title: String,
  var content: String,
  var description2: String)
//id
//iconRes
//title
//content

object DataStore {

  fun refreshHead(): Observable<List<Character>> {
    return Observable.just<List<Character>>(source.slice(0 until 10))
      .delay(100, TimeUnit.MILLISECONDS)
      .subscribeOn(Schedulers.io())
      .observeOn(AndroidSchedulers.mainThread())
  }

  fun refreshHead(count : Int): Observable<List<Character>>{
    return Observable.just<List<Character>>(source.slice(0 until count))
      .delay(100, TimeUnit.MILLISECONDS)
      .subscribeOn(Schedulers.io())
      .observeOn(AndroidSchedulers.mainThread())
  }


  private val source = arrayListOf<Character>()

  init {
    source.add(Character("id_01", R.drawable.head_001, R.drawable.card_01, "刘备", """
仁德：惟贤惟德，仁服于人/以德服人
激将：蜀将何在？！/尔等敢应战否？？
阵亡：难道这就是桃园吗……
""", ""))
    source.add(Character("id_02", R.drawable.head_002, R.drawable.card_02, "关羽", """
武圣：观尔乃插标卖首。/关羽在此，尔等受死。
阵亡：什么？此地叫麦城？
""", ""))
    source.add(Character("id_03", R.drawable.head_003, R.drawable.card_03, "张飞", """
咆哮：啊啊~！/燕人张飞在此！！！
阵亡：实在是杀不动了。。。
""", ""))
    source.add(Character("id_04", R.drawable.head_004, R.drawable.card_04, "诸葛亮", """
观星：观今夜天象，知天下大事！/知天易,逆天难！
阵亡：将星陨落，天命难违。。。
""", ""))
    source.add(Character("id_05", R.drawable.head_005, R.drawable.card_05, "赵云", """
龙胆：能进能退乃真正法器/吾乃常山赵子龙也！
阵亡：难道这就是失败的滋味吗？
""", ""))
    source.add(Character("id_06", R.drawable.head_006, R.drawable.card_06, "马超", """
铁骑：全军突击~~/（马蹄声）
阵亡：（马蹄声远去。。。）
""", ""))
    source.add(Character("id_07", R.drawable.head_007, R.drawable.card_07, "黄月英", """
集智：哼哼~/呵~
阵亡：亮~
""", ""))
    source.add(Character("id_08", R.drawable.head_008, R.drawable.card_08, "孙权", """
制衡：容我三思/且慢！
救援：好舒服呀~~/有汝辅佐，甚好
阵亡：父亲，大哥，仲谋愧也！
""", ""))
    source.add(Character("id_09", R.drawable.head_009, R.drawable.card_09, "甘宁", """
奇袭：你的牌太多啦！/接招吧！
阵亡：20年后,又是一条好汉！
""", ""))
    source.add(Character("id_10", R.drawable.head_010, R.drawable.card_10, "吕蒙", """
克己：不是不报，时候未到！/留得青山在，不怕没柴烧！
阵亡：被看穿了吗……
""", ""))
    source.add(Character("id_11", R.drawable.head_011, R.drawable.card_11, "黄盖", """
苦肉：请鞭挞我吧，公瑾！/赴汤蹈火，在所不辞！
阵亡：失血过多了
""", ""))
    source.add(Character("id_12", R.drawable.head_012, R.drawable.card_12, "同瑜", """
反间：挣扎吧，在血和暗的深渊里！
英姿：哈哈哈哈~/汝等给我看好了！
阵亡:既生瑜,何生…(咳咳)
""", ""))
    source.add(Character("id_13", R.drawable.head_013, R.drawable.card_13, "大乔", """
国色：请休息吧~/你累了~
流离：交给你了~/你来嘛~
阵亡：伯符，我去了。。。
""", ""))
    source.add(Character("id_14", R.drawable.head_014, R.drawable.card_14, "陆逊", """
连营：牌不是万能的，但是没牌是万万不能的。/旧的不去，新的不来~
阵亡：我还是太年轻了！
""", ""))
    source.add(Character("id_15", R.drawable.head_015, R.drawable.card_15, "曹操", """
护驾：魏将何在？/来人，护驾~
奸雄：宁教我负天下人,休教天下人负我！/孤好梦中杀人！
阵亡：霸业未成…未成啊…
""", ""))
    source.add(Character("id_16", R.drawable.head_016, R.drawable.card_16, "司马懿", """
反馈：下次注意点！/出来混，早晚要还的！
鬼才：天命？哈哈哈哈～/吾乃天命之子！
阵亡：难道真的是天命难违？
""", ""))
    source.add(Character("id_17", R.drawable.head_017, R.drawable.card_17, "夏侯惇", """
刚烈：鼠辈,竟然伤我！/以彼之道，还施彼身！
阵亡：两，两边都看不见啦~
""", ""))
    source.add(Character("id_18", R.drawable.head_018, R.drawable.card_18, "张辽", """
突袭：没想到吧？！/拿来吧~！
阵亡：真没想到。
""", ""))
    source.add(Character("id_19", R.drawable.head_019, R.drawable.card_19, "许褚", """
裸衣：谁来和我大战300回合/破！
阵亡：冷~好冷啊~~
""", ""))
    source.add(Character("id_20", R.drawable.head_020, R.drawable.card_20, "郭嘉", """
天妒：就这样吧~/罢了
遗计：也好！/就这样吧
阵亡：咳、咳~~~~
""", ""))
    source.add(Character("id_21", R.drawable.head_021, R.drawable.card_21, "甄妃", """
洛神：仿佛兮若轻云之蔽月，飘飘兮若流风之回雪。
倾国：凌波微步，罗袜生尘。/体恤飞服，飘忽若神
阵亡：悼良会之永绝兮，哀一逝而异乡。
""", ""))
    source.add(Character("id_22", R.drawable.head_022, R.drawable.card_22, "华佗", """
青囊：越老越要补啊！/早睡早起,方能养生！
急救：别紧张，有老夫呢~/救人一命，胜造七级浮屠~
阵亡：医者不能自医啊…
""", ""))
    source.add(Character("id_23", R.drawable.head_023, R.drawable.card_23, "吕布", """
无双：谁能挡我？！/神挡杀神，佛挡杀佛！
阵亡：不可能！~~~~~~
""", ""))
    source.add(Character("id_24", R.drawable.head_024, R.drawable.card_24, "貂蝉", """
离间：嗯哼~/夫君，你要替妾身做主啊~~~~~~~~~~
闭月：失礼啦~~/羡慕吧~~
阵亡：父亲大人,对不起！
""", ""))
    source.add(Character("id_25", R.drawable.head_025, R.drawable.card_25, "孙尚香", """
联姻：夫君,身体要紧~/他好我也好
枭姬：哼~/看我的厉害~
阵亡：不，还不可以死……
""", ""))
    source.add(Character("id_26", R.drawable.head_026, R.drawable.card_26, "黄忠", """
烈弓：百步穿杨！/中！
阵亡：不得不服老啊~~
""", ""))
    source.add(Character("id_27", R.drawable.head_027, R.drawable.card_27, "魏延", """
狂骨：我会怕你吗？！/真是美味啊！
阵亡：谁敢杀我，啊……
""", ""))
    source.add(Character("id_28", R.drawable.head_028, R.drawable.card_28, "夏侯渊", """
神速：孤善于千里袭人！/取汝首级犹如探囊取物！
阵亡：竟然……比我……还快~
""", ""))
    source.add(Character("id_29", R.drawable.head_029, R.drawable.card_29, "曹仁", """
据守：我先休息一会！/尽管来吧！
阵亡：实在是守不住了！~
""", ""))
    source.add(Character("id_30", R.drawable.head_030, R.drawable.card_30, "小乔", """
天香：接住哦！~/替我挡着！
阵亡：公瑾，我先走一步了~
""", ""))
    source.add(Character("id_31", R.drawable.head_031, R.drawable.card_31, "周泰", """
不屈：还不够！/我绝不会倒下！
阵亡：已经……尽力了！~
""", ""))
    source.add(Character("id_32", R.drawable.head_032, R.drawable.card_32, "张角", """
雷击：以我之真气，合天地之造化！/雷公助我！
鬼道：天下大事，为我所控！/honghonghong！（坏笑的声音）
阵亡：黄天……也死了!
""", ""))
    source.add(Character("id_33", R.drawable.head_033, R.drawable.card_33, "于吉", """
蛊惑：猜猜看呐！/你信吗？~
阵亡：居然……被猜到了！
""", ""))
    source.add(Character("id_34", R.drawable.head_034, R.drawable.card_34, "典韦", """
强袭：吃我一戟！/看我三步之内取你小命！
阵亡：主公,快走……
""", ""))
    source.add(Character("id_35", R.drawable.head_035, R.drawable.card_35, "荀彧", """
驱虎：此乃驱虎吞狼之计！/借你之手与他一搏吧！
节命：秉忠贞之志，守谦退之节……/我…永不背弃
阵亡主公要臣死，臣不得不死……
""", ""))
    source.add(Character("id_36", R.drawable.head_036, R.drawable.card_36, "庞统", """
连环：伤一敌可连其百！/通通连起来吧
涅槃：凤雏岂能消亡？/浴火重生！
阵亡：看来我命中注定将丧命于此……
""", ""))
    source.add(Character("id_37", R.drawable.head_037, R.drawable.card_37, "卧龙", """
火计：燃烧吧！/此火可助我军大获全胜！
八阵：你可识得此阵？/太极生两仪，两仪生四象，四象生八卦
看破：雕虫小计！/你的计谋被识破了！
阵亡：我的计谋竟被……
""", ""))
    source.add(Character("id_38", R.drawable.head_038, R.drawable.card_38, "太史慈", """
天义：我当要替天行道！/请助我一臂之力！
阵亡：大丈夫当带三尺之剑，立不世之功……
""", ""))
    source.add(Character("id_39", R.drawable.head_039, R.drawable.card_39, "庞德", """
猛进：我要杀你们个片甲不留！/你可敢挡我？
阵亡：四面都是水……我命休矣……
""", ""))
    source.add(Character("id_40", R.drawable.head_040, R.drawable.card_40, "颜良文丑", """
双雄：吾们乃河北上将颜良、文丑是也/快来与我等决一死战！
阵亡：这红脸长须大将是……
""", ""))
    source.add(Character("id_41", R.drawable.head_041, R.drawable.card_41, "袁绍", """
乱击：弓箭手准备，放箭！/全都去死吧！
阵亡：老天不助我袁家啊……
""", ""))
    source.add(Character("id_42", R.drawable.head_042, R.drawable.card_42, "徐晃", """
断粮：人是铁，饭是钢/截其源，断其粮，贼可擒也！
阵亡：一顿不吃~饿的慌~
""", ""))
    source.add(Character("id_43", R.drawable.head_043, R.drawable.card_43, "曹丕", """
放逐：给我翻过来！/死罪可免，活罪难赦！
行殇：我的是我的！你的~还是我的！/来~管杀还管埋
颂威：仙福永享~寿与天齐！/千秋万载~一统江山！
阵亡：子建~~子建~~
""", ""))
    source.add(Character("id_44", R.drawable.head_044, R.drawable.card_44, "孙坚", """
英魂：不诛此贼三族！则~吾死不瞑目！/以吾魂魄，保佑吾儿之基业
阵亡：有埋伏额~啊！~~
""", ""))
    source.add(Character("id_45", R.drawable.head_045, R.drawable.card_45, "董卓", """
酒池：呃呵呵，再来~一壶！/呃呵呵呵呵，好酒好酒！
肉林：美人儿~来香一个！/食色~性也！
崩坏：呃~~/哎！我是不是该减肥了？
暴虐：顺我者昌，逆我者亡！/呵呵哈哈哈哈哈哈！
阵亡：汉室衰落~非我一人之罪~
""", ""))
    source.add(Character("id_46", R.drawable.head_046, R.drawable.card_46, "祝融夫人", """
巨象：小小把戏~/大王，看我的！
猎刃：尝尝我飞刀的厉害！/亮兵器吧~
阵亡：大王~我~先走一步了
""", ""))
    source.add(Character("id_47", R.drawable.head_047, R.drawable.card_47, "孟获", """
再起：起~~~~~~~~~~~~~/丞相助我~~
祸首：背黑锅我来，送死~你去！/统统算我的！
阵亡：七纵之恩，来世再报了！
""", ""))
    source.add(Character("id_48", R.drawable.head_048, R.drawable.card_48, "贾诩", """
帷幕：你奈我何？/此计伤不到我！
乱舞：哭喊吧！哀求吧！挣扎吧！然后死吧！/呵呵呵~坐山观虎斗
完杀：我要你三更死，谁敢留你到五更！/神仙难救!神仙难救啊!
阵亡：我的时辰~~也到了
""", ""))
  }

}

