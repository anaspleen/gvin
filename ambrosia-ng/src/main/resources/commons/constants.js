angular.module('ambrosia.commons.constants', [])
    .factory('REST_URL', function ()
    {
      var _api_prefix = '/api-rest';

      return {
        AMB_REST: _api_prefix
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