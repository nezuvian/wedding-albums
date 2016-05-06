(function() {
    'use strict';
    angular
        .module('weddingApp')
        .factory('Album', Album);

    Album.$inject = ['$resource', 'DateUtils'];

    function Album ($resource, DateUtils) {
        var resourceUrl =  'api/albums/:id';

        return $resource(resourceUrl, {}, {
            'query': { method: 'GET', isArray: true},
            'get': {
                method: 'GET',
                transformResponse: function (data) {
                    data = angular.fromJson(data);
                    data.createdAt = DateUtils.convertLocalDateFromServer(data.createdAt);
                    return data;
                }
            },
            'update': {
                method: 'PUT',
                transformRequest: function (data) {
                    data.createdAt = DateUtils.convertLocalDateToServer(data.createdAt);
                    return angular.toJson(data);
                }
            },
            'save': {
                method: 'POST',
                transformRequest: function (data) {
                    data.createdAt = DateUtils.convertLocalDateToServer(data.createdAt);
                    return angular.toJson(data);
                }
            }
        });
    }
})();
