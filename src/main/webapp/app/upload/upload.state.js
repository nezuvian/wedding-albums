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
            url: '/',
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
                images: ['$stateParams', 'Album', function($stateParams, Album) {
                    return Album.query().$promise.then(function(result) {
                        for(var i = 0; i < result.length; i++)
                        {
                            // restructuring data for ngGallery
                            for(var j=0; j< result[i].pictures.length; j++) {
                                result[i].pictures[j].thumb = result[i].pictures[j].path;
                                result[i].pictures[j].img = result[i].pictures[j].path;
                            }
                        }

                        return result;
                    });
                }]
            }
       });
    }

})();
