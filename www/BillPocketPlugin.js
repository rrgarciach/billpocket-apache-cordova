console.log("Setting BillPocketPlugin...");
var exec = require('cordova/exec');

module.exports = {
	sendPayment: function(arrParams, callback) {
        var urlScheme, amount, pin, transaction, transactionid, tip, identifier, reference, email, phone;
        if (arrParams.urlScheme.length > 0 && typeof arrParams.urlScheme != 'undefined') {
            app.arrData.push(arrParams.urlScheme);
        } else {
            app.arrData.push(arrParams.urlScheme);
            app.arrData.push( parseFloat(arrParams.amount).toFixed(2) );
            app.arrData.push(arrParams.pin);
            app.arrData.push(arrParams.transaction);
            app.arrData.push(arrParams.transactionid);
            app.arrData.push(arrParams.tip);
            app.arrData.push(arrParams.identifier);
            app.arrData.push(arrParams.reference);
            app.arrData.push(arrParams.email);
            app.arrData.push(arrParams.phone);
        }
        console.log("BillPocketPlugin.js: added to scope.");
        exec(callback, callback, "BillPocketPlugin", "sendPayment", arrParams);
	},
    callbacksuccess: function(message) {
        return message;
    },
    callbackerror: function() {
        return false;
    }
};