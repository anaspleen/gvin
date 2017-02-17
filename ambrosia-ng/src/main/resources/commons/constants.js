angular.module('ambrosia.commons.constants', [])
    .factory('REST_URL', function ()
    {
      var _api_prefix = '/ambrosia-api-rest/api-rest';

      return {
        AMB_REST       : _api_prefix,
        DOM_BOUTEILLES : _api_prefix + "/execute/BouteilleService"
      };
    })
    .constant('NG_ROUTE', {
      ROOT      : 'root',
      BOUTEILLES: 'bouteilles'
    })
    .constant('CONSTANTS', {
      BOUTEILLES: {
        TITLE: 'Bouteilles'
      }
    })
    .factory('TOOLING', function ()
    {
      return {
        copyObject: function (pObjectToCopy)
        {
          var copy = {};
          for (var attr in pObjectToCopy) {
            if (pObjectToCopy.hasOwnProperty(attr)) {
              copy[attr] = pObjectToCopy[attr];
            }
          }
          return copy;
        }
      }
    });