(function() {
    'use strict';

    angular
        .module('weddingApp')
        .controller('AlbumController', AlbumController);

    AlbumController.$inject = ['$scope', '$state', 'Album'];

    function AlbumController ($scope, $state, Album) {
        var vm = this;
        vm.albums = [];
        vm.loadAll = function() {
            Album.query(function(result) {
                vm.albums = result;
            });
        };

        vm.loadAll();
        
    }
})();
