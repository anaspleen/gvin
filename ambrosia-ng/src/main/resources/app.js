'use strict';

var modules = [
  'ngResource',
  'ui.router',
  'ui.router.state.events',
  'ui.bootstrap',
  'toastr',
  'ambrosia.commons',
  'ambrosia.public'
];

angular.module('ambrosia', modules)
    .config(function ($stateProvider, $urlRouterProvider, NG_ROUTE)
    {
      // For any unmatched url
      $urlRouterProvider.otherwise("/" + NG_ROUTE.BOUTEILLES);

      // Route Settings
      $stateProvider
          .state({
            name  : NG_ROUTE.BOUTEILLES,
            url   : "/" + NG_ROUTE.BOUTEILLES,
            views : {
              ""                     : {
                templateUrl: 'public/views/bouteilles/bouteilles.html',
                controller : 'BouteillesController as bouteillesCtrl'
              }
            }
          });
    })
    .config(function (toastrConfig)
    {
      angular.extend(toastrConfig, {
        // Toast Container
        maxOpened    : 5,
        positionClass: 'toast-bottom-center',
        // Toast
        allowHtml    : true,
        closeButton  : true,
        timeOut      : 5000
      });
    })
    .controller('AppController',
        function ($rootScope)
        {
          var appCtrl = this;
          $rootScope.$on('$stateChangeSuccess',
              function (event, toState)
              {
                appCtrl.title = 'Ambrosia :: ' + toState.name.toUpperCase();
              }
          );
        });
