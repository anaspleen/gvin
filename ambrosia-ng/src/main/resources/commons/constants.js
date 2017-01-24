angular.module('ambrosia.commons.constants', [])
    .factory('REST_URL', function ()
    {
      var _root                = '/',
          _api_prefix          = _root + '/api',;

      return {
        ROOT                      : _root,
      };
    })
    .constant('NG_ROUTE', {
      ROOT         : 'root',
      HOME         : 'home',
      BOUTEILLES    : 'bouteilles'
    })
    .constant('ERRORS', {
      CODES   : {
        COMMONS   : {

        },
        BOUTEILLES : {

        }
      },
      MESSAGES: {
        COMMONS   : {
          INVALID_PARAMS   : 'Invalid Params',
        },
        BOUTEILLES : {

        }
      }
    });