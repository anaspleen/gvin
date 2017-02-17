angular.module('ambrosia.public.bouteilles', ['ambrosia.commons'])
    .controller('BouteillesController',
        function ($rootScope, httpService, toastr, CONSTANTS)
        {
          var bouteillesCtrl = this;

          bouteillesCtrl.title = CONSTANTS.BOUTEILLES.TITLE;

          // Form elements
          bouteillesCtrl.name = '';
          bouteillesCtrl.vignoble = '';
          bouteillesCtrl.aoc = '';
          bouteillesCtrl.appellation = '';
          bouteillesCtrl.chateau = '';
          bouteillesCtrl.anneeMEB = 1970;
          bouteillesCtrl.anneeConsoOpt = 1970;
          bouteillesCtrl.achatDate = '';
          bouteillesCtrl.achatMagasin = '';
          bouteillesCtrl.achatPrix = '';

          // TODO with a visual map
          bouteillesCtrl.longitude = 0.0;
          bouteillesCtrl.latitude = 0.0;

          bouteillesCtrl.save = function ()
          {
            // TODO Dunno if every field are required so no check for now
            var aBottleToAdd =
                {
                  'nom'                      : bouteillesCtrl.name,
                  'vignoble'                 : bouteillesCtrl.vignoble,
                  'aoc'                      : bouteillesCtrl.aoc,
                  'appellation'              : bouteillesCtrl.appellation,
                  'chateau'                  : bouteillesCtrl.chateau,
                  'anneeMiseEnBouteille'     : bouteillesCtrl.anneeMEB,
                  'anneeConsommationOptimale': bouteillesCtrl.anneeConsoOpt,
                  'achatDate'                : bouteillesCtrl.achatDate,
                  'achatMagasin'             : bouteillesCtrl.achatMagasin,
                  'achatPrix'                : bouteillesCtrl.achatPrix,
                  'location'                 : {
                    'longitude': bouteillesCtrl.longitude,
                    'latitude' : bouteillesCtrl.latitude
                  }
                };

            httpService.addBottle(aBottleToAdd).then(
                function onSuccess()
                {
                  // TODO show the added bottle ?
                  toastr.success('Bouteille Ajoutée !', 'Success');
                },
                function onError()
                {
                  toastr.error('Erreur lors de l\'ajout de la bouteille', 'Error');
                }
            );
          }

          bouteillesCtrl.buyDatePicker = {
            opened: false
          };

          bouteillesCtrl.openBuyDate = function ()
          {
            bouteillesCtrl.buyDatePicker.opened = true;
          };
        }
    );
