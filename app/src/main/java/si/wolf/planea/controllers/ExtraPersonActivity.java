package si.wolf.planea.controllers;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.database.sqlite.SQLiteOpenHelper;
import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.util.Log;
import android.util.TypedValue;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;
import java.util.ArrayList;

import si.wolf.planea.database.DatabaseHelper;
import si.wolf.planea.HelperUtils.HelperUtilities;

import com.baoyz.swipemenulistview.SwipeMenu;
import com.baoyz.swipemenulistview.SwipeMenuCreator;
import com.baoyz.swipemenulistview.SwipeMenuItem;
import com.baoyz.swipemenulistview.SwipeMenuListView;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;


import java.util.ArrayList;

import si.wolf.planea.R;
import si.wolf.planea.models.Client;

public class ExtraPersonActivity extends AppCompatActivity {

    private static final int REQUEST_CODE = 999;
    private SQLiteOpenHelper databaseHelper;
    private SQLiteDatabase db;

    private SwipeMenuListView mListView;
    private ArrayList<String> mArrayList=new ArrayList<>();
    private ListDataAdapter mListDataAdapter;

    ArrayList<Client> sopotniki;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_extra_person);

        initControls();
        sopotniki = new ArrayList<Client>(); // Create an ArrayList object
    }
    private void initControls() {

        mListView=(SwipeMenuListView)findViewById(R.id.ep_listView);
        mListView.setSwipeDirection(SwipeMenuListView.DIRECTION_LEFT);

        //for (int i=0;i<5;i++){
        //    mArrayList.add("List item --> "+i);
        //}
        // mListView.setCloseInterpolator(new BounceInterpolator());

        mListDataAdapter=new ListDataAdapter();
        mListView.setAdapter(mListDataAdapter);


        SwipeMenuCreator creator = new SwipeMenuCreator() {

            @Override
            public void create(SwipeMenu menu) {
                // Create different menus depending on the view type

                // create "delete" item
                SwipeMenuItem deleteItem = new SwipeMenuItem(
                        getApplicationContext());
                // set item background
                deleteItem.setBackground(new ColorDrawable(Color.rgb(0xF9,
                        0x3F, 0x25)));
                // set item width
                deleteItem.setWidth(dp2px(90));
                // set a icon
                deleteItem.setIcon(R.drawable.ic_delete);
                // add to menu
                menu.addMenuItem(deleteItem);
            }
        };

        // set creator
        mListView.setMenuCreator(creator);

        mListView.setOnMenuItemClickListener(new SwipeMenuListView.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(int position, SwipeMenu menu, int index) {

                switch (index) {
                    case 0:
                        mArrayList.remove(position);
                        mListDataAdapter.notifyDataSetChanged();
                        Toast.makeText(ExtraPersonActivity.this,("Sopotnik + " + sopotniki.get(position).getFirstName() + " odstranjen"),Toast.LENGTH_SHORT).show();
                        sopotniki.remove(position);
                        break;
                }
                return true;
            }
        });

        //mListView

        mListView.setOnMenuStateChangeListener(new SwipeMenuListView.OnMenuStateChangeListener() {
            @Override
            public void onMenuOpen(int position) {

            }

            @Override
            public void onMenuClose(int position) {

            }
        });

        mListView.setOnSwipeListener(new SwipeMenuListView.OnSwipeListener() {
            @Override
            public void onSwipeStart(int position) {

            }

            @Override
            public void onSwipeEnd(int position) {

            }
        });

        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), ExtraPersonAddActivity.class);
                intent.putExtra("key", "value");
                startActivityForResult(intent, REQUEST_CODE);
            }
        });

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK && requestCode == REQUEST_CODE) {
            if (data.hasExtra("myData1")) {

                Bundle bundle = data.getExtras();
                Client cobject1 = (Client) (Client)bundle.getSerializable("myData1");

                sopotniki.add(cobject1);
                mArrayList.add(cobject1.getFirstName());
                mListDataAdapter.notifyDataSetChanged();

                //Toast.makeText(this, ("Ime: " + cobject1.getFirstName()),
                //        Toast.LENGTH_SHORT).show();
            }
        }
    }

    class ListDataAdapter extends BaseAdapter {

        ViewHolder holder;
        @Override
        public int getCount() {
            return mArrayList.size();
        }

        @Override
        public Object getItem(int i) {
            return null;
        }

        @Override
        public long getItemId(int i) {
            return 0;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {

            if(convertView==null){

                holder=new ViewHolder();

                convertView=getLayoutInflater().inflate(R.layout.e_list_item,null);

                holder.mTextview=(TextView)convertView.findViewById(R.id.textView);

                convertView.setTag(holder);
            }else {

                holder= (ViewHolder) convertView.getTag();
            }

            holder.mTextview.setText(mArrayList.get(position));

            return convertView;
        }

        class ViewHolder {

            TextView mTextview;

        }
    }
    private int dp2px(int dp) {
        return (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, dp,
                getResources().getDisplayMetrics());
    }

    //Make button go home instead to profile page
    @Override
    public void onBackPressed() {
        Log.d("CDA", "onBackPressed Called");
        Intent home = new Intent(getApplicationContext(),
                MainActivity.class);

        Bundle bundle = new Bundle();
        bundle.putSerializable("sopotniki", sopotniki);
        home.putExtras(bundle);
        startActivity(home);
        finish();
    }

    //Make action back button behave the same as back button (go home instead of profile page)
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            onBackPressed();
            return true;
        }
        return false;
    }
}
