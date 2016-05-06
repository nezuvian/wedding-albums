(function() {
    'use strict';

    angular
        .module('weddingApp')
        .config(paginationConfig);

    function paginationConfig(uibPaginationConfig, paginationConstants) {
        uibPaginationConfig.itemsPerPage = paginationConstants.itemsPerPage;
        uibPaginationConfig.maxSize = 5;
        uibPaginationConfig.boundaryLinks = true;
        uibPaginationConfig.firstText = '«';
        uibPaginationConfig.previousText = '‹';
        uibPaginationConfig.nextText = '›';
        uibPaginationConfig.lastText = '»';
    }
})();
