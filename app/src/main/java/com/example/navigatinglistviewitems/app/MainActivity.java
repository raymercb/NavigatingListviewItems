package com.example.navigatinglistviewitems.app;

import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;


public class MainActivity extends ActionBarActivity {

    private HashMap<String, List<String>> channels = new HashMap<String, List<String>>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        loadSampleData();

        ListView listView1 = (ListView) findViewById(R.id.listView1);
        MyAdapter myAdapter = new MyAdapter(channels);
        listView1.setAdapter(myAdapter);

    }

    private void loadSampleData() {
        List<String> episodes1 = new ArrayList<String>();
        episodes1.add("Episode 1");
        episodes1.add("Episode 2");
        episodes1.add("Episode 3");
        channels.put("Channel 1", episodes1);

        List<String> episodes2 = new ArrayList<String>();
        episodes2.add("Episode 1");
        episodes2.add("Episode 2");
        channels.put("Channel 2", episodes2);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    public class MyAdapter extends BaseAdapter {
        private final ArrayList mData;

        public MyAdapter(HashMap<String, List<String>> channels) {
            mData = new ArrayList();
            mData.addAll(channels.entrySet());
        }

        @Override
        public int getCount() {
            return mData.size();
        }

        @Override
        public HashMap.Entry<String, List<String>> getItem(int position) {
            return (HashMap.Entry) mData.get(position);
        }

        @Override
        public long getItemId(int i) {
            // TODO implement you own logic with ID
            return 0;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            final View result;

            if (convertView == null) {
                result = LayoutInflater.from(parent.getContext()).inflate(R.layout.row_layout, parent, false);
            } else {
                result = convertView;
            }

            LinearLayout container = (LinearLayout) result.findViewById(R.id.layout_container);
            container.removeAllViews();

            HashMap.Entry<String, List<String>> channel = getItem(position);

            for (String episode : channel.getValue()) {
                Button tv = (Button) container.inflate(container.getContext(), R.layout.episode_button, null);
                tv.setText(episode);
                container.addView(tv);
            }

            return result;
        }
    }
}
