console.log("Setting BillPocketPlugin...");
var exec = require('cordova/exec');

module.exports = {
	console.log("BillPocketPlugin.js: added to scope.");
	sendPayment: function(arrParams) {
	exec( function(result){ console.log("OK" + reply) }, function(result){ console.log("Error" + reply) }, "BillPocketPlugin", "sendPayment", arrParams);
	}
};