console.log("Setting BillPocketPlugin...");
var exec = require('cordova/exec');

module.exports = {
	sendPayment: function(arrParams, callback) {
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