package com.example.prf.Kminer.activities.main;

import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;

import com.example.prf.Kminer.R;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

import protocol.MessageType;
import protocol.RequestMessage;
import protocol.ResponseMessage;


class FetchDiscoverDataAsyncTask extends AsyncTask<String, Void, String> {
    private static final String TAG = "FetchDiscoverAsyncTask";

    Context context;

    public FetchDiscoverDataAsyncTask(Context context) {
        this.context = context;
    }


    @Override
    protected String doInBackground(String... strings) {
        String serverAddress = context.getResources().getString(R.string.server_address);
        String jsonString = "";
        RequestMessage req = null;

        try (
                Socket socket = new Socket(serverAddress, 9999);
                ObjectOutputStream out = new ObjectOutputStream(socket.getOutputStream());
                ObjectInputStream in = new ObjectInputStream(socket.getInputStream())
        ) {



            if("DISCOVER".equals(strings[0])){
                req = new RequestMessage(MessageType.DISCOVER);
            }
            else if("READ".equals(strings[0])) {
                req = new RequestMessage(MessageType.READ);
            }

            req.addBodyField("table", strings[1]);
            req.addBodyField("clusters", strings[2]);
            req.addBodyField("sendJson", "true");

            out.writeObject(req);

            ResponseMessage resp = (ResponseMessage) in.readObject();

            if (resp.getStatus().equals("OK")) {
                jsonString = resp.getBodyField("data");
            }

        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }

        return jsonString;
    }

    @Override
    protected void onPostExecute(String result) {
        super.onPostExecute(result);

        Log.d(TAG, result);

        if (result.isEmpty()) {

            //TODO SHOW error
        } else {
            new ParseJsonAsyncTask(context).execute(result);
        }

    }
}
