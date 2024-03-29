package it.kminer.activities.main.asyncTasks;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.preference.PreferenceManager;
import android.support.design.widget.Snackbar;
import android.util.Log;
import android.view.View;

import it.kminer.R;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

import protocol.MessageType;
import protocol.RequestMessage;
import protocol.ResponseMessage;

/**
 * This class execute an async task to retrieve data from server.
 * This class use {@link protocol} classes for client-server communication.
 */
public final class FetchDataAsyncTask extends AsyncTask<String, Void, ResponseMessage> {
    private static final String TAG = "FetchDiscoverAsyncTask";
    private String serverAddress;
    private int serverPort;
    private Context context;
    private View rootView;

    public FetchDataAsyncTask(Context context, View rootView) {
        this.context = context;
        this.rootView = rootView;
        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(context);
        serverAddress = prefs.getString("server_address","10.0.2.2");
        serverPort = Integer.parseInt(prefs.getString("server_port","9999"));
    }

    /**
     * Send request to the server
     * @param strings request parameters
     * @return a  response message sent from server
     */
    @Override
    protected ResponseMessage doInBackground(String... strings) {

        RequestMessage req;
        ResponseMessage resp = null;

        try (
            Socket socket = new Socket(serverAddress, serverPort);
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

    /**
     * Start a {@link it.kminer.activities.main.asyncTasks.ParseJsonAsyncTask}
     * with json data extracted from the server response.
     *
     * @param result the server response
     */
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
            Snackbar.make(rootView, "Sorry, can't retrieve data from server. Try later!",Snackbar.LENGTH_LONG).show();
        }

    }
}
