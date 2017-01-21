(function() {
	var app = angular.module('gemStore', []);

	app.controller('StoreController', function() {
		this.products = gems;
	});

	var gems = [ {
		name : 'Dodecahedron',
		price : 2.95,
		description : 'test',
		canPruchase : true
	}, {
		name : 'Dodecahedron Gem',
		price : 10.2,
		description : 'trop',
		canPruchase : true
	} ];

})();