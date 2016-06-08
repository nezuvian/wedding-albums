/**
 * Created by ezolcho on 5/6/2016.
 */

(function () {
    'use strict';

    angular
        .module('weddingApp')
        .controller('UploadController', UploadController);

    UploadController.$inject = ['$scope', 'Upload', '$cookies', '$stateParams', 'Album', 'images'];

    function UploadController($scope, Upload, $cookies, $stateParams, Album, images) {
        $scope.uploaded = false;
        $scope.progress = 0;
        $scope.rejFiles = [];
        $scope.contentType = $stateParams.contentType;
        $scope.images = images;

        $scope.uploadAll = function () {
            if ($scope.uploader == "" || $scope.uploader == null) {
                alert("Add meg a feltőltő nevét");
            } else {
                if ($scope.files && $scope.files.length) {
                    for (var i = 0; i < $scope.files.length; i++) {
                        (function (file) {
                            $scope.upload(file);
                        })($scope.files[i]);
                    }
                }
            }
            $scope.files = [];
        }
        $scope.upload = function (file) {
            file.upload = Upload.upload({
                url: 'api/upload',
                method: 'POST',
                headers: {
                    'X-CSRF-TOKEN': $cookies['CSRF-TOKEN']
                },
                fields: {
                    '_csrf': $cookies['CSRF-TOKEN']
                },
                data: {
                    file: file,
                    'uploader': $scope.uploader
                }
            });
        }

        $scope.removeFile = function (i) {
            if (i > -1) {
                $scope.files.splice(i, 1);
            }
        }
        
    }

}());
