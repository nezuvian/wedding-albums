(function() {
    'use strict';

    angular
        .module('weddingApp')
        .controller('AlbumDetailController', AlbumDetailController);

    AlbumDetailController.$inject = ['$scope', '$rootScope', '$stateParams', 'entity', 'Album'];

    function AlbumDetailController($scope, $rootScope, $stateParams, entity, Album) {
        var vm = this;
        vm.album = entity;
        vm.load = function (id) {
            Album.get({id: id}, function(result) {
                vm.album = result;
            });
        };
        var unsubscribe = $rootScope.$on('weddingApp:albumUpdate', function(event, result) {
            vm.album = result;
        });
        $scope.$on('$destroy', unsubscribe);

    }
})();
