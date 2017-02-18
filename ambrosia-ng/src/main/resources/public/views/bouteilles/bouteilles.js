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
          bouteillesCtrl.longitude = 0.1;
          bouteillesCtrl.latitude = 0.1;

          bouteillesCtrl.save = function ()
          {
            // TODO Dunno if every field are required so no check for now
            var aBottleToAdd = {};
            aBottleToAdd['nom'] = bouteillesCtrl.name;
            aBottleToAdd['vignoble'] = bouteillesCtrl.vignoble;
            aBottleToAdd['aoc'] = bouteillesCtrl.aoc;
            aBottleToAdd['appellation'] = bouteillesCtrl.appellation;
            aBottleToAdd['chateau'] = bouteillesCtrl.chateau;
            aBottleToAdd['anneeMiseEnBouteille'] = bouteillesCtrl.anneeMEB;
            aBottleToAdd['anneeConsommationOptimale'] = bouteillesCtrl.anneeConsoOpt;
            aBottleToAdd['achatDate'] = bouteillesCtrl.achatDate;
            aBottleToAdd['achatMagasin'] = bouteillesCtrl.achatMagasin;
            aBottleToAdd['achatPrix'] = bouteillesCtrl.achatPrix;

            var location = {};
            location['longitude'] = bouteillesCtrl.longitude;
            location['latitude'] = bouteillesCtrl.latitude;

            aBottleToAdd['location'] = location;

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
