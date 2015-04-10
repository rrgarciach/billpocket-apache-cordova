package com.billpocket.cordova.billpocketplugin;

import org.apache.cordova.CordovaWebView;
import org.apache.cordova.CallbackContext;
import org.apache.cordova.CordovaPlugin;
import org.apache.cordova.CordovaInterface;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Typeface;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import android.view.inputmethod.InputMethodManager;
import android.widget.AdapterView;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;
import java.util.ArrayList;

import android.util.Log;
import android.provider.Settings;
import android.widget.Toast;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class BillPocketPlugin extends CordovaPlugin {
	public static final String TAG = "BillPocket Plugin";
    public static final int BP_INTENT_CODE = 666;
    Activity activity;
    CallbackContext callbackContext;

	public BillPocketPlugin() {}

	public void initialize(CordovaInterface cordova, CordovaWebView webView) {
		super.initialize(cordova, webView);
		Log.v(TAG,"Init ToastPlugin");
	}

	@Override
	public boolean execute(String action, JSONArray args, CallbackContext callbackContext) throws JSONException {
		Log.v(TAG,"BillPocketPlugin received: " + action);
//		final int duration = Toast.LENGTH_SHORT;
        if ("sendPayment".equals(action)) {
//            cordova.getActivity().runOnUiThread(new Runnable() {
//                public void run() {
//                    JSONArray data = args;
                    this.sendPayment(args, callbackContext);
//                    Toast toast = Toast.makeText(cordova.getActivity().getApplicationContext(), action, duration);
//                    toast.show();
//                     callbackContext.success();
//                }
//            });
            return true;
        }
        Log.v(TAG,"MethodNotFound");
        return false; // Returning false results in a "MethodNotFound" error.
	}
    
    private void sendPayment(JSONArray args, CallbackContext callbackContext) throws JSONException {
        Intent intent = new Intent("billpocket.payments.START");

        String urlScheme = args.getString(0);

        if (urlScheme.length() > 0) {
            intent.putExtra("urlScheme", urlScheme);
        }

        intent.putExtra("amount", args.getString(1));
        intent.putExtra("pin", args.getString(2));
        intent.putExtra("transaction", args.getString(3));
        intent.putExtra("transactionid", args.getString(4));
        intent.putExtra("tip", args.getString(5));
        intent.putExtra("identifier", args.getString(6));
        intent.putExtra("reference", args.getString(7));
        intent.putExtra("email", args.getString(8));
        intent.putExtra("phone", args.getString(9));

        this.callbackContext = callbackContext;
        cordova.getActivity().startActivityForResult(intent, BP_INTENT_CODE);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent intent){
        Bundle extras = intent.getExtras();

        if (extras == null){
            return;
        }
        String response = extras.getString("result");

        if(response.equals("aprobada")){
            Toast.makeText(cordova.getActivity(),"Accion realizada con exito",Toast.LENGTH_SHORT).show();
        } else if (response.equals("declinada")){
            Toast.makeText(cordova.getActivity(),"transacion rechazada",Toast.LENGTH_SHORT).show();
        }else{
            Toast.makeText(cordova.getActivity(),"ERROR DE BILLPOCKET",Toast.LENGTH_SHORT).show();
        }
        this.callbackContext.success(response);
    }
}