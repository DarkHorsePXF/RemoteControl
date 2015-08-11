package com.dk.remotecontrol.app.activity;

import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.*;
import com.dk.remotecontrol.app.R;
import com.dk.remotecontrol.app.common.CMD;
import com.dk.remotecontrol.app.common.Controller;
import com.dk.remotecontrol.app.common.NetContent;
import com.dk.remotecontrol.app.db.DBHelper;
import com.dk.remotecontrol.app.util.ToastUtil;

import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;


public class MainActivity extends ActionBarActivity {
    private EditText etIP;
    private Button btnShutDown;
    private Spinner spIP;
    private ExecutorService executor;
    private Socket socket;
    private NetContent netContent;
    private String ip;
    private Context mContext;
    private SQLiteDatabase db;

    private final int LINK_SHUTDOWN_SUCCESS_MSG = 0x11;
    private final int LINK_ERROR_MSG = 0x31;


    private final String SQL_QUERY_IP="" +
            "SELECT * " +
            "FROM connect_ip";

    private Handler mHandler= new Handler()
    {
        @Override
        public void handleMessage(Message msg) {
            switch (msg.what){
                case LINK_SHUTDOWN_SUCCESS_MSG:{
                    ToastUtil.shortToast(mContext, getResources().getString(R.string.shutdown_success));
                    insertIP2DataBase(db, ip);
                    break;
                }
                case LINK_ERROR_MSG:{
                    ToastUtil.shortToast(mContext,getResources().getString(R.string.connect_error));
                    break;
                }
            }
            super.handleMessage(msg);
        }
    };

    private void insertIP2DataBase(SQLiteDatabase db, String ip) {
        try {
            final String SQL_INSERT_IP= "" +
                    "INSERT " +
                    "INTO connect_ip(ip) " +
                    "VALUES(\'" +
                    ip+
                    "\')";
            db.execSQL(SQL_INSERT_IP);
        } catch (SQLException e) {
            Log.v("sqlite",getResources().getString(R.string.ip_exists));
        }
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        init();
    }

    private void init() {
        mContext=this;

        etIP = (EditText) findViewById(R.id.host_input_et);
        btnShutDown= (Button) findViewById(R.id.shutdown_btn);
        spIP = (Spinner) findViewById(R.id.select_ip_sp);

        DBHelper dbHelper=new DBHelper(mContext);
        db=dbHelper.getWritableDatabase();

        Cursor cursor=db.rawQuery(SQL_QUERY_IP,null);
        final String[] items=new String[cursor.getCount()];
        int index=0;
        while (cursor.moveToNext()){
            items[index]=cursor.getString(1);
        }

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(mContext,R.layout.layout_ip_list,R.id.ip_tv,items);
        spIP.setAdapter(adapter);
        spIP.setClickable(false);
        spIP.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                etIP.setText(items[position]);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        executor= Executors.newCachedThreadPool();

        btnShutDown.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ip = etIP.getText().toString();
                requestSocket(Controller.SHUTDOWN);
            }
        });
    }

    private void requestSocket(int cmd) {
        switch (cmd){
            case Controller.SHUTDOWN:{
                SocketClient task=new SocketClient(CMD.SHUTDOWN);
                executor.execute(task);
            }
        }
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
    class SocketClient implements Runnable{
        String input;
        SocketClient(String input){
            this.input=input;
        }

        @Override
        public void run() {
            try {
                netContent=new NetContent(ip);
                socket=new Socket(netContent.getHost(), netContent.getPort());

                if (!socket.isConnected()||socket.isClosed()||!socket.isBound()){
                    mHandler.sendEmptyMessage(LINK_ERROR_MSG);
                    return;
                }

                socket.setSoTimeout(5*1000);
                mHandler.sendEmptyMessage(LINK_SHUTDOWN_SUCCESS_MSG);
                DataOutputStream dos=new DataOutputStream(socket.getOutputStream());
                dos.writeUTF(input);
                dos.flush();
                dos.close();
            } catch (IOException e) {
                mHandler.sendEmptyMessage(LINK_ERROR_MSG);
            }
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (!executor.isShutdown()){
            executor.shutdown();
        }
    }
}
