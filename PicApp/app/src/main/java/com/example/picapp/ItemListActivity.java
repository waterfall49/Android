package com.example.picapp;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;
import androidx.appcompat.widget.Toolbar;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import com.example.picapp.dummy.DummyContent;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.lang.reflect.Type;
import java.util.ArrayList;

/**
 * An activity representing a list of Items. This activity
 * has different presentations for handset and tablet-size devices. On
 * handsets, the activity presents a list of items, which when touched,
 * lead to a {@link ItemDetailActivity} representing
 * item details. On tablets, the activity presents the list of items and
 * item details side-by-side using two vertical panes.
 */
public class ItemListActivity extends AppCompatActivity {

    private static String color = "#808080";
    private static int choice = 6;
    ArrayList<DummyContent.DummyItem> myArrayList;
    DBadapter db = new DBadapter(this);

    public static String[] nameArray;
    public static Integer[] imageId = {R.drawable.cappuccino, R.drawable.flatwhite, R.drawable.gingerlatte,
            R.drawable.hazelapo, R.drawable.lattehazel, R.drawable.longblackice, R.drawable.macchiato,
            R.drawable.mochalatte, R.drawable.reversointenso, R.drawable.tiracapu};


    /**
     * Whether or not the activity is in two-pane mode, i.e. running on a tablet
     * device.
     */
    private boolean mTwoPane;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        loadData();
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_item_list);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        toolbar.setTitle(getTitle());

        nameArray = getResources().getStringArray(R.array.coffee); //call StringArray from Strings.xml

        int COUNT = nameArray.length;
        if(DummyContent.ITEMS.isEmpty()){
            for (int i = 1; i <= COUNT; i++) {
                String temp = Integer.toString(i);
                DummyContent.addItem(new DummyContent.DummyItem(temp, nameArray[i-1], imageId[i-1]));
            }
        }

        try{
            String destPath = "/data/data/" + getPackageName() +"/database/MyDB";
            //Alternate way to do destPath:
            //String destPath = Environment.getExternalStorageDirectory().getPath() +
            //getPackageName() + "/database/MyDB";
            File f = new File(destPath);
            if(!f.exists()){
                CopyDB(getBaseContext().getAssets().open("mydb"),
                        new FileOutputStream(destPath));
            }

        }catch (FileNotFoundException e){
            e.printStackTrace();
        }catch (IOException e){
            e.printStackTrace();
        }

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Previous choices were reset!", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
                myArrayList = new ArrayList<>(); //reset previous choice
                View recyclerView = findViewById(R.id.item_list); //restart recyclerview
                assert recyclerView != null;
                setupRecyclerView((RecyclerView) recyclerView);
                saveData();
            }
        });

        if (findViewById(R.id.item_detail_container) != null) {
            // The detail container view will be present only in the
            // large-screen layouts (res/values-w900dp).
            // If this view is present, then the
            // activity should be in two-pane mode.
            mTwoPane = true;
        }

        View recyclerView = findViewById(R.id.item_list);
        assert recyclerView != null;
        setupRecyclerView((RecyclerView) recyclerView);
    }//end onCreate

    private void setupRecyclerView(@NonNull RecyclerView recyclerView) {
        recyclerView.setAdapter(new SimpleItemRecyclerViewAdapter(this, DummyContent.ITEMS, mTwoPane));
    }

    public class SimpleItemRecyclerViewAdapter
            extends RecyclerView.Adapter<SimpleItemRecyclerViewAdapter.ViewHolder> {

        private final ItemListActivity mParentActivity;
        private final ArrayList<DummyContent.DummyItem> mValues;
        private final boolean mTwoPane;
        private final View.OnClickListener mOnClickListener = new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DummyContent.DummyItem item = (DummyContent.DummyItem) view.getTag();
                if (mTwoPane) {
                    Bundle arguments = new Bundle();
                    arguments.putString(ItemDetailFragment.ARG_ITEM_ID, item.id);
                    ItemDetailFragment fragment = new ItemDetailFragment();
                    fragment.setArguments(arguments);
                    mParentActivity.getSupportFragmentManager().beginTransaction()
                            .replace(R.id.item_detail_container, fragment)
                            .commit();
                } else {
                    Context context = view.getContext();
                    Intent intent = new Intent(context, ItemDetailActivity.class);
                    intent.putExtra(ItemDetailFragment.ARG_ITEM_ID, item.id);
                    context.startActivity(intent);
                }
                myArrayList.add(item);
                saveData();

                /**
                 * add to DB- CREATE
                 */
                db.open();
                String picName = item.pic_name;
                String imgName = Integer.toString(item.pic_image);
                long id = db.insertChoice(item.pic_name, Integer.toString(item.pic_image));
                db.close();

                System.out.println(myArrayList);
            }
        };

        SimpleItemRecyclerViewAdapter(ItemListActivity parent,
                                      ArrayList<DummyContent.DummyItem> items,
                                      boolean twoPane) {
            mValues = items;
            mParentActivity = parent;
            mTwoPane = twoPane;
        }

        // 뷰 홀더를 생성하는 메소드
        // 본 메소드에서 return된 값이 onBindViewHolder()에서 처리됨
        @Override
        public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View view = LayoutInflater.from(parent.getContext()) // 파라미터로 받은 ViewGroup의 context를 메모리에 로딩함
                    .inflate(R.layout.item_list_content, parent, false); // 뷰그룹에 각 아이템들을 붙임
            return new ViewHolder(view);
        }

        //앞으로 필요한 아이템을 리사이클러뷰에 띄우는 역할을 함
        //viewholder를 통해 아이템들을 가져오고
        //position 변수를 통해 현재까지 몇번째의 아이템을 로드했는지 표시해줌
        @Override
        public void onBindViewHolder(final ViewHolder holder, int position) {
            holder.mIdView.setText(mValues.get(position).id);
            holder.mContentView.setText(mValues.get(position).pic_name);

            //If I had choice, I cannot choose again
            for(int i=0; i<myArrayList.size(); i++){
                choice = (Integer.parseInt(myArrayList.get(i).id))-1;
                if(position == choice) {
                    holder.mIdView.setTextColor(Color.parseColor(color));
                    holder.mContentView.setTextColor(Color.parseColor(color));
                    holder.itemView.setEnabled(false);
                }
            }

            holder.itemView.setTag(mValues.get(position));
            holder.itemView.setOnClickListener(mOnClickListener);
        }

        @Override
        public int getItemCount() {
            return mValues.size();
        }

        class ViewHolder extends RecyclerView.ViewHolder {
            final TextView mIdView;
            final TextView mContentView;

            ViewHolder(View view) {
                super(view);  //전달받은 뷰 객체를 부모클래스의 변수에 저장
                mIdView = (TextView) view.findViewById(R.id.id_text);
                mContentView = (TextView) view.findViewById(R.id.content);

            }
        }
    }//end RecyclerViewAdapter

    /**
     * Save my Selections using SharedPreferences
     */
    private void saveData() {
        SharedPreferences prefs = getSharedPreferences("info", MODE_PRIVATE);
        SharedPreferences.Editor editor = prefs.edit();
        Gson gson = new Gson();
        String json = gson.toJson(myArrayList);
        editor.putString("mylist", json);
        editor.apply();
    }//end saveData

    /**
     * Load saved data when we open app again
     */
    private void loadData() {
        SharedPreferences prefs = getSharedPreferences("info", MODE_PRIVATE);
        Gson gson = new Gson();
        String json = prefs.getString("mylist", null);
        Type type = new TypeToken<ArrayList<DummyContent.DummyItem>>() {}.getType();
        System.out.println(type);
        myArrayList = gson.fromJson(json, type);

        if(myArrayList == null) {
            myArrayList = new ArrayList<>();
//            choice = 6;
        }

    }//end loadData


    public void CopyDB(InputStream inputStream, OutputStream outputStream)
            throws IOException{
        //copy 1k bytes at a time
        byte[] buffer = new byte[1024];
        int length;
        while((length = inputStream.read(buffer)) > 0)
        {
            outputStream.write(buffer,0,length);
        }
        inputStream.close();
        outputStream.close();

    }//end method CopyDB

}//end class
