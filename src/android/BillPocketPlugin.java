package com.billpocket.cordova.billpocketplugin;

import org.apache.cordova.CordovaWebView;
import org.apache.cordova.CallbackContext;
import org.apache.cordova.CordovaPlugin;
import org.apache.cordova.CordovaInterface;
import android.util.Log;
import android.provider.Settings;
import android.widget.Toast;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class BillPocketPlugin extends CordovaPlugin {
	public static final String TAG = "BillPocket Plugin";

	public BillPocketPlugin() {}

	public void initialize(CordovaInterface cordova, CordovaWebView webView) {
		super.initialize(cordova, webView);
		Log.v(TAG,"Init ToastPlugin");
	}

	@Override
	public boolean execute(final String action, JSONArray args, CallbackContext callbackContext) throws JSONException {
		final int duration = Toast.LENGTH_SHORT;
		Log.v(TAG,"BillPocketPlugin received: " + action);
        if("sendPayment".equals(action)) {
            cordova.getActivity().runOnUiThread(new Runnable() {
                public void run() {
                    JSONArray data = args;
                    this.sendPayment(data, callbackContext);
//                    Toast toast = Toast.makeText(cordova.getActivity().getApplicationContext(), action, duration);
//                    toast.show();
//                     callbackContext.success();
                }
            });
            return true;
        }
        Log.v(TAG,"MethodNotFound");
        return false; // Returning false results in a "MethodNotFound" error.
	}
    
    public void sendPayment(JSONArray args, CallbackContext callbackContext) {
        Intent intent = new Intent("billpocket.payments.START");

        String urlScheme = args.get("urlScheme");

        if (urlScheme.length() > 0) {
            intent.putExtra("urlScheme", urlScheme);
        }

        intent.putExtra("amount", args.get("amount"));
        intent.putExtra("pin", args.get("pin"));
        intent.putExtra("transaction", args.get("transaction"));
        intent.putExtra("transactionid", args.get("transactionid"));
        intent.putExtra("tip", args.get("tip"));
        intent.putExtra("identifier", args.get("identifier"));
        intent.putExtra("reference", args.get("reference"));
        intent.putExtra("email", args.get("email"));
        intent.putExtra("phone", args.get("phone"));

        startActivityForResult(intent, BP_INTENT_CODE);
    }



    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent intent){
        Bundle extras = intent.getExtras();

        if (extras == null){
            return;
        }
        String response = extras.getString("result");

        if(response.equals("aprobada")){
            Intent intenAprovado = new Intent(getActivity(), MenuActivity.class);
            startActivity(intenAprovado);
            Toast.makeText(getActivity(),"Accion realizada con exito",Toast.LENGTH_SHORT).show();
        } else if (response.equals("declinada")){
            Toast.makeText(getActivity(),"transacion rechazada",Toast.LENGTH_SHORT).show();
        }else{
            Toast.makeText(getActivity(),"ERROR DE BILLPOCKET",Toast.LENGTH_SHORT).show();
        }
    }
}