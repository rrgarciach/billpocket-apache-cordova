# BillPocket Plugin for Apache Cordova
#### (Android platform only)

## Installation

- Install Apache Cordova:

``npm i cordova -g``

- Add Android platform to project:

``cordova platform add android``

- Install plugin from this repo:

``cordova plugin https://github.com/rrgarciach/billpocket-apache-cordova``

- Implement it in your code:

```javascript
// a literal with sale data
var sale = {
	// urlScheme: 1234, // URL is optional
	amount: 9.99, 
	pin: 123456 , 
	transaction: 'venta', 
	transactionid: 1000, 
	tip: 1.00, 
	identifier: 1, 
	reference: 'venta tienda', 
	email: 'email@email.com', 
	phone: 55555555
};

// callback to handle response
function successCallback(message) {
	alert(message);
}

// submit payment to device
BillPocketPlugin.sendPayment(sale, successCallback);

```

- Run your app:

```cordova run android```

Enjoy it!