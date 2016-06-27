(function() {
	var app = angular.module('gemStore', []);

	app.controller('StoreController', function() {
		this.product = gem;
	});

	var gem = {
		name : 'Dodecahedron',
		price : 2.95,
		description : 'test'
	};

})();