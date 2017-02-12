angular.module('ambrosia.commons.httpservices', [])
    .factory('httpServices', function ($q, $http, TOOLING, REST_URL)
    {
      var templateCreate = {
        'action': 'creer',
        'bouteille': {}
      };

      return {
        addBottle : function (pBottleToAdd)
          {
            var deferred = $q.defer();
            var dataSent = TOOLING.copyObject(templateCreate);

            if (pBottleToAdd && pBottleToAdd.length > 0){
              dataSent['bouteille'] = pBottleToAdd;

              $http.post(REST_URL.AMB_REST, pBottleToAdd).then(
                function onSuccess ()
                {
                  deferred.resolve();
                },
                function onReject ()
                {
                  deferred.reject();
                }
              );
            }
            else
            {
              deferred.resolve();
            }
            return deferred.promise;
          }
        }
    };