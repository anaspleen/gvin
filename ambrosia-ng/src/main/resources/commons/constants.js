angular.module('ambrosia.commons.constants', [])
    .factory('REST_URL', function ()
    {
      var _root                = '/',
          _api_prefix          = _root + '/api-rest';

      return {
        ROOT     : _root,
        AMB_REST : _api_prefix
      };
    })
    .constant('NG_ROUTE', {
      ROOT          : 'root',
      BOUTEILLES    : 'bouteilles'
    })
    .constant('CONSTANTS', {
      BOUTEILLES :
        {
          TITLE : 'Bouteilles'
        }
    })
    .factory('TOOLING', function ()
    {
      return {
        copyObject : function (pObjectToCopy) {
          var copy = {};
          for (var attr in pObjectToCopy) {
              if (obj.hasOwnProperty(attr)) copy[attr] = clone(pObjectToCopy[attr]);
          }
          return copy;
        }
      }
    });