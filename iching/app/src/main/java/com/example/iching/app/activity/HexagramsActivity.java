package com.example.iching.app.activity;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.ListView;

import com.example.iching.app.db.DatabaseHelper;
import com.example.iching.app.model.Hex;
import com.example.iching.app.R;

import java.util.ArrayList;
import java.util.List;

public class HexagramsActivity extends IChingBaseActivity {
    List<Hex> hexes;
    GridView gridView;
    private HexagramAdapter adapter;
    DatabaseHelper helper;
    Animation anim;
    Hex hex;
    ListView hexagramListView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.hexagrams);
        helper = new DatabaseHelper(getApplicationContext());
        setTitle("64 Hexagrams");
        hexes = new ArrayList<Hex>();

        if (helper.getPostDAO().queryForAll() == null) {
            databaseCreate();
        }
        hexes = helper.getPostDAO().queryForAll();


        final Dialog dialog = new Dialog(this);
        dialog.setContentView(R.layout.switch_gridview_listview_dialog);
        dialog.setTitle("change display");
        WindowManager.LayoutParams lp = new WindowManager.LayoutParams();
        lp.width = 500;
        lp.height = 300;
        dialog.getWindow().setAttributes(lp);


        hexagramListView= (ListView) findViewById(R.id.hexagramsDisplayListView);
        gridView = (GridView) findViewById(R.id.hexagramsDisplay);
        anim = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.drop_down);
        adapter = new HexagramAdapter(this, hexes);
        gridView.setAdapter(adapter);
        anim.start();
        gridView.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(HexagramsActivity.this, HexagramsDisplayActivity.class);
                intent.putExtra(HexagramsDisplayActivity.ID_IN, position);
                startActivity(intent);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        gridView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {

                dialog.show();
                dialog.findViewById(R.id.listview).setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        gridView.setVisibility(View.GONE);
                        hexagramListView.setAdapter(adapter);
                        hexagramListView.setVisibility(View.VISIBLE);
                        dialog.dismiss();
                    }
                });
                dialog.findViewById(R.id.gridview).setOnClickListener(dontchangedisplay(dialog));
                return false;
            }
        });
        hexagramListView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                dialog.show();
                dialog.findViewById(R.id.gridview).setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        gridView.setVisibility(View.VISIBLE);
                        hexagramListView.setVisibility(View.GONE);
                        dialog.dismiss();
                    }
                });
                dialog.findViewById(R.id.listview).setOnClickListener(dontchangedisplay(dialog));
                return false;
            }
        });
    }

    private View.OnClickListener dontchangedisplay(final Dialog dialog) {
        View.OnClickListener onClickListener = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        };
        return onClickListener;
    }

    private void databaseCreate() {
        hex = new Hex(1, "Hexagram 1 is named 乾 (qián), \"Force\". Other variations include \"the creative\", \"strong action\", \"the key\", and \"god\". Its inner (lower) trigram is ☰ (乾 qián) force = (天) heaven, and its outer (upper) trigram is the same.", "乾（拼音：qián，注音：ㄑㄧㄢˊ，中古拼音：gien），六十四卦之首。上下皆由相同的乾卦组成Trigramme2630 ☰.svg，其六个爻皆为阳。通称“乾为天”。 代表“天”的形象。置于六十四卦之首、其次是象征“地”的坤卦，序卦传：天地定位、万物生焉。", R.drawable.hex1, "111111");
        helper.getPostDAO().create(hex);
        hex = new Hex(2, "Hexagram 2 is named 坤 (kūn), \"Field\". Other variations include \"the receptive\", \"acquiescence\", and \"the flow\". Its inner trigram is ☷ (坤 kūn) field = (地) earth, and its outer trigram is identical.", "坤（拼音：kūn，注音：ㄎㄨㄣ，中古拼音：khuon），六十四卦中排行第二之卦。上下皆是由坤卦组成Trigramme2637 ☷.svg，六个爻皆是阴爻。通称为“坤为地”。象征“大地”、与天共同孕育万物之生成。", R.drawable.hex2, "000000");
        helper.getPostDAO().create(hex);
        hex = new Hex(3, "Hexagram 3 is named 屯 (zhūn), \"Sprouting\". Other variations include \"difficulty at the beginning\", \"gathering support\", and \"hoarding\". Its inner trigram is ☳ (震 zhèn) shake = (雷) thunder, and its outer trigram is ☵ (坎 kǎn) gorge = (水) water.", "屯（拼音：zhūn，注音：ㄓㄨㄣ，中古拼音：tryn），六十四卦中排序第三之卦。外卦（上卦）为坎Trigramme2635 ☵.svg、内卦（下卦）为Trigramme2633 ☳.svg震。因为上卦是代表水的坎卦、下卦是代表雷的震卦，所以通称为“水雷屯”。 天地定位后万物生长，屯卦有“盈”“万物始生”之意。", R.drawable.hex3, "010001");
        helper.getPostDAO().create(hex);
        hex = new Hex(4, "Hexagram 4 is named 蒙 (méng), \"Enveloping\". Other variations include \"youthful folly\", \"the young shoot\", and \"discovering\". Its inner trigram is ☵ (坎 kǎn) gorge = (水) water. Its outer trigram is ☶ (艮 gèn) bound = (山) mountain.", "蒙，六十四卦中第四卦。外卦（上卦）为Trigramme2636 ☶艮、内卦（下卦）为Trigramme2635 ☵.svg坎。因为上卦是代表山的艮卦、下卦是代表水的坎卦，故通称“山水蒙”。象征万初生，“蒙昧”的状态。", R.drawable.hex4, "100010");
        helper.getPostDAO().create(hex);
        hex = new Hex(5, "Hexagram 5 is named 需 (xū), \"Attending\". Other variations include \"waiting\", \"moistened\", and \"arriving\". Its inner trigram is ☰ (乾 qián) force = (天) heaven, and its outer trigram is ☵ (坎 kǎn) gorge = (水) water.", "需，六十四卦中第五卦。外卦（上卦）为Trigramme2635 ☵.svg坎、内卦（下卦）为Trigramme2630 ☰.svg乾。因为代表水的坎卦在上、代表天的乾卦在下，故通称为“水天需”。依序卦传的解释需为“饮食之道”，指万物启蒙后的养育。", R.drawable.hex5, "010111");
        helper.getPostDAO().create(hex);
        hex = new Hex(6, "Hexagram 6 is named 訟 (sòng), \"Arguing\". Other variations include \"conflict\" and \"lawsuit\". Its inner trigram is ☵ (坎 kǎn) gorge = (水) water, and its outer trigram is ☰ (乾 qián) force = (天) heaven.", "讼，六十四卦中第六卦。外卦（上卦）Trigramme2630 ☰.svg乾、内卦（下卦）Trigramme2635 ☵坎。因为代表天的乾卦在上、代表水的坎卦在下，是故通称为“天水讼”。依序卦传的解释，为了饮食生活的“需”求，开始会有争执，是为“争讼”，是以排序在需卦之后。", R.drawable.hex6, "111010");
        helper.getPostDAO().create(hex);
        hex = new Hex(7, "Hexagram 7 is named 師 (shī), \"Leading\". Other variations include \"the army\" and \"the troops\". Its inner trigram is ☵ (坎 kǎn) gorge = (水) water, and its outer trigram is ☷ (坤 kūn) field = (地) earth.", "师，六十四卦中第七卦。外卦（上卦）Trigramme2637 ☷.svg坤、内卦（下卦）Trigramme2635 ☵.svg坎、因为代表地的坤卦在上、代表水的坎卦在下，所以通称为“地水师”。师为军队之意、因为群众的争执，减变成“兴兵为师”的状况。", R.drawable.hex7, "000010");
        helper.getPostDAO().create(hex);
        hex = new Hex(8, "Hexagram 8 is named 比 (bǐ), \"Grouping\". Other variations include \"holding together\" and \"alliance\". Its inner trigram is ☷ (坤 kūn) field = (地) earth, and its outer trigram is ☵ (坎 kǎn) gorge = (水) water.", "比，六十四卦中第八卦。外卦（上卦）Trigramme2635 ☵.svg坎、内卦（下卦）Trigramme2637 ☷.svg坤。因为代表水的坎卦在上、代表地的坤卦在下，故通称为“水地比”。比为比邻，亲近友好之意，起兵兴师后同群之人为“比”。", R.drawable.hex8, "010000");
        helper.getPostDAO().create(hex);
        hex = new Hex(9, "Hexagram 9 is named 小畜 (xiǎo chù), \"Small Accumulating\". Other variations include \"the taming power of the small\" and \"small harvest\". Its inner trigram is ☰ (乾 qián) force = (天) heaven, and its outer trigram is ☴ (巽 xùn) ground = (風) wind.", "小畜（“畜”，拼音：xù，注音：ㄒㄩˋ，中古拼音：hiuk），六十四卦中第九卦。内卦（下卦）Trigramme2630 ☰.svg乾、外卦（上卦）Trigramme2634 ☴.svg巽。通称“风天小畜”。小畜有集合之意，人们亲近后开始集合", R.drawable.hex9, "110111");

        helper.getPostDAO().create(hex);
        hex = new Hex(10, "Hexagram 10 is named 履 (lǚ), \"Treading\". Other variations include \"treading (conduct)\" and \"continuing\". Its inner trigram is ☱ (兌 duì) open = (澤) swamp, and its outer trigram is ☰ (乾 qián) force = (天) heaven.", "履，六十四卦中第十卦。内卦（下卦）Trigramme2631 ☱.svg兑、外卦（上卦）Trigramme2630 ☰.svg乾。通称“天泽履”。履为踩踏之意，序卦传另云：履者礼也。", R.drawable.hex10, "111011");
        helper.getPostDAO().create(hex);

        hex = new Hex(11, "Hexagram 11 is named 泰 (tài), \"Pervading\". Other variations include \"peace\" and \"greatness\". Its inner trigram is ☰ (乾 qián) force = (天) heaven, and its outer trigram is ☷ (坤 kūn) field = (地) earth.", "泰，六十四卦中第十一卦。内卦（下卦）Trigramme2630 ☰.svg乾、外卦（上卦）Trigramme2637 ☷.svg坤。通称“地天泰”。泰为通达之意。", R.drawable.hex11, "000111");
        helper.getPostDAO().create(hex);
        hex = new Hex(12, "Hexagram 12 is named 否 (pǐ), \"Obstruction\". Other variations include \"standstill (stagnation)\" and \"selfish persons\". Its inner trigram is ☷ (坤 kūn) field = (地) earth, and its outer trigram is ☰ (乾 qián) force = (天) heaven.", "否（拼音：pǐ，中古拼音：biix），六十四卦中第十二卦。内卦（下卦）Trigramme2637 ☷.svg坤、外卦（上卦）Trigramme2630 ☰.svg乾。通称“天地否”。否为闭“塞”之意。", R.drawable.hex12, "111000");
        helper.getPostDAO().create(hex);
        hex = new Hex(13, "Hexagram 13 is named 同人 (tóng rén), \"Concording People\". Other variations include \"fellowship with men\" and \"gathering men\". Its inner trigram is ☲ (離 lí) radiance = (火) fire, and its outer trigram is ☰ (乾 qián) force = (天) heaven.", "同人，六十四卦中第十三卦。内卦（下卦）Trigramme2632 ☲.svg离、外卦（上卦）Trigramme2630 ☰.svg乾。通称“天火同人”。同人是“会同”・“协同”之意。", R.drawable.hex13, "111101");
        helper.getPostDAO().create(hex);
        hex = new Hex(14, "Hexagram 14 is named 大有 (dà yǒu), \"Great Possessing\". Other variations include \"possession in great measure\" and \"the great possession\". Its inner trigram is ☰ (乾 qián) force = (天) heaven, and its outer trigram is ☲ (離 lí) radiance = (火) fire.", "大有，六十四卦中第十四卦。内卦（下卦）Trigramme2630 ☰.svg乾、外卦（上卦)、Trigramme2632 ☲.svg离。通称“火天大有”。意指大的收获。", R.drawable.hex14, "101111");
        helper.getPostDAO().create(hex);
        hex = new Hex(15, "Hexagram 15 is named 謙 (qiān), \"Humbling\". Other variations include \"modesty\". Its inner trigram is ☶ (艮 gèn) bound = (山) mountain and its outer trigram is ☷ (坤 kūn) field = (地) earth.", "谦，六十四卦中第十五卦。内卦（下卦）Trigramme2636 ☶.svg艮、外卦（上卦）Trigramme2637 ☷.svg坤。通称“地山谦”。谦为谦逊之意。", R.drawable.hex15, "000100");
        helper.getPostDAO().create(hex);
        hex = new Hex(16, "Hexagram 16 is named 豫 (yù), \"Providing-For\". Other variations include \"enthusiasm\" and \"excess\". Its inner trigram is ☷ (坤 kūn) field = (地) earth, and its outer trigram is ☳ (震 zhèn) shake = (雷) thunder.", "豫，六十四卦中第十六卦。内卦（下卦）Trigramme2637 ☷.svg坤、外卦（上卦）Trigramme2633 ☳.svg震。通称“雷地豫”。豫为喜悦之意。", R.drawable.hex16, "001000");
        helper.getPostDAO().create(hex);
        hex = new Hex(17, "Hexagram 17 is named 隨 (suí), \"Following\". Its inner trigram is ☳ (震 zhèn) shake = (雷) thunder, and its outer trigram is ☱ (兌 duì) open = (澤) swamp.", "随，六十四卦中第十七卦。内卦（下卦）Trigramme2633 ☳.svg震、外卦（上卦）Trigramme2631 ☱.svg兑。通称“泽雷随”。随为跟随之意。", R.drawable.hex17, "011001");
        helper.getPostDAO().create(hex);
        hex = new Hex(18, "Hexagram 18 is named '蠱' (gŭ), \"Correcting\". Other variations include \"work on what has been spoiled (decay)\", decaying and \"branch\".[1] Its inner trigram is ☴ (巽 xùn) ground = (風) wind, and its outer trigram is ☶ (艮 gèn) bound = (山) mountain. Gu is the name of a venom-based poison traditionally used in Chinese witchcraft.", "蛊（拼音：gǔ，中古拼音：kox），六十四卦中第十八卦。内卦（下卦）Trigramme2634 ☴.svg巽、外卦（上卦）Trigramme2636 ☶.svg艮。通称“山风蛊”。蛊，为“腐败”之意。", R.drawable.hex18, "100110");
        helper.getPostDAO().create(hex);
        hex = new Hex(19, "Hexagram 19 is named 臨 (lín), \"Nearing\". Other variations include \"approach\" and \"the forest\". Its inner trigram is ☱ (兌 duì) open = (澤) swamp, and its outer trigram is ☷ (坤 kūn) field = (地) earth.", "临，六十四卦中第十九卦。内卦（下卦）Trigramme2631 ☱.svg兑、外卦（上卦）Trigramme2637 ☷.svg坤。通称“地泽临”。临者，临近之意。", R.drawable.hex19, "000011");
        helper.getPostDAO().create(hex);
        hex = new Hex(20, "Hexagram 20 is named 觀 (guān), \"Viewing\". Other variations include \"contemplation (view)\" and \"looking up\". Its inner trigram is ☷ (坤 kūn) field = (地) earth, and its outer trigram is ☴ (巽 xùn) ground = (風) wind.", "观，六十四卦中第廿卦。内卦（下卦）Trigramme2637 ☷.svg坤、外卦（上卦）Trigramme2634 ☴.svg巽。通称“风地观”。观者，观看之意。", R.drawable.hex20, "110000");
        helper.getPostDAO().create(hex);
        hex = new Hex(21, "Hexagram 21 is named 噬嗑 (shì kè), \"Gnawing Bite\". Other variations include \"biting through\" and \"biting and chewing\". Its inner trigram is ☳ (震 zhèn) shake = (雷) thunder, and its outer trigram is ☲ (離 lí) radiance = (火) fire.", "噬嗑（拼音：shì hé，注音：ㄕˋ ㄏㄜˊ，中古拼音：zjed ghap），六十四卦中第廿一卦。内卦（下卦）Trigramme2633 ☳.svg震、外卦（上卦）Trigramme2632 ☲.svg离。通称“火雷噬嗑”。噬嗑为咬之意。", R.drawable.hex21, "101001");
        helper.getPostDAO().create(hex);
        hex = new Hex(22, "Hexagram 22 is named 賁 (bì), \"Adorning\". Other variations include \"grace\" and \"luxuriance\". Its inner trigram is ☲ (離 lí) radiance = (火) fire, and its outer trigram is ☶ (艮 gèn) bound = (山) mountain.", "贲（拼音：bì，注音：ㄅㄧˋ，中古拼音：pieh），六十四卦中第廿二卦。内卦（下卦）Trigramme2632 ☲.svg离、外卦（上卦）Trigramme2636 ☶.svg艮。通称“山火贲”。贲者饰也、装饰，修饰之意。", R.drawable.hex22, "100101");
        helper.getPostDAO().create(hex);
        hex = new Hex(23, "Hexagram 23 is named 剝 (bō), \"Stripping\". Other variations include \"splitting apart\" and \"flaying\". Its inner trigram is ☷ (坤 kūn) field = (地) earth, and its outer trigram is ☶ (艮 gèn) bound = (山) mountain.", "剥，六十四卦中第廿三卦。内卦（下卦）Trigramme2637 ☷.svg坤、外卦（上卦）Trigramme2636 ☶.svg艮。通称“山地剥”。剥为“剥”落之意。", R.drawable.hex23, "100000");
        helper.getPostDAO().create(hex);
        hex = new Hex(24, "Hexagram 24 is named 復 (fù), \"Returning\". Other variations include \"return (the turning point)\". Its inner trigram is ☳ (震 zhèn) shake = (雷) thunder, and its outer trigram is ☷ (坤 kūn) field = (地) earth.", "复，六十四卦中第廿四卦。内卦（下卦）Trigramme2633 ☳.svg震、外卦（上卦）Trigramme2637 ☷.svg坤。通称“地雷复”。复者，回“复”之意。", R.drawable.hex24, "000001");
        helper.getPostDAO().create(hex);
        hex = new Hex(25, "Hexagram 25 is named 無妄 (wú wàng), \"Without Embroiling\". Other variations include \"innocence (the unexpected)\" and \"pestilence\". Its inner trigram is ☳ (震 zhèn) shake = (雷) thunder, and its outer trigram is ☰ (乾 qián) force = (天) heaven.", "无妄，六十四卦中第廿五卦。内卦（下卦）Trigramme2633 ☳.svg震、外卦（上卦）Trigramme2630 ☰.svg乾。通称“天雷无妄”。\n" +
                "妄与诚相反，虚伪的意思；无妄 ，即不虚伪。无妄也是无妄之灾之意。", R.drawable.hex25, "111001");
        helper.getPostDAO().create(hex);
        hex = new Hex(26, "Hexagram 26 is named 大畜 (dà chù), \"Great Accumulating\". Other variations include \"the taming power of the great\", \"great storage\", and \"potential energy.\" Its inner trigram is ☰ (乾 qián) force = (天) heaven, and its outer trigram is ☶ (艮 gèn) bound = (山) mountain.", "大畜，六十四卦中第廿六卦。内卦（下卦）Trigramme2630 ☰.svg乾、外卦（上卦）Trigramme2636 ☶.svg艮。通称“山天大畜”。为“丰收”之意。", R.drawable.hex26, "100111");
        helper.getPostDAO().create(hex);
        hex = new Hex(27, "Hexagram 27 is named 頤 (yí), \"Swallowing\". Other variations include \"the corners of the mouth (providing nourishment)\", \"jaws\" and \"comfort/security\". Its inner trigram is ☳ (震 zhèn) shake = (雷) thunder, and its outer trigram is ☶ (艮 gèn) bound = (山) mountain.", "颐，六十四卦中第廿七卦。内卦（下卦）Shin.png震、外卦（上）Gon.png艮。通称“山雷颐”。颐为下颚，引伸为吞噬之意。", R.drawable.hex27, "100001");
        helper.getPostDAO().create(hex);
        hex = new Hex(28, "Hexagram 28 is named 大過 (dà guò), \"Great Exceeding\". Other variations include \"preponderance of the great\", \"great surpassing\" and \"critical mass.\" Its inner trigram is ☴ (巽 xùn) ground = (風) wind, and its outer trigram is ☱ (兌 duì) open = (澤) swamp.", "大过，六十四卦中第廿八卦。内卦（下卦)Xun.png巽、外卦（上卦）Da.png兑。因为代表泽的兑卦在上、代表风的巽卦在下，是故通称“泽风大过”。有超越太多、“过犹不及”之意。", R.drawable.hex28, "011110");
        helper.getPostDAO().create(hex);
        hex = new Hex(29, "Hexagram 29 is named 坎 (kǎn), \"Gorge\". Other variations include \"the abysmal (water)\" and \"repeated entrapment\". Its inner trigram is ☵ (坎 kǎn) gorge = (水) water, and its outer trigram is identical.", "坎，六十四卦中第廿九卦。上下卦皆为Kan.png坎。其代表水，通称为“坎为水”，意为水洼、“坎”陷之意。", R.drawable.hex29, "010010");
        helper.getPostDAO().create(hex);
        hex = new Hex(30, "Hexagram 30 is named 離 (lí), \"Radiance\". Other variations include \"the clinging, fire\" and \"the net\". Its inner trigram is ☲ (離 lí) radiance = (火) fire, and its outer trigram is identical. The origin of the character has its roots in symbols of long-tailed birds such as the peacock or the legendary phoenix.", "离，六十四卦中第卅卦。上下同为Ri.png离。离者，为“火”，通称为“离为火”。亦有“丽”之意。", R.drawable.hex30, "101101");
        helper.getPostDAO().create(hex);
        hex = new Hex(31, "Hexagram 31 is named 咸 (xián), \"Conjoining\". Other variations include \"influence (wooing)\" and \"feelings\". Its inner trigram is ☶ (艮 gèn) bound = (山) mountain, and its outer trigram is ☱ (兌 duì) open = (澤) swamp.", "咸，六十四卦中第卅一卦。外卦（上卦）Trigramme2631 ☱.svg兑、内卦（下卦）Trigramme2636 ☶.svg艮。因为上卦是代表泽的兑卦、下卦是代表山的艮卦，所以通称为“泽山咸”。为“交感”，互相连结之意。", R.drawable.hex31, "011100");
        helper.getPostDAO().create(hex);
        hex = new Hex(32, "Hexagram 32 is named 恆 (héng), \"Persevering\". Other variations include \"duration\" and \"constancy\". Its inner trigram is ☴ (巽 xùn) ground = (風) wind, and its outer trigram is ☳ (震 zhèn) shake = (雷) thunder.", "恒，六十四卦中第卅二卦。外卦（上卦）Trigramme2633 ☳.svg震、内卦（下卦）Trigramme2634 ☴.svg巽。因为上卦是代表雷的震卦、下卦是代表风的巽卦，是以通称为“雷风恒”。恒者，“永恒”之意。", R.drawable.hex32, "001110");
        helper.getPostDAO().create(hex);
        hex = new Hex(33, "Hexagram 33 is named 遯 (dùn), \"Retiring\". Other variations include \"retreat\" and \"yielding\". Its inner trigram is ☶ (艮 gèn) bound = (山) mountain, and its outer trigram is ☰ (乾 qián) force = (天) heaven.", "遁（拼音：dùn，注音：ㄉㄨㄣˋ，中古拼音：duonh），六十四卦中第卅三卦。外卦（上卦）Trigramme2630 ☰.svg乾、内卦（下卦）Trigramme2636 ☶.svg艮。因为上卦是代表天的乾卦、下卦是代表山的艮卦，所以通称为“天山遁”。序卦传云：遁者，退也。“隐匿”之意。", R.drawable.hex33, "111100");
        helper.getPostDAO().create(hex);
        hex = new Hex(34, "Hexagram 34 is named 大壯 (dà zhuàng), \"Great Invigorating\". Other variations include \"the power of the great\" and \"great maturity\". Its inner trigram is ☰ (乾 qián) force = (天) heaven, and its outer trigram is ☳ (震 zhèn) shake = (雷) thunder.", "大壮，六十四卦中第卅四卦。外卦（上卦）Trigramme2633 ☳.svg震、内卦（下卦）Trigramme2630 ☰.svg乾。因为上卦是代表雷的震卦、下卦是代表天的乾卦，所以通称为“雷天大壮”。为“阳刚壮盛”之意。", R.drawable.hex34, "001111");
        helper.getPostDAO().create(hex);
        hex = new Hex(35, "Hexagram 35 is named 晉 (jìn), \"Prospering\". Other variations include \"progress\" and \"aquas\". Its inner trigram is ☷ (坤 kūn) field = (地) earth, and its outer trigram is ☲ (離 lí) radiance = (火) fire.", "晋，六十四卦中第卅五卦。外卦（上卦）Trigramme2632 ☲.svg离、内卦（下卦）Trigramme2637 ☷.svg坤。因为上卦是代表火的离卦、下卦是代表地的坤卦，所以通称为“火地晋”。序卦传云：晋者，进也。是“进步”的象征。", R.drawable.hex35, "101000");
        helper.getPostDAO().create(hex);
        hex = new Hex(36, "Hexagram 36 is named 明夷 (míng yí), “Darkening of the Light.” Other variations are \"brilliance injured\" and \"intelligence hidden\". Its inner trigram is ☲ (離 lí) radiance = (火) fire, and its outer trigram is ☷ (坤 kūn) field = (地) earth.", "明夷，六十四卦卦第卅六卦。内卦（下卦）Trigramme2632 ☲.svg离、外卦（上卦）Trigramme2637 ☷.svg坤。因为上卦是代表地的坤卦、下卦是代表火的离卦，所以通称为“地火明夷”。序卦传云：夷者，伤也。乃光明受到损伤，是故为“黑暗”之象。", R.drawable.hex36, "000101");
        helper.getPostDAO().create(hex);
        hex = new Hex(37, "Hexagram 37 is named 家人 (jiā rén), \"Dwelling People\". Other variations include \"the family (the clan)\" and \"family members\". Its inner trigram is ☲ (離 lí) radiance = (火) fire, and its outer trigram is ☴ (巽 xùn) ground = (風) wind.", "家人，六十四卦中第卅七卦。内卦（下卦)Trigramme2632 ☲.svg离、外卦（上卦）Trigramme2634 ☴.svg巽。因为上卦是代表风的巽卦、下卦是代表火的离卦，所以通称为“风火家人”。序卦传云：家人，内也。为“齐家”之象。", R.drawable.hex37, "110101");
        helper.getPostDAO().create(hex);
        hex = new Hex(38, "Hexagram 38 is named 睽 (kuí), \"Polarising\". Other variations include \"opposition\" and \"perversion\". Its inner trigram is ☱ (兌 duì) open = (澤) swamp, and its outer trigram is ☲ (離 lí) radiance = (火) fire.", "睽（拼音：kuí，中古拼音：khue），六十四卦中第卅八卦。内卦（下卦）Trigramme2631 ☱.svg兑、外卦（上卦）Trigramme2632 ☲.svg离。因为上卦是代表火的离卦、下卦是代表泽的兑卦，所以通称为“火泽睽”。序卦传云：睽者，乖也。为“乖违、违背”之象。", R.drawable.hex38, "101011");
        helper.getPostDAO().create(hex);
        hex = new Hex(39, "Hexagram 39 is named 蹇 (jiǎn), \"Limping\". Other variations include \"obstruction\" and \"afoot\". Its inner trigram is ☶ (艮 gèn) bound = (山) mountain, and its outer trigram is ☵ (坎 kǎn) gorge = (水) water.", "蹇（拼音：jiǎn，注音：ㄐㄧㄢˇ，中古拼音：kianx），六十四卦中第卅九卦。内卦（下卦）Trigramme2636 ☶.svg艮、外卦（上卦）Trigramme2635 ☵.svg坎。因为上卦是代表水的坎卦、下卦是代表山的艮卦，所以通称为“水山蹇”。序卦传：蹇者，难也。为“艰难”之意。", R.drawable.hex39, "010100");
        helper.getPostDAO().create(hex);
        hex = new Hex(40, "Hexagram 40 is named 解 (xiè), \"Taking-Apart\". Other variations include \"deliverance\" and \"untangled\". Its inner trigram is ☵ (坎 kǎn) gorge = (水) water, and its outer trigram is ☳ (震 zhèn) shake = (雷) thunder.", "解（拼音：jiè，中古拼音：kreh），六十四卦中第四十卦。内卦（下卦）Trigramme2635 ☵.svg坎、外卦（上卦）Trigramme2633 ☳.svg震。因为上卦是代表雷的震卦、下卦是代表水的坎卦，所以通称为“雷水解”。序卦传：解者，缓也。乃“消除、缓和”之意。", R.drawable.hex40, "001010");
        helper.getPostDAO().create(hex);
        hex = new Hex(41, "Hexagram 41 is named 損 (sǔn), \"Diminishing\". Other variations include \"decrease\". Its inner trigram is ☱ (兌 duì) open = (澤) swamp, and its outer trigram is ☶ (艮 gèn) bound = (山) mountain.", "损，六十四卦中第四十一卦。内卦（下卦）Trigramme2631 ☱.svg兑、外卦（上卦）Trigramme2636 ☶.svg艮。因为上卦是代表山的艮卦、下卦是代表泽的兑卦，所以通称为“山泽损”。损，为“减损”之意。", R.drawable.hex41, "100011");
        helper.getPostDAO().create(hex);
        hex = new Hex(42, "Hexagram 42 is named 益 (yì), \"Augmenting\". Other variations include \"increase\". Its inner trigram is ☳ (震 zhèn) shake = (雷) thunder, and its outer trigram is ☴ (巽 xùn) ground = (風) wind.", "益，六十四卦中第四十二卦。内卦（下卦）Trigramme2633 ☳.svg震、外卦（上卦）Trigramme2634 ☴.svg巽。因为上卦是代表风的巽卦、下卦是代表雷的震卦，所以通称为“风雷益”。益者，“利益”之意。", R.drawable.hex42, "110001");
        helper.getPostDAO().create(hex);
        hex = new Hex(43, "Hexagram 43 is named 夬 (guài), \"Displacement\" Other variations include \"resoluteness\", \"parting\", and \"break-through\". Its inner trigram is ☰ (乾 qián) force = (天) heaven, and its outer trigram is ☱ (兌 duì) open = (澤) swamp.", "夬（拼音：guài，注音：ㄍㄨㄞˋ，中古拼音：kruad），六十四卦中第四十三卦。内卦（下卦）Trigramme2630 ☰.svg乾、外卦（上卦）Trigramme2631 ☱.svg兑。因为上卦是代表泽的兑卦、下卦是代表天的乾卦，所以通称为“泽天夬”。夬者，决者。为“决裂”之意。", R.drawable.hex43, "011111");
        helper.getPostDAO().create(hex);
        hex = new Hex(44, "Hexagram 44 is named 姤 (gòu), \"Coupling\". Other variations include \"coming to meet\" and \"meeting\". Its inner trigram is ☴ (巽 xùn) ground = (風) wind, and its outer trigram is ☰ (乾 qián) force = (天) heaven.", "姤（拼音：gòu，中古拼音：kuh），六十四卦中第四十四卦。内卦（下卦）Trigramme2634 ☴.svg巽、外卦（上卦）Trigramme2630 ☰.svg乾。因为上卦是代表天的乾卦、下卦是代表风的巽卦，所以通称为“天风姤”。序卦传所言：姤，遇也，柔遇刚也。为“相遇、邂逅”之意。", R.drawable.hex44, "111110");
        helper.getPostDAO().create(hex);
        hex = new Hex(45, "Hexagram 45 is named 萃 (cuì), \"Clustering\". Other variations include \"gathering together (massing)\" and \"finished\". Its inner trigram is ☷ (坤 kūn) field = (地) earth, and its outer trigram is ☱ (兌 duì) open = (澤) swamp.", "萃（拼音：cuì，中古拼音：zyih），六十四卦中第四十五卦。内卦（下卦）Trigramme2637 ☷.svg坤、外卦（上卦）Trigramme2631 ☱.svg兑。因为上卦是代表泽的兑卦、下卦是代表地的坤卦，所以通称为“泽地萃”。序卦传：萃者，聚也。为“汇聚”之象。", R.drawable.hex45, "011000");
        helper.getPostDAO().create(hex);
        hex = new Hex(46, "Hexagram 46 is named 升 (shēng), \"Ascending\". Other variations include \"pushing upward\". Its inner trigram is ☴ (巽 xùn) ground = (風) wind, and its outer trigram is ☷ (坤 kūn) field = (地) earth.", "升，六十四卦中第四十六卦。内卦（下卦）Trigramme2634 ☴.svg巽、外卦（上卦）Trigramme2637 ☷.svg坤。因为上卦是代表地的坤卦、下卦是代表风的巽卦，所以通称为“地风升”。序卦传所言：聚而上者，谓之升。为“上升”之象。", R.drawable.hex46, "000110");
        helper.getPostDAO().create(hex);
        hex = new Hex(47, "Hexagram 47 is named 困 (kùn), \"Confining\". Other variations include \"oppression (exhaustion)\" and \"entangled\". Its inner trigram is ☵ (坎 kǎn) gorge = (水) water, and its outer trigram is ☱ (兌 duì) open = (澤) swamp.", "困，六十四卦中第四十七卦。内卦（下卦）Trigramme2635 ☵.svg坎、外卦（上卦Trigramme2631 ☱.svg兑。因为上卦是代表泽的兑卦、下卦是代表水的坎卦，所以通称为“泽水困”。为“受围困”之象。", R.drawable.hex47, "011010");
        helper.getPostDAO().create(hex);
        hex = new Hex(48, "Hexagram 48 is named 井 (jǐng), \"Welling\". Other variations include \"the well\". Its inner trigram is ☴ (巽 xùn) ground = (風) wind, and its outer trigram is ☵ (坎 kǎn) gorge = (水) water.", "井，六十四卦中第四十八卦。内卦（下卦）Trigramme2634 ☴.svg巽、外卦（上卦）Trigramme2635 ☵.svg坎。因为代表水的坎卦在上，代表风的巽卦在下，故通称“水风井”。为用木桶汲井水之象。代表能“养生民而无穷”。", R.drawable.hex48, "010110");
        helper.getPostDAO().create(hex);
        hex = new Hex(49, "Hexagram 49 is named 革 (gé), \"Skinning\". Other variations including \"revolution (molting)\" and \"the bridle\". Its inner trigram is ☲ (離 lí) radiance = (火) fire, and its outer trigram is ☱ (兌 duì) open = (澤) swamp.", "革，六十四卦中第四十九卦。本卦为异卦相叠(离上,兑下)。上卦（外卦）Trigramme2632 ☲.svg为离，离为火；下卦（内卦）Trigramme2631 ☱.svg为兑，兑为泽[1]。序卦传所言：革，去故也。为“改革、革新、革命”之象。", R.drawable.hex49, "011101");
        helper.getPostDAO().create(hex);
        hex = new Hex(50, "Hexagram 50 is named 鼎 (dǐng), \"Holding\". Other variations include \"the cauldron\". Its inner trigram is ☴ (巽 xùn) ground = (風) wind, and its outer trigram is ☲ (離 lí) radiance = (火) fire.", "鼎，六十四卦中第五十卦。内卦（下卦）Trigramme2634 ☴.svg巽、外卦（上卦）Trigramme2632 ☲.svg离。因为代表火的离卦在上、代表风的巽卦在下，故通称“火风鼎”。序卦传所言：鼎，取新也。为“鼎新”之意。", R.drawable.hex50, "101110");
        helper.getPostDAO().create(hex);
        hex = new Hex(51, "Hexagram 51 is named 震 (zhèn), \"Shake\". Other variations include \"the arousing (shock, thunder)\" and \"thunder\". Both its inner and outer trigrams are ☳ (震 zhèn) shake = (雷) thunder.", "震（拼音：zhèn，注音：ㄓㄣˋ，中古拼音：cjinh），六十四卦中第五十一卦。上下卦皆是八卦中的Trigramme2633 ☳.svg震卦。因为震卦代表“雷”，故通称为“震为雷”。序卦传：震者，“动”也。", R.drawable.hex51, "001001");
        helper.getPostDAO().create(hex);
        hex = new Hex(52, "Hexagram 52 is named 艮 (gèn), \"Bound\". Other variations include \"keeping still, mountain\" and \"stilling\". Both its inner and outer trigrams are ☶ (艮 gèn) bound = (山) mountain.", "艮（拼音：gèn，注音：ㄍㄣˋ，中古拼音：konh），六十四卦中第五十二卦。上下卦皆是由八卦中的代表山的Trigramme2636 ☶.svg艮所组成。因为艮卦代表“山”，故通称为“艮为山”。艮者，止也。有“高山静止不动、不动如山”之意。", R.drawable.hex52, "100100");
        helper.getPostDAO().create(hex);
        hex = new Hex(53, "Hexagram 53 is named 漸 (jiàn), \"Infiltrating\". Other variations include \"development (gradual progress)\" and \"advancement\". Its inner trigram is ☶ (艮 gèn) bound = (山) mountain, and its outer trigram is ☴ (巽 xùn) ground = (風) wind.", "渐（拼音：jiàn，中古拼音：ziemx），六十四卦中第五十三卦。内卦（下卦）Trigramme2636 ☶.svg艮、外卦（上卦）Trigramme2634 ☴.svg巽。因为上卦是代表风的巽卦、下卦是代表山的艮卦，故通称为“风山渐”。序卦传：渐者，进也。为“渐进”之意。", R.drawable.hex53, "110100");
        helper.getPostDAO().create(hex);
        hex = new Hex(54, "Hexagram 54 is named 歸妹 (guī mèi), \"Converting the Maiden\". Other variations include \"the marrying maiden\" and \"returning maiden\". Its inner trigram is ☱ (兌 duì) open = (澤) swamp, and its outer trigram is ☳ (震 zhèn) shake = (雷) thunder.", "归妹，六十四卦中第五十四卦。内卦（下卦）Trigramme2631 ☱.svg兑、外卦（上卦）Trigramme2633 ☳.svg震。因为上卦是代表雷的震卦、下卦是代表泽的兑卦，所以通称为“雷泽归妹”。序卦传云：归妹，女之终也。形容“女子出嫁”。", R.drawable.hex54, "001011");
        helper.getPostDAO().create(hex);
        hex = new Hex(55, "Hexagram 55 is named 豐 (fēng), \"Abounding\". Other variations include \"abundance\" and \"fullness\". Its inner trigram is ☲ (離 lí) radiance = (火) fire, and its outer trigram is ☳ (震 zhèn) shake = (雷) thunder.", "丰，六十四卦中第五十五卦。内卦（下卦）Trigramme2632 ☲.svg离、外卦（上卦）Trigramme2633 ☳.svg震。因为上卦是代表雷的震卦、下卦是代表火的离卦，所以通称为“雷火丰”。为“丰盛”之意。", R.drawable.hex55, "001101");
        helper.getPostDAO().create(hex);
        hex = new Hex(56, "Hexagram 56 is named 旅 (lǚ), \"Sojourning\". Other variations include \"the wanderer\" and \"traveling\". Its inner trigram is ☶ (艮 gèn) bound = (山) mountain, and its outer trigram is ☲ (離 lí) radiance = (火) fire.", "旅，六十四卦中第五十六卦。内卦（下卦）Trigramme2636 ☶.svg艮、外卦（上卦）Trigramme2632 ☲.svg离。因为上卦是代表火的离卦、下卦是代表山的艮卦，故通称为“火山旅”。为“探索”之意。", R.drawable.hex56, "101100");
        helper.getPostDAO().create(hex);
        hex = new Hex(57, "Hexagram 57 is named 巽 (xùn), \"Ground\". Other variations include \"the gentle (the penetrating, wind)\" and \"calculations\". Both its inner and outer trigrams are ☴ (巽 xùn) ground = (風) wind.", "巽（拼音：xùn，注音：ㄒㄩㄣˋ，中古拼音：suonh），六十四卦中第五十七卦。上下卦皆由八卦中代表‘风’的Trigramme2634 ☴.svg巽所组成，因此通称为“巽为风”。序卦传云：巽者，入也。象征风“无孔不入”的特性。", R.drawable.hex57, "110110");
        helper.getPostDAO().create(hex);
        hex = new Hex(58, "Hexagram 58 is named 兌 (duì), \"Open\". Other variations include \"the joyous, lake\" and \"usurpation\". Both its inner and outer trigrams are ☱ (兌 duì) open = (澤) swamp.", "兑（拼音：duì，注音：ㄉㄨㄟˋ，中古拼音：duad）六十四卦卦第五十八卦。上下卦皆是由八卦中代表(沼)泽的Trigramme2631 ☱.svg兑所组成，是故又通称为“兑为泽”。序卦传云：兑者，悦也。代表“喜悦”之意。", R.drawable.hex58, "011011");
        helper.getPostDAO().create(hex);
        hex = new Hex(59, "Hexagram 59 is named 渙 (huàn), \"Dispersing\". Other variations include \"dispersion (dissolution)\" and \"dispersal\". Its inner trigram is ☵ (坎 kǎn) gorge = (水) water, and its outer trigram is ☴ (巽 xùn) ground = (風) wind.", "涣，为六十四卦中第五十九卦。内卦（下卦）Trigramme2635 ☵.svg坎、外卦（上卦）Trigramme2634 ☴.svg巽。因为上卦是代表风的巽卦、下卦是代表水的坎卦，故通称为“风水涣”。序卦传：涣者，离散也。有“涣散”的意思。", R.drawable.hex59, "110010");
        helper.getPostDAO().create(hex);
        hex = new Hex(60, "Hexagram 60 is named 節 (jié), \"Articulating\". Other variations include \"limitation\" and \"moderation\". Its inner trigram is ☱ (兌 duì) open = (澤) swamp, and its outer trigram is ☵ (坎 kǎn) gorge = (水) water.", "节，六十四卦中第六十卦。内卦（下卦）Trigramme2631 ☱.svg兑、外卦（上卦）Trigramme2635 ☵.svg坎。因为上卦是代表水的坎卦、下卦是代表泽的兑卦，所以通称为“水泽节”。序卦传：节，止也。有“节止”、“节制”之意。", R.drawable.hex60, "010011");
        helper.getPostDAO().create(hex);
        hex = new Hex(61, "Hexagram 61 is named 中孚 (zhōng fú), \"Center Returning\". Other variations include \"inner truth\" and \"central return\". Its inner trigram is ☱ (兌 duì) open = (澤) swamp, and its outer trigram is ☴ (巽 xùn) ground = (風) wind.", "中孚（“孚”，拼音：fú，中古拼音：phyo），是六十四卦中第六十一卦。内卦（下卦）Trigramme2631 ☱.svg兑、外卦（上卦）Trigramme2634 ☴.svg巽。因为上卦是代表风的巽卦、下卦是代表泽的兑卦，所以通称为“风泽中孚”。序卦传：中孚，中孚，信也。代表“诚信”之意。", R.drawable.hex61, "110011");
        helper.getPostDAO().create(hex);
        hex = new Hex(62, "Hexagram 62 is named 小過 (xiǎo guò), \"Small Exceeding\". Other variations include \"preponderance of the small\" and \"small surpassing\". Its inner trigram is ☶ (艮 gèn) bound = (山) mountain, and its outer trigram is ☳ (震 zhèn) shake = (雷) thunder.", "小过，是六十四卦中第六十二卦。内卦（下卦）Trigramme2636 ☶.svg艮、外卦（上卦）Trigramme2633 ☳.svg震。因为上卦是代表雷的震卦、下卦是代表山的艮卦，所以通称为“雷山小过”。为“稍有过失”之意。", R.drawable.hex62, "001100");
        helper.getPostDAO().create(hex);
        hex = new Hex(63, "Hexagram 63 is named 既濟 (jì jì), \"Already Fording\". Other variations include \"after completion\" and \"already completed\" or \"already done\" . Its inner trigram is ☲ (離 lí) radiance = (火) fire, and its outer trigram is ☵ (坎 kǎn) gorge = (水) water.", "既济，六十四卦中第六十三卦。内卦（下卦）Trigramme2632 ☲.svg离、外卦（上卦）Trigramme2635 ☵.svg坎，因为上卦是代表水的坎卦、下卦是代表火的离卦，所以通称为“水火既济”。序卦传云：既济，定也，有安定之象。", R.drawable.hex63, "010101");
        helper.getPostDAO().create(hex);
        hex = new Hex(64, "Hexagram 64 is named 未濟 (wèi jì), \"Not Yet Fording\". Other variations include \"before completion\" and \"not yet completed\". Its inner trigram is ☵ (坎 kǎn) gorge = (水) water, and its outer trigram is ☲ (離 lí) radiance = (火) fire.", "未济，六十四卦中最后一卦。内卦（下卦）Trigramme2635 ☵.svg坎、外卦（上卦）Trigramme2632 ☲.svg离。因为上卦是代表火的离卦、下卦是代表水的坎卦，所以通称为“火水未济”。代表“事未成”之意。", R.drawable.hex64, "101010");
        helper.getPostDAO().create(hex);

    }


    class HexagramAdapter extends BaseAdapter {
        private Context mContext;
        private List<Hex> list;

        public HexagramAdapter(Context c, List<Hex> List) {
            mContext = c;
            this.list = List;
        }


        @Override
        public int getCount() {
            return list.size();
        }

        @Override
        public Object getItem(int position) {
            return null;
        }

        @Override
        public long getItemId(int position) {
            return 0;
        }

        // create a new ImageView for each item referenced by the Adapter
        public View getView(int position, View convertView, ViewGroup parent) {
            ImageView imageView;
            if (convertView == null) {  // if it's not recycled, initialize some attributes
                imageView = new ImageView(mContext);
                imageView.setPadding(10, 10, 10, 10);
                imageView.setAnimation(anim);
                imageView.getAnimation().setDuration(position * 100 + 1000);
            } else {
                imageView = (ImageView) convertView;
            }
            imageView.setImageResource(list.get(position).getImageSource());
            return imageView;
        }
    }
}
