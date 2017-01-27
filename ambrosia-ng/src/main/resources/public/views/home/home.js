angular.module('ambrosia.public.home', [])
    .controller('HomeController',
        function ($rootScope,stateService)
        {
          var homeCtrl = this;

          // Init nav links
          homeCtrl.navLinks = [];

          homeCtrl.homeLinkName = NG_ROUTE.BOUTEILLES;

          homeCtrl.navLinks.push({
            name     : NG_ROUTE.BOUTEILLES,
            title    : 'Gestion De Bouteilles de Vin',
            iconClass: 'fa fa-arrow-down'
          });

          var activeLink = function (currentState)
          {
            if (currentState) {
              homeCtrl.navLinks.forEach(function (link)
              {
                link.active = currentState.name === link.name;
              });
            }
          };
          activeLink(stateService.getCurrent());
          stateService.onSuccess(function (state, toState)
          {
            activeLink(toState);
          });
       });
