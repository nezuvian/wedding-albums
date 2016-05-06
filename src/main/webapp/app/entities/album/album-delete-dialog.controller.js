(function() {
    'use strict';

    angular
        .module('weddingApp')
        .controller('AlbumDeleteController',AlbumDeleteController);

    AlbumDeleteController.$inject = ['$uibModalInstance', 'entity', 'Album'];

    function AlbumDeleteController($uibModalInstance, entity, Album) {
        var vm = this;
        vm.album = entity;
        vm.clear = function() {
            $uibModalInstance.dismiss('cancel');
        };
        vm.confirmDelete = function (id) {
            Album.delete({id: id},
                function () {
                    $uibModalInstance.close(true);
                });
        };
    }
})();
