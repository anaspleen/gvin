angular.module('ambrosia.commons.httpService', [])
    .factory('httpService', function ($q, $http, TOOLING, REST_URL)
        {
          var templateCreate = {
            'action'   : 'creer',
            'bouteille': {}
          };

          return {
            addBottle: function (pBottleToAdd)
            {
              var deferred = $q.defer();
              var dataSent = TOOLING.copyObject(templateCreate);

              if (pBottleToAdd) {
                dataSent['bouteille'] = pBottleToAdd;

                $http.post(REST_URL.DOM_BOUTEILLES, pBottleToAdd).then(
                    function onSuccess()
                    {
                      deferred.resolve();
                    },
                    function onReject()
                    {
                      deferred.reject();
                    }
                );
              }
              else {
                deferred.reject();
              }
              return deferred.promise;
            }
          }
        }
    );