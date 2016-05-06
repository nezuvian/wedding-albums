(function() {
    'use strict';

    angular
        .module('weddingApp')
        .controller('AlbumDialogController', AlbumDialogController);

    AlbumDialogController.$inject = ['$scope', '$stateParams', '$uibModalInstance', 'entity', 'Album'];

    function AlbumDialogController ($scope, $stateParams, $uibModalInstance, entity, Album) {
        var vm = this;
        vm.album = entity;
        vm.load = function(id) {
            Album.get({id : id}, function(result) {
                vm.album = result;
            });
        };

        var onSaveSuccess = function (result) {
            $scope.$emit('weddingApp:albumUpdate', result);
            $uibModalInstance.close(result);
            vm.isSaving = false;
        };

        var onSaveError = function () {
            vm.isSaving = false;
        };

        vm.save = function () {
            vm.isSaving = true;
            if (vm.album.id !== null) {
                Album.update(vm.album, onSaveSuccess, onSaveError);
            } else {
                Album.save(vm.album, onSaveSuccess, onSaveError);
            }
        };

        vm.clear = function() {
            $uibModalInstance.dismiss('cancel');
        };

        vm.datePickerOpenStatus = {};
        vm.datePickerOpenStatus.createdAt = false;

        vm.openCalendar = function(date) {
            vm.datePickerOpenStatus[date] = true;
        };
    }
})();
