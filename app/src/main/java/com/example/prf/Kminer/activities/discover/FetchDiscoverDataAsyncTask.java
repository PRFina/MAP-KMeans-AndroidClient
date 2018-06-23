package com.example.prf.Kminer.activities.discover;

import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;

import com.example.prf.Kminer.R;
import com.example.prf.Kminer.models.ClusterSet;

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


        String jsonString = "";
        String address = context.getResources()
                .getString(R.string.server_address);


        try (
                Socket socket = new Socket(address, 9999);
                ObjectOutputStream out = new ObjectOutputStream(socket.getOutputStream());
                ObjectInputStream in = new ObjectInputStream(socket.getInputStream())
        ) {


            RequestMessage req = new RequestMessage(MessageType.DISCOVER);
            req.addBodyField("table", strings[0]);
            req.addBodyField("clusters", strings[1]);
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
