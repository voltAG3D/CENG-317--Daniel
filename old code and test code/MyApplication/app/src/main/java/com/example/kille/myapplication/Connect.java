package com.example.kille.myapplication;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.Socket;
import java.net.UnknownHostException;
import android.os.AsyncTask;
import android.os.Bundle;
import android.app.Activity;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class Connect extends AppCompatActivity {

    TextView textResponse;
    EditText editTextAddress, editTextPort;
    Button buttonConnect, buttonClear, btnBack;

protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_connect);

        editTextAddress = (EditText)findViewById(R.id.address);
        editTextPort = (EditText)findViewById(R.id.port);
        buttonConnect = (Button)findViewById(R.id.connect);
        buttonClear = (Button)findViewById(R.id.clear);
        textResponse = (TextView)findViewById(R.id.response);
        btnBack = (Button)findViewById(R.id.back);
        buttonConnect.setOnClickListener(buttonConnectOnClickListener);


    btnBack.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View view) {

            finish();
        }
    });

    buttonClear.setOnClickListener(new OnClickListener(){
        @Override
        public void onClick(View v) {
        textResponse.setText("");
        }});
        }

    OnClickListener buttonConnectOnClickListener =
        new OnClickListener(){

            @Override
        public void onClick(View arg0) {
        MyClientTask myClientTask = new MyClientTask(
        editTextAddress.getText().toString(),
        Integer.parseInt(editTextPort.getText().toString()));
        myClientTask.execute();
        }};


public class MyClientTask extends AsyncTask<Void, Void, Void> {

    String dstAddress;
    int dstPort;
    String response;

    MyClientTask(String addr, int port){
        dstAddress = addr;
        dstPort = port;
    }
    @Override
    protected Void doInBackground(Void... arg0) {
        try {
            Socket socket = new Socket(dstAddress, dstPort);
            InputStream inputStream = socket.getInputStream();
            ByteArrayOutputStream byteArrayOutputStream =
                    new ByteArrayOutputStream(1024);
            byte[] buffer = new byte[1024];
            int bytesRead;
            while ((bytesRead = inputStream.read(buffer)) != -1){
                byteArrayOutputStream.write(buffer, 0, bytesRead);
            }
            socket.close();
            response = byteArrayOutputStream.toString("UTF-8");
        } catch (UnknownHostException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return null;
    }
    @Override
    protected void onPostExecute(Void result) {
        textResponse.setText(response);
        super.onPostExecute(result);
    }
}

}