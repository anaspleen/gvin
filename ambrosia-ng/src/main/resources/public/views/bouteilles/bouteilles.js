angular.module('ambrosia.public.bouteilles', ['ambrosia.commons'])
    .controller('BouteillesController',
        function ($rootScope, CONSTANTS)
        {
          var bouteillesCtrl = this;

          bouteillesCtrl.title = CONSTANTS.BOUTEILLES.TITLE;
        }
    );
