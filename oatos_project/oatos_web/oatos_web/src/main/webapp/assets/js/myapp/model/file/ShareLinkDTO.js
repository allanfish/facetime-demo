define(function (require, exports, module) {

    /**
     * 新建文件DTO
     */
    window.ShareLinkDTO = Backbone.DeepModel.extend({
        urlRoot: "",
        defaults: {
            shareLinkId: undefined,
            checkExpireDate: false,
            checkDownloadAmt: false,
            checkPassword: false,

            linkCode: undefined,
            password: undefined,
            downloadAmt: undefined,
            expireDate: undefined
        }
    })
})