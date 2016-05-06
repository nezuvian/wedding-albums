(function() {
    'use strict';

    angular
        .module('weddingApp')
        .config(stateConfig);

    stateConfig.$inject = ['$stateProvider'];

    function stateConfig($stateProvider) {
        $stateProvider
        .state('upload', {
            parent: 'app',
            url: '/upload',
            data: {
                authorities: [],
                pageTitle: 'Upload'
            },
            views: {
                'content@': {
                    templateUrl: 'app/upload/upload.html',
                    controller: 'UploadController',
                    // controllerAs: 'vm'
                }
            },
            resolve: {
            }
       });
    }

})();
