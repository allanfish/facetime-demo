define(function (require) {

    var $ = jQuery = require('jquery');
    var Backbone = require('backbone');
    var _ = require('underscore');
    var Handlebars = require('handlebars');
    window.moment = require('moment');
    window.$ = $;

    window.Backbone = Backbone;
    window._ = _;
    // require('moment-zh-cn');
    window._.str = require("underscore.string");
    _.mixin(_.str);

    window.Handlebars = Handlebars;

    window.swfobject = require('swfobject');

    require('jquery.hoverIntent');
    require("select2");
    require("select2-zh-CN");

    require('backbone.routefilter');
    require('backbone.localStorage');
    require('backbone.layoutmanager');
    require('backbone.deepmodel');

    require('backbone.validation');
    Backbone.ModelBinder = require('backbone.modelbinder');
    require('backbone.collectionbinder');

    // 不能使用require('bootstrap')($);
    require('bootstrap');
    require('tbl-editable');
    require('dialog');
    require("bootstrap-datepicker");
    require("bootstrap-datepicker.zh-CN");

    require("wind");
    require('./myapp/utils/constants');
    require("./myapp/utils/util");
    require("./myapp/utils/security");

    require("./myapp/utils/crypto-sha256");
    require("amq.jquery.adapter");

    require("amq");

    // dtos
    require('./myapp/model/ent/EnterpriseDTO');
    require('./myapp/model/user/UserDTO');
    require('./myapp/collection/user/UserListDTO');
    require('./myapp/model/user/DepartmentDTO');
    require('./myapp/model/user/MessageDTO');
    require('./myapp/collection/user/MessageListDTO');
    require('./myapp/collection/user/DepartmentListDTO');

    require('./myapp/collection/user/UsualContactListDTO');

    require('./myapp/model/file/EntFileDTO');
    require('./myapp/model/file/ShareLinkDTO');
    require('./myapp/collection/file/EntFileListDTO');
    require('./myapp/collection/file/RecycleFileListDTO');
    require('./myapp/model/file/NewFileDTO');
    require('./myapp/collection/file/FileBreadListDTO');
    require('./myapp/model/file/UploadFileDTO');
    require('./myapp/model/user/SettingDTO');
    require('./myapp/collection/file/UploadFileListDTO');

    var app = {
        model: {},
        modelbinder: {},
        tpl: {},
        view: {},
        tplpre: {},
        collection: {},
        htmlbody: {},
        temp: {},
        constants: {},
        resturl: {},
        cache: {},
        events: {},
        router: {},
        setting: {}
    };

    var initAmq = function () {
        var clientId = util.guid();
        org.activemq.Amq.init({
            uri: 'amq',
            logging: true,
            timeout: 45,
            clientId: clientId,
            headers: {
                "UT": cache.token
            }
        });
        log.info("init amq -> ok");
    };


    var messageHandler = function (message) {
        var text = message.childNodes[0].data;
        var messageDTO = new MessageDTO(JSON.parse(text));
        log.info("messageDTO: ", messageDTO.toJSON());
        var messageTypeMap = ["InfEditSta"];
        var msgType=messageDTO.get('messageType');

        if ($.inArray(msgType, messageTypeMap) === -1) {
            collection.messageList.add(messageDTO);
            var fileMsgType = constants.messageType.getFileMsgType();
            var chatMsgType = constants.messageType.getChatMsgType();
            var systemMsgType = constants.messageType.getSystemMsgType();

            if(_.contains(fileMsgType,msgType)){
                collection.fileMessageList.add(messageDTO);
            }else if(_.contains(chatMsgType,msgType)){
                collection.instantMessageList.add(messageDTO);
                //模拟
                var content=messageDTO.toJSON();
                content.messageBody={'name':'aaa.doc'};
                content.messageType='FileUploadDone';
                collection.fileMessageList.add(new MessageDTO(content));
                content.messageBody='2013/5/30集体开会';
                content.messageType='SystemMsg';
                collection.systemMessageList.add(new MessageDTO(content));

            }else if(_.contains(systemMsgType,msgType)){
                collection.systemMessageList.add(messageDTO);
            }
        }
    };

    var addAmqListener = function () {
        var userTopic = constants.amqTopic + cache.userId;
        var entTopic = constants.amqTopic + cache.entId;

        log.info("listener to topics: ", userTopic, entTopic);
        org.activemq.Amq.addListener(userTopic, userTopic, messageHandler);
        org.activemq.Amq.addListener(entTopic, entTopic, messageHandler);
    };

    var getUserProfileTask = eval(Wind.compile('async', function () {
        var userInfo = $await(resturl.getUserProfile(cache.userId));
        model.currentUser.set(userInfo);
        cache.username = model.currentUser.get("userName");
        cache.entId = model.currentUser.get("enterpriseId");
        log.info("[ajax] user load -> OK");
        log.info('entId: ', cache.entId);

        addAmqListener();
    }));

    // 加载当前用户和同事部门数据
    var getDepartmentAndUserWithStatusByUserIdTask = eval(Wind.compile('async', function () {
        var departmentAndUser = $await(resturl.getDepartmentAndUserWithStatusByUserId(cache.userId));
        collection.userList.add(_.map(departmentAndUser.userList, function (user) {
            return new UserDTO(user);
        }));

        collection.departmentList.add(_.map(departmentAndUser.departmentList, function (department) {
            return new DepartmentDTO(department);
        }));

        // 发出初始化公司同事事件
        collection.departmentList.trigger('department: init', collection.departmentList);
        log.info("[ajax] department load -> OK");
    }));


    // 加载根企业文件夹数据
    var getEntFolderAndFileByFolderIdTask = eval(Wind.compile("async", function () {
        var rootFolders = $await(resturl.getEntFolderAndFileByFolderId({
            userId: cache.userId,
            folderIds: []
        }));
        log.info("rootFolders load -> ok, length: ", rootFolders['forderList'].length);

        var entFiles = _.map(rootFolders['forderList'].concat(rootFolders['fileList']), function (each) {
            var entFileDTO = each.fileId ? new EntFileDTO().initFromFile(each) : new EntFileDTO().initFromFolder(each);
            entFileDTO.set("diskType", constants.fileType.shareDisk);
            return entFileDTO;
        });

        collection.entFileList.add(entFiles).trigger(events.loadFile, entFiles);
        model.currentFolder.trigger('change');
    }));

    var getPersonalFolderAndFileByFolderId = eval(Wind.compile("async", function () {
        var personRootFiles = $await(resturl.getPersonalFolderAndFileByFolderId({
            userId: cache.userId,
            folderIds: []
        }));
        log.info('load person root files ok!');

        var personFiles = _.map(personRootFiles['forderList'].concat(personRootFiles['fileList']), function (each) {
            var personFileDTO = each.fileId ? new EntFileDTO().initFromFile(each) : new EntFileDTO().initFromPersonFolder(each);
            personFileDTO.set("diskType", constants.fileType.onlineDisk);
            return personFileDTO;
        });

        collection.personFileList.add(personFiles).trigger(events.loadFile, personFiles);
    }));


    // 获取常用联系人数据
    var getUsualContactGroups = eval(Wind.compile('async', function () {
        var usualContact = $await(resturl.getUsualContactGroupsByUserId(cache.userId));
        log.info("usualContact:", usualContact);
        collection.usualContactList.add(_.map(usualContact.userList, function (user) {
            var userDTO = new UserDTO(user);
            if (userDTO.get("departmentId")) {
                var departmentName = collection.departmentList.where({'departmentId': userDTO.get("departmentId")})[0].get("name");
                userDTO.set("departmentName", departmentName);
            }
            return userDTO;
        }));
        log.info("[ajax] usualContact load -> OK");
    }));


    var initData = function () {
        model.currentUser = new UserDTO();
        model.currentEnterprise = new EnterpriseDTO();
        collection.messageList = new MessageListDTO();
        collection.historyMessageList = new MessageListDTO();
        collection.fileMessageList = new MessageListDTO();
        collection.instantMessageList = new MessageListDTO();
        collection.systemMessageList = new MessageListDTO();
        collection.entFileList = new EntFileListDTO();
        collection.personFileList = new EntFileListDTO();

        collection.departmentList = new DepartmentListDTO();
        collection.userList = new UserListDTO();
        collection.usualContactList = new UsualContactListDTO();
        collection.chatUserList = new UserListDTO();

        collection.uploadFileList = new UploadFileListDTO();

        model.rootEntFolder = new EntFileDTO({
            parentId: null,
            fileId: 'entRoot',
            name: '企业文件',
            diskType: constants.fileType.shareDisk,
            loaded: true
        });
        model.rootPersonFolder = new EntFileDTO({
            parentId: null,
            fileId: 'personRoot',
            name: '个人文件',
            diskType: constants.fileType.onlineDisk,
            loaded: true
        });

        model.currentFolder = new EntFileDTO(model.rootEntFolder.toJSON());

        var initTask = eval(Wind.compile('async', function () {
            $await(getUserProfileTask());

            var getEnterpriseTask = resturl.getEnterprise(cache.entId, function (result) {
                model.currentEnterprise.set(result);
                log.info("enterprise load -> OK");
            });
            getEnterpriseTask.start();
            getEntFolderAndFileByFolderIdTask().start();
            getPersonalFolderAndFileByFolderId().start();

            $await(getDepartmentAndUserWithStatusByUserIdTask());
            getUsualContactGroups().start();
        }));
        initTask().start();
    };


    var initlogger = function () {
        require('log4javascript');
        window.log = log4javascript.getLogger();
        var consoleAppender = new log4javascript.BrowserConsoleAppender();
        consoleAppender.setThreshold(log4javascript.Level.DEBUG);
        log.addAppender(consoleAppender);

//        var popupAppender = new log4javascript.PopUpAppender();
//        popupAppender.setThreshold(log4javascript.Level.DEBUG);
//        log.addAppender(popupAppender);
    };

    var initSetting = function () {
        model.setting = new SettingDTO({
            contentLeftWidth: 206,
            slideRightWidth: 270,
            width: $(window).width(),
            height: $(window).height(),
            innerWidth: $(window).innerWidth(),
            innerHeight: $(window).innerHeight()
        });

    }

    $(function () {
        _.extend(window, app);
        $.fn.editable.defaults.mode = 'inline';
        initlogger();
        resturl = require('./myapp/utils/resturl');
        constants = require('./myapp/utils/constants');
        constants.errorType = require("./myapp/utils/ErrorType");
        constants.messageType = require("./myapp/utils/MessageType");
        events = require('./myapp/utils/app.events');

        log.debug(" msgType: OfflineFile: ", constants.messageType.OfflineFile);

        log.debug("msgType: ChatMsgType: ", constants.messageType.getChatMsgType());

        var clientToken = require('./myapp/model/user/ClientToken');
        cache.token = clientToken.token;
        cache.userId = clientToken.userId;
        log.info('userId: ', cache.userId);

        initSetting();
        initAmq();
        initData();

        $(window).resize(function (event) {
            model.setting.set({
                width: $(window).width(),
                height: $(window).height(),
                innerWidth: $(window).innerWidth(),
                innerHeight: $(window).innerHeight()
            });

            log.debug("model.setting: ", model.setting.toJSON());
        });

        var BBRouter = require('./myapp/webrouter');
        BBRouter.initialize();
        $.fn.datepicker.defaults = {
            language: 'zh-CN',
            autoclose: true,
            weekStart: 1
        };
    });

})
;
