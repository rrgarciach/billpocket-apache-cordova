console.log("Setting BillPocketPlugin...");
var exec = require('cordova/exec');

module.exports = {
	sendPayment: function(arrParams) {
        console.log("BillPocketPlugin.js: added to scope.");
        exec( function(result){ console.log("OK" + reply) }, function(result){ console.log("Error" + reply) }, "BillPocketPlugin", "sendPayment", arrParams);
	}
};