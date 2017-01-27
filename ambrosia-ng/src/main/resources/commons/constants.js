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
      ROOT         : 'root',
      HOME         : 'home',
      BOUTEILLES    : 'bouteilles'
    });