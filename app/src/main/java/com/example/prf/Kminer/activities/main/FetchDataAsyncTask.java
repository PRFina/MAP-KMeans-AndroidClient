package com.example.prf.Kminer.activities.main;

import android.content.Context;
import android.os.AsyncTask;
import android.support.design.widget.Snackbar;
import android.util.Log;
import android.view.View;

import com.example.prf.Kminer.R;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

import protocol.MessageType;
import protocol.RequestMessage;
import protocol.ResponseMessage;

/**
 * This class execute an async task to retrieve data from server.
 *
 * This class use RequestMessage and ResponseMessage to communicate with server
 *
 * In a chainable way, the onPostExecute method call another Async tack to parse the json response
 *
 */

class FetchDataAsyncTask extends AsyncTask<String, Void, ResponseMessage> {
    private static final String TAG = "FetchDiscoverAsyncTask";

    private Context context;
    private View rootView;

    public FetchDataAsyncTask(Context context, View rootView) {
        this.context = context;
        this.rootView = rootView;
    }

    @Override
    protected ResponseMessage doInBackground(String... strings) {
        String serverAddress = context.getString(R.string.server_address); //TODO replace with app config

        RequestMessage req;
        ResponseMessage resp = null;

        try (
                Socket socket = new Socket(serverAddress, 9999);
                ObjectOutputStream out = new ObjectOutputStream(socket.getOutputStream());
                ObjectInputStream in = new ObjectInputStream(socket.getInputStream())
        ) {
            req = new RequestMessage(MessageType.valueOf(strings[0])); //Get MessageType enum value from parameter

            req.addBodyField("table", strings[1]);
            req.addBodyField("clusters", strings[2]);
            req.addBodyField("sendJson", "true");

            out.writeObject(req);

            resp = (ResponseMessage) in.readObject();

        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }

        return resp;
    }

    @Override
    protected void onPostExecute(ResponseMessage result) {
        super.onPostExecute(result);

        if(result != null) {
            if (result.getStatus().equals("OK")) {
                new ParseJsonAsyncTask(context).execute(result.getBodyField("data"));
                Log.d(TAG, result.getBodyField("data"));

            } else {
                Snackbar.make(rootView, result.getBodyField("errorMsg"),Snackbar.LENGTH_LONG).show();
                Log.d(TAG, result.getBodyField("errorMsg"));
            }
        }
        else{
            Snackbar.make(rootView, "Sorry, can't find any data. Try later!",Snackbar.LENGTH_LONG).show();
        }

    }
}
