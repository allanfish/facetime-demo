define(function (require, exports, module) {

    require("wind");

    return {
        serviceUrl: "/os/flexService",
        sendMessageUrl: '/os/sendmessage',
        uploadUrl: '/os/fileRoutingUpload',

        ajaxTask: function (option, callback) {
            return new Wind.Async.Task(function (task) {
                jQuery.extend(option, {
                    success: function (data) {
                        task.complete("success", data);
                        if (callback && _.isFunction(callback))
                            callback(data);
                    },
                    error: function (data) {
                        task.complete("failure", data);
                        if (callback && _.isFunction(callback))
                            callback(data);
                    }
                });
                $.ajax(option);
            });
        },

        param: function (serviceType, data) {
            return {
                url: this.serviceUrl,
                type: 'POST',
                headers: {
                    'serviceType': serviceType,
                    'UT': cache.token,
                    'userId': cache.userId || 0,
                    'entId': cache.entId || 0,
                    'Content-Type': 'text/plain; charset=UTF-8',
                    'Accept': 'text/plain;charset=UTF-8'
                },
                data: JSON.stringify(data),
                dataType: 'json'
            };
        },

        paramString: function (serviceType, data) {
            return _.extend(this.param(serviceType, data), {'dataType': 'text', processData: false});
        },

        sendMessageParam: function (data) {
            return _.extend(this.param("", data), {'dataType': 'text', "url": this.sendMessageUrl});
        },

        /**
         * 发送JMS消息
         * @param  {object} data
         * @return {string}      return OK if success.
         */
        sendMessage: function (data, callback) {
            var sendUrl = this.sendMessageUrl;
            return this.ajaxTask(this.sendMessageParam(data), callback);
        },

        /** 获取用户信息 */
        getUserProfile: function (data) {
            return this.ajaxTask(this.param("/sc/login/getUserProfile", data));
        },

        /**
         *更新用户信息，
         * @param  {[type]} data [UserInfoDTO]
         * @return {[type]}      [ok，error]
         */
        updateUserProfile: function (data) {
            return this.ajaxTask(this.paramString("/sc/login/updateUserProfile", data));
        },

        /** 获取企业信息 */
        getEnterprise: function (data, callback) {
            return this.ajaxTask(this.param("/sc/ent/getEnterprise", data), callback);
        },

        /**
         * 按文件夹取企业网盘子文件夹和文件，取顶层文件夹时，文件夹id传null
         *
         * @param {FolderAndFileParamDTO}
         *            folderAndFileParamDTO 包含了userId和folderIds的DTO
         * @return {ShareFolderAndFileDTO} 企业网盘文件夹list和文件list
         */
        getEntFolderAndFileByFolderId: function (folderAndFileParamDTO) {
            return this.ajaxTask(this.param("/sc/shareDisk/getEntFolderAndFileByFolderId", folderAndFileParamDTO));
        },
        /**
         * 按文件夹取个人网盘子文件夹和文件，取顶层文件夹时，文件夹ID传null
         *
         * @param {FolderAndFileParamDTO}
         *            folderAndFileParamDTO 如果要去顶层文件夹, folderId为null
         * @return {[type]} [description]
         */
        getPersonalFolderAndFileByFolderId: function (folderAndFileParamDTO) {
            return this.ajaxTask(this.param('/sc/disk/getPersonalFolderAndFileByFolderId', folderAndFileParamDTO));
        },

        /**
         * 按用户id取企业部门和成员,包含在线状态，不包含角色信息
         *
         * @param {string}
         *            userId
         * @return {DepartmentAndUserDTO} 企业部门和用户DTO
         */
        getDepartmentAndUserWithStatusByUserId: function (userId) {
            return this.ajaxTask(this.param('/sc/ent/getDepartmentAndUserWithStatusByUserId', userId));
        },

        /**
         * 获取常用联系人信息
         *
         * @param {string}
         *            userId
         * @return {UsualContactGroupAndUserDTO} 企业部门和用户DTO
         */
        getUsualContactGroupsByUserId: function (userId) {
            return this.ajaxTask(this.param('/sc/usualContact/getUsualContactGroupAndUserByUserId', userId));
        },

        /**
         * 删除常用联系人信息
         *
         * @param {UsualContactDTO}
         *            usualContactDTO
         * @return {String} OK error500
         */
        deleteUsualContact: function (usualContactDTO) {
            return this.ajaxTask(this.paramString('/sc/usualContact/deleteUsualContact', usualContactDTO));
        },

        /**
         * 设置常用联系人信息
         *
         * @param {UsualContactDTO}
         *            usualContactDTO
         * @return {String} OK error500
         */
        setUsualContact: function (usualContactDTO) {
            return this.ajaxTask(this.paramString('/sc/usualContact/setUsualContact', usualContactDTO));
        },

        /**
         * 取会议列表包括参会者
         *
         * @param {string}
         *            userId
         */
        getConferenceListByUserId: function (userId) {
            return this.ajaxTask(this.param('/sc/conference/getConferenceByUserId', userId));
        },

        /**
         * 取会议列表包括参会者
         *
         * @param {ConferenceDTO}
         *            conDTO
         */
        createConference: function (conDTO, callback) {
            return this.ajaxTask(this.paramString('/sc/conference/createConference', conDTO));
        },

        /**
         *  取企业网盘回收站中的文件和文件夹
         * @param   long entId 企业ID
         * @return     企业网盘文件夹list,和文件list
         */
        getEntFolderAndFileInRecycle: function (entId, callback) {
            return this.ajaxTask(this.paramString('/sc/shareDisk/getEntFolderAndFileInRecycle', entId), callback);
        },

        /**
         * 取个人网盘中的回收站的文件和文件列表
         * @param userId    用户id;
         * @param callback  回调函数
         * @returns {*}回调函数中的值
         */
        getPersonalFolderAndFileInRecycle: function (userId, callback) {
            return this.ajaxTask(this.paramString('/sc/disk/getPersonalFolderAndFileInRecycle', userId), callback);
        },

        getPersonalFolderAndFileInRecycle: function (userId, callback) {
            return this.ajaxTask(this.paramString('/sc/disk/getPersonalFolderAndFileInRecycle', userId), callback);
        },

        /**
         * 新建企业网盘目录
         */
        newShareFolder: function (entFile, callback) {
            return this.ajaxTask(this.paramString('/sc/shareDisk/newShareFolder', entFile), callback);
        },

        /**
         * 添加个人文件夹
         */
        addDiskFolder: function (param, callback) {
            return this.ajaxTask(this.paramString('/sc/disk/addFolder/', param), callback);
        },

        /**
         * 新建文档
         *
         * @param {json}
         *            newFileDTO 新建文档DTO
         * @return {FileDTO} [description]
         */
        saveFile: function (newFileDTO, callback) {
            return this.ajaxTask(this.paramString('/sc/file/saveHtml', newFileDTO), callback);
        },

        /**
         * 用户密码修改
         *
         * @param {json}
         *            passwordChangeDTO
         */
        changeAdminPassword: function (passwordChangeDTO) {
            return this.ajaxTask(this.paramString('/sc/ent/changeAdminPassword', passwordChangeDTO));

        },
        /**
         * 用户密码修改
         *
         * @param {json}
         *            passwordChangeDTO
         */
        changePassword: function (passwordChangeDTO) {
            return this.ajaxTask(this.paramString('/sc/login/changePassword', passwordChangeDTO));

        },
        /**
         * [ 验证用户密码 ]
         *
         * @param {[type]}
         *            passwordChangeDTO ｛json ｝
         * @return {[type]} String
         */
        checkPassword: function (passwordChangeDTO) {
            return this.ajaxTask(this.paramString('/sc/ent/checkUserPassword', passwordChangeDTO));
        },

        /**
         * 删除个人文件到回收站中
         * @param fileId
         * @param [optional] callback
         * @return {string} OK or error500
         */
        deletePersonFileToRecycle: function (fileId, callback) {
            return this.ajaxTask(this.paramString('/sc/disk/deleteFileToRecycle', fileId), callback);
        },

        /**
         * 删除个人文件夹到回收站中
         *
         * @param folderId
         * @param [optional] callback
         * @return {string} OK or error500
         */
        deletePersonFolderToRecycle: function (folderId, callback) {
            return this.ajaxTask(this.paramString('/sc/disk/deleteFolderToRecycle', folderId), callback);
        },

        /**
         * 修改企业信息
         */
        updateEnterprise: function (data) {
            return this.ajaxTask(this.paramString('/sc/ent/updateEnterprise', data));
        },

        /**
         * 删除企业网盘文件至回收站
         * @param data
         * @param [optional] callback
         * @returns {string} OK or error500
         */
        deleteShareFile: function (data, callback) {
            return this.ajaxTask(this.paramString('/sc/shareDisk/deleteShareFile', data), callback);
        },

        /**
         *  删除企业文件夹
         * @param data
         * @param [optional] callback
         * @returns {string} OK or error500
         */
        deleteShareFolder: function (data, callback) {
            return this.ajaxTask(this.paramString('/sc/shareDisk/deleteShareFolder', data), callback);
        },

        /**
         *  获得个人收藏夹文件列表
         * @param userId
         * @param callback  回调函数, 可以不传
         * @returns {FavoriteFilesDTO}  企业网盘文件列表DTO. 个人网盘不能收藏文件
         */
        getFavoriteFiles: function (userId, callback) {
            return this.ajaxTask(this.paramString('/sc/favoriteFile/getFavoriteFile', userId), callback);
        },

        /**
         * 添加个人收藏夹文件列表
         * @param favoriteFilesDTO
         * @param callback
         * @returns {string} OK or error500
         */
        addFavoriteFile: function (favoriteFilesDTO, callback) {
            return this.ajaxTask(this.paramString('/sc/favoriteFile/addFavoriteFile', favoriteFilesDTO), callback);
        },

        /**
         * 取消个人收藏夹文件列表
         * @param favoriteFilesDTO
         * @param callback
         * @returns OK or error500
         */
        delFavoriteFile: function (favoriteFilesDTO, callback) {
            return this.ajaxTask(this.paramString('/sc/favoriteFile/delFavoriteFile', favoriteFilesDTO), callback);
        },

        /**
         *  获取企业文件
         * @param fileId
         * @returns {string} ShareLinkDTO
         */
        getShareFileById: function (fileId, callback) {
            return this.ajaxTask(this.paramString('/sc/shareDisk/getShareFileById', fileId), callback);
        },

        /**
         * 获取个人文件
         * @param fileId
         * @param callback
         * @returns {*}
         */
        getPersonFileById: function (fileId, callback) {
            return this.ajaxTask(this.paramString('/sc/disk/getFileById', fileId), callback);
        },

        /*
         * 创建文件共享外链
         * @return {object} ShareLinkDTO
         * */
        createShareFileLink: function (createFileLinkParam, callback) {
            return this.ajaxTask(this.paramString('/sc/shareLink/createShareFileLink', createFileLinkParam), callback);
        },

        /**
         * 企业网盘文件重命名
         * @param {RenameShareFileParam}
         * @returns {string}
         */
        renameShareFile: function (param, callback) {
            return this.ajaxTask(this.paramString('/sc/shareDisk/renameShareFile', param), callback);
        },

        /**
         *  企业网盘文件夹重命名
         * @param {RenameShareFolderParam}
         * @returns {string}
         */
        renameShareFolder: function (param, callback) {
            return this.ajaxTask(this.paramString('/sc/shareDisk/renameShareFolder', param), callback);
        },

        /**
         * 个人网盘文件重命名
         * @param {RenamePersonalFileParam}
         * @returns {string}
         */
        renamePersonalFile: function (param, callback) {
            return this.ajaxTask(this.paramString('/sc/disk/renamePersonalFile', param), callback);
        },

        /**
         *  个人网盘文件夹重命名
         * @param {RenamePersonalFolderParam}
         * @returns {string}
         */
        renamePersonalFolder: function (param, callback) {
            return this.ajaxTask(this.paramString('/sc/shareDisk/renamePersonalFolder', param), callback);
        },

        /**
         * 企业网盘文件移动
         * @param moveShareFileParam
         * @returns {string} OK
         */
        moveShareFile: function (moveShareFileParam, callback) {
            return this.ajaxTask(this.paramString('/sc/shareDisk/moveShareFile', moveShareFileParam), callback);
        },

        /**
         * 企业网盘文件夹移动
         * @param moveShareFolderParam
         * @returns {string} OK
         */
        moveShareFolder: function (moveShareFolderParam, callback) {
            return this.ajaxTask(this.paramString('/sc/shareDisk/moveShareFolder', moveShareFolderParam), callback);
        },

        /**
         * 企业网盘文件和文件夹移动
         * @param MoveShareFolderAndFileParam
         * @returns {string} OK
         */
        moveShareFolderAndFile: function (MoveShareFolderAndFileParam, callback) {
            return this.ajaxTask(this.paramString('/sc/shareDisk/moveShareFolderAndFile', MoveShareFolderAndFileParam), callback);
        },


        /**
         * 个人网盘文件移动
         * @param movePersonalFileParam
         * @returns {string} OK
         */
        movePersonalFile: function (movePersonalFileParam, callback) {
            return this.ajaxTask(this.paramString('/sc/disk/movePersonalFile', movePersonalFileParam), callback);
        },

        /**
         * 个人网盘文件夹移动
         * @param movePersonalFolderParam
         * @returns {string} OK
         */
        movePersonalFolder: function (movePersonalFolderParam, callback) {
            return this.ajaxTask(this.paramString('/sc/disk/movePersonalFolder', movePersonalFolderParam), callback);
        },

        /**
         * 个人网盘文件和文件夹移动
         * @param MovePersonalFolderAndFileParam
         * @returns {string} OK
         */
        movePersonalFolderAndFile: function (MovePersonalFolderAndFileParam, callback) {
            return this.ajaxTask(this.paramString('/sc/shareDisk/movePersonalFolderAndFile', MovePersonalFolderAndFileParam), callback);
        }
    };
})